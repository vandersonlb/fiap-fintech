package br.com.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import br.com.fintech.bean.Categoria;
import br.com.fintech.bean.Conta;
import br.com.fintech.bean.Investimento;
import br.com.fintech.bean.Tipo;
import br.com.fintech.bean.Transacao;
import br.com.fintech.dao.CategoriaDAO;
import br.com.fintech.dao.ContaDAO;
import br.com.fintech.dao.InvestimentoDAO;
import br.com.fintech.dao.TipoDAO;
import br.com.fintech.dao.TransacaoDAO;
import br.com.fintech.factory.DAOFactory;
import br.com.fintech.jdbc.ConnectionManager;

public class OracleTransacaoDAO implements TransacaoDAO {

  private Connection conn;

  @Override
  public void createTransacao(Transacao transacao) {
    PreparedStatement stmt = null;
    conn = ConnectionManager.getInstance().getConnectionDB();
    ContaDAO contaDAO = DAOFactory.getContaDAO();
    InvestimentoDAO investDAO = DAOFactory.getInvestimentoDAO();

    try {
      conn.setAutoCommit(false);

      addTransacao(transacao, conn, stmt);
      int codTipo = transacao.getTipo().getCodTipo();

      // Atualiza o saldo da conta
      Conta conta = contaDAO.getConta(transacao.getConta().getNumConta());
      double valor = (codTipo == 2 || codTipo == 3) ? transacao.getValor() * -1 : transacao.getValor();
      conta.setSaldo(conta.getSaldo() + valor);
      OracleContaDAO.updateSaldo(conta, conn, stmt);

      // Atualiza o saldo do investimento
      if (transacao.getInvestimento() != null && codTipo > 2) {
        Investimento invest = investDAO.getInvestimento(transacao.getInvestimento().getCodInvestimento());
        double valorInvest = (codTipo == 4) ? transacao.getValor() * -1 : transacao.getValor();
        invest.setSaldo(invest.getSaldo() + valorInvest);
        OracleInvestimentoDAO.updateSaldo(invest, conn, stmt);
      }

      System.out.println("Transação cadastrada com sucesso!");
      conn.commit();

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("ROLLBACK: Erro no cadastro, confira os dados informados.");
      DbUtils.rollbackAndCloseQuietly(conn);
    } finally {
      DbUtils.closeQuietly(stmt);
      DbUtils.closeQuietly(conn);
    }
  }

  private void addTransacao(Transacao transacao, Connection conn, PreparedStatement stmt) throws SQLException {

    String sql = "INSERT INTO T_FT_TRANSACAO VALUES(?, SQ_FT_TRANSACAO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
    stmt = conn.prepareStatement(sql);

    stmt.setInt(1, transacao.getConta().getNumConta());
    stmt.setString(2, transacao.getNome());
    if (transacao.getInvestimento() != null) {
      stmt.setInt(3, transacao.getInvestimento().getCodInvestimento());
    } else {
      stmt.setNull(3, Types.INTEGER);
    }
    stmt.setInt(4, transacao.getTipo().getCodTipo());
    stmt.setDouble(5, transacao.getValor());
    java.sql.Date data = new java.sql.Date(transacao.getData().getTimeInMillis());
    stmt.setDate(6, data);
    stmt.setInt(7, transacao.getCategoria().getCodCategoria());
    stmt.setString(8, transacao.getObsevacao());

    stmt.executeUpdate();

  }

  @Override
  public void updateTransacao(Transacao transacao) {
    PreparedStatement stmt = null;

    try {
      conn = ConnectionManager.getInstance().getConnectionDB();
      String sql = "UPDATE T_FT_TRANSACAO SET NM_TRANSACAO = ?, DT_TRANSACAO = ?, CD_CATEGORIA = ?, OB_TRANSACAO = ? WHERE SQ_TRANSACAO = ?";
      stmt = conn.prepareStatement(sql);

      stmt.setString(1, transacao.getNome());
      java.sql.Date data = new java.sql.Date(transacao.getData().getTimeInMillis());
      stmt.setDate(2, data);
      stmt.setInt(3, transacao.getCategoria().getCodCategoria());
      stmt.setString(4, transacao.getObsevacao());
      stmt.setInt(5, transacao.getSequencia());

      System.out.println("Transação atualizada com sucesso!");
      stmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Não foi possível atualizar a transação.");
    } finally {
      DbUtils.closeQuietly(stmt);
      DbUtils.closeQuietly(conn);
    }

  }

  @Override
  public void deleteTransacao(int codigo) {
    PreparedStatement stmt = null;
    conn = ConnectionManager.getInstance().getConnectionDB();
    ContaDAO contaDAO = DAOFactory.getContaDAO();
    InvestimentoDAO investDAO = DAOFactory.getInvestimentoDAO();
    Transacao transacao = getTransacao(codigo);

    if (transacao != null) {

      try {
        conn = ConnectionManager.getInstance().getConnectionDB();
        stmt = conn.prepareStatement("DELETE FROM T_FT_TRANSACAO WHERE SQ_TRANSACAO = ?");
        conn.setAutoCommit(false);

        stmt.setInt(1, codigo);
        stmt.executeUpdate();

        int codTipo = transacao.getTipo().getCodTipo();

        // Atualiza o saldo da conta
        Conta conta = contaDAO.getConta(transacao.getConta().getNumConta());
        double valor = (codTipo == 1 || codTipo == 4) ? transacao.getValor() * -1 : transacao.getValor();
        conta.setSaldo(conta.getSaldo() + valor);
        OracleContaDAO.updateSaldo(conta, conn, stmt);

        // Atualiza o saldo do investimento
        if (transacao.getInvestimento() != null && codTipo > 2) {
          Investimento invest = investDAO.getInvestimento(transacao.getInvestimento().getCodInvestimento());
          double valorInvest = (codTipo == 3) ? transacao.getValor() * -1 : transacao.getValor();
          invest.setSaldo(invest.getSaldo() + valorInvest);
          OracleInvestimentoDAO.updateSaldo(invest, conn, stmt);
        }

        System.out.println("Transação excluída com sucesso!");
        conn.commit();

      } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("ROLLBACK: Erro na exclusão da transação");
        DbUtils.rollbackAndCloseQuietly(conn);
      } finally {
        DbUtils.closeQuietly(stmt);
        DbUtils.closeQuietly(conn);
      }
    }

  }

  @Override
  public Transacao getTransacao(int codigo) {
    PreparedStatement stmt = null;
    ResultSet rs = null;

    ContaDAO contaDAO = DAOFactory.getContaDAO();
    InvestimentoDAO investDAO = DAOFactory.getInvestimentoDAO();
    TipoDAO tipoDAO = DAOFactory.getTipoDAO();
    CategoriaDAO categoriaDAO = DAOFactory.getCategoriaoDAO();

    try {
      conn = ConnectionManager.getInstance().getConnectionDB();
      stmt = conn.prepareStatement("SELECT * FROM T_FT_TRANSACAO WHERE SQ_TRANSACAO = ?");
      stmt.setInt(1, codigo);
      rs = stmt.executeQuery();

      if (rs.next()) {
        Conta conta = contaDAO.getConta(rs.getInt("NR_CONTA"));
        int seqTrans = rs.getInt("SQ_TRANSACAO");
        String nomeTrans = rs.getString("NM_TRANSACAO");
        Investimento invest = investDAO.getInvestimento(rs.getInt("CD_INVESTIMENTO"));
        Tipo tipo = tipoDAO.getTipo(rs.getInt("CD_TIPO"));
        double valor = rs.getDouble("VL_TRANSACAO");
        java.sql.Date data = rs.getDate("DT_TRANSACAO");
        Calendar dataTrans = Calendar.getInstance();
        dataTrans.setTimeInMillis(data.getTime());
        Categoria categoria = categoriaDAO.getCategoria(rs.getInt("CD_CATEGORIA"));
        String obs = rs.getString("OB_TRANSACAO");

        Transacao transacao = new Transacao(conta, seqTrans, nomeTrans, invest, tipo, valor, dataTrans, categoria, obs);

        System.out.println("Usuário obtido com sucesso!");
        return transacao;
      }

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Erro na recuperação do usuário.");
    } finally {
      DbUtils.closeQuietly(rs);
      DbUtils.closeQuietly(stmt);
      DbUtils.closeQuietly(conn);
    }
    return null;

  }

  @Override
  public List<Transacao> getAllTransacao(int numConta) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Transacao> transacoes = new ArrayList<Transacao>();

    try {
      ContaDAO contaDAO = DAOFactory.getContaDAO();
      Conta conta = contaDAO.getConta(numConta);
//      InvestimentoDAO investDAO = DAOFactory.getInvestimentoDAO();
      TipoDAO tipoDAO = DAOFactory.getTipoDAO();
      CategoriaDAO categoriaDAO = DAOFactory.getCategoriaoDAO();

      conn = ConnectionManager.getInstance().getConnectionDB();
//      String sql = "SELECT * FROM T_FT_TRANSACAO WHERE extract(month from DT_TRANSACAO) = ? AND extract (year from DT_TRANSACAO) = ? AND NR_CONTA = ?";
      stmt = conn.prepareStatement("SELECT * FROM T_FT_TRANSACAO WHERE NR_CONTA = ? ORDER BY DT_TRANSACAO DESC");
      stmt.setInt(1, numConta);
      rs = stmt.executeQuery();

      while (rs.next()) {
//        Conta conta = contaDAO.getConta(rs.getInt("NR_CONTA"));
        int seqTrans = rs.getInt("SQ_TRANSACAO");
        String nomeTrans = rs.getString("NM_TRANSACAO");
//        Investimento invest = investDAO.getInvestimento(rs.getInt("CD_INVESTIMENTO"));
        Tipo tipo = tipoDAO.getTipo(rs.getInt("CD_TIPO"));
        double valor = rs.getDouble("VL_TRANSACAO");
        java.sql.Date data = rs.getDate("DT_TRANSACAO");
        Calendar dataTrans = Calendar.getInstance();
        dataTrans.setTimeInMillis(data.getTime());
        Categoria categoria = categoriaDAO.getCategoria(rs.getInt("CD_CATEGORIA"));
        String obs = rs.getString("OB_TRANSACAO");

        transacoes.add(new Transacao(conta, seqTrans, nomeTrans, tipo, valor, dataTrans, categoria, obs));

      }

      System.out.println("Transações obtidas com sucesso!");
      return transacoes;

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Erro na recuperação das transações.");
    } finally {
      DbUtils.closeQuietly(rs);
      DbUtils.closeQuietly(stmt);
      DbUtils.closeQuietly(conn);
    }
    return null;
  }

  @Override
  public List<Transacao> getLastestTransacao(int numConta) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Transacao> transacoes = new ArrayList<Transacao>();

    try {
      ContaDAO contaDAO = DAOFactory.getContaDAO();
      Conta conta = contaDAO.getConta(numConta);
//      InvestimentoDAO investDAO = DAOFactory.getInvestimentoDAO();
      TipoDAO tipoDAO = DAOFactory.getTipoDAO();
      CategoriaDAO categoriaDAO = DAOFactory.getCategoriaoDAO();

      conn = ConnectionManager.getInstance().getConnectionDB();
//      String sql = "SELECT * FROM ( " + "SELECT * FROM T_FT_TRANSACAO " + "WHERE extract(month from DT_TRANSACAO) = ? "
//          + "AND extract (year from DT_TRANSACAO) = ? " + "AND NR_CONTA = ? " + "ORDER BY DT_TRANSACAO DESC)"
//          + "WHERE ROWNUM <= 5";
      stmt = conn.prepareStatement("SELECT * FROM (SELECT * FROM T_FT_TRANSACAO WHERE NR_CONTA = ? ORDER BY DT_TRANSACAO DESC) WHERE ROWNUM <= 5");
      stmt.setInt(1, numConta);
      rs = stmt.executeQuery();

      while (rs.next()) {
//        Conta conta = contaDAO.getConta(rs.getInt("NR_CONTA"));
        int seqTrans = rs.getInt("SQ_TRANSACAO");
        String nomeTrans = rs.getString("NM_TRANSACAO");
//        Investimento invest = investDAO.getInvestimento(rs.getInt("CD_INVESTIMENTO"));
        Tipo tipo = tipoDAO.getTipo(rs.getInt("CD_TIPO"));
        double valor = rs.getDouble("VL_TRANSACAO");
        java.sql.Date data = rs.getDate("DT_TRANSACAO");
        Calendar dataTrans = Calendar.getInstance();
        dataTrans.setTimeInMillis(data.getTime());
        Categoria categoria = categoriaDAO.getCategoria(rs.getInt("CD_CATEGORIA"));
        String obs = rs.getString("OB_TRANSACAO");

        transacoes.add(new Transacao(conta, seqTrans, nomeTrans, tipo, valor, dataTrans, categoria, obs));

      }

      System.out.println("Transações obtidas com sucesso!");
      return transacoes;

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Erro na recuperação das transações.");
    } finally {
      DbUtils.closeQuietly(rs);
      DbUtils.closeQuietly(stmt);
      DbUtils.closeQuietly(conn);
    }
    return null;
  }

}

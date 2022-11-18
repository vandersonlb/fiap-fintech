package br.com.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import br.com.fintech.bean.Conta;
import br.com.fintech.bean.Investimento;
import br.com.fintech.bean.Transacao;
import br.com.fintech.dao.ContaDAO;
import br.com.fintech.dao.InvestimentoDAO;
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
    // TODO Auto-generated method stub

  }

  @Override
  public void deleteTransacao(int codigo) {
    // TODO Auto-generated method stub

  }

  @Override
  public Transacao getTransacao(int codigo) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Transacao> getLastestTransacaoInMonth(int currentMonth) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Transacao> getAllTransacaoByMonth(int currentMonth) {
    // TODO Auto-generated method stub
    return null;
  }

}

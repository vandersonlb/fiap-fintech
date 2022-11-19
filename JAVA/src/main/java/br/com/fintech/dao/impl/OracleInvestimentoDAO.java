package br.com.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import br.com.fintech.bean.Investimento;
import br.com.fintech.dao.InvestimentoDAO;
import br.com.fintech.jdbc.ConnectionManager;

public class OracleInvestimentoDAO implements InvestimentoDAO {

  private Connection conn;

  @Override
  public void createInvestimento(Investimento investimento) {
    PreparedStatement stmt = null;

    try {
      conn = ConnectionManager.getInstance().getConnectionDB();
      stmt = conn.prepareStatement("INSERT INTO T_FT_INVESTIMENTO VALUES(?, SQ_FT_CD_INVESTIMENTO.NEXTVAL, ?, ?, ?)");

      stmt.setInt(1, investimento.getNumConta());
      stmt.setString(2, investimento.getNomeInvestimento());
      stmt.setDouble(3, investimento.getSaldo());
      stmt.setDouble(4, investimento.getMeta());

      stmt.executeUpdate();
      System.out.println("Investimento cadastrado com sucesso!");

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Erro no cadastro, confira os dados informados.");
    } finally {
      DbUtils.closeQuietly(stmt);
      DbUtils.closeQuietly(conn);
    }

  }

  @Override
  public void updateSaldo(Investimento investimento) {
    PreparedStatement stmt = null;

    try {
      conn = ConnectionManager.getInstance().getConnectionDB();
      stmt = conn.prepareStatement("UPDATE T_FT_INVESTIMENTO SET VL_SALDO = ? WHERE CD_INVESTIMENTO = ?");

      stmt.setDouble(1, investimento.getSaldo());
      stmt.setInt(2, investimento.getCodInvestimento());

      System.out.println("Saldo atualizado com sucesso!");
      stmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Não foi possível atualizar o saldo.");
    } finally {
      DbUtils.closeQuietly(stmt);
      DbUtils.closeQuietly(conn);
    }

  }

  @Override
  public void deleteInvestimento(int codigo) {
    PreparedStatement stmt = null;

    try {
      conn = ConnectionManager.getInstance().getConnectionDB();
      stmt = conn.prepareStatement("DELETE FROM T_FT_INVESTIMENTO WHERE CD_INVESTIMENTO = ?");

      stmt.setInt(1, codigo);
      stmt.executeUpdate();
      System.out.println("Investimento apagado com sucesso!");

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Ocorreu algum erro na deleção.");
    } finally {
      DbUtils.closeQuietly(stmt);
      DbUtils.closeQuietly(conn);
    }

  }

  @Override
  public Investimento getInvestimento(int codigo) {
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      conn = ConnectionManager.getInstance().getConnectionDB();
      stmt = conn.prepareStatement("SELECT * FROM T_FT_INVESTIMENTO WHERE CD_INVESTIMENTO = ?");
      stmt.setInt(1, codigo);
      rs = stmt.executeQuery();

      if (rs.next()) {

        int numConta = rs.getInt("NR_CONTA");
        int codInvest = rs.getInt("CD_INVESTIMENTO");
        String nomeInvest = rs.getString("NM_INVESTIMENTO");
        double saldo = rs.getDouble("VL_SALDO");
        double meta = rs.getDouble("VL_META");

        Investimento invest = new Investimento(numConta, codInvest, nomeInvest, saldo, meta);
        System.out.println("Investimento obtido com sucesso!");

        return invest;

      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Erro na recuperação do investimento.");
    } finally {
      DbUtils.closeQuietly(rs);
      DbUtils.closeQuietly(stmt);
      DbUtils.closeQuietly(conn);
    }
    return null;
  }

  @Override
  public List<Investimento> getAllInvestimentoByConta(int numConta) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Investimento> invests = new ArrayList<Investimento>();

    try {
      conn = ConnectionManager.getInstance().getConnectionDB();
      stmt = conn.prepareStatement("SELECT * FROM T_FT_INVESTIMENTO WHERE NR_CONTA = ? ORDER BY CD_INVESTIMENTO");
      stmt.setInt(1, numConta);
      rs = stmt.executeQuery();

      while (rs.next()) {

        int numConta_ = rs.getInt("NR_CONTA");
        int codInvest = rs.getInt("CD_INVESTIMENTO");
        String nomeInvest = rs.getString("NM_INVESTIMENTO");
        double saldo = rs.getDouble("VL_SALDO");
        double meta = rs.getDouble("VL_META");

        invests.add(new Investimento(numConta_, codInvest, nomeInvest, saldo, meta));

      }

      System.out.println("Investimentos obtidos com sucesso!");
      return invests;

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Erro na recuperação dos investimentos.");
    } finally {
      DbUtils.closeQuietly(rs);
      DbUtils.closeQuietly(stmt);
      DbUtils.closeQuietly(conn);
    }
    return null;
  }

  public static void updateSaldo(Investimento invest, Connection conn, PreparedStatement stmt) throws SQLException {
    stmt = conn.prepareStatement("UPDATE T_FT_INVESTIMENTO SET VL_SALDO = ? WHERE CD_INVESTIMENTO = ?");

    stmt.setDouble(1, invest.getSaldo());
    stmt.setInt(2, invest.getCodInvestimento());

    stmt.executeUpdate();

  }

}

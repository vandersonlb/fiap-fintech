package br.com.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import br.com.fintech.bean.Conta;
import br.com.fintech.dao.ContaDAO;
import br.com.fintech.jdbc.ConnectionManager;

public class OracleContaDAO implements ContaDAO {

  private Connection conn;

  @Override
  public void createConta(Conta conta) {
    PreparedStatement stmt = null;

    try {
      conn = ConnectionManager.getInstance().getConnectionDB();
      stmt = conn.prepareStatement("INSERT INTO T_FT_CONTA VALUES(SQ_FT_NR_CONTA.NEXTVAL, ?, ?, ?)");
      stmt.setLong(1, conta.getNumCPF());
      stmt.setString(2, conta.getNomeConta());
      stmt.setDouble(3, conta.getSaldo());

      stmt.executeUpdate();
      System.out.println("Conta cadastrada com sucesso!");

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Erro no cadastro, confira os dados informados.");
    } finally {
      DbUtils.closeQuietly(stmt);
      DbUtils.closeQuietly(conn);
    }
  }

  @Override
  public Conta getConta(int numConta) {
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      conn = ConnectionManager.getInstance().getConnectionDB();
      stmt = conn.prepareStatement("SELECT * FROM T_FT_CONTA WHERE NR_CONTA = ?");
      stmt.setInt(1, numConta);
      rs = stmt.executeQuery();

      if (rs.next()) {

        int numConta_ = rs.getInt("NR_CONTA");
        long numCPF = rs.getLong("NR_CPF");
        String nomeConta = rs.getString("NM_CONTA");
        double saldo = rs.getDouble("VL_SALDO");

        Conta conta = new Conta(numConta_, numCPF, nomeConta, saldo);

        System.out.println("Conta obtida com sucesso!");
        return conta;

      }

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Erro na recuperação da conta.");
    } finally {
      DbUtils.closeQuietly(rs);
      DbUtils.closeQuietly(stmt);
      DbUtils.closeQuietly(conn);
    }
    return null;
  }

  @Override
  public void updateSaldo(Conta conta) {
    PreparedStatement stmt = null;
    conn = ConnectionManager.getInstance().getConnectionDB();

    try {
      stmt = conn.prepareStatement("UPDATE T_FT_CONTA SET VL_SALDO = ? WHERE NR_CONTA = ?");
      conn.setAutoCommit(false);

      stmt.setDouble(1, conta.getSaldo());
      stmt.setInt(2, conta.getNumConta());

      System.out.println("Saldo atualizado com sucesso!");
      stmt.executeUpdate();
      conn.commit();

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Não foi possível atualizar o saldo.");
      try {
        conn.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
        System.out.println("ROLLBACK no atualiza saldo conta!");
      }
    } finally {
      DbUtils.closeQuietly(stmt);
      DbUtils.closeQuietly(conn);
    }

  }

  @Override
  public List<Conta> getAllConta(long cpf) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Conta> contas = new ArrayList<Conta>();

    try {
      conn = ConnectionManager.getInstance().getConnectionDB();
      stmt = conn.prepareStatement("SELECT * FROM T_FT_CONTA WHERE NR_CPF = ?");
      stmt.setLong(1, cpf);
      rs = stmt.executeQuery();

      while (rs.next()) {

        int numConta = rs.getInt("NR_CONTA");
        long numCPF = rs.getLong("NR_CPF");
        String nomeConta = rs.getString("NM_CONTA");
        double saldo = rs.getDouble("VL_SALDO");

        contas.add(new Conta(numConta, numCPF, nomeConta, saldo));

      }

      System.out.println("Contas obtida com sucesso!");
      return contas;

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Erro na recuperação das contas.");
    } finally {
      DbUtils.closeQuietly(rs);
      DbUtils.closeQuietly(stmt);
      DbUtils.closeQuietly(conn);
    }
    return null;
  }

  public static void updateSaldo(Conta conta, Connection conn, PreparedStatement stmt) throws SQLException {
    stmt = conn.prepareStatement("UPDATE T_FT_CONTA SET VL_SALDO = ? WHERE NR_CONTA = ?");

    stmt.setDouble(1, conta.getSaldo());
    stmt.setInt(2, conta.getNumConta());

    stmt.executeUpdate();
  }

}

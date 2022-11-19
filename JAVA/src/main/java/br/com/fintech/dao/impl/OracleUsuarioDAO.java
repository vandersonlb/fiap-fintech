package br.com.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.apache.commons.dbutils.DbUtils;

import br.com.fintech.bean.Usuario;
import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.jdbc.ConnectionManager;

public class OracleUsuarioDAO implements UsuarioDAO {

  private Connection conn;

  @Override
  public void createUsuario(Usuario usuario) {
    PreparedStatement stmt = null;

    try {
      conn = ConnectionManager.getInstance().getConnectionDB();
      stmt = conn.prepareStatement("INSERT INTO T_FT_USUARIO VALUES(?, ?, ?,?, ?, ?)");
      stmt.setLong(1, usuario.getNumCPF());
      stmt.setString(2, usuario.getNome());
      java.sql.Date dataNasc = new java.sql.Date(usuario.getDataNasc().getTimeInMillis());
      stmt.setDate(3, dataNasc);
      stmt.setString(4, usuario.getEmail().toLowerCase());
      stmt.setString(5, usuario.getCelular());
      stmt.setString(6, usuario.getSenha());

      stmt.executeUpdate();
      System.out.println("Usuário cadastrado com sucesso!");

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Erro no cadastro, confira os dados informados.");
    } finally {
      DbUtils.closeQuietly(stmt);
      DbUtils.closeQuietly(conn);
    }

  }

  @Override
  public Usuario getUsuario(Usuario usuario) {
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      conn = ConnectionManager.getInstance().getConnectionDB();
      stmt = conn.prepareStatement("SELECT * FROM T_FT_USUARIO WHERE DS_EMAIL = LOWER(?)");
      stmt.setString(1, usuario.getEmail());
      rs = stmt.executeQuery();

      if (rs.next()) {
        Long cpf = rs.getLong("NR_CPF");
        String nome = rs.getString("NM_COMPLETO");
        java.sql.Date data = rs.getDate("DT_NASCIMENTO");
        Calendar dataNasc = Calendar.getInstance();
        dataNasc.setTimeInMillis(data.getTime());
        String email = rs.getString("DS_EMAIL");
        String celular = rs.getString("NR_CELULAR");
        String senha = rs.getString("DS_SENHA");

        System.out.println("Usuário obtido com sucesso!");
        return usuario = new Usuario(cpf, nome, dataNasc, email, celular, senha);
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
  public void updateUsuario(Usuario usuario) {
    PreparedStatement stmt = null;

    try {
      conn = ConnectionManager.getInstance().getConnectionDB();
      String sql = "UPDATE T_FT_USUARIO SET NM_COMPLETO = ?, DT_NASCIMENTO = ?, NR_CELULAR = ? WHERE DS_EMAIL = LOWER(?)";
      stmt = conn.prepareStatement(sql);

      stmt.setString(1, usuario.getNome());
      java.sql.Date dataNasc = new java.sql.Date(usuario.getDataNasc().getTimeInMillis());
      stmt.setDate(2, dataNasc);
      stmt.setString(3, usuario.getCelular());
      stmt.setString(4, usuario.getEmail());

      System.out.println("Usuário atualizado com sucesso!");
      stmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Não foi possível atualizar o cadastro.");
    } finally {
      DbUtils.closeQuietly(stmt);
      DbUtils.closeQuietly(conn);
    }

  }

  @Override
  public Boolean authUsuario(Usuario usuario) {
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      conn = ConnectionManager.getInstance().getConnectionDB();
      stmt = conn.prepareStatement("SELECT * FROM T_FT_USUARIO WHERE DS_EMAIL = LOWER(?) AND DS_SENHA = ?");
      stmt.setString(1, usuario.getEmail());
      stmt.setString(2, usuario.getSenha());
      rs = stmt.executeQuery();

      if (rs.next()) {
        return true;
      }

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Erro na autenticação do usuário");
    } finally {
      DbUtils.closeQuietly(rs);
      DbUtils.closeQuietly(stmt);
      DbUtils.closeQuietly(conn);
    }

    return false;
  }

}

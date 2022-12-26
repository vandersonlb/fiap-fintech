package br.com.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import br.com.fintech.bean.Tipo;
import br.com.fintech.dao.TipoDAO;
import br.com.fintech.jdbc.ConnectionManager;

public class OracleTipoDAO implements TipoDAO {

  private Connection conn;

  @Override
  public Tipo getTipo(int codigo) {
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      conn = ConnectionManager.getInstance().getConnectionDB();
      stmt = conn.prepareStatement("SELECT * FROM T_FT_TIPO WHERE CD_TIPO = ?");
      stmt.setInt(1, codigo);
      rs = stmt.executeQuery();

      if (rs.next()) {

        int cod = rs.getInt("CD_TIPO");
        String nome = rs.getString("NM_TIPO");

        System.out.println("Tipo obtido com sucesso!");
        Tipo tipo = new Tipo(cod, nome);
        return tipo;
      }

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Erro na recuperação do tipo.");
    } finally {
      DbUtils.closeQuietly(rs);
      DbUtils.closeQuietly(stmt);
      DbUtils.closeQuietly(conn);
    }
    return null;
  }

  @Override
  public List<Tipo> getAllTipo() {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Tipo> tipos = new ArrayList<Tipo>();

    try {
      conn = ConnectionManager.getInstance().getConnectionDB();
      stmt = conn.prepareStatement("SELECT * FROM T_FT_TIPO");
      rs = stmt.executeQuery();

      while (rs.next()) {

        int cod = rs.getInt("CD_TIPO");
        String nome = rs.getString("NM_TIPO");

        tipos.add(new Tipo(cod, nome));
      }

      System.out.println("Tipos obtidos com sucesso!");
      return tipos;

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Erro na recuperação dos tipos.");
    } finally {
      DbUtils.closeQuietly(rs);
      DbUtils.closeQuietly(stmt);
      DbUtils.closeQuietly(conn);
    }
    return null;
  }

}

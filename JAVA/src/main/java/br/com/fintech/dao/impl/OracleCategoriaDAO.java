package br.com.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import br.com.fintech.bean.Categoria;
import br.com.fintech.bean.GrupoCategoria;
import br.com.fintech.dao.CategoriaDAO;
import br.com.fintech.jdbc.ConnectionManager;

public class OracleCategoriaDAO implements CategoriaDAO {

  private Connection conn;

  @Override
  public Categoria getCategoria(int codigo) {
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      conn = ConnectionManager.getInstance().getConnectionDB();
      stmt = conn.prepareStatement("SELECT * FROM T_FT_CATEGORIA WHERE CD_CATEGORIA = ?");
      stmt.setInt(1, codigo);
      rs = stmt.executeQuery();

      if (rs.next()) {

        int codCategoria = rs.getInt("CD_CATEGORIA");
        String nomeCategoria = rs.getString("NM_CATEGORIA");

        Categoria categoria = new Categoria(codCategoria, nomeCategoria);
        System.out.println("Categoria obtida com sucesso!");
        return categoria;

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
  
  /**
  @Override
  public List<GrupoCategoria> getAllCategoriaGrouping() {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<GrupoCategoria> grupoCategorias = new ArrayList<GrupoCategoria>();

    try {
      conn = ConnectionManager.getInstance().getConnectionDB();
      stmt = conn.prepareStatement("SELECT * FROM T_FT_GRUPO_CATEGORIA ORDER BY CD_GRUPO");
      rs = stmt.executeQuery();

      while (rs.next()) {

        int codGrupo = rs.getInt("CD_GRUPO");
        String nomeGrupo = rs.getString("NM_GRUPO");

        GrupoCategoria grupo = new GrupoCategoria(codGrupo, nomeGrupo);
        grupo.setCategorias(getAllCategoriaByGroup(grupo.getCodGrupo()));
        grupoCategorias.add(grupo);
      }

      System.out.println("Grupos e categorias obtidos com sucesso!");
      return grupoCategorias;

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Erro na recuperação dos grupos e categorias.");
    } finally {
      DbUtils.closeQuietly(rs);
      DbUtils.closeQuietly(stmt);
      DbUtils.closeQuietly(conn);
    }
    return null;
  }

  private List<Categoria> getAllCategoriaByGroup(int codigoGrupo) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Categoria> categorias = new ArrayList<Categoria>();

    try {
      conn = ConnectionManager.getInstance().getConnectionDB();
      stmt = conn.prepareStatement("SELECT * FROM T_FT_CATEGORIA WHERE CD_GRUPO = ?");
      stmt.setInt(1, codigoGrupo);
      rs = stmt.executeQuery();

      while (rs.next()) {

        int codCategoria = rs.getInt("CD_CATEGORIA");
        String nomeCategoria = rs.getString("NM_CATEGORIA");
        categorias.add(new Categoria(codCategoria, nomeCategoria));

      }

      System.out.println("Categorias obtidas com sucesso!");
      return categorias;

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
  **/
  
  @Override
  public List<GrupoCategoria> getAllCategoriaGrouping() {
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      conn = ConnectionManager.getInstance().getConnectionDB();
      
//      List<GrupoCategoria> grupos = getAllGrupoCategoria(conn);
//      List<Categoria> categorias = getAllCategoria(conn);
      
//      System.out.println(grupos);
//      System.out.println(categorias);
      
      List<GrupoCategoria> grupos = new ArrayList<>();
      
      for (GrupoCategoria grupo : getAllGrupoCategoria(conn)) {
//        System.out.println(grupo);
        List<Categoria> categorias = new ArrayList<>();
        grupo.setCategorias(categorias);
        for (Categoria categoria : getAllCategoria(conn)) {
//          System.out.println(categoria);
//          System.out.println(categoria.getCodGrupo());
          if(categoria.getCodGrupo() == grupo.getCodGrupo()) {
//            System.out.printf("%s - %s \n", grupo.getNomeGrupo(), categoria.getNomeCategoria());
            grupo.getCategorias().add(categoria);
          }
        }
        grupos.add(grupo);
      }
      
      System.out.println("Grupos e categorias obtidos com sucesso!");
      return grupos;

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Erro na recuperação dos grupos e categorias.");
    } finally {
      DbUtils.closeQuietly(rs);
      DbUtils.closeQuietly(stmt);
      DbUtils.closeQuietly(conn);
    }
    return null;
  }
  
  private List<GrupoCategoria> getAllGrupoCategoria(Connection conn) throws SQLException {
//    PreparedStatement stmt = null;
//    ResultSet rs = null;
    List<GrupoCategoria> grupos = new ArrayList<>();

//      conn = ConnectionManager.getInstance().getConnectionDB();
    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM T_FT_GRUPO_CATEGORIA ORDER BY CD_GRUPO");
    ResultSet rs = stmt.executeQuery();
      
      while (rs.next()) {

        int codGrupo = rs.getInt("CD_GRUPO");
        String nomeGrupo = rs.getString("NM_GRUPO");
        
        grupos.add(new GrupoCategoria(codGrupo, nomeGrupo));
      }

      return grupos;
  }
  
  private List<Categoria> getAllCategoria(Connection conn) throws SQLException {
//    PreparedStatement stmt = null;
//    ResultSet rs = null;
    List<Categoria> categorias = new ArrayList<>();
    
//    conn = ConnectionManager.getInstance().getConnectionDB();
    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM T_FT_GRUPO_CATEGORIA NATURAL JOIN T_FT_CATEGORIA ORDER BY CD_CATEGORIA");
    ResultSet rs = stmt.executeQuery();
    
    while (rs.next()) {
      
      int codCategoria = rs.getInt("CD_CATEGORIA");
      String nomeCategoria = rs.getString("NM_CATEGORIA");
      int codGrupo = rs.getInt("CD_GRUPO");
      
      categorias.add(new Categoria(codCategoria, nomeCategoria, codGrupo));
    }
    
    return categorias;
  }


}

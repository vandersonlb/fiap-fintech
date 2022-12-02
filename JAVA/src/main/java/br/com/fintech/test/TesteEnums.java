package br.com.fintech.test;

import java.util.List;

import br.com.fintech.bean.Categoria;
import br.com.fintech.bean.GrupoCategoria;
import br.com.fintech.dao.CategoriaDAO;
import br.com.fintech.factory.DAOFactory;
import br.com.fintech.util.EnumCategoria;
import br.com.fintech.util.EnumGrupo;

public class TesteEnums {

  public static void main(String[] args) {

    CategoriaDAO categoriaDAO = DAOFactory.getCategoriaoDAO();

    List<GrupoCategoria> grupoCategorias = categoriaDAO.getAllCategoriaGrouping();

    int codGrupo = 1;

    for (GrupoCategoria grupo : grupoCategorias) {
      System.out.println("************************************");
      System.out.println(EnumGrupo.values()[codGrupo - 1].label);
      codGrupo++;
      for (Categoria categoria : grupo.getCategorias()) {
        String teste = categoria.getNomeCategoria().toUpperCase().replaceAll("[\\s\\/]+", "_");
        System.out.printf("%s - %d \n", EnumCategoria.valueOf(teste).label, EnumCategoria.valueOf(teste).ordinal());
      }
    }

  }

}

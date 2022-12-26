package br.com.fintech.test;

import java.util.List;

import br.com.fintech.bean.Categoria;
import br.com.fintech.bean.GrupoCategoria;
import br.com.fintech.dao.CategoriaDAO;
import br.com.fintech.factory.DAOFactory;

public class TesteDAOCategoria {

  public static void main(String[] args) {

    CategoriaDAO categoriaDAO = DAOFactory.getCategoriaoDAO();

    // RECUPERAR TODAS AS CATEGORIAS AGRUPADAS
//    List<GrupoCategoria> grupoCategorias = categoriaDAO.getAllCategoriaGrouping();
//
//    for (GrupoCategoria grupo : grupoCategorias) {
//      System.out.println(grupo);
//    }

    // RECUPERAR CATEGORIA POR CÓDIGO
    Categoria categoria = categoriaDAO.getCategoria(7);
    System.out.println(categoria);

  }

}

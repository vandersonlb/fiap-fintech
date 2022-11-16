package br.com.fintech.dao;

import java.util.List;

import br.com.fintech.bean.Categoria;
import br.com.fintech.bean.GrupoCategoria;

public interface CategoriaDAO {

  Categoria getCategoria(int codigo);

  List<GrupoCategoria> getAllCategoriaGrouping();
}

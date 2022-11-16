package br.com.fintech.dao;

import java.util.List;

import br.com.fintech.bean.Tipo;

public interface TipoDAO {

  Tipo getTipo(int codigo);

  List<Tipo> getAllTipo();

}

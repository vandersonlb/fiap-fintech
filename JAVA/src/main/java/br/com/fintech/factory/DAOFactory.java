package br.com.fintech.factory;

import br.com.fintech.dao.ContaDAO;
import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.dao.impl.OracleContaDAO;
import br.com.fintech.dao.impl.OracleUsuarioDAO;

public class DAOFactory {

  public static UsuarioDAO getUsuarioDAO() {
    return new OracleUsuarioDAO();
  }

  public static ContaDAO getContaDAO() {
    return new OracleContaDAO();
  }

}

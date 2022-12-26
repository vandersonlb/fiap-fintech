package br.com.fintech.factory;

import br.com.fintech.dao.CategoriaDAO;
import br.com.fintech.dao.ContaDAO;
import br.com.fintech.dao.InvestimentoDAO;
import br.com.fintech.dao.TipoDAO;
import br.com.fintech.dao.TransacaoDAO;
import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.dao.impl.OracleCategoriaDAO;
import br.com.fintech.dao.impl.OracleContaDAO;
import br.com.fintech.dao.impl.OracleInvestimentoDAO;
import br.com.fintech.dao.impl.OracleTipoDAO;
import br.com.fintech.dao.impl.OracleTransacaoDAO;
import br.com.fintech.dao.impl.OracleUsuarioDAO;

public class DAOFactory {

  public static UsuarioDAO getUsuarioDAO() {
    return new OracleUsuarioDAO();
  }

  public static ContaDAO getContaDAO() {
    return new OracleContaDAO();
  }

  public static TipoDAO getTipoDAO() {
    return new OracleTipoDAO();
  }

  public static InvestimentoDAO getInvestimentoDAO() {
    return new OracleInvestimentoDAO();
  }

  public static CategoriaDAO getCategoriaoDAO() {
    return new OracleCategoriaDAO();
  }

  public static TransacaoDAO getTransacaoDAO() {
    return new OracleTransacaoDAO();
  }

}

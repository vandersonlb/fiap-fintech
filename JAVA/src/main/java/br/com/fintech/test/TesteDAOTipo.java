package br.com.fintech.test;

import java.util.List;

import br.com.fintech.bean.Tipo;
import br.com.fintech.dao.TipoDAO;
import br.com.fintech.factory.DAOFactory;

public class TesteDAOTipo {

  public static void main(String[] args) {

    TipoDAO tipoDAO = DAOFactory.getTipoDAO();

    // RECUPERANDO TODOS OS TIPOS
    List<Tipo> tipos = tipoDAO.getAllTipo();

    for (Tipo tipo : tipos) {
      System.out.println(tipo);
    }

    // RECUPERANDO TIPO POR CÓDIGO
    Tipo tipo_1 = tipoDAO.getTipo(1);
    System.out.println(tipo_1);

    Tipo tipo_2 = tipoDAO.getTipo(2);
    System.out.println(tipo_2);

  }

}

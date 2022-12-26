package br.com.fintech.test;

import java.util.Calendar;

import br.com.fintech.bean.Usuario;
import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.factory.DAOFactory;

public class TesteDAOUsuario {

  public static void main(String[] args) {
    UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
    Calendar calendario = Calendar.getInstance();
    Calendar dataNasc = calendario;

    // CRIAR USUARIO
    calendario.set(1985, Calendar.JUNE, 16);
    dataNasc = calendario;
    Usuario vanderson_1 = new Usuario(Long.parseLong("32582511855"), "Vanderson Luis Bonacuore", dataNasc,
        "VANDERSON@hotmail.com", "11967533321", "123456");

    usuarioDAO.createUsuario(vanderson_1);
    
    // RECUPERAR USUARIO
    Usuario teste1 = new Usuario("vanDERSon@hotmail.com", "123abc");
    Usuario teste2 = new Usuario("fabio.coelho@gmail.com", "123abc");
    System.out.println(usuarioDAO.getUsuario(teste1));
    System.out.println(usuarioDAO.getUsuario(teste2));

    // ATUALIZAR USUARIO
    Usuario vanderson_2 = usuarioDAO.getUsuario(new Usuario("vAnDerSon@hotmail.com", "123"));
    vanderson_2.setNome("Vanderson L. B.");
    vanderson_2.setCelular("11999998999");
    calendario.set(2033, Calendar.OCTOBER, 17);
    dataNasc = calendario;
    vanderson_2.setDataNasc(dataNasc);

    usuarioDAO.updateUsuario(vanderson_2);

    // AUTENTICAR USUARIO
    Usuario vanderson_3 = new Usuario("vandersonlb@hotmail.com", "123456");
    System.out.println(usuarioDAO.authUsuario(vanderson_3));

    Usuario desconhecido = new Usuario("vAndeRson@hotmail.com", "123456");
    System.out.println(usuarioDAO.authUsuario(desconhecido));

  }

}

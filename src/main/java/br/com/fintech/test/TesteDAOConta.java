package br.com.fintech.test;

import java.util.List;

import br.com.fintech.bean.Conta;
import br.com.fintech.bean.Usuario;
import br.com.fintech.dao.ContaDAO;
import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.factory.DAOFactory;

public class TesteDAOConta {

  public static void main(String[] args) {
    UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
    ContaDAO contaDAO = DAOFactory.getContaDAO();

    // CRIAR CONTA
    Usuario vanderson = usuarioDAO.getUsuario(new Usuario("vandersonlb@hotmail.com", "123"));
    Conta conta1 = new Conta(0, vanderson.getNumCPF(), "Conta TESTE", 0);

    contaDAO.createConta(conta1);

    // RECUPERAR CONTA
    Conta conta2 = contaDAO.getConta(123460);
    System.out.println(conta2);

    // ATUALIZAR SALDO
    Conta conta3 = contaDAO.getConta(123460);
    conta3.setSaldo(1500.55F);
    contaDAO.updateSaldo(conta3);
    System.out.println(contaDAO.getConta(123460));

    // RECUPERAR TODAS AS CONTAS DE UM USUÁRIO
    List<Conta> contas = contaDAO.getAllConta(Long.parseLong("63557321010"));

    for (Conta conta : contas) {
      System.out.println(conta);
    }

  }

}

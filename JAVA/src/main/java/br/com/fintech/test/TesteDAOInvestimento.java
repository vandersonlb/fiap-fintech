package br.com.fintech.test;

import java.util.List;

import br.com.fintech.bean.Conta;
import br.com.fintech.bean.Investimento;
import br.com.fintech.dao.ContaDAO;
import br.com.fintech.dao.InvestimentoDAO;
import br.com.fintech.factory.DAOFactory;

public class TesteDAOInvestimento {

  public static void main(String[] args) {
    InvestimentoDAO investDAO = DAOFactory.getInvestimentoDAO();
    ContaDAO contaDAO = DAOFactory.getContaDAO();

    // CRIAR INVESTIMENTO
    Conta conta = contaDAO.getConta(123460);

    Investimento invest_1 = new Investimento(conta.getNumConta(), 0, "Invest TESTE", 150, 1500.55F);
    investDAO.createInvestimento(invest_1);

    // RECUPERAR INVESTIMENTO POR CODIGO
    Investimento invest_2 = investDAO.getInvestimento(10);
    System.out.println(invest_2);

    // RECUPERAR TODOS INVESTIMENTOS DE UMA CONTA
    List<Investimento> invests = investDAO.getAllInvestimentoByConta(123460);

    for (Investimento invest : invests) {
      System.out.println(invest);
    }

    // ATUALIZAR SALDO
    Investimento invest_3 = investDAO.getInvestimento(14);
    invest_3.setSaldo(0F);

    investDAO.updateSaldo(invest_3);

    System.out.println(investDAO.getInvestimento(14));

    // DELETAR SALDO
    investDAO.deleteInvestimento(14);

  }

}

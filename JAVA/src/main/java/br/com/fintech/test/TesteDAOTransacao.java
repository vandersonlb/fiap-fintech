package br.com.fintech.test;

import java.util.Calendar;
import java.util.List;

import br.com.fintech.bean.Categoria;
import br.com.fintech.bean.Conta;
import br.com.fintech.bean.Investimento;
import br.com.fintech.bean.Tipo;
import br.com.fintech.bean.Transacao;
import br.com.fintech.dao.CategoriaDAO;
import br.com.fintech.dao.ContaDAO;
import br.com.fintech.dao.InvestimentoDAO;
import br.com.fintech.dao.TipoDAO;
import br.com.fintech.dao.TransacaoDAO;
import br.com.fintech.factory.DAOFactory;

public class TesteDAOTransacao {

  public static void main(String[] args) {
    ContaDAO contaDAO = DAOFactory.getContaDAO();
    InvestimentoDAO investDAO = DAOFactory.getInvestimentoDAO();
    TipoDAO tipoDAO = DAOFactory.getTipoDAO();
    CategoriaDAO categoriaDAO = DAOFactory.getCategoriaoDAO();
    TransacaoDAO transacaoDAO = DAOFactory.getTransacaoDAO();
    
    /////// CRIANDO TRANSAÇÔES
    Conta conta_1 = contaDAO.getConta(123460);
    Investimento invest_1 = investDAO.getInvestimento(13);
    Tipo entrada = tipoDAO.getTipo(1);
    Tipo saida = tipoDAO.getTipo(2);
    Tipo aplicacao = tipoDAO.getTipo(3);
    Tipo resgate = tipoDAO.getTipo(4);
    Categoria categoria = categoriaDAO.getCategoria(36);

    // Entrada de 1500 na conta (Espera: Saldo conta = 1500)
    Transacao transEntrada = new Transacao(conta_1, 0, "ENTRADA", null, entrada, 1500, Calendar.getInstance(),
        categoria, "");
    transacaoDAO.createTransacao(transEntrada);

    // Gasto de 500 (Espera: Saldo conta = 1000)
    Transacao transSaida = new Transacao(conta_1, 0, "SAIDA", null, saida, 500, Calendar.getInstance(), categoria, "");
    transacaoDAO.createTransacao(transSaida);

    // Aporte de 700 (Espera: Saldo conta = 300, Saldo investimento = 700)
    Transacao transAplicacao = new Transacao(conta_1, 0, "APLICAÇÃO", invest_1, aplicacao, 700, Calendar.getInstance(),
        categoria, "");
    transacaoDAO.createTransacao(transAplicacao);

    // Resgate de 300 (Espera: Saldo conta = 600, Saldo investimento = 400)
    Transacao transResgate = new Transacao(conta_1, 0, "RESGATE", invest_1, resgate, 300, Calendar.getInstance(),
        categoria, "");
    transacaoDAO.createTransacao(transResgate);

    // RECUPERANDO E ATUALIZANDO UMA TRANSACAO
    Transacao transacao = transacaoDAO.getTransacao(14);
    transacao.setNome("TESTE ATUALIZAÇÃO");
    transacao.setData(Calendar.getInstance());
    transacao.setCategoria(categoriaDAO.getCategoria(25));
    transacao.setObsevacao("Isso é um teste de atualização");

    transacaoDAO.updateTransacao(transacao);

    // RECUPERANDO TODAS AS TRANSAÇÕES DE UM USUÁRIO NO MÊS
    List<Transacao> allTransac = transacaoDAO.getAllTransacao(123460);

    for (Transacao trans : allTransac) {
      System.out.println(trans);
    }

    // RECUPERANDO AS ÚLTIMAS TRANSAÇÕES DO MÊS
    List<Transacao> lastestTransac = transacaoDAO.getLastestTransacao(123460);

    for (Transacao trans : lastestTransac) {
      System.out.println(trans);
    }

    // DELETANDO UMA TRANSAÇÃO (e atualizando valores)
    transacaoDAO.deleteTransacao(64);

    // ATALHO PARA CRIAR TRANSACOES PARA TESTE
    Calendar calendario = Calendar.getInstance();
    Conta conta_2 = contaDAO.getConta(123460);
    Investimento invest_2 = investDAO.getInvestimento(54);
    Tipo tipo = tipoDAO.getTipo(4);
    Categoria categ = categoriaDAO.getCategoria(36);

    calendario.set(2022, Calendar.NOVEMBER, 23);
    Calendar data = calendario;
    Transacao transTeste = new Transacao(conta_2, 0, "Teste 9 resgate", invest_2, tipo, 300, data, categ, "");
    transacaoDAO.createTransacao(transTeste);

  }

}

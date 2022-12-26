package br.com.fintech.dao;

import java.util.List;

import br.com.fintech.bean.Investimento;

public interface InvestimentoDAO {

  void createInvestimento(Investimento investimento);

  void updateSaldo(Investimento investimento);

  void deleteInvestimento(int codigo);

  Investimento getInvestimento(int codigo);

  List<Investimento> getAllInvestimentoByConta(int numConta);

}

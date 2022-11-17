package br.com.fintech.dao;

import java.util.List;

import br.com.fintech.bean.Conta;

public interface ContaDAO {

  void createConta(Conta conta);

  Conta getConta(int numConta);

  void updateSaldo(Conta conta);

  List<Conta> getAllConta(long cpf);

}

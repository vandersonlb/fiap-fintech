package br.com.fintech.dao;

import java.util.List;

public interface ContaDAO {

  void createConta(ContaDAO conta);
  
  void updateSaldo(ContaDAO conta);
  
  ContaDAO getConta(int conta);
  
  List<ContaDAO> getAllConta(int cpf);

}

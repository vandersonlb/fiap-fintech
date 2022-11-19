package br.com.fintech.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.fintech.bean.Conta;
import br.com.fintech.bean.Transacao;

public interface TransacaoDAO {

  void createTransacao(Transacao transacao);

  void updateTransacao(Transacao transacao);

  void deleteTransacao(int codigo);

  Transacao getTransacao(int codigo);

  List<Transacao> getAllTransacaoByMonth(int numConta, int month, int year);

  List<Transacao> getLastestTransacaoInMonth(int numConta, int month, int year);

}

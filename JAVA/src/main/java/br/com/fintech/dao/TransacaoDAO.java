package br.com.fintech.dao;

import java.util.List;

import br.com.fintech.bean.Transacao;

public interface TransacaoDAO {

  void createTransacao(Transacao transacao);

  void updateTransacao(Transacao transacao);

  void deleteTransacao(int codigo);

  Transacao getTransacao(int codigo);

  List<Transacao> getAllTransacao(int numConta);

  List<Transacao> getLastestTransacao(int numConta);

}

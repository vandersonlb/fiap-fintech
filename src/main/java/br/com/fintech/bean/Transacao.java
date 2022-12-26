package br.com.fintech.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Transacao implements Serializable {

  static final long serialVersionUID = 1L;
  private Conta conta;
  private int sequencia;
  private String nome;
  private Investimento investimento;
  private Tipo tipo;
  private double valor;
  private Calendar data;
  private Categoria categoria;
  private String obsevacao;

  public Transacao() {
    super();
  }

  public Transacao(Conta conta, int sequencia, String nome, Investimento investimento, Tipo tipo, double valor,
      Calendar data, Categoria categoria, String obsevacao) {
    super();
    this.conta = conta;
    this.sequencia = sequencia;
    this.nome = nome;
    this.investimento = investimento;
    this.tipo = tipo;
    this.valor = valor;
    this.data = data;
    this.categoria = categoria;
    this.obsevacao = obsevacao;
  }

  public Transacao(Conta conta, int sequencia, String nome, Tipo tipo, double valor, Calendar data, Categoria categoria,
      String obsevacao) {
    super();
    this.conta = conta;
    this.sequencia = sequencia;
    this.nome = nome;
    this.tipo = tipo;
    this.valor = valor;
    this.data = data;
    this.categoria = categoria;
    this.obsevacao = obsevacao;
  }

  public Conta getConta() {
    return conta;
  }

  public void setConta(Conta conta) {
    this.conta = conta;
  }

  public int getSequencia() {
    return sequencia;
  }

  public void setSequencia(int sequencia) {
    this.sequencia = sequencia;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Investimento getInvestimento() {
    return investimento;
  }

  public void setInvestimento(Investimento investimento) {
    this.investimento = investimento;
  }

  public Tipo getTipo() {
    return tipo;
  }

  public void setTipo(Tipo tipo) {
    this.tipo = tipo;
  }

  public double getValor() {
    return valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

  public Calendar getData() {
    return data;
  }

  public void setData(Calendar data) {
    this.data = data;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public String getObsevacao() {
    return obsevacao;
  }

  public void setObsevacao(String obsevacao) {
    this.obsevacao = obsevacao;
  }

  @Override
  public String toString() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    return String.format("%s, %d, %s, %s, %s, %.2f, %s, %s, %s", conta.getNumConta(), sequencia, nome, investimento,
        tipo.getNomeTipo(), valor, sdf.format(data.getTime()), categoria.getNomeCategoria(), obsevacao);
  }

}

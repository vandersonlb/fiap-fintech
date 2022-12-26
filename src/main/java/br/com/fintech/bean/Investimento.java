package br.com.fintech.bean;

import java.io.Serializable;

public class Investimento implements Serializable {

  private static final long serialVersionUID = 1L;
  private int numConta;
  private int codInvestimento;
  private String nomeInvestimento;
  private double saldo;
  private double meta;

  public Investimento() {
    super();
  }

  public Investimento(int numConta, int codInvestimento, String nomeInvestimento, double saldo, double meta) {
    super();
    this.numConta = numConta;
    this.codInvestimento = codInvestimento;
    this.nomeInvestimento = nomeInvestimento;
    this.saldo = saldo;
    this.meta = meta;
  }

  public int getNumConta() {
    return numConta;
  }

  public void setNumConta(int numConta) {
    this.numConta = numConta;
  }

  public int getCodInvestimento() {
    return codInvestimento;
  }

  public void setCodInvestimento(int codInvestimento) {
    this.codInvestimento = codInvestimento;
  }

  public String getNomeInvestimento() {
    return nomeInvestimento;
  }

  public void setNomeInvestimento(String nomeInvestimento) {
    this.nomeInvestimento = nomeInvestimento;
  }

  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }

  public double getMeta() {
    return meta;
  }

  public void setMeta(double meta) {
    this.meta = meta;
  }

  @Override
  public String toString() {
    return String.format("{Investimento: %s, Código: %d, Conta: %d, Saldo: %.2f, Meta: %.2f}", nomeInvestimento,
        codInvestimento, numConta, saldo, meta);
  }

}

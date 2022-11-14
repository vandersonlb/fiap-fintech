package br.com.fintech.bean;

import java.io.Serializable;
import java.util.List;

public class Conta implements Serializable {

  private static final long serialVersionUID = 1L;
  private int numConta;
  private long numCPF;
  private String nomeConta;
  private double saldo;
  private List<Investimento> investimentos;

  public Conta() {
    super();
  }

  public Conta(int numConta, long numCPF, String nomeConta, double saldo) {
    super();
    this.numConta = numConta;
    this.numCPF = numCPF;
    this.nomeConta = nomeConta;
    this.saldo = saldo;
  }

  public int getNumConta() {
    return numConta;
  }

  public void setNumConta(int numConta) {
    this.numConta = numConta;
  }

  public long getNumCPF() {
    return numCPF;
  }

  public void setNumCPF(int numCPF) {
    this.numCPF = numCPF;
  }

  public String getNomeConta() {
    return nomeConta;
  }

  public void setNomeConta(String nomeConta) {
    this.nomeConta = nomeConta;
  }

  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }

  public List<Investimento> getInvestimentos() {
    return investimentos;
  }

  public void setInvestimentos(List<Investimento> investimentos) {
    this.investimentos = investimentos;
  }

  @Override
  public String toString() {
    return String.format("{Conta %s, Código %d, CPF: %d, Saldo: %.2f, Investimentos: %s}", nomeConta, numConta, numCPF,
        saldo, investimentos);
  }

}

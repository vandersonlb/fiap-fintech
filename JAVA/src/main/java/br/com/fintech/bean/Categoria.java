package br.com.fintech.bean;

import java.io.Serializable;

public class Categoria implements Serializable {

  private static final long serialVersionUID = 1L;
  private int codCategoria;
  private String nomeCategoria;

  public Categoria() {
    super();
  }

  public Categoria(int codCategoria, String nomeCategoria) {
    super();
    this.codCategoria = codCategoria;
    this.nomeCategoria = nomeCategoria;
  }

  public int getCodCategoria() {
    return codCategoria;
  }

  public void setCodCategoria(int codCategoria) {
    this.codCategoria = codCategoria;
  }

  public String getNomeCategoria() {
    return nomeCategoria;
  }

  public void setNomeCategoria(String nomeCategoria) {
    this.nomeCategoria = nomeCategoria;
  }

  @Override
  public String toString() {
    return String.format("{Categoria: %s, Código: %d}", nomeCategoria, codCategoria);
  }

}

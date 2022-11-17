package br.com.fintech.bean;

import java.io.Serializable;

public class Tipo implements Serializable {

  private static final long serialVersionUID = 1L;
  private int codTipo;
  private String nomeTipo;

  public Tipo() {
    super();
  }

  public Tipo(int codTipo, String nomeTipo) {
    super();
    this.codTipo = codTipo;
    this.nomeTipo = nomeTipo;
  }

  public int getCodTipo() {
    return codTipo;
  }

  public void setCodTipo(int codTipo) {
    this.codTipo = codTipo;
  }

  public String getNomeTipo() {
    return nomeTipo;
  }

  public void setNomeTipo(String nomeTipo) {
    this.nomeTipo = nomeTipo;
  }

  @Override
  public String toString() {
    return String.format("Tipo: %s, Código: %d", nomeTipo, codTipo);
  }

}

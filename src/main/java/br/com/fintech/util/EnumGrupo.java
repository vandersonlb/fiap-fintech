package br.com.fintech.util;

public enum EnumGrupo {

  RENDA("border-success text-success"),
  GASTOS_ESSENCIAIS("border-danger text-danger"),
  ESTILO_DE_VIDA("border-primary text-primary"),
  INVESTIMENTOS("border-info text-info"),
  EMPRÉSTIMOS("border-secondary text-secondary"),
  NÂO_CLASSIFICADO("border-dark text-dark");

  public final String label;

  EnumGrupo(String label) {
    this.label = label;
  }
  
  public String getLabel() {
    return label;
  }

}
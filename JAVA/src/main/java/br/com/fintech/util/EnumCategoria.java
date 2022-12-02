package br.com.fintech.util;

public enum EnumCategoria {

  BÔNUS("bi-currency-dollar"),
  EMPRÉSTIMO("bi-coin"),
  OUTRAS_RENDAS("bi-cash-stack"),
  REMUNERAÇÃO("bi-cash"),
  RENDIMENTO("bi-piggy-bank"),
  CONTAS_RESIDENCIAIS("bi-receipt"),
  EDUCAÇÃO("bi-journal-text"),
  MERCADO("bi-basket"),
  MORADIA("bi-house"),
  SAÚDE("bi-bandaid"),
  TRANSPORTE("bi-train-freight-front"),
  BARES_RESTAURANTES("bi-cup-hot"),
  CARRO("bi-car-front"),
  COMPRAS("bi-bag"),
  CUIDADOS_PESSOAIS("bi-droplet-fill"),
  DESPESAS_DO_TRABALHO("bi-archive"),
  EMPREGADOS_DOMÉSTICO("bi-house-fill"),
  FAMÍLIA_FILHOS("bi-person-heart"),
  LAZER("bi-image"),
  OUTROS_GASTOS("bi-slash-circle"),
  PRESENTES_DOAÇÕES("bi-gift"),
  SAQUES("bi-safe2"),
  SERVIÇOS("bi-hammer"),
  TV_INTERNET_TELEFONE("bi-at"),
  TAXAS_BANCÁRIAS("bi-bank"),
  VIAGEM("bi-airplane"),
  APLICAÇÃO("bi-piggy-bank"),
  RESGATE("bi-piggy-bank-fill"),
  CARNÊ("bi-wallet"),
  CHEQUE_ESPECIAL("bi-wallet2"),
  CREDIÁRIO("bi-credit-card"),
  CRÉDITO_CONSIGNADO("bi-cash-stack"),
  JUROS("bi-bank2"),
  JUROS_DE_CARTÃO("bi-credit-card-2-back-fill"),
  OUTROS_EMPRÉSTIMOS("bi-safe2"),
  OUTROS("bi-exclamation-circle");

  public final String label;

  EnumCategoria(String label) {
    this.label = label;
  }
  
  public String getLabel() {
    return label;
  }

}
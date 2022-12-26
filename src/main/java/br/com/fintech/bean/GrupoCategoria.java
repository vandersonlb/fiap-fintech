package br.com.fintech.bean;

import java.io.Serializable;
import java.util.List;

public class GrupoCategoria implements Serializable {

  private static final long serialVersionUID = 1L;
  private int codGrupo;
  private String nomeGrupo;
  List<Categoria> categorias;

  public GrupoCategoria() {
    super();
  }

  public GrupoCategoria(int codGrupo, String nomeGrupo, List<Categoria> categorias) {
    super();
    this.codGrupo = codGrupo;
    this.nomeGrupo = nomeGrupo;
    this.categorias = categorias;
  }

  public GrupoCategoria(int codGrupo, String nomeGrupo) {
    super();
    this.codGrupo = codGrupo;
    this.nomeGrupo = nomeGrupo;
  }

  public int getCodGrupo() {
    return codGrupo;
  }

  public void setCodGrupo(int codGrupo) {
    this.codGrupo = codGrupo;
  }

  public String getNomeGrupo() {
    return nomeGrupo;
  }

  public void setNomeGrupo(String nomeGrupo) {
    this.nomeGrupo = nomeGrupo;
  }

  public List<Categoria> getCategorias() {
    return categorias;
  }

  public void setCategorias(List<Categoria> categorias) {
    this.categorias = categorias;
  }

  @Override
  public String toString() {
    return String.format("Grupo: %s, Código: %d, Categorias: %s", nomeGrupo, codGrupo, categorias);
  }

}

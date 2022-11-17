package br.com.fintech.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.com.fintech.util.CriptografiaUtils;

public class Usuario implements Serializable {

  private static final long serialVersionUID = 1L;
  private long numCPF;
  private String nome;
  private Calendar dataNasc = null;
  private String email;
  private String celular;
  private String senha;
  private List<Conta> contas;

  public Usuario() {
    super();
  }

  public Usuario(String email, String senha) {
    super();
    this.email = email;
    setSenha(senha);
  }

  public Usuario(long numCPF, String nome, Calendar dataNasc, String email, String celular, String senha) {
    super();
    this.numCPF = numCPF;
    this.nome = nome;
    this.dataNasc = dataNasc;
    this.email = email;
    this.celular = celular;
    setSenha(senha);
  }

  public long getNumCPF() {
    return numCPF;
  }

  public void setNumCPF(long numCPF) {
    this.numCPF = numCPF;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Calendar getDataNasc() {
    return dataNasc;
  }

  public void setDataNasc(Calendar dataNasc) {
    this.dataNasc = dataNasc;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCelular() {
    return celular;
  }

  public void setCelular(String celular) {
    this.celular = celular;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    try {
      this.senha = CriptografiaUtils.criptografar(senha);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List<Conta> getContas() {
    return contas;
  }

  public void setContas(List<Conta> contas) {
    this.contas = contas;
  }

  @Override
  public String toString() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String data = null;
    if (this.dataNasc != null) {
      data = sdf.format(dataNasc.getTime());
    }

    return String.format("%s, %d, %s, %s, %s, %s, %s", nome, numCPF, data, email, celular, senha, contas);
  }

}

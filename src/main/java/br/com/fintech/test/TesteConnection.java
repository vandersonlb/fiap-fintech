package br.com.fintech.test;

import java.sql.Connection;

import br.com.fintech.jdbc.ConnectionManager;

public class TesteConnection {
  
  public static void main(String[] args) {
    Connection con = ConnectionManager.getInstance().getConnectionDB();
  }

}

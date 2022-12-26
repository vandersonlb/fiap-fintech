package br.com.fintech.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
  private static ConnectionManager instancia;

  private ConnectionManager() {
  }

  public static ConnectionManager getInstance() {
    if (instancia == null) {
      instancia = new ConnectionManager();
    }
    return instancia;
  }

  public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
  public static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
  public static final String USER = "rm93448";
  public static final String PASS = "140495";

  public Connection getConnectionDB() {
    Connection conexao = null;

    try {
      Class.forName(DRIVER);
      conexao = DriverManager.getConnection(URL, USER, PASS);

      System.out.println("Conexão obtida com sucesso!");

    } catch (ClassNotFoundException e) {
      System.out.println("Deu algum erro com o driver");
      e.printStackTrace();
    } catch (SQLException se) {
      System.out.println("Deu algum erro com a conexão com o DB");
      se.printStackTrace();
    }

    return conexao;
  }

}

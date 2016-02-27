/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Lucas Basseti
 */
public class JDBCConnection {
    
    private static Connection conn = null;
    private static final JDBCConnection jdbcConnection = new JDBCConnection();
    
    private Connection getOpenConnectOracle() throws IOException, ClassNotFoundException, SQLException  {
    Connection connection;
    Properties confBanco = new Properties();
    
    String caminho = new java.io.File("jdbcconnections.properties").getCanonicalPath();
    confBanco.load(new FileInputStream(caminho));
         
    Class.forName(confBanco.getProperty("JDBCConnect.driver"));
    String url =      confBanco.getProperty("JDBCConnect.url");
    String ip =       confBanco.getProperty("JDBCConnect.ip");
    String porta =    confBanco.getProperty("JDBCConnect.porta");
    String banco =    confBanco.getProperty("JDBCConnect.Database");
    String charSet =  confBanco.getProperty("JDBCConnect.charset");
    String user =     confBanco.getProperty("JDBCConnect.user");
    String password = confBanco.getProperty("JDBCConnect.password");
    System.out.println(url + ip + ":" + porta + ":" + banco) ;
    connection = DriverManager.getConnection(url + ip + ":" + porta + ":" + banco, user, password);
    //jdbc:oracle:thin:@localhost:1521:XE
        
    return connection;
  }

    
  public static Connection getConnectOracle() throws IOException, ClassNotFoundException, SQLException {
    if (conn == null) 
      conn = jdbcConnection.getOpenConnectOracle();
    return conn;
  }  
    
  public static void closeConnectOracle() throws SQLException {
      if (conn != null)
          conn.close();
  }
    
}

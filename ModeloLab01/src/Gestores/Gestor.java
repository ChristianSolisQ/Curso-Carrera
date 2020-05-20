/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Usuario
 */
public class Gestor {
    protected static Connection conexion;
    private static  Gestor gestor;
    String url,baseDatos;
    public  static Gestor getGestor(String url,String baseDatos) {
 
 if (gestor==null) {
 
 gestor=new Gestor(url,baseDatos);
 }
 return gestor;
 }
 private Gestor(String url,String baseDatos){
 
 this.url=url;
 this.baseDatos=baseDatos;
 
 }

    public Gestor() {
        conexion = null;
    }

    protected static void connection() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "root");
    }

    protected static void disconnect() throws SQLException {
        if (!conexion.isClosed()) {
            conexion.close();
        }
    }
    private Connection getJdbcMydbsource() throws NamingException {
        Context c = new InitialContext();
        try {
            return ((DataSource) c.lookup("jdbc/Mydbsource")).getConnection();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
      
}

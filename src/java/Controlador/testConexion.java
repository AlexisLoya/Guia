/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alexl
 */
public class testConexion {
    //Usuario en Mysql
    private String user = "root";
    //Contraseña en Mysql
    private String pass = "";
    //Lugar de coneccion 
    private String host = "localhost";
    //Puerto
    private String port = "3306";
    //Base de datos
    private String db = "asesoria";
    private String className = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://" + host + ":" + port + "/" + db;
    private Connection con;

    public testConexion() {
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            System.err.println("1. Error: " + e);
        } catch (SQLException e) {
            System.err.println("2. Error: " + e);
        }
    }
    
    public Connection getConexion(){
        return con;
    }
    
    
    
}
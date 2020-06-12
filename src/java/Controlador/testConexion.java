/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexl
 */
public class testConexion {

    public static Connection getConnection() throws SQLException {
        try {
            //Usuario en Mysql
            final String user = "root";
            //Contraseña en Mysql
            final String pass = "";
            //Lugar de coneccion 
            String host = "localhost";
            //Puerto
            String port = "3306";
            //Base de datos
            String db = "asesoria";
            final String url = "jdbc:mysql://" + host + ":" + port + "/" + db;
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(testConexion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
     }
    public static void main(String[] args) {
        try {
            Connection con = testConexion.getConnection();
            if (con != null) {
                System.out.println("Jalo");
            } else {
                System.out.println("No salio");
            }
        } catch (SQLException ex) {
            Logger.getLogger(testConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

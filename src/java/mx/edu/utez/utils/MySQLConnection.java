/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alexl
 */
public class MySQLConnection {
    //Lugar de conexión Local
    private static String ipAddress = "localhost";
    //Base de datos
    private static String dbName = "guiabd";
    //Usuario en Mysql
    private static String user = "root";
    //Contraseña en Mysql
    private static String password = "";
    //Puerto
    private static String service = "3306";
    
//    //Lugar de conexión Servidor
//    private static String ipAddress = "192.168.0.1";
//    //Base de datos
//    private static String dbName = "guiabd";
//    //Usuario en Mysql
//    private static String user = "guia";
//    //Contraseña en Mysql
//    private static String password = "Guia123!";
//    //Puerto
//    private static String service = "3306";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(
                "jdbc:mysql://" + ipAddress + ":" + service + "/" + dbName, user, password
        );
    }

    public static void main(String[] args) {
        try {
            Connection con = MySQLConnection.getConnection();
            if (con != null) {
                System.out.println("Funcionaaa");
            } else {
                System.err.println("No funcionaaa :(");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

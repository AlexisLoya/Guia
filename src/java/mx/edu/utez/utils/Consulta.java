/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Controlador.testConexion;
import mx.edu.utez.model.Dao;
import mx.edu.utez.model.DaoInterface;
import java.sql.SQLException;

/**
 *
 * @author alexl
 */
public class Consulta extends MySQLConnection {

    public boolean autentificacion(String email, String password) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        //Importante
        //mySQLRepository("checkAccess");
        try {
            String consulta = "SELECT * FROM `estudiante` where correo = ? and password = ?";
            pst = getConnection().prepareStatement(consulta);
            pst.setString(1, email);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("1. Error: " + e);
        } finally {
            {
                try {
                    if (getConnection() != null) {
                        getConnection().close();
                    }
                    if (pst != null) {
                        pst.close();
                    }
                    if (rs != null) {
                        pst.close();
                    }
                } catch (Exception e) {
                    System.err.println("2. Error: " + e);
                }

            }
            return false;
        }

    }
    
    public static void main(String[] args) {
        Consulta pepe = new Consulta();
        System.out.println(pepe.autentificacion("alexloy27@outlook.com", "123"));
    }
    
}

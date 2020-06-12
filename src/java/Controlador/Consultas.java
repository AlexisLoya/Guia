 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author alexl
 */
public class Consultas extends testConexion {

    public boolean autentificacion(String email, String pass) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String consulta = "select * from email where email = ? and pass = ?";
//            pst = getConexion().prepareStatement(consulta);
            pst.setString(1, email);
            pst.setString(2, pass);
            rs = pst.executeQuery();

            if (rs.absolute(1)) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("1. Error: " + e);
        } finally {
            try {
//                if (getConexion() != null) {
//                    getConexion().close();
//                }
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

    public boolean registro(String email, String pass) {
        PreparedStatement pst = null;
        try {
            String consulta = "insert into persona (email, pass) values (?,?)";
//            pst = getConexion().prepareStatement(consulta);
            pst.setString(1, email);
            pst.setString(2, pass);

            if (pst.executeUpdate() == 1) {
                return true;
            }

        } catch (Exception e) {
            System.err.println("1. Error: " + e);
        } finally {
            try {
//                if (getConexion() != null) {
//                    getConexion().close();
//                }
                if (pst != null) {
                    pst.close();
                }
            } catch (Exception e) {
                System.err.println("2. Error: " + e);
            }

        }

        return false;
    }
    
    

}

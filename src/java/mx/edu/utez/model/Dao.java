/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model;
import Controlador.testConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import mx.edu.utez.utils.MySQLConnection;


/**
 *
 * @author alexl
 */
public class Dao {
    
    
    protected ResourceBundle sqlSentences;
    protected Connection connection;
    protected ResultSet resultSet;
    protected PreparedStatement preparedStatement;
    protected boolean status;
   
    protected Dao() {
        this.status = false;
    }

    
    /**
     * Inicia los recursos necesarios para realizar una actividad en base de datos
     * @param databaseActivity sentencia sql guardada en el archivo MySQLRepository.properties
     */
    protected void mySQLRepository(String databaseActivity) {
        try {
            this.connection = MySQLConnection.getConnection();
            sqlSentences = ResourceBundle.getBundle("SQLRepository");
            this.preparedStatement = this.connection.prepareStatement(sqlSentences.getString(databaseActivity),Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            System.err.println("No se pudieron iniciar los recursos: " + e.getMessage());
        }
    }
    
    /**
     * Cierra todas las conexiones abiertas
     */
    protected void closeAllConnections() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
            if (this.resultSet != null) {
                this.resultSet.close();
            }
            if (this.preparedStatement != null) {
                this.preparedStatement.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexiones: " + e.getMessage());
        }
    }


}

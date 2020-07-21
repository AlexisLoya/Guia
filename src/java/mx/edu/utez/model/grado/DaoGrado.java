/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.grado;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.edu.utez.model.Dao;
import mx.edu.utez.model.DaoInterface;

/**
 *
 * @author alexl
 */
public class DaoGrado extends Dao implements DaoInterface<Grado>{
private final String REPOSITORY = "gradoRepository";
    @Override
    public int add(Grado obj) {
        mySQLRepository(REPOSITORY,"gradoAdd");
        try {
            preparedStatement.setString(1, obj.getNumero());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
        } catch (SQLException ex) {
            Logger.getLogger(DaoGrado.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            closeAllConnections();
        }
        return 0;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Grado obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Grado> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Grado findOne(int id) {
        mySQLRepository(REPOSITORY, "gradoFindOne");
        Grado grado = null;
    try {
        preparedStatement.setInt(1, id);
        resultSet=preparedStatement.executeQuery();
        if(resultSet.next()){
            grado = new Grado(
                    resultSet.getInt("id_grado"),
                    resultSet.getString("numero"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(DaoGrado.class.getName()).log(Level.SEVERE, null, ex);
    } finally{
        closeAllConnections();
    }
    return grado;
    }    
    
}

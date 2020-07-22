/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.carrera;

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
public class DaoCarrera extends Dao implements DaoInterface<Carrera> {

    private final String REPOSITORY = "carreraRepository";

    @Override
    public int add(Carrera obj) {
        //Importante
        mySQLRepository(REPOSITORY, "carreraAdd");
        try {
            preparedStatement.setString(1, obj.getNombre());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoCarrera.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return 0;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Carrera obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Carrera> findAll() {
        mySQLRepository(REPOSITORY,"carreraFindAll");
        ArrayList<Carrera> carreras = new ArrayList();
        try {
            resultSet= preparedStatement.executeQuery();
            DaoCarrera daoCarrea = new DaoCarrera();
            while(resultSet.next()) {
                carreras.add(daoCarrea.findOne(resultSet.getInt("id_carrera")));
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoCarrera.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            closeAllConnections();
        }
        return carreras;
    }

    @Override
    public Carrera findOne(int id) {
        mySQLRepository(REPOSITORY, "carreraFindOne");
        Carrera carrera = null;
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                carrera = new Carrera(
                        resultSet.getInt("id_carrera"),
                        resultSet.getString("nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoCarrera.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            closeAllConnections();
        }
        return carrera;
    }
    public static void main(String[] args) {
        DaoCarrera daoCarrera = new DaoCarrera();
        for (Carrera carrera : daoCarrera.findAll()) {
            System.out.println(carrera);
            
        }
    }
}

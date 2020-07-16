/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.rol;

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
public class DaoRol extends Dao implements DaoInterface<Rol> {
    private final String REPOSITORY = "rolRepository";
    
    @Override
    public int add(Rol obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Rol obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Rol> findAll() {
        mySQLRepository(REPOSITORY, "rolesShowAll");
        ArrayList<Rol> list = new ArrayList<>();
        try {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(
                        new Rol(
                                resultSet.getInt("id_rol"),
                                resultSet.getString("nombre")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoRol.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return list;
    }

    @Override
    public Rol findOne(int id) {
        //importante
        mySQLRepository(REPOSITORY, "rolesShowOne");
        Rol list = null;
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                list = new Rol(
                        resultSet.getInt("id_rol"),
                        resultSet.getString("nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoRol.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return list;
    }
    public boolean setRolEmpleado(int id, int rol){
        mySQLRepository(REPOSITORY, "rolesEmpleadoAdd");
        try {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, rol);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoRol.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            closeAllConnections();
        }       
        return false;
    }
    
    
    public static void main(String[] args) {
        DaoRol rolRepo = new DaoRol();

        for (Rol rol : rolRepo.findAll()) {
            System.out.println("name: " + rol.getName());
        }
    }

}

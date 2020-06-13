/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.empleado;

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
public class DaoEmpleado extends Dao implements DaoInterface<Empleado>{

    @Override
    public int add(Empleado obj) {
        System.out.println(obj);
        //sentenciaSQl
        mySQLRepository("addEmpleado");
        
        try {
            preparedStatement.setInt(1, obj.getPersona().getId());
            preparedStatement.setString(2, obj.getCorreo());
            preparedStatement.setString(3, obj.getPassword());
            resultSet = preparedStatement.getGeneratedKeys();
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(DaoEmpleado.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean update(Empleado obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Empleado> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Empleado findOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

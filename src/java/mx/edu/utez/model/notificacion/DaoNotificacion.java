/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.notificacion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.edu.utez.model.Dao;
import mx.edu.utez.model.DaoInterface;
import mx.edu.utez.model.notificacion.Notificacion;

/**
 *
 * @author alexl
 */
public class DaoNotificacion extends Dao implements DaoInterface<Notificacion>{

    @Override
    public int add(Notificacion obj) {
        //Importante
        mySQLRepository("addNotificacion");
        try {
            preparedStatement.setString(1, obj.getMensaje());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoNotificacion.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeAllConnections();
        }
        return 0;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Notificacion obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Notificacion> findAll() {
         mySQLRepository("showNotificacion");
        ArrayList<Notificacion> list = new ArrayList<>();
        try {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(
                        new Notificacion(
                                resultSet.getInt("id_notificacion"),
                                resultSet.getString("mensaje")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoNotificacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return list;
    }

    @Override
    public Notificacion findOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

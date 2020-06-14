/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.solicitud_asesoria;

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
public class DaoSolicitud_Asesoria extends Dao implements DaoInterface<Solicitud_Asesoria >{

    @Override
    public int add(Solicitud_Asesoria obj) {
        try {
            preparedStatement.setInt(1,obj.getEmpleado().getId());
            preparedStatement.setInt(2,obj.getMateria().getId());
            preparedStatement.setString(3, obj.getTema());
            preparedStatement.setInt(4,obj.getEstudiante().getId());
            preparedStatement.setString(5, obj.getFecha());
            preparedStatement.setInt(6,obj.getTotal());
        } catch (SQLException ex) {
            Logger.getLogger(DaoSolicitud_Asesoria.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean update(Solicitud_Asesoria obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Solicitud_Asesoria> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Solicitud_Asesoria findOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

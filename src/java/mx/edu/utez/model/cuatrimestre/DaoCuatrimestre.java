/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.cuatrimestre;

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
public class DaoCuatrimestre extends Dao implements DaoInterface<Cuatrimestre> {

    @Override
    public int add(Cuatrimestre obj) {
        mySQLRepository("addCuatrimestre");
        try {
            preparedStatement.setInt(1, obj.getPeriodo().getId());
            preparedStatement.setInt(2, obj.getAnio().getId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoCuatrimestre.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean update(Cuatrimestre obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Cuatrimestre> findAll() {
        mySQLRepository("showCuatrimestre");

        try {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(
                        new Cuatrimestre(
                                resultSet.getInt("id_rol"),
                                resultSet.getInt("id_periodo"),
                                resultSet.getInt("id_anio")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoCuatrimestre.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return list;
    }

    @Override
    public Cuatrimestre findOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

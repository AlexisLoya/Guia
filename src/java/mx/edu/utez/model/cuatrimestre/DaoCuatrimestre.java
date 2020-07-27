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
import mx.edu.utez.model.anio.DaoAnio;
import mx.edu.utez.model.periodo.DaoPeriodo;

/**
 *
 * @author alexl
 */
public class DaoCuatrimestre extends Dao implements DaoInterface<Cuatrimestre> {

    private final String REPOSITORY = "cuatrimestreRepository";

    @Override
    public int add(Cuatrimestre obj) {
        mySQLRepository(REPOSITORY, "cuatrimestreAdd");
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
        mySQLRepository(REPOSITORY, "cuatrimestreFindAll");
        ArrayList<Cuatrimestre> list = new ArrayList();
        try {
            resultSet = preparedStatement.executeQuery();
            DaoCuatrimestre daoCuatrimestre = new DaoCuatrimestre();
            while(resultSet.next()){
                list.add(daoCuatrimestre.findOne(resultSet.getInt("id_cuatrimestre")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoCuatrimestre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Cuatrimestre findOne(int id) {
        mySQLRepository(REPOSITORY, "cuatrimestreFindOne");
        Cuatrimestre cuatrimestre = null;
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                DaoPeriodo daoPeriodo = new DaoPeriodo();
                DaoAnio daoAnio = new DaoAnio();
                cuatrimestre = new Cuatrimestre(
                        resultSet.getInt("id_cuatrimestre"),
                        daoPeriodo.findOne(resultSet.getInt("id_periodo")),
                        daoAnio.findOne(resultSet.getInt("id_anio")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoCuatrimestre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cuatrimestre;
    }

    public static void main(String[] args) {
        DaoCuatrimestre dao = new DaoCuatrimestre();
        System.out.println(dao.findOne(2));
        for (Cuatrimestre cuatrimestre : dao.findAll()) {
            System.out.println(cuatrimestre);
        }
    }
}

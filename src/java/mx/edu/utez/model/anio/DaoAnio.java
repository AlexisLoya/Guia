/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.anio;

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
public class DaoAnio extends Dao implements DaoInterface<Anio> {

    private final String REPOSITORY = "anioRepository";

    @Override
    public int add(Anio obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Anio obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Anio> findAll() {
        mySQLRepository(REPOSITORY, "showAnio");
        ArrayList<Anio> list = new ArrayList<>();
        try {
            resultSet = preparedStatement.executeQuery();
            DaoAnio daoAnio = new DaoAnio();
            while (resultSet.next()) {
                list.add(daoAnio.findOne(resultSet.getInt("id_anio")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoAnio.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return list;
    }

    @Override
    public Anio findOne(int id) {
        mySQLRepository(REPOSITORY, "anioFindOne");
        Anio anio = null;
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                anio = new Anio(
                        resultSet.getInt("id_anio"),
                        resultSet.getInt("numero"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoAnio.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            closeAllConnections();
        }
        return anio;
    }

    public static void main(String[] args) {
        DaoAnio anio = new DaoAnio();
        for (Anio anios : anio.findAll()) {
            System.out.println("Año: " + anios.getNumero());
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.dia;

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
public class DaoDia extends Dao implements DaoInterface<Dia> {

    private final String REPOSITORY = "diaRepository";

    @Override
    public int add(Dia obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Dia obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Dia> findAll() {
        mySQLRepository("diaRepository", "diaFindAll");
        ArrayList<Dia> list = new ArrayList<>();
        try {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Dia(
                        resultSet.getInt("id_dia"),
                        resultSet.getString("nombre")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoDia.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return list;
    }

    @Override
    public Dia findOne(int id) {
        mySQLRepository(REPOSITORY,"diaFindOne");
        Dia dia = null;
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
               dia  = new Dia(
                       resultSet.getInt("id_dia"),
                       resultSet.getString("nombre")               
               );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoDia.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            closeAllConnections();
        }
        return dia;
    }

    public static void main(String[] args) {
        DaoDia diarepo = new DaoDia();
        for (Dia dia : diarepo.findAll()) {
            System.out.println("Día:" + dia.getNombre());
        }
    }
}

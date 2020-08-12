/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.encuesta;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.edu.utez.model.Dao;
import mx.edu.utez.model.DaoInterface;
import mx.edu.utez.model.invitado.DaoInvitado;

/**
 *
 * @author alexl
 */
public class DaoEncuesta extends Dao implements DaoInterface<Encuesta> {

    private final String REPOSITORY = "encuestaRepository";

    @Override
    public int add(Encuesta obj) {
        mySQLRepository(REPOSITORY, "encuestaAdd");
        try {
            preparedStatement.setInt(1, obj.getInvitado().getId());
            preparedStatement.setInt(2, obj.getP1());
            preparedStatement.setInt(3, obj.getP2());
            preparedStatement.setInt(4, obj.getP3());
            preparedStatement.setInt(5, obj.getP4());
            preparedStatement.setString(6, obj.getOpinion());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoEncuesta.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean update(Encuesta obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Encuesta> findAll() {
        mySQLRepository(REPOSITORY, "encuestaFindAll");
        ArrayList<Encuesta> list = new ArrayList();
        try {
            resultSet = preparedStatement.executeQuery();
            DaoEncuesta daoEncuesta = new DaoEncuesta();
            while (resultSet.next()) {
                list.add(daoEncuesta.findOne(resultSet.getInt("id_encuesta")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoEncuesta.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return list;
    }

    @Override
    public Encuesta findOne(int id) {
        mySQLRepository(REPOSITORY, "encuestaFindOne");
        Encuesta encuesta = null;
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                DaoInvitado daoInvitado = new DaoInvitado();

                encuesta = new Encuesta(
                        resultSet.getInt("id_encuesta"),
                        daoInvitado.findOne(resultSet.getInt("id_invitado")),
                        resultSet.getInt("p1"),
                        resultSet.getInt("p2"),
                        resultSet.getInt("p3"),
                        resultSet.getInt("p4"),
                        resultSet.getString("opinion"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoEncuesta.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return encuesta;
    }

    public static void main(String[] args) {
        DaoEncuesta daoEncuesta = new DaoEncuesta();
//        System.out.println( daoEncuesta.add(new Encuesta(0, new DaoInvitado().findOne(10), 1, 1, 1, 1, "tortillas")));
        for (Encuesta encuesta : daoEncuesta.findAll()) {
            System.out.println(encuesta);
            
        }
    }
}

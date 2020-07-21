/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.materia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.edu.utez.model.Dao;
import mx.edu.utez.model.DaoInterface;
import mx.edu.utez.model.carrera.Carrera;
import mx.edu.utez.model.carrera.DaoCarrera;
import mx.edu.utez.model.grado.DaoGrado;
import mx.edu.utez.model.grado.Grado;

/**
 *
 * @author alexl
 */
public class DaoMateria extends Dao implements DaoInterface<Materia> {

    private final String REPOSITORY = "materiaRepository";

    @Override
    public int add(Materia obj) {
        mySQLRepository(REPOSITORY, "materiaAdd");
        try {
            preparedStatement.setString(1, obj.getNombre());
            preparedStatement.setString(2, obj.getGrado().getNumero());
            preparedStatement.setString(3, obj.getCarrera().getNombre());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoMateria.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean update(Materia obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Materia> findAll() {
        mySQLRepository(REPOSITORY, "materiaFindAll");
        ArrayList<Materia> list = new ArrayList();
        try {
            resultSet = preparedStatement.executeQuery();
            DaoMateria daoMateria = new DaoMateria();
            while (resultSet.next()) {
                list.add(daoMateria.findOne(resultSet.getInt("id_materia")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoMateria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return list;
    }

    @Override
    public Materia findOne(int id) {
        mySQLRepository(REPOSITORY, "materiaFindOne");
        Materia materia = null;
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                DaoGrado daoGrado = new DaoGrado();

                DaoCarrera daoCarrea = new DaoCarrera();
                materia = new Materia(
                        resultSet.getInt("id_materia"),
                        resultSet.getString("nombre"),
                        daoGrado.findOne(resultSet.getInt("id_grado")),
                        daoCarrea.findOne(resultSet.getInt("id_carrera"))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoMateria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return materia;
    }

    public static void main(String[] args) {
        DaoMateria daoMateria = new DaoMateria();
        ArrayList<Materia> materias = daoMateria.findAll();
        for (Object materia : materias) {
            System.out.println(materia);
        }

    }

}

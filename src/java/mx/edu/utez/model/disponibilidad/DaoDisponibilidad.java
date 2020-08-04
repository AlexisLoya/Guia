/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.disponibilidad;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.edu.utez.model.Dao;
import mx.edu.utez.model.DaoInterface;
import mx.edu.utez.model.cuatrimestre.DaoCuatrimestre;
import mx.edu.utez.model.dia.DaoDia;
import mx.edu.utez.model.empleado.DaoEmpleado;
import mx.edu.utez.model.rango_hora.DaoRango_Hora;

/**
 *
 * @author alexl
 */
public class DaoDisponibilidad extends Dao implements DaoInterface<Disponibilidad> {

    private final String REPOSITORY = "disponibilidadRepository";

    @Override
    public int add(Disponibilidad obj) {
        mySQLRepository(REPOSITORY, "disponibilidadAdd");
        try {
            preparedStatement.setInt(1, obj.getEmpleado().getId());
            preparedStatement.setInt(2, obj.getDia().getId());
            preparedStatement.setInt(3, obj.getRango_hora().getId());
            preparedStatement.setInt(4, obj.getCuatrimestre().getId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoDisponibilidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Disponibilidad obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Disponibilidad> findAll() {
        mySQLRepository(REPOSITORY, "disponibilidadFindAll");
        ArrayList<Disponibilidad> list = new ArrayList();
        try {
            resultSet = preparedStatement.executeQuery();
            DaoDisponibilidad daoDisponibilidad = new DaoDisponibilidad();
            while (resultSet.next()) {
                list.add(daoDisponibilidad.findOne(resultSet.getInt("id_disponibilidad")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoDisponibilidad.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return list;
    }
    public ArrayList<Disponibilidad> findEmpleado(int id ) {
        mySQLRepository(REPOSITORY, "disponibilidadFindEmpleado");
        ArrayList<Disponibilidad> list = new ArrayList();
        try {
            preparedStatement.setInt(1, id);
            
            resultSet = preparedStatement.executeQuery();
            DaoDisponibilidad daoDisponibilidad = new DaoDisponibilidad();
            while (resultSet.next()) {
                list.add(daoDisponibilidad.findOne(resultSet.getInt("id_disponibilidad")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoDisponibilidad.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return list;
    }

    @Override
    public Disponibilidad findOne(int id) {
        mySQLRepository(REPOSITORY, "disponibilidadFindOne");
        Disponibilidad disponibilidad = null;
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                disponibilidad = new Disponibilidad(
                        resultSet.getInt("id_disponibilidad"),
                        new DaoEmpleado().findOne(resultSet.getInt("id_empleado")),
                        new DaoDia().findOne(resultSet.getInt("id_dia")),
                        new DaoRango_Hora().findOne(resultSet.getInt("id_rango_hora")),
                        new DaoCuatrimestre().findOne(resultSet.getInt("id_cuatrimestre"))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoDisponibilidad.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return disponibilidad;
    }

    public static void main(String[] args) {
      
         DaoDisponibilidad daoDisponibilidad = new DaoDisponibilidad();
            System.out.println(daoDisponibilidad.findOne(8));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.invitado;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.edu.utez.model.Dao;
import mx.edu.utez.model.DaoInterface;
import mx.edu.utez.model.estudiante.DaoEstudiante;
import mx.edu.utez.model.estudiante.Estudiante;
import mx.edu.utez.model.materia.DaoMateria;
import mx.edu.utez.model.solicitud_asesoria.DaoSolicitud_Asesoria;
import mx.edu.utez.model.solicitud_asesoria.Solicitud_Asesoria;

/**
 *
 * @author alexl
 */
public class DaoInvitado extends Dao implements DaoInterface<Invitado> {

    private final String REPOSITORY = "invitadoRepository";

    @Override
    public int add(Invitado obj) {
        mySQLRepository(REPOSITORY, "invitadoAdd");
        try {
            preparedStatement.setInt(1, obj.getSolicitud_asesoria().getId());
            preparedStatement.setInt(2, obj.getEstudiante().getId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoInvitado.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean update(Invitado obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Invitado> findAll() {
        mySQLRepository(REPOSITORY, "invitadoFindAll");
        ArrayList<Invitado> list = new ArrayList<>();
        try {
            resultSet = preparedStatement.executeQuery();
            DaoInvitado daoInvitado = new DaoInvitado();
            while (resultSet.next()) {
                list.add(daoInvitado.findOne(resultSet.getInt("id_invitado")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoInvitado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return list;
    }

    @Override
    public Invitado findOne(int id) {
        mySQLRepository(REPOSITORY, "invitadoFindOne");
        Invitado invitado = null;
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                invitado = new Invitado(
                        resultSet.getInt("id_invitado"),
                        new DaoSolicitud_Asesoria().findOne(
                                resultSet.getInt("id_solicitud_asesoria")),
                        new DaoEstudiante().findOne(resultSet.getInt(
                                "id_estudiante"))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoInvitado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return invitado;
    }

    public ArrayList<Invitado> estudiantefindAll(int id) {
        mySQLRepository(REPOSITORY, "invitadoFindEstudiante");
        ArrayList<Invitado> list = new ArrayList<>();
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            DaoInvitado daoInvitado = new DaoInvitado();
            while (resultSet.next()) {
                list.add(daoInvitado.findOne(resultSet.getInt("id_invitado")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoInvitado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return list;
    }

    public boolean estudianteRepetido(int solicitudAsesoriaId, int estudianteId) {
        boolean result = false;
        mySQLRepository(REPOSITORY, "invitadoEstudianteRepetido");
        try {
            preparedStatement.setInt(1, estudianteId);
            preparedStatement.setInt(2, solicitudAsesoriaId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoInvitado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static void main(String[] args) {
//        int id_asesoria = Integer.parseInt("1");
//        Estudiante estudianteAdd = new DaoEstudiante().findOneMatricula("20193TN147");
//        Invitado invitado = new Invitado(0, new DaoSolicitud_Asesoria().findOne(id_asesoria), estudianteAdd);
//        System.out.println(invitado);
//        if (estudianteAdd.getId() != 0) {
//            DaoInvitado daoInvitado = new DaoInvitado();
//            System.out.println(daoInvitado.add(invitado));
//
//        } else {
//            System.out.println("error");
//        }

        DaoInvitado daoInvitado = new DaoInvitado();

        System.out.println(daoInvitado.add(new Invitado(0,new DaoSolicitud_Asesoria().findOne(1), new DaoEstudiante().findOne(2))));
    }
}

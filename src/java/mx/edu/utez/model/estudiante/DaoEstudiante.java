/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.estudiante;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.edu.utez.model.Dao;
import mx.edu.utez.model.DaoInterface;
import mx.edu.utez.model.persona.DaoPersona;
import mx.edu.utez.model.persona.Persona;

/**
 *
 * @author alexl
 */
public class DaoEstudiante extends Dao implements DaoInterface<Estudiante> {

    @Override
    public int add(Estudiante obj) {
        System.out.println(obj);
        //Importante
        mySQLRepository("addEstudiante");
        try {
            preparedStatement.setInt(1, obj.getPersona().getId());
            preparedStatement.setString(2, obj.getMatricula());
            preparedStatement.setString(3, obj.getCorreo());
            preparedStatement.setString(4, obj.getPassword());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoEstudiante.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean update(Estudiante obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Estudiante> findAll() {
        //importante
        mySQLRepository("estudianteFindAll");
        ArrayList<Estudiante> list = new ArrayList();
        try {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                list.add(
                        new Estudiante(
                                resultSet.getInt("id_estudiante"),
                                new Persona(
                                        resultSet.getInt("id_persona"),
                                        resultSet.getInt("id_status"),
                                        resultSet.getString("sexo"),
                                        resultSet.getString("nombre"),
                                        resultSet.getString("materno"),
                                        resultSet.getString("paterno")),
                                resultSet.getString("matricula"),
                                resultSet.getString("correo"),
                                "")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            closeAllConnections();
        }
        return list;
    }

    @Override
    public Estudiante findOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean setInt(int i, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {
        DaoEstudiante estudianteRepo = new DaoEstudiante();
        DaoPersona personaRepo = new DaoPersona();

        Persona persona = new Persona(0, 1, "H", "Chocobo", "materno", "De Jesus");
        persona.setId(personaRepo.add(persona));

        Estudiante estudiante = new Estudiante(0, persona, "sdfsdfsdf", "sdfgsdth", "sdfdfg");
        estudiante.setId(estudianteRepo.add(estudiante));

        System.out.println(estudiante);

    }

}
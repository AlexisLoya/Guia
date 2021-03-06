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

    private final String REPOSITORY = "estudianteRepository";

    @Override
    public int add(Estudiante obj) {
        System.out.println(obj);
        //Importante
        mySQLRepository(REPOSITORY, "estudianteAdd");
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
        mySQLRepository(REPOSITORY,"estudianteUpdate");
        try {
            preparedStatement.setString(1,obj.getCorreo());
            preparedStatement.setString(2,obj.getPersona().getNombre());
            preparedStatement.setString(1,obj.getPersona().getPaterno());
            preparedStatement.setString(1,obj.getPersona().getMaterno());
            preparedStatement.setString(1,obj.getPersona().getSexo());
            resultSet =  preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            closeAllConnections();
        }
        return false;        
    }

    @Override
    public ArrayList<Estudiante> findAll() {
        //importante
        mySQLRepository(REPOSITORY, "estudianteFindAll");
        ArrayList<Estudiante> list = new ArrayList();
        try {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                list.add(
                        new Estudiante(
                                resultSet.getInt("id_estudiante"),
                                new Persona(
                                        resultSet.getInt("id_persona"),
                                        resultSet.getString("nombre"),
                                        resultSet.getString("materno"),
                                        resultSet.getString("paterno"),
                                        resultSet.getString("sexo"),
                                        resultSet.getInt("status")),
                                resultSet.getString("matricula"),
                                resultSet.getString("correo"),
                                "")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return list;
    }

    @Override
    public Estudiante findOne(int id) {
        //importante
        mySQLRepository(REPOSITORY, "estudianteFindOne");
        Estudiante estudiante = null;
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                estudiante = new Estudiante(
                        resultSet.getInt("id_estudiante"),
                        new Persona(
                                resultSet.getInt("id_persona"),
                                resultSet.getString("nombre"),
                                resultSet.getString("paterno"),
                                resultSet.getString("materno"),
                                resultSet.getString("sexo"),
                                resultSet.getInt("status")),
                        resultSet.getString("matricula"),
                        resultSet.getString("correo"),
                        "");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return estudiante;
    }
    
    public Estudiante findOneMatricula(String matricula) {
        //importante
        mySQLRepository(REPOSITORY, "estudianteFindMatricula");
        Estudiante estudiante = null;
        try {
            preparedStatement.setString(1, matricula);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                estudiante = new Estudiante(
                        resultSet.getInt("id_estudiante"),
                        new Persona(
                                resultSet.getInt("id_persona"),
                                resultSet.getString("nombre"),
                                resultSet.getString("paterno"),
                                resultSet.getString("materno"),
                                resultSet.getString("sexo"),
                                resultSet.getInt("status")),
                        resultSet.getString("matricula"),
                        resultSet.getString("correo"),
                        "");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return estudiante;
    }

    public int autentificacion(String email, String password) {
        //consulta
        mySQLRepository(REPOSITORY, "checkAccess");
        try {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
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

    public boolean check(String email, String password) {
        //consulta
        mySQLRepository(REPOSITORY, "checkAccess");
        try {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return false;
    }
    
    public boolean checkEmail(String email) {
        //consulta
        mySQLRepository(REPOSITORY, "checkEmail");
        try {
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return false;
    }

     public boolean updatePassword(String password,String email) {
        mySQLRepository(REPOSITORY, "estudianteChange");
        try {
            preparedStatement.setString(1,password);
            preparedStatement.setString(2,email);
            status = preparedStatement.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(DaoEstudiante.class.getName()).log(Level.SEVERE, null, ex);
            status = false;
        } finally {
            closeAllConnections();
        }
        return status;
    }
    
    
    public static void main(String[] args) {
        DaoEstudiante estudianteRepo = new DaoEstudiante();
        DaoPersona personaRepo = new DaoPersona();
        Estudiante estudiante = estudianteRepo.findOne(1);
        System.out.println(new DaoEstudiante().updatePassword("3333", "20193tn151@utez.edu.mx"));

    }

}

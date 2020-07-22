/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.persona;

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
public class DaoPersona extends Dao implements DaoInterface<Persona> {

    private final String REPOSITORY = "personaRepository";

    @Override
    public int add(Persona obj) {
        System.out.println(obj);
        //Importante
        mySQLRepository(REPOSITORY, "personaAdd");
        try {
            preparedStatement.setString(1, obj.getNombre());
            preparedStatement.setString(2, obj.getPaterno());
            preparedStatement.setString(3, obj.getMaterno());
            preparedStatement.setString(4, obj.getSexo());
            preparedStatement.setInt(5, obj.getStatus());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoPersona.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean update(Persona obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Persona> findAll() {
        //Consulta
        mySQLRepository(REPOSITORY, "personaFindAll");
        ArrayList<Persona> list = new ArrayList();
        try {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(
                        new Persona(
                                resultSet.getInt("id_persona"),
                                resultSet.getString("nombre"),
                                resultSet.getString("paterno"),
                                resultSet.getString("materno"),
                                resultSet.getString("sexo"),
                                resultSet.getInt("status"))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoPersona.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return list;
    }

    @Override
    public Persona findOne(int id) {
        //Consulta
        mySQLRepository(REPOSITORY, "personaFindOne");
        Persona persona = null;
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                persona = new Persona(
                        resultSet.getInt("id_persona"),
                        resultSet.getString("nombre"),
                        resultSet.getString("paterno"),
                        resultSet.getString("materno"),
                        resultSet.getString("sexo"),
                        resultSet.getInt("status")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoPersona.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            closeAllConnections();
        }
        return persona;
    }

    public String gender(int id) {
        //consulta
        mySQLRepository(REPOSITORY, "personaGender");
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getString("sexo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoPersona.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            closeAllConnections();
        }
               return "";
    }

    public static void main(String[] args) {
        DaoPersona daoPersona = new DaoPersona();
        System.out.println("Sexo: "+daoPersona.gender(2));
        Persona persona = daoPersona.findOne(2);
        System.out.println(persona.getSexo());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.grupo_estudiante;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.edu.utez.model.Dao;
import mx.edu.utez.model.DaoInterface;
import mx.edu.utez.model.estudiante.DaoEstudiante;
import mx.edu.utez.model.grupo.DaoGrupo;

/**
 *
 * @author alexl
 */
public class DaoGrupo_Estudiante extends Dao implements DaoInterface<Grupo_Estudiante> {

    private final String REPOSITORY = "grupo_estudianteRepository";

    @Override
    public int add(Grupo_Estudiante obj) {
        mySQLRepository(REPOSITORY, "grupo_estudianteAdd");

        try {
            preparedStatement.setInt(1, obj.getId());
            preparedStatement.setInt(2, obj.getEstudiante().getId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoGrupo_Estudiante.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean update(Grupo_Estudiante obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Grupo_Estudiante> findAll() {
        mySQLRepository(REPOSITORY, "grupo_empleadoFindAll");
        ArrayList<Grupo_Estudiante> list = new ArrayList();
        try {
            resultSet = preparedStatement.executeQuery();
            DaoGrupo_Estudiante daoGrupo_Estudiante = new DaoGrupo_Estudiante();
            while(resultSet.next()){
                list.add(daoGrupo_Estudiante.findOne(resultSet.getInt("id_grupo_estudiante")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoGrupo_Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            closeAllConnections();
        }
        return list;
    }

    @Override
    public Grupo_Estudiante findOne(int id) {
        mySQLRepository(REPOSITORY, "grupo_estudianteFindOne");
        Grupo_Estudiante grupo_Estudiante = null;
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                DaoGrupo daoGrupo = new DaoGrupo();
                DaoEstudiante daoEstudiante = new DaoEstudiante();
                grupo_Estudiante = new Grupo_Estudiante(
                        resultSet.getInt("id_grupo_estudiante"),
                        daoGrupo.findOne(resultSet.getInt("id_grupo")),
                        daoEstudiante.findOne(resultSet.getInt("id_estudiante")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoGrupo_Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return grupo_Estudiante;
    }
    
    
     public ArrayList<Grupo_Estudiante> grupoEstudianteAll(int id) {
        mySQLRepository(REPOSITORY, "grupoFindAllEstudiantes");
        ArrayList<Grupo_Estudiante> list = new ArrayList<>();
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            DaoGrupo_Estudiante daoGrupo = new DaoGrupo_Estudiante();
            while (resultSet.next()) {
                list.add(daoGrupo.findOne(resultSet.getInt("id_grupo_estudiante")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoGrupo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return list;
    }
     public boolean updatePassword(String password,String email) {
        mySQLRepository(REPOSITORY, "empleadoChange");
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
        DaoGrupo_Estudiante daoGrupo_Estudiante = new DaoGrupo_Estudiante();
        for (Grupo_Estudiante grupo_Estudiante : daoGrupo_Estudiante.findAll()) {
            System.out.println(grupo_Estudiante);
        }
    }
}

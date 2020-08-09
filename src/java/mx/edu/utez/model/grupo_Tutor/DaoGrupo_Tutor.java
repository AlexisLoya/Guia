/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.grupo_Tutor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.edu.utez.model.Dao;
import mx.edu.utez.model.DaoInterface;
import mx.edu.utez.model.empleado.DaoEmpleado;
import mx.edu.utez.model.grupo.DaoGrupo;

/**
 *
 * @author alexl
 */
public class DaoGrupo_Tutor extends Dao implements DaoInterface<Grupo_Tutor> {
    private final String REPOSITORY = "grupo_tutorRepository";

    @Override
    public int add(Grupo_Tutor obj) {
        try {
            mySQLRepository(REPOSITORY, "grupo_tutorAdd");
            preparedStatement.setInt(1, obj.getGrupo().getId());
            preparedStatement.setInt(2, obj.getEmpleado().getId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoGrupo_Tutor.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean update(Grupo_Tutor obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Grupo_Tutor> findAll() {
        mySQLRepository(REPOSITORY,"grupo_tutorFindAll");
        ArrayList<Grupo_Tutor> list = new ArrayList();
        try {
            resultSet=preparedStatement.executeQuery();
            DaoGrupo_Tutor daoGrupo_Tutor = new DaoGrupo_Tutor();
            while(resultSet.next()){
                list.add(daoGrupo_Tutor.findOne(resultSet.getInt("id_grupo_tutor")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoGrupo_Tutor.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeAllConnections();
        }
        return list;
    }

    @Override
    public Grupo_Tutor findOne(int id) {
        mySQLRepository(REPOSITORY, "grupo_tutorFindOne");
        Grupo_Tutor grupo_Tutor = null;
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                DaoGrupo daoGrupo = new DaoGrupo();
                DaoEmpleado daoEmpleado = new DaoEmpleado();
                grupo_Tutor = new Grupo_Tutor(
                        resultSet.getInt("id_grupo_tutor"),
                        daoGrupo.findOne(resultSet.getInt("id_grupo")),
                        daoEmpleado.findOne(resultSet.getInt("id_empleado")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoGrupo_Tutor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return grupo_Tutor;
    } 

    public static void main(String[] args) {
        DaoGrupo_Tutor daoGrupo_Tutor = new DaoGrupo_Tutor();
        for (Grupo_Tutor grupo_Tutor : daoGrupo_Tutor.findAll()) {
            System.out.println(grupo_Tutor);
        }
    }
    
    
}

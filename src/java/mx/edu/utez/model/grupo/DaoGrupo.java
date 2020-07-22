/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.grupo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.edu.utez.model.Dao;
import mx.edu.utez.model.DaoInterface;
import mx.edu.utez.model.carrera.Carrera;
import mx.edu.utez.model.carrera.DaoCarrera;
import mx.edu.utez.model.cuatrimestre.Cuatrimestre;
import mx.edu.utez.model.cuatrimestre.DaoCuatrimestre;
import mx.edu.utez.model.grado.DaoGrado;
import mx.edu.utez.model.grado.Grado;

/**
 *
 * @author alexl
 */
public class DaoGrupo extends Dao implements DaoInterface<Grupo> {

    private final String REPOSITORY = "grupoRepository";

    @Override
    public int add(Grupo obj) {
        mySQLRepository(REPOSITORY, "grupoAdd");
        try {
            preparedStatement.setInt(1, obj.getCarrera().getId());
            preparedStatement.setInt(2, obj.getCarrera().getId());
            preparedStatement.setInt(3, obj.getCuatrimestre().getId());
            preparedStatement.setInt(4, obj.getGrado().getId());
            preparedStatement.setString(5, obj.getLetra());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoGrupo.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean update(Grupo obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Grupo> findAll() {
        mySQLRepository(REPOSITORY, "grupoFindAll");
        ArrayList<Grupo> list = new ArrayList<>();
        try {
            resultSet = preparedStatement.executeQuery();
            DaoGrupo daoGrupo = new DaoGrupo();
            while (resultSet.next()) {
                list.add(daoGrupo.findOne(resultSet.getInt("id_grupo")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoGrupo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return list;
    }

    @Override
    public Grupo findOne(int id) {
        mySQLRepository(REPOSITORY, "grupoFindOne");
        Grupo grupo = null;
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                DaoCarrera daoCarrera = new DaoCarrera();
                DaoCuatrimestre daoCuatrimestre = new DaoCuatrimestre();
                DaoGrado daoGrado = new DaoGrado();
                grupo = new Grupo(
                        resultSet.getInt("id_grupo"),
                        daoCarrera.findOne(resultSet.getInt("id_carrera")),
                        daoCuatrimestre.findOne(resultSet.getInt("id_cuatrimestre")),
                        daoGrado.findOne(resultSet.getInt("id_grado")),
                        resultSet.getString("letra")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoGrupo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return grupo;
    }

    public int  grupoEstudiante (int id_grupo, int id_estudiante){
        mySQLRepository(REPOSITORY,"grupoEstudiante");
        try {
            preparedStatement.setInt(1, id_grupo);
            preparedStatement.setInt(2, id_estudiante);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    
    public static void main(String[] args) {
        DaoGrupo daogrupo = new DaoGrupo();
        System.out.println(daogrupo.grupoEstudiante(2,2));
        

    }
}

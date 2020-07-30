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
import mx.edu.utez.model.empleado.DaoEmpleado;
import mx.edu.utez.model.empleado.Empleado;
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

    //tabla Empleado-Materia    
    public int addEmpleado(Materia obj, Empleado obj2) {
        mySQLRepository(REPOSITORY, "empleadoMateriaAdd");
        try {
            preparedStatement.setInt(1, obj.getId());
            preparedStatement.setInt(2, obj2.getId());
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

    public boolean deleteEmpleado(int id) {
        mySQLRepository(REPOSITORY, "empleadoMateriaDelete");

        try {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DaoMateria.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }

    public ArrayList<EmpleadoMateria> findAllEmpleado(int id) {
        mySQLRepository(REPOSITORY, "empleadoMateriaFindAll");
        ArrayList<EmpleadoMateria> list = new ArrayList();
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            DaoMateria daoMateria = new DaoMateria();
            while (resultSet.next()) {
                list.add(daoMateria.findOneEmpleado(resultSet.getInt("id_materia")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoMateria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return list;
    }

    public EmpleadoMateria findOneEmpleado(int id) {
        mySQLRepository(REPOSITORY, "empleadoMateriaFindOne");
        EmpleadoMateria empleadoMateria = null;
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                DaoMateria daoMateria = new DaoMateria();
                DaoEmpleado daoEmpleado = new DaoEmpleado();
                empleadoMateria = new EmpleadoMateria(
                        resultSet.getInt("id_empleado_materia"),
                        daoMateria.findOne(resultSet.getInt("id_materia")),
                        daoEmpleado.findOne(resultSet.getInt("id_empleado"))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoMateria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return empleadoMateria;
    }

    public static void main(String[] args) {
        DaoMateria daoMateria = new DaoMateria();
        
        DaoEmpleado daoEmpleado = new DaoEmpleado();
        Empleado empleado = daoEmpleado.findOne(3);
                
        System.out.println(daoMateria.deleteEmpleado(16));
    }

}

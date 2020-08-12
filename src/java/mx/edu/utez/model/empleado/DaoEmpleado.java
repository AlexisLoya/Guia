/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.empleado;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.edu.utez.model.Dao;
import mx.edu.utez.model.DaoInterface;
import mx.edu.utez.model.persona.Persona;
import mx.edu.utez.model.rol.Rol;

/**
 *
 * @author alexl
 */
public class DaoEmpleado extends Dao implements DaoInterface<Empleado> {

    private final String REPOSITORY = "empleadoRepository";

    @Override
    public int add(Empleado obj) {
        System.out.println(obj);
        //sentenciaSQl
        mySQLRepository(REPOSITORY, "empleadoAdd");

        try {
            preparedStatement.setInt(1, obj.getPersona().getId());
            preparedStatement.setString(2, obj.getCorreo());
            preparedStatement.setString(3, obj.getPassword());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoEmpleado.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean update(Empleado obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Empleado> findAll() {
        mySQLRepository(REPOSITORY, "empleadoFindAll");
        ArrayList<Empleado> list = new ArrayList();
        try {
            resultSet = preparedStatement.executeQuery();
            DaoEmpleado daoEmpleado = new DaoEmpleado();
            while (resultSet.next()) {
                list.add(daoEmpleado.findOne(resultSet.getInt("e.id_empleado")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return list;
    }

    @Override
    public Empleado findOne(int id) {
        mySQLRepository(REPOSITORY, "empleadoFindOne");
        Empleado empleado = null;
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            empleado = new Empleado();
            empleado.setPersona(new Persona());
            ArrayList<Rol> roles = new ArrayList<>();
            while (resultSet.next()) {
                empleado.getPersona().setNombre(resultSet.getString("nombre"));
                empleado.getPersona().setMaterno(resultSet.getString("materno"));
                empleado.getPersona().setPaterno(resultSet.getString("paterno"));
                empleado.getPersona().setId(resultSet.getInt("id_persona"));
                empleado.getPersona().setSexo(resultSet.getString("sexo"));
                empleado.getPersona().setStatus(resultSet.getInt("status"));
                empleado.setId(id);
                empleado.setCorreo(resultSet.getString("correo"));
                empleado.setPassword(resultSet.getString("password"));
                roles.add(new Rol(resultSet.getInt("id_rol"), resultSet.getString("nombre_rol")));
            }
            empleado.setRoles(roles);
            return empleado;
        } catch (SQLException ex) {
            Logger.getLogger(DaoEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Integer checkAccess(String email, String password) {
        mySQLRepository(REPOSITORY, "checkAccess");

        try {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            closeAllConnections();
        }
        return null;
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
            Logger.getLogger(DaoEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return 0;
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
            Logger.getLogger(DaoEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }

        return false;
    }

    public boolean updatePassword(String password,String email) {
        mySQLRepository(REPOSITORY, "empleadoChange");
        try {
            preparedStatement.setString(1,password);
            preparedStatement.setString(2,email);
            status = preparedStatement.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(DaoEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            status = false;
        } finally {
            closeAllConnections();
        }
        return status;
    }
    
    public static void main(String[] args) {
        DaoEmpleado daoEmpleado = new DaoEmpleado();
        for (Empleado empleado : daoEmpleado.findAll()) {
            System.out.println(empleado);
        }
    }
}

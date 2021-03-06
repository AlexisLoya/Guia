/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.solicitud_asesoria;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.edu.utez.model.Dao;
import mx.edu.utez.model.DaoInterface;
import mx.edu.utez.model.disponibilidad.DaoDisponibilidad;
import mx.edu.utez.model.disponibilidad.Disponibilidad;
import mx.edu.utez.model.empleado.DaoEmpleado;
import mx.edu.utez.model.estudiante.DaoEstudiante;
import mx.edu.utez.model.estudiante.Estudiante;
import mx.edu.utez.model.materia.DaoMateria;

/**
 *
 * @author alexl
 */
public class DaoSolicitud_Asesoria extends Dao implements DaoInterface<Solicitud_Asesoria> {

    private final String REPOSITORY = "solicitud_asesoriaRepository";

    @Override
    public int add(Solicitud_Asesoria obj) {
        mySQLRepository(REPOSITORY, "solicitud_asesoriaAdd");
        try {
            preparedStatement.setInt(1, obj.getEmpleado().getId());
            preparedStatement.setInt(2, obj.getMateria().getId());
            preparedStatement.setString(3, obj.getTema());
            preparedStatement.setString(4, obj.getFecha());
            preparedStatement.setString(5, obj.getHora());
            preparedStatement.setInt(6, obj.getTotal());
            preparedStatement.setInt(7, obj.getStatus());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoSolicitud_Asesoria.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean update(Solicitud_Asesoria obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Solicitud_Asesoria> findAll() {
        mySQLRepository(REPOSITORY, "solicitud_asesoriaFindAll");
        ArrayList<Solicitud_Asesoria> list = new ArrayList();
        DaoSolicitud_Asesoria asesoria = new DaoSolicitud_Asesoria();
        try {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(asesoria.findOne(resultSet.getInt("id_solicitud_asesoria")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoSolicitud_Asesoria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return list;
    }

    @Override
    public Solicitud_Asesoria findOne(int id) {
        mySQLRepository(REPOSITORY, "solicitud_asesoriaFindOne");
        Solicitud_Asesoria asesoria = null;
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            asesoria = new Solicitud_Asesoria();
            DaoEmpleado daoEmpleado = new DaoEmpleado();
            DaoMateria daoMateria = new DaoMateria();
            DaoEstudiante daoEstudiante = new DaoEstudiante();
            ArrayList<Estudiante> estudiantes = new ArrayList<>();
            while (resultSet.next()) {
                asesoria.setId(resultSet.getInt("id_solicitud_asesoria"));
                asesoria.setEmpleado(daoEmpleado.findOne(resultSet.getInt("id_empleado")));
                asesoria.setMateria(daoMateria.findOne(resultSet.getInt("id_materia")));
                asesoria.setTema(resultSet.getString("tema"));
                asesoria.setFecha(resultSet.getString("fecha"));
                asesoria.setHora(resultSet.getString("hora"));
                asesoria.setTotal(resultSet.getInt("total"));
                asesoria.setStatus(resultSet.getInt("status"));
                estudiantes.add(daoEstudiante.findOne(resultSet.getInt("id_estudiante")));
            }
            asesoria.setEstudiante(estudiantes);
        } catch (SQLException ex) {
            Logger.getLogger(DaoSolicitud_Asesoria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return asesoria;
    }

    public String fechaActual() {

        Calendar year = Calendar.getInstance();
        Date fecha = new Date(year.getTimeInMillis());
        java.util.Date date = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        return "" + sdf.format(date);
    }

    public ArrayList<Solicitud_Asesoria> alumnoAsesoria(int id) {
        mySQLRepository(REPOSITORY, "solicitud_asesoriaEstudiante");
        ArrayList<Solicitud_Asesoria> list = new ArrayList();
        DaoSolicitud_Asesoria asesoria = new DaoSolicitud_Asesoria();
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(asesoria.findOne(resultSet.getInt("id_solicitud_asesoria")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoSolicitud_Asesoria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return list;
    }

    public ArrayList<Solicitud_Asesoria> empleadoAsesoria(int id) {
        mySQLRepository(REPOSITORY, "solicitud_asesoriaEmpleado");
        ArrayList<Solicitud_Asesoria> list = new ArrayList();
        DaoSolicitud_Asesoria asesoria = new DaoSolicitud_Asesoria();
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(asesoria.findOne(resultSet.getInt("id_solicitud_asesoria")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoSolicitud_Asesoria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return list;
    }

    public ArrayList<Solicitud_Asesoria> empleadoShowAsesoria(int id) {
        mySQLRepository(REPOSITORY, "solicitud_asesoriaShowEmpleado");
        ArrayList<Solicitud_Asesoria> list = new ArrayList();
        DaoSolicitud_Asesoria asesoria = new DaoSolicitud_Asesoria();
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(asesoria.findOne(resultSet.getInt("id_solicitud_asesoria")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoSolicitud_Asesoria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return list;
    }

    public boolean aceptarAsesoria(int id) {
        mySQLRepository(REPOSITORY, "solicitud_asesoriaAceptar");
        try {
            preparedStatement.setInt(1, id);
            status = preparedStatement.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(DaoSolicitud_Asesoria.class.getName()).log(Level.SEVERE, null, ex);
            status = false;
        } finally {
            closeAllConnections();
        }
        return status;
    }

    public boolean rechazarAsesoria(int id) {
        mySQLRepository(REPOSITORY, "solicitud_asesoriaRechazada");
        try {
            preparedStatement.setInt(1, id);
            status = preparedStatement.executeUpdate() == 0;
        } catch (SQLException ex) {
            Logger.getLogger(DaoSolicitud_Asesoria.class.getName()).log(Level.SEVERE, null, ex);
            status = false;
        } finally {
            closeAllConnections();
        }
        return status;
    }

    public Date SumarRestarDiasFecha(Date fecha, int dias){//Añadir dias a una fecha
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        
        return (Date) calendar.getTime();
        
    }
    
      public String ObtenerFecha(){//Obtener la fecha actual
        
        Calendar year = Calendar.getInstance();
        Date fecha = new Date(year.getTimeInMillis());
        java.util.Date date = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
        
        return "" + sdf.format(date);
    }
    
    public static void main(String[] args) {
        DaoSolicitud_Asesoria daoAsesoria = new DaoSolicitud_Asesoria();

//        Añadirla a la base de datos 
       Solicitud_Asesoria object = daoAsesoria.findOne(2);
            System.out.println(object);
        
       
    }
    

}

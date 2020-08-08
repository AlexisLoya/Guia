/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.clave;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.edu.utez.model.Dao;
import mx.edu.utez.model.DaoInterface;

/**
 *
 * @author alexl
 */
public class DaoClave extends Dao implements DaoInterface<Clave> {

    private final String REPOSITORY = "claveRepository";

    @Override
    public int add(Clave obj) {
        mySQLRepository(REPOSITORY, "claveAdd");

        try {
            preparedStatement.setString(1, obj.getClave());
            preparedStatement.setInt(2, obj.getStatus());
            preparedStatement.setString(3, obj.getCaducidad());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
              
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoClave.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return 0;
    }

    @Override
    public boolean delete(int id) {
        mySQLRepository(REPOSITORY, "claveDelete");
        try {
            preparedStatement.setInt(1, id);
            status = preparedStatement.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(DaoClave.class.getName()).log(Level.SEVERE, null, ex);
            status = false;
        } finally {
            closeAllConnections();
        }
        return status;
    }

    @Override
    public boolean update(Clave obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Clave> findAll() {
        mySQLRepository(REPOSITORY,"claveFindAll");
        ArrayList<Clave> list = new ArrayList();
        try {
            resultSet = preparedStatement.executeQuery();
            DaoClave daoClave = new DaoClave();
            while(resultSet.next()){
                list.add(daoClave.findOne(resultSet.getInt("id_clave")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoClave.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            closeAllConnections();
        }
       return list;
    }

    @Override
    public Clave findOne(int id) {
        mySQLRepository(REPOSITORY,"claveFindOne");
        Clave clave = null;
        try {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                clave = new Clave(
                        resultSet.getInt("id_clave"),
                        resultSet.getString("clave"),
                        resultSet.getInt("status"),
                        resultSet.getString("caducidad"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoClave.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            closeAllConnections();
        }
        return clave;
    }

    public String generator(int longitud) {
        String[] characters = "ab1cdefg2hi3jkl3mn9opq6rst4uv7wx8yz0".split("");
        String code = "";
        Random rand = new Random();
        for (int i = 0; i < longitud; i++) {
            code += characters[rand.nextInt(characters.length - 1)];
        }
        return code;
    }

    public Clave searchOne(String codigo) {
        mySQLRepository(REPOSITORY, "claveFindClave");
        Clave clave = null;
        try {
            preparedStatement.setString(1, codigo);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                clave = new Clave(
                        resultSet.getInt("id_clave"),
                        resultSet.getString("clave"),
                        resultSet.getInt("status"),
                        resultSet.getString("caducidad"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoClave.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }

        return clave;
    }

    public boolean checkClave(Clave obj){
        mySQLRepository(REPOSITORY, "claveValidate");
        try {
            preparedStatement.setInt(1, obj.getId());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Calendar fechaActual = Calendar.getInstance();
                Calendar fechaCaducidad = Calendar.getInstance();
                String fecha = resultSet.getString("caducidad");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                try {
                    fechaCaducidad.setTime(sdf.parse(fecha));
                } catch (ParseException ex) {
                    Logger.getLogger(DaoClave.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Fecha Caducidad: "+fechaCaducidad.getTime());
                System.out.println("Fecha Actual: "+fechaActual.getTime());
                
                if (fechaCaducidad.getTimeInMillis() >= fechaActual.getTimeInMillis()) {
                    return true;
                } 
            } else {
                System.out.println("Error");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoClave.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeAllConnections();
        }
        return false;
    }

    public String dateCaducidad(int cantidad) {
      
        Calendar year = Calendar.getInstance();
        year.add(Calendar.DATE, cantidad);
        Date fecha = new Date(year.getTimeInMillis());
        java.util.Date date = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return "" + sdf.format(fecha);
    }

    public static void main(String[] args) {
        DaoClave dao = new DaoClave();
        Calendar fechaActual = Calendar.getInstance();
        Calendar fecha = Calendar.getInstance();
        fecha.set(Calendar.MONTH, Calendar.FEBRUARY);
        Clave clave = new Clave(2, dao.generator(7), 1, "2020-08-4 10:33:44");

        
        System.out.println(dao.dateCaducidad(1));
        
//        
    }

}

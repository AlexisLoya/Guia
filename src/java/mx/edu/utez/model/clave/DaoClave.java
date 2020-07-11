/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.clave;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

    @Override
    public int add(Clave obj) {
        mySQLRepository("claveRepository", "claveAdd");
        
        try {
            preparedStatement.setString(1, obj.getClave());
            preparedStatement.setInt(2, obj.getStatus());
            preparedStatement.setString(3, obj.getRol());
            preparedStatement.setString(4, obj.getCaducidad());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Clave obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Clave> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Clave findOne(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public String dateCaducidad(String cantidad) {
        int numDias = Integer.parseInt(cantidad);
        Calendar fecha = Calendar.getInstance();
        fecha.add(Calendar.DATE, numDias);
        Date code = new Date(fecha.getTimeInMillis());
        return ""+code;
    }

    public static void main(String[] args) {
        DaoClave dao = new DaoClave();
        Clave clave = new Clave(0, dao.generator(7), 1, "empleado", dao.dateCaducidad("7"));
        dao.add(clave);
        System.out.println(clave.getClave());
        System.out.println(clave.getStatus());
        System.out.println(clave.getRol());
        System.out.println(clave.getCaducidad());
    }

}

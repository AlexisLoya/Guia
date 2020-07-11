/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.clave;

import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author alexl
 */
public class Clave {
    private int id;
    private String clave;
    private int status;
    private String rol;
    private String caducidad;

    public Clave() {
    }
    
    public Clave(int id, String clave, int status, String rol, String caducidad) {
        this.id = id;
        this.clave = clave;
        this.status = status;
        this.rol = rol;
        this.caducidad = caducidad;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(String caducidad) {
        this.caducidad = caducidad;
    }
    
}

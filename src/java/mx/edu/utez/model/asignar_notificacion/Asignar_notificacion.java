/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.asignar_notificacion;

import mx.edu.utez.model.notificacion.Notificacion;
import mx.edu.utez.model.persona.Persona;

/**
 *
 * @author alexl
 */
public class Asignar_notificacion {
    private int id;
    private Notificacion notificacion;
    private Persona persona;    

    public Asignar_notificacion() {
    }

    public Asignar_notificacion(int id, Notificacion notificacion, Persona persona) {
        this.id = id;
        this.notificacion = notificacion;
        this.persona = persona;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Notificacion getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(Notificacion notificacion) {
        this.notificacion = notificacion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    
    
}

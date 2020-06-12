/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.invitado;

import mx.edu.utez.model.estudiante.Estudiante;
import mx.edu.utez.model.solicitud_asesoria.Solicitud_Asesoria;

/**
 *
 * @author alexl
 */
public class Invitado {
    private int id;
    private Solicitud_Asesoria solicitud_asesoria;
    private Estudiante estudiante;

    public Invitado() {
    }

    public Invitado(int id, Solicitud_Asesoria solicitud_asesoria, Estudiante estudiante) {
        this.id = id;
        this.solicitud_asesoria = solicitud_asesoria;
        this.estudiante = estudiante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Solicitud_Asesoria getSolicitud_asesoria() {
        return solicitud_asesoria;
    }

    public void setSolicitud_asesoria(Solicitud_Asesoria solicitud_asesoria) {
        this.solicitud_asesoria = solicitud_asesoria;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    
    
}

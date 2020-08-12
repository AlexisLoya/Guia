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
    private int asistencia;

    public Invitado() {
    }

    public Invitado(int id, Solicitud_Asesoria solicitud_asesoria, Estudiante estudiante, int asistencia) {
        this.id = id;
        this.solicitud_asesoria = solicitud_asesoria;
        this.estudiante = estudiante;
        this.asistencia = asistencia;
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

    public int getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(int asistencia) {
        this.asistencia = asistencia;
    }

    @Override
    public String toString() {
        return "Invitado{" + "id=" + id + ", solicitud_asesoria=" + solicitud_asesoria + ", estudiante=" + estudiante + ", asistencia=" + asistencia + '}';
    }
    
}

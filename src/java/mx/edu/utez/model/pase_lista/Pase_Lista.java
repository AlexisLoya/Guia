/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.pase_lista;

import mx.edu.utez.model.invitado.Invitado;
import mx.edu.utez.model.solicitud_asesoria.Solicitud_Asesoria;

/**
 *
 * @author alexl
 */
public class Pase_Lista {
    private int id;
    private Invitado invitado;
    private int asistencia;

    public Pase_Lista() {
        
    }

    public Pase_Lista(int id, Invitado invitado, int asistencia) {
        this.id = id;
        this.invitado = invitado;
        this.asistencia = asistencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Invitado getInvitado() {
        return invitado;
    }

    public void setInvitado(Invitado invitado) {
        this.invitado = invitado;
    }

    public int getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(int asistencia) {
        this.asistencia = asistencia;
    }

    @Override
    public String toString() {
        return "Pase_Lista{" + "id=" + id + ", invitado=" + invitado + ", asistencia=" + asistencia + '}';
    }

    
    
    
    
}

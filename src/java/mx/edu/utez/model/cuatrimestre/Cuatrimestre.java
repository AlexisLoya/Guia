/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.cuatrimestre;

import mx.edu.utez.model.anio.Anio;
import mx.edu.utez.model.periodo.Periodo;

/**
 *
 * @author alexl
 */
public class Cuatrimestre {
    private int id;
    private Periodo periodo;    
    private Anio anio;
    private int status;

    public Cuatrimestre() {
    }

    public Cuatrimestre(int id, Periodo periodo, Anio anio, int status) {
        this.id = id;
        this.periodo = periodo;
        this.anio = anio;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Anio getAnio() {
        return anio;
    }

    public void setAnio(Anio anio) {
        this.anio = anio;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Cuatrimestre{" + "id=" + id + ", periodo=" + periodo + ", anio=" + anio + ", status=" + status + '}';
    }

    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.disponibilidad;

import mx.edu.utez.model.cuatrimestre.Cuatrimestre;
import mx.edu.utez.model.dia.Dia;
import mx.edu.utez.model.empleado.Empleado;
import mx.edu.utez.model.rango_hora.Rango_Hora;

/**
 *
 * @author alexl
 */
public class Disponibilidad {
    private int id;
    private Empleado empleado;
    private Dia dia;
    private Rango_Hora rango_hora;
    private Cuatrimestre cuatrimestre;

    public Disponibilidad() {
    }

    public Disponibilidad(int id, Empleado empleado, Dia dia, Rango_Hora rango_hora, Cuatrimestre cuatrimestre) {
        this.id = id;
        this.empleado = empleado;
        this.dia = dia;
        this.rango_hora = rango_hora;
        this.cuatrimestre = cuatrimestre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    public Rango_Hora getRango_hora() {
        return rango_hora;
    }

    public void setRango_hora(Rango_Hora rango_hora) {
        this.rango_hora = rango_hora;
    }

    public Cuatrimestre getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(Cuatrimestre cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

    @Override
    public String toString() {
        return "Disponibilidad{" + "id=" + id + ", empleado=" + empleado + ", dia=" + dia + ", rango_hora=" + rango_hora + ", cuatrimestre=" + cuatrimestre + '}';
    }
    
    
    
}

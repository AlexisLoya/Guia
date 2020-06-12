/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.solicitud_asesoria;

import mx.edu.utez.model.empleado.Empleado;
import mx.edu.utez.model.estudiante.Estudiante;
import mx.edu.utez.model.materia.Materia;

/**
 *
 * @author alexl
 */
public class Solicitud_Asesoria {
    private int id;
    private Empleado empleado;
    private Materia materia;
    private String tema;
    private Estudiante estudiante;
    private String fecha;
    private int total;

    public Solicitud_Asesoria() {
    }

    public Solicitud_Asesoria(int id, Empleado empleado, Materia materia, String tema, Estudiante estudiante, String fecha, int total) {
        this.id = id;
        this.empleado = empleado;
        this.materia = materia;
        this.tema = tema;
        this.estudiante = estudiante;
        this.fecha = fecha;
        this.total = total;
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

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
}

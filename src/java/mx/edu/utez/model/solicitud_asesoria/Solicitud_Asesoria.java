/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.solicitud_asesoria;

import java.util.ArrayList;
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
    private ArrayList<Estudiante> estudiante;
    private String fecha;
    private String hora;
    private int total;
    private int status;

    public Solicitud_Asesoria() {
    }

    public Solicitud_Asesoria(int id, Empleado empleado, Materia materia, String tema, ArrayList<Estudiante> estudiante, String fecha, String hora, int total, int status) {
        this.id = id;
        this.empleado = empleado;
        this.materia = materia;
        this.tema = tema;
        this.estudiante = estudiante;
        this.fecha = fecha;
        this.hora = hora;
        this.total = total;
        this.status = status;
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

    public ArrayList<Estudiante> getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(ArrayList<Estudiante> estudiante) {
        this.estudiante = estudiante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Solicitud_Asesoria{" + "id=" + id + ", empleado=" + empleado + ", materia=" + materia + ", tema=" + tema + ", estudiante=" + estudiante + ", fecha=" + fecha + ", hora=" + hora + ", total=" + total + ", status=" + status + '}';
    }
}
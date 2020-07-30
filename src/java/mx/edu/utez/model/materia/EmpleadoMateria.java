/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.materia;

import mx.edu.utez.model.empleado.Empleado;

/**
 *
 * @author alexl
 */
public class EmpleadoMateria {
    private int id;
    private Materia materia;
    private Empleado empleado;

    public EmpleadoMateria() {
    }

    public EmpleadoMateria(int id, Materia materia, Empleado empleado) {
        this.id = id;
        this.materia = materia;
        this.empleado = empleado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public String toString() {
        return "EmpleadoMateria{" + "id=" + id + ", materia=" + materia + ", empleado=" + empleado + '}';
    }
    
    
    
}
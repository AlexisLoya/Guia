/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.grupo_Tutor;

import mx.edu.utez.model.empleado.Empleado;
import mx.edu.utez.model.grupo.Grupo;

/**
 *
 * @author alexl
 */
public class Grupo_Tutor {
    private int id;
    private Grupo grupo;
    private Empleado empleado;

    public Grupo_Tutor() {
    }

    public Grupo_Tutor(int id, Grupo grupo, Empleado empleado) {
        this.id = id;
        this.grupo = grupo;
        this.empleado = empleado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.grupo_estudiante;

import mx.edu.utez.model.estudiante.Estudiante;
import mx.edu.utez.model.grupo.Grupo;

/**
 *
 * @author alexl
 */
public class Grupo_Estudiante {
    private int id;
    private Grupo grupo;
    private Estudiante estudiante; 

    public Grupo_Estudiante() {
    }

    public Grupo_Estudiante(int id, Grupo grupo, Estudiante estudiante) {
        this.id = id;
        this.grupo = grupo;
        this.estudiante = estudiante;
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

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public String toString() {
        return "Grupo_Estudiante{" + "id=" + id + ", grupo=" + grupo + ", estudiante=" + estudiante + '}';
    }
    
    
}

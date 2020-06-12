/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.materia;

import mx.edu.utez.model.carrera.Carrera;
import mx.edu.utez.model.grado.Grado;

/**
 *
 * @author alexl
 */
public class Materia {
    private int id;
    private String nombre;
    private Grado grado;
    private Carrera carrera;

    public Materia() {
    }

    public Materia(int id, String nombre, Grado grado, Carrera carrera) {
        this.id = id;
        this.nombre = nombre;
        this.grado = grado;
        this.carrera = carrera;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
    
    
    
}

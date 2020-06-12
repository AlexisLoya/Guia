/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.grupo;

import mx.edu.utez.model.carrera.Carrera;
import mx.edu.utez.model.cuatrimestre.Cuatrimestre;
import mx.edu.utez.model.grado.Grado;

/**
 *
 * @author alexl
 */
public class Grupo {
    private int id;
    private Carrera carrera;    
    private Cuatrimestre cuatrimestre;
    private Grado grado;
    private String letra;

    public Grupo() {
    }

    public Grupo(int id, Carrera carrera, Cuatrimestre cuatrimestre, Grado grado, String letra) {
        this.id = id;
        this.carrera = carrera;
        this.cuatrimestre = cuatrimestre;
        this.grado = grado;
        this.letra = letra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Cuatrimestre getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(Cuatrimestre cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }
    
}

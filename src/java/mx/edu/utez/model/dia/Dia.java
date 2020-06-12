/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.dia;

/**
 *
 * @author alexl
 */
public class Dia {
    private int dia;
    private String nombre;

    public Dia() {
    }

    
    public Dia(int dia, String nombre) {
        this.dia = dia;
        this.nombre = nombre;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}

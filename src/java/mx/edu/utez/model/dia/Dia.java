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
    private int id;
    private String nombre;

    public Dia() {
    }

    
    public Dia(int id_dia, String nombre) {
        this.id = id_dia;
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "Dia{" + "id=" + id + ", nombre=" + nombre + '}';
    }
    
}

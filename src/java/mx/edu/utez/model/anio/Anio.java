/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.anio;

/**
 *
 * @author alexl
 */
public class Anio {

    private int id;
    private int numero;

    public Anio() {
    }

    public Anio(int id, int numero) {
        this.id = id;
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Anio{" + "id=" + id + ", numero=" + numero + '}';
    }

}

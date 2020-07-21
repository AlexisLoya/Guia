/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.rango_hora;

/**
 *
 * @author alexl
 */
public class Rango_Hora {
    private int id;
    private String inicio;
    private String fin;

    public Rango_Hora() {
    }

    public Rango_Hora(int id, String inicio, String fin) {
        this.id = id;
        this.inicio = inicio;
        this.fin = fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    @Override
    public String toString() {
        return "Rango_Hora{" + "id=" + id + ", inicio=" + inicio + ", fin=" + fin + '}';
    }
    
    
}

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
    private int Rango_Hora;
    private String inicio;
    private String fin;

    public Rango_Hora() {
    }

    public Rango_Hora(int Rango_Hora, String inicio, String fin) {
        this.Rango_Hora = Rango_Hora;
        this.inicio = inicio;
        this.fin = fin;
    }

    public int getRango_Hora() {
        return Rango_Hora;
    }

    public void setRango_Hora(int Rango_Hora) {
        this.Rango_Hora = Rango_Hora;
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
    
    
}

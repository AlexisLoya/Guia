/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.encuesta;

import mx.edu.utez.model.invitado.Invitado;
import mx.edu.utez.model.pase_lista.Pase_Lista;

/**
 *
 * @author alexl
 */
public class Encuesta {

    private int id;
    private Pase_Lista pase_lista;
    private String opinion;
    private int completada;

    public Encuesta() {
    }

    public Encuesta(int id, Pase_Lista pase_lista, String opinion, int completada) {
        this.id = id;
        this.pase_lista = pase_lista;
        this.opinion = opinion;
        this.completada = completada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pase_Lista getPase_lista() {
        return pase_lista;
    }

    public void setPase_lista(Pase_Lista pase_lista) {
        this.pase_lista = pase_lista;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public int getCompletada() {
        return completada;
    }

    public void setCompletada(int completada) {
        this.completada = completada;
    }

}

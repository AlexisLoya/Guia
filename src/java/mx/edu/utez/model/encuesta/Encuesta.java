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
    private Invitado invitado;
    private int p1;
    private int p2;
    private int p3;
    private int p4;
    private String opinion;

    public Encuesta() {
    }

    public Encuesta(int id, Invitado invitado, int p1, int p2, int p3, int p4, String opinion) {
        this.id = id;
        this.invitado = invitado;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.opinion = opinion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Invitado getInvitado() {
        return invitado;
    }

    public void setInvitado(Invitado invitado) {
        this.invitado = invitado;
    }

    public int getP1() {
        return p1;
    }

    public void setP1(int p1) {
        this.p1 = p1;
    }

    public int getP2() {
        return p2;
    }

    public void setP2(int p2) {
        this.p2 = p2;
    }

    public int getP3() {
        return p3;
    }

    public void setP3(int p3) {
        this.p3 = p3;
    }

    public int getP4() {
        return p4;
    }

    public void setP4(int p4) {
        this.p4 = p4;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    @Override
    public String toString() {
        return "Encuesta{" + "id=" + id + ", invitado=" + invitado + ", p1=" + p1 + ", p2=" + p2 + ", p3=" + p3 + ", p4=" + p4 + ", opinion=" + opinion + '}';
    }

   
}

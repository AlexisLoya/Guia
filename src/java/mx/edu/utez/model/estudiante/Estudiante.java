/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.estudiante;

import java.util.ArrayList;
import mx.edu.utez.model.persona.Persona;

/**
 *
 * @author alexl
 */
public class Estudiante {

    private int id;
    private Persona persona;
    private String matricula;
    private String correo;
    private String password;

    public Estudiante() {
    }

    public Estudiante(int id, Persona persona, String matricula, String correo, String password) {
        this.id = id;
        this.persona = persona;
        this.matricula = matricula;
        this.correo = correo;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Estudiante{" + "id=" + id + ", persona=" + persona + ", matricula=" + matricula + ", correo=" + correo + ", password=" + password + '}';
    }

    
    
}

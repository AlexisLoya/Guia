/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.usuario;

import mx.edu.utez.model.persona.Persona;

/**
 *
 * @author alexl
 */
public class Usuario {
    private String correo;
    private String password;
    private Persona persona;
    private String rol;

    public Usuario() {
    }

    public Usuario(String correo, String password, Persona persona, String rol) {
        this.correo = correo;
        this.password = password;
        this.persona = persona;
        this.rol = rol;
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
     
      public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}

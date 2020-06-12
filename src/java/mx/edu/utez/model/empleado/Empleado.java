/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.empleado;
import java.util.ArrayList;
import mx.edu.utez.model.persona.Persona;
import mx.edu.utez.model.rol.Rol;

/**
 *
 * @author alexl
 */
public class Empleado {

    private int id;
    private Persona persona;
    private String correo;
    private String password;
    private ArrayList<Rol> roles;

    public Empleado() {
    }

    public Empleado(int id, Persona persona, String correo, String password, ArrayList<Rol> roles) {
        this.id = id;
        this.persona = persona;
        this.correo = correo;
        this.password = password;
        this.roles = roles;
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

    public ArrayList<Rol> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Rol> roles) {
        this.roles = roles;
    }
    
    

}

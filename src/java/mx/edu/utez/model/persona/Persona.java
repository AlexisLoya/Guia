/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.persona;

/**
 *
 * @author alexl
 */
public class Persona {

    private int id;
    private String nombre;
    private String paterno;
    private String materno;
    private String sexo;
    private int status;


    public Persona() {
    }

    public Persona(int id, String nombre, String paterno, String materno,String sexo,int status ) {
        this.id = id;
        this.status = status;
        this.sexo = sexo;
        this.nombre = nombre;
        this.materno = materno;
        this.paterno = paterno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", status=" + status + ", sexo=" + sexo + ", nombre=" + nombre + ", materno=" + materno + ", paterno=" + paterno + '}';
    }
    
    

}

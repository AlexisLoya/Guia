/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model.rol;

/**
 *
 * @author alexl
 */
public class Rol {
    private int id;
    private String name;

    public Rol() {
    }

    public Rol(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Rol{" + "id=" + id + ", name=" + name + '}';
    }
    
    
    
}

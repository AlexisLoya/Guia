/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.utez.model;
import java.util.ArrayList;

/**
 *
 * @author alexl
 */
public interface DaoInterface <T>{
    int add(T obj);
    boolean delete(int id);
    boolean update(T obj);
    ArrayList<T> findAll();
    T findOne(int id);
}

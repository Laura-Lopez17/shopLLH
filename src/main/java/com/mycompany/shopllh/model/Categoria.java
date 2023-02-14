/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shopllh.model;

/**
 *
 * @author laura
 */
public class Categoria {
 //es una clase JavaBean, esta formada por sus atributos,getters y setters

    String nombre;
    int id;
    boolean activo;

    public Categoria(String nombre, int id, boolean activo) {
        this.nombre = nombre;
        this.id = id;
        this.activo = activo;
    }

    public Categoria() {
        this.nombre="";
        this.id=-1;
        this.activo=true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    
}

package com.mycompany.shopllh.model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author laura
 */
import java.util.List;

public class Tienda implements Cloneable {

    int id;
    String nombre;
    String direccion;
    String descripcion;
    String coordenadas;
    boolean activo;
    private Categoria categoria;

    public Tienda(int id, String nombre, String direccion, String descripcion, String coordenadas, boolean activo, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.coordenadas = coordenadas;
        this.activo = activo;
    }
    
    public Tienda() {

    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public Object clone() {
        Tienda t = new Tienda();
        t.setId(id);
        t.setDireccion(direccion);
        t.setActivo(activo);
        t.setNombre(nombre);
        t.setCategoria(categoria);
        t.setDescripcion(descripcion);
        t.setCoordenadas (coordenadas);

        return t;
    }

}

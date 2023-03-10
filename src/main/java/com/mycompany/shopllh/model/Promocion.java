/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shopllh.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author laura
 */
public class Promocion implements Comparable<Promocion>, Cloneable { //comparable le permite ser comparada con otras instancias de promocion y cloneable crear una copia de la instancia de esta clase.
//es una clase JavaBean, esta formada por sus atributos,getters y setters
    private String nombre;
    
    private int id;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    public Promocion() {
        this.id = -1;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Promocion o) {
        if (o == null) {
            return -1;
        } else {
            if (o.getId() == this.id) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    @Override
    protected Object clone() {
        Promocion l = new Promocion();
      
        l.id = this.id;
        l.nombre = this.nombre;
        l.descripcion = this.descripcion;
        l.fechaInicio = this.fechaInicio;
        l.fechaFin = this.fechaFin;
        return l;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }


}
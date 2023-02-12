/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.shopllh.controllers;

import jakarta.annotation.PostConstruct;
import java.util.List;

/**
 *
 * @author laura
 */
    public interface IController<T> {

    @PostConstruct
    public abstract void load();

    public String create();

    public String add();
    public String cancel();
    public T getSelected();

    public void setSelected(T element);
    //public void setSelected(T selected);
    public String preEdit();
    public List<T> getItems();

}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shopllh.controllers;

import com.mycompany.shopllh.model.Categoria;
import com.mycompany.shopllh.model.Tienda;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author laura
 */
@Named
@ApplicationScoped
public class TiendaController extends AbstractController<Tienda> {

     @Inject
    private CategoriaController categoriaController;
   

    public TiendaController() {
        super(Tienda::new);
    }

    @Override
    public Tienda getSelected() {
        return super.getSelected();
    }

    @PostConstruct
    public void load() {
        this.create();
        this.getSelected().setNombre("Asador Alberto");
        this.getSelected().setDireccion("C/Arbol,78");
        this.getSelected().setDescripcion("Asador");
        this.getSelected().setCoordenadas("-52.4675, 48.6691");
        this.getSelected().setActivo(true);
        this.getSelected().setCategoria(this.categoriaController.getItems().get(0));
        this.getSelected().setId(-1);
        this.add();
    }

    public String remove() {
        if (this.getSelected() != null) {
            this.repositorio.remove(this.getSelected());
        }
        return "remove";
    }

    @Override
    public String preEdit() {
        return "edit";
    }

    public void selectedChange(ValueChangeEvent event) {
        this.setSelected((Tienda) event.getNewValue());
    }

    public Tienda getTiendaById(int id) {
        Tienda t = null;
        Optional<Tienda> element = this.getItems().stream().filter(item -> {
            return item.getId() == id;
        }).findFirst();
        if (element.isPresent()) {
            t = element.get();
        }
        return t;
    }

    @Override
    public String add() {
        //si es nuevo
        if (this.getSelected() != null) {
            if (this.getSelected().getId() == -1) {
                this.getSelected().setId(this.repositorio.getAll().size() + 1);
                this.repositorio.create(this.getSelected());
            } else {

                this.repositorio.update(this.getSelected());
            }
        }
        return "sucess";
    }

}

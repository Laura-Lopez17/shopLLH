/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shopllh.controllers;

import com.mycompany.shopllh.controller.repository.IRepository;
import com.mycompany.shopllh.model.Promocion;
import com.mycompany.shopllh.model.Promocion;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

/**
 *
 * @author laura
 */
@Named//estas anotaciones significan que estan disponibles en toda la aplicación y que se puede acceder a ellas por su nombre
@ApplicationScoped
public class PromocionController extends AbstractController<Promocion> {

    @Inject
    TiendaController tiendacontroller;

    public PromocionController() {
        super(Promocion::new);
        //this.load();
    }

    @Override
    @PostConstruct
    public void load() { //este metodo crea promociones y las agrega a un repositorio
        this.create();
        //this.getSelected().setId(-1);
        this.getSelected().setNombre("2x1");
        this.getSelected().setDescripcion("Tiene 2x1");
        this.getSelected().setFechaInicio(new Date());
        this.getSelected().setFechaFin(new Date());
        this.add();

    }

    public String remove() {//elimina una promocion del repositorio
        if (this.getSelected() != null) {
            if (this.tiendacontroller.getItems().stream().filter(item -> {
                return item.getPromocion() == this.getSelected();
            }).count() == 0) {
                this.repositorio.remove(this.getSelected());
                return "remove";
            } else {
                return "";
            }

        }
        //se tiene que poner el error
        return "";

    }

    @Override
    public String preEdit() {//para entrar a la pagina de editar

        return "edit";
    }

    @Override
    public String add() {//para añadir promociones o actualizarlas
        //si es nuevo
        if (this.getSelected().getId() == -1) {
            this.getSelected().setId(this.repositorio.getAll().size() + 1);
            this.repositorio.create(this.getSelected());
        } else {
            this.repositorio.update(this.getSelected());
            //si ya existe

        }
        return "sucess";
    }
}

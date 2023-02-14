package com.mycompany.shopllh.controllers;

import com.mycompany.shopllh.controller.repository.IRepository;
import com.mycompany.shopllh.model.Categoria;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author laura
 */
@Named//estas anotaciones significan que estan disponibles en toda la aplicación y que se puede acceder a ellas por su nombre
@ApplicationScoped
public class CategoriaController extends AbstractController<Categoria> {
    //@Inject
  //  TiendaController tiendacontroller;
    public CategoriaController() {
        super(Categoria::new);
        //this.load();
    }

    @Override
    @PostConstruct
    public void load() { //este metodo crea categorias y las agrega a un repositorio
        this.create();
        this.getSelected().setActivo(true);
        //this.getSelected().setId(-1);
        this.getSelected().setNombre("Restaurantes");
        this.add();

        this.create();
        this.getSelected().setActivo(true);
        //this.getSelected().setId(-2);
        this.getSelected().setNombre("Zapaterias");
        this.add();

        this.create();
        this.getSelected().setActivo(false);
        //this.getSelected().setId(3);
        this.getSelected().setNombre("Peluquerías");
        this.add();
    }

    
public String remove() { //elimina una categoria del repositorio
        if (this.getSelected() != null) {
            /*if (this.tiendacontroller.getItems().stream().filter(item -> {
                return item.getCategoria()== this.getSelected();
            }).count() == 0) {*/
                this.repositorio.remove(this.getSelected());
                return "remove";
           /* } else {
                return "";
            }*/

        }
        //se tiene que poner el error
        return "";

    }

    @Override
    public String preEdit() { //para entrar a la pagina de editar

        return "edit";
    }

    @Override
    public String add() { //para añadir categorias o actualizarlas
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

    public IRepository<Categoria> getRepositorio() { //get y set para almacenar las categorias
        return repositorio;
    }

    public void setRepositorio(IRepository<Categoria> repositorio) {
        this.repositorio = repositorio;
    }
    
}
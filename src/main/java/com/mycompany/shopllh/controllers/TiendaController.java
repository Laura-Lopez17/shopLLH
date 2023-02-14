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
@Named//estas anotaciones significan que estan disponibles en toda la aplicación y que se puede acceder a ellas por su nombre
@ApplicationScoped
public class TiendaController extends AbstractController<Tienda> {

    @Inject
    private CategoriaController categoriaController;
    @Inject
    private PromocionController promocionController;
    
    public TiendaController() {
        super(Tienda::new);
    }

    @Override
    public Tienda getSelected() {
        return super.getSelected();
    }

    @PostConstruct
    public void load() { //este metodo crea tiendas y las agrega a un repositorio
        this.create();
        this.getSelected().setNombre("Asador Alberto");
        this.getSelected().setDireccion("C/Arbol,78");
        this.getSelected().setDescripcion("Asador");
        this.getSelected().setCoordenadas("-52.4675, 48.6691");
        this.getSelected().setActivo(true);
        this.getSelected().setCategoria(this.categoriaController.getItems().get(0));
        this.getSelected().setPromocion(this.promocionController.getItems().get(0));
        this.getSelected().setId(-1);
        this.add();
    }

    public String remove() {//elimina una promocion del repositorio
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
    public String preEdit() {//para entrar a la pagina de editar
        return "edit";
    }

    public void selectedChange(ValueChangeEvent event) { //se activa cuando se produce un cambio en la selección de tiendas
        this.setSelected((Tienda) event.getNewValue());
    }

    public Tienda getTiendaById(int id) {//evuelve una tienda dada su id.
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
    public String add() { //para añadir promociones o actualizarlas
        //si es nuevo
        if (this.getSelected() != null) {
            if (this.getSelected().getId() <= 0) {
                this.getSelected().setId(this.repositorio.getAll().size() + 1);
                this.repositorio.create(this.getSelected());
            } else {

                this.repositorio.update(this.getSelected());
            }
        }
        return "sucess";
    }

}

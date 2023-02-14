/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shopllh.views;

import com.mycompany.shopllh.controllers.CategoriaController;
import com.mycompany.shopllh.controllers.PromocionController;
import com.mycompany.shopllh.controllers.TiendaController;
import com.mycompany.shopllh.model.Categoria;
import com.mycompany.shopllh.model.Promocion;
import com.mycompany.shopllh.model.Tienda;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Administrador
 */
@Named //estas anotaciones significan que estan disponibles en toda la aplicación y que se puede acceder a ellas por su nombre
@ViewScoped
public class TiendaEditView implements Serializable {

    @Inject                                         //inyeccion de dependencias para inyectar los controladores y obtener los datos necesarios
    private PromocionController promocionController;
    @Inject
    private CategoriaController categoriaController;
    @Inject
    private TiendaController tiendaController;
    private Tienda tienda;

    @PostConstruct
    public void init() { //para establecer el objeto tienda que se va a editar, si no hay una seleccionada se crea una nueva,si no clona la tienda que habia seleccionada
        if (this.tiendaController.getSelected() == null) {
            this.tienda = new Tienda();
        } else {
            //se clona por si se da a cancelar
            this.tienda = (Tienda) this.tiendaController.getSelected();
        }
    }

    public List<Categoria> getCategorias() { //getters y setters de categoria para obtener y darle una categoria a tienda 
        return this.categoriaController.getItems();
    }

    public void setCategoria(String item) {
        Optional<Categoria> consulta = this.categoriaController.getItems().stream().filter(element -> {
            return element.getNombre().equals(item);
        }).findFirst();
        if (!consulta.isEmpty()) {
            this.tienda.setCategoria(consulta.get());
        }
    }

    public String getCategoria() {
        if (this.tienda.getCategoria() != null) {
            return this.tienda.getCategoria().getNombre();
        } else {
            return "";
        }
    }

    public List<Promocion> getPromociones() { //getters y setter de promocion para obtener y darle promociones a una tienda
        return this.promocionController.getItems();
    }

    public String getPromocion() {
        if (this.tienda.getPromocion() != null) {
            return this.tienda.getPromocion().getNombre();
        } else {
            return "";
        }

    }

    public void setPromocion(String item) {
        Optional<Promocion> consulta = this.promocionController.getItems().stream().filter(element -> {
            return element.getNombre().equals(item);
        }).findFirst();
        if (!consulta.isEmpty()) {
            this.tienda.setPromocion(consulta.get());
        }
    }

    /**
     * @return the tienda
     */
    public Tienda getTienda() {
        return tienda;
    }

    /**
     * @param tienda the tienda to set
     */
    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public String add() { //cuando se guarda la edicion de una tienda, si se está editando una existente, se obtiene la original y se actualizan los datos. Si no,se crea una tienda.
        Tienda t;
        if (this.tienda != null) {
            if (this.tienda.getId() > 0) {
                //se obtiene el original
                t = this.tiendaController.getTiendaById(tienda.getId());
                t.setDireccion(tienda.getDireccion());
                t.setActivo(tienda.isActivo());
                t.setCategoria(tienda.getCategoria());
                t.setNombre(tienda.getNombre());
                t.setDescripcion(tienda.getDescripcion());
                t.setCoordenadas(tienda.getCoordenadas());
                t.setPromocion(tienda.getPromocion());
                this.tiendaController.setSelected(null);
                return "sucess";
            } else {
                //nuevo
                this.tiendaController.setSelected(this.tienda);
                this.tiendaController.add();
                return "sucess";
            }
        } else {
            this.tiendaController.setSelected(null);
            return "failed";
        }
    }

    public String preEdit() {
        return "edit";
    }

    public String create() { //crea una nueva instancia de la clase Tienda y la establece como la tienda seleccionada en el controlador
        this.tiendaController.setSelected(null);
        this.tienda = new Tienda();
        return "create";
    }

    public String cancel() {
        this.tienda = null;
        return "sucess";
    }

}

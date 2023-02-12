/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.shopllh.views;

import com.mycompany.shopllh.controllers.CategoriaController;
import com.mycompany.shopllh.controllers.TiendaController;
import com.mycompany.shopllh.model.Categoria;
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
@Named
@ViewScoped
public class TiendaEditView implements Serializable {

    @Inject
    private CategoriaController categoriaController;
    @Inject
    private TiendaController tiendaController;
    private Tienda tienda;

    @PostConstruct
    public void init() {
        if (this.tiendaController.getSelected() == null) {
            this.tienda = new Tienda();
        } else {
            //se clona por si se da a cancelar
            this.tienda = (Tienda) this.tiendaController.getSelected();
        }
    }

    public List<Categoria> getCategorias() {
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

    public String add() {
        Tienda t;
        if (this.tienda != null) {
            if (this.tienda.getId() != -1) {
                //se obtiene el original
                t = this.tiendaController.getTiendaById(tienda.getId());
                t.setDireccion(tienda.getDireccion());
                t.setActivo(tienda.isActivo());
                t.setCategoria(tienda.getCategoria());
                t.setNombre(tienda.getNombre());
                t.setDescripcion(tienda.getDescripcion());
                t.setCoordenadas(tienda.getCoordenadas());
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

    public String create() {
        this.tiendaController.setSelected(null);
        this.tienda = new Tienda();
        return "create";
    }

    public String cancel() {
        this.tienda = null;
        return "sucess";
    }

}

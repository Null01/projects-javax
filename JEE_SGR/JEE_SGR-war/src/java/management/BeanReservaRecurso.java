/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import entities.Articulo;
import entities.Recurso;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author duran
 */
public class BeanReservaRecurso {

    private List<Recurso> listaRecursos;
    private List<Articulo> listaArticulos;
    /**
     * Parametros necesarios para realizar el prestamos de algun recurso en
     * especifico.
     */
    private List<String> optionsSelected;
    private Recurso recursoSelected;
    private Articulo articuloSelected;
    private Date dateSelected;

    public BeanReservaRecurso() {
        listaArticulos = new ArrayList<>();
        listaRecursos = new ArrayList<>();
    }

    @PostConstruct
    public void initialize() {

    }

    public List<Recurso> getListaRecursos() {
        return listaRecursos;
    }

    public void setListaRecursos(List<Recurso> listaRecursos) {
        this.listaRecursos = listaRecursos;
    }

    public List<Articulo> getListaArticulos() {
        return listaArticulos;
    }

    public void setListaArticulos(List<Articulo> listaArticulos) {
        this.listaArticulos = listaArticulos;
    }

    public List<String> getOptionsSelected() {
        return optionsSelected;
    }

    public void setOptionsSelected(List<String> optionsSelected) {
        this.optionsSelected = optionsSelected;
    }

    public Recurso getRecursoSelected() {
        return recursoSelected;
    }

    public void setRecursoSelected(Recurso recursoSelected) {
        this.recursoSelected = recursoSelected;
    }

    public Articulo getArticuloSelected() {
        return articuloSelected;
    }

    public void setArticuloSelected(Articulo articuloSelected) {
        this.articuloSelected = articuloSelected;
    }

    public Date getDateSelected() {
        return dateSelected;
    }

    public void setDateSelected(Date dateSelected) {
        this.dateSelected = dateSelected;
    }
}

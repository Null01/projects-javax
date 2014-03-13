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
import javax.ejb.EJB;
import session.ArticuloFacadeLocal;

/**
 *
 * @author AGarcia
 */
public class BeanSolicitudPrestamo {

    @EJB
    private ArticuloFacadeLocal articuloFacade;

    private List<Articulo> articleList;

    /**
     * Parametros necesarios para realizar el prestamos de algun recurso en
     * especifico.
     */
    private Recurso resourceSelected;
    private Articulo articleSelected;
    private Date dateSelected;
    private List<String> optionsSelected;

    public BeanSolicitudPrestamo() {
    }

    @PostConstruct
    public void initialize() {
        if (resourceSelected != null) {
            System.out.println("resource enable");
            List<Articulo> articlesEnable = articuloFacade.getArticlesEnable(resourceSelected);
            articleList = new ArrayList<>(articlesEnable);
        } else {
            articleList = new ArrayList<>();
        }
    }

    public ArticuloFacadeLocal getArticuloFacade() {
        return articuloFacade;
    }

    public void setArticuloFacade(ArticuloFacadeLocal articuloFacade) {
        this.articuloFacade = articuloFacade;
    }

    public List<Articulo> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Articulo> articleList) {
        this.articleList = articleList;
    }

    public Recurso getResourceSelected() {
        return resourceSelected;
    }

    public void setResourceSelected(Recurso resourceSelected) {
        this.resourceSelected = resourceSelected;
    }

    public Articulo getArticleSelected() {
        return articleSelected;
    }

    public void setArticleSelected(Articulo articleSelected) {
        this.articleSelected = articleSelected;
    }

    public Date getDateSelected() {
        return dateSelected;
    }

    public void setDateSelected(Date dateSelected) {
        this.dateSelected = dateSelected;
    }

    public List<String> getOptionsSelected() {
        return optionsSelected;
    }

    public void setOptionsSelected(List<String> optionsSelected) {
        this.optionsSelected = optionsSelected;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import entities.Articulo;
import entities.Recurso;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import org.primefaces.event.RowEditEvent;
import session.ArticuloFacadeLocal;
import session.RecursoFacadeLocal;

/**
 *
 * @author duran
 */
public class BeanAdminRecurso implements Serializable {

    @EJB
    private RecursoFacadeLocal recursoFacade;
    @EJB
    private ArticuloFacadeLocal articuloFacade;

    // Consulta de recursos
    private Recurso resourceSelected;
    private List<Recurso> listaRecursos;

    // Consulta de Articulos a partir de un recurso.
    private List<Articulo> articleList;

    // Creacion de un recurso
    private Recurso recurso;

    public BeanAdminRecurso() {
    }

    @PostConstruct
    public void initialize() {
        List<Recurso> findAll = recursoFacade.findAll();
        if (findAll != null) {
            listaRecursos = new ArrayList<>(findAll);
        }
    }

    /**
     * Cantidad de Articulos que pertenecen a un recurso en especifico.
     *
     * @param recurso
     * @return cantidad de articulos.
     */
    public String countArticle(Recurso recurso) {
        if (recurso != null) {
            int countArticlesEnable = articuloFacade.countArticle(recurso);
            if (countArticlesEnable != 0) {
                return countArticlesEnable + "";
            }
        }
        return "<Empty>";
    }

    /**
     * Cantidad de Articulos disponibles, pertenecientes a un recurso en
     * especifico.
     *
     * @param recurso
     * @return cantidad de articulos disponibles
     */
    public String countArticleEnable(Recurso recurso) {
        if (recurso != null) {
            int countArticlesEnable = articuloFacade.countArticlesEnable(recurso);
            if (countArticlesEnable != 0) {
                return countArticlesEnable + "";
            }
        }
        return "<Empty>";
    }

    public void onClickEditAccept(RowEditEvent event) {

    }

    public void onClickEditCancel(RowEditEvent event) {

    }

    public void onClickShowDialog(Recurso r) {
        setResourceSelected(r);
        if (resourceSelected != null) {
            articleList = articuloFacade.getArticlesEnable(resourceSelected);
            if (articleList == null) {
                articleList = new ArrayList<>();
            }
        } else {
            articleList = new ArrayList<>();
        }
    }

    public void onClickPreCreateResource(ActionEvent event) {

    }

    public void onClickCreateResource(ActionEvent event) {
        System.out.println("create");
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public List<Recurso> getListaRecursos() {
        return listaRecursos;
    }

    public void setListaRecursos(List<Recurso> listaRecursos) {
        this.listaRecursos = listaRecursos;
    }

    public Recurso getResourceSelected() {
        return resourceSelected;
    }

    public void setResourceSelected(Recurso resourceSelected) {
        this.resourceSelected = resourceSelected;
    }

    public List<Articulo> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Articulo> articleList) {
        this.articleList = articleList;
    }

}

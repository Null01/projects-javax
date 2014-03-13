/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import entities.Recurso;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import session.ArticuloFacadeLocal;
import session.RecursoFacadeLocal;

/**
 *
 * @author duran
 */
public class BeanAdminPrestamo implements Serializable {

    @EJB
    private RecursoFacadeLocal recursoFacade;
    @EJB
    private ArticuloFacadeLocal articuloFacade;

    private List<Recurso> listaRecursos;
    private Recurso recursoSelected;

    public BeanAdminPrestamo() {
    }

    @PostConstruct
    public void initialize() {
        List<Recurso> findAll = recursoFacade.findAll();
        if (findAll != null) {
            listaRecursos = new ArrayList<>(findAll);
        }
    }

    public String countArticle(Recurso recurso) {
        if (recurso != null) {
            int countArticlesEnable = articuloFacade.countArticle(recurso);
            if (countArticlesEnable != 0) {
                return countArticlesEnable + "";
            }
        }
        return "<Empty>";
    }

    public String countArticleEnable(Recurso recurso) {
        if (recurso != null) {
            int countArticlesEnable = articuloFacade.countArticlesEnable(recurso);
            if (countArticlesEnable != 0) {
                return countArticlesEnable + "";
            }
        }
        return "<Empty>";
    }

    public List<Recurso> getListaRecursos() {
        return listaRecursos;
    }

    public void setListaRecursos(List<Recurso> listaRecursos) {
        this.listaRecursos = listaRecursos;
    }

    public Recurso getRecursoSelected() {
        return recursoSelected;
    }

    public void setRecursoSelected(Recurso recursoSelected) {
        this.recursoSelected = recursoSelected;
    }
}

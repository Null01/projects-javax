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
import javax.faces.event.ActionEvent;
import org.primefaces.event.RowEditEvent;
import session.RecursoFacadeLocal;

/**
 *
 * @author duran
 */
public class BeanAdminRecurso implements Serializable {

    @EJB
    private RecursoFacadeLocal recursoFacade;
   

    // Consulta de recursos
    private Recurso resourceSelected;
    private List<Recurso> listaRecursos;


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



    public void onClickEditAccept(RowEditEvent event) {

    }

    public void onClickEditCancel(RowEditEvent event) {

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
}

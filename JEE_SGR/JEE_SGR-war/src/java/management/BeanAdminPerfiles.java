/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import entities.Funcion;
import entities.Perfil;
import entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DualListModel;
import session.FuncionFacadeLocal;
import session.PerfilFacadeLocal;

/**
 *
 * @author duran
 */
public class BeanAdminPerfiles implements Serializable {

    @EJB
    private FuncionFacadeLocal funcionFacade;
    @EJB
    private PerfilFacadeLocal perfilFacade;

    // Consulta de perfiles
    private List<Perfil> listaPerfiles;
    private Perfil profileSelected;

    // Consulta de usuarios
    private List<Usuario> usersList;
    private List<Usuario> listaFilteredUsuario;

    // Creacion de un perfil
    private Perfil perfil;
    private DualListModel<Funcion> listFuncion;

    public BeanAdminPerfiles() {
        listFuncion = new DualListModel<>();
    }

    @PostConstruct
    public void initialize() {
        List<Perfil> findAll = perfilFacade.findAll();
        if (findAll != null) {
            listaPerfiles = new ArrayList<>(findAll);
        }
    }

    public List<Usuario> showUser(Perfil perfil) {
        List<Usuario> list = perfil.getUsuarioList();
        return (list != null) ? list : new ArrayList<Usuario>();
    }

    public void onClickShowDialog(Perfil perfil) {
        setProfileSelected(perfil);
        if (profileSelected != null) {
            List<Usuario> list = profileSelected.getUsuarioList();
            if (list != null) {
                usersList = new ArrayList<>(list);
            } else {
                usersList = new ArrayList<>();
            }
        } else {
            usersList = new ArrayList<>();
        }
    }

    public void onClickEditAccept(RowEditEvent event) {
        Perfil perfil = (Perfil) event.getObject();
        perfilFacade.edit(perfil);
    }

    public void onClickEditCancel(RowEditEvent event) {

    }

    public void onClickPreCreateProfile(ActionEvent event) {
        System.out.println("pre create profile");
        perfil = new Perfil();
        List<Funcion> source = funcionFacade.findAll();
        if (source == null) {
            source = new ArrayList<>();
        }
        List<Funcion> target = new ArrayList<>();
        listFuncion = new DualListModel<>(source, target);
    }

    public void onClickCreateProfile(ActionEvent event) {
        System.out.println("create profile---->");
        System.out.println("ASCAOSDAS");
    }

    public void onClickDeleteProfile(Perfil perfil1) {
        perfilFacade.remove(perfil1);
        listaPerfiles = perfilFacade.findAll();
        if (listaPerfiles == null) {
            listaPerfiles = new ArrayList<>();
        }
    }

    public DualListModel<Funcion> getListFuncion() {
        return listFuncion;
    }

    public void setListFuncion(DualListModel<Funcion> listFuncion) {
        this.listFuncion = listFuncion;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public List<Perfil> getListaPerfiles() {
        return listaPerfiles;
    }

    public void setListaPerfiles(List<Perfil> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }

    public Perfil getProfileSelected() {
        return profileSelected;
    }

    public void setProfileSelected(Perfil profileSelected) {
        this.profileSelected = profileSelected;
    }

    public List<Usuario> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Usuario> usersList) {
        this.usersList = usersList;
    }

    public List<Usuario> getListaFilteredUsuario() {
        return listaFilteredUsuario;
    }

    public void setListaFilteredUsuario(List<Usuario> listaFilteredUsuario) {
        this.listaFilteredUsuario = listaFilteredUsuario;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import entities.Perfil;
import entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.event.RowEditEvent;
import session.PerfilFacadeLocal;
import util.FacesUtil;

/**
 *
 * @author duran
 */
public class BeanAdminPerfiles implements Serializable {

    @EJB
    private PerfilFacadeLocal perfilFacade;

    //
    private List<Perfil> listaPerfiles;
    private Perfil profileSelected;
    private List<Usuario> listaFilteredUsuario;

    //
    private List<Usuario> usersList;

    public BeanAdminPerfiles() {
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
            System.out.println("profile enable");
            List<Usuario> list = profileSelected.getUsuarioList();
            if (list != null) {
                usersList = new ArrayList<>(list);
                System.out.println("updateee");
            } else {
                usersList = new ArrayList<>();
            }
        } else {
            usersList = new ArrayList<>();
        }
    }

    public void onClickEditAccept(RowEditEvent event) {
        System.out.println("EDITA");
        Perfil perfil = (Perfil) event.getObject();
        perfilFacade.edit(perfil);
    }

    public void onClickEditCancel(RowEditEvent event) {

    }

    public void onClickCreateProfile() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 500);
        options.put("contentWidth", 530);
        FacesUtil.getFacesUtil().openDialog("adminPerfiles/crearPerfil", options);
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

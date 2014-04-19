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
    private DualListModel<Funcion> listFuncion;
    private String namePerfil;
    private String descPerfil;

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

    public void onClickShowDialog(Perfil perfil1) {
        setProfileSelected(perfil1);
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
        listaFilteredUsuario = usersList;
    }

    public void onClickEditAccept(RowEditEvent event) {
        Perfil perfil = (Perfil) event.getObject();
        perfil.setDescPerfil(perfil.getDescPerfil().trim());
        perfil.setNamePerfil(perfil.getNamePerfil().trim());
        perfilFacade.edit(perfil);
    }

    public void onClickPreCreateProfile(ActionEvent event) {
        List<Funcion> source = funcionFacade.findAll();  // <-- Mejorar
        if (source == null) {
            source = new ArrayList<>();
        }
        List<Funcion> target = new ArrayList<>();
        listFuncion = new DualListModel<>(source, target);
    }

    public void onClickCreateProfile(ActionEvent event) {

        Perfil perfil = new Perfil();
        perfil.setNamePerfil(namePerfil.trim());
        perfil.setDescPerfil(descPerfil.trim());

        List<Funcion> target = new ArrayList<>();
        // REVISAR, no existe coherencia en el objeto Funcion y la lista de target del DualListModel
        for (int i = 0; i < listFuncion.getTarget().size(); i++) {
     //       Object o = listFuncion.getTarget().get(i);
            //       int index = o.toString().indexOf("=") + 1;
            //       String key_clean = o.toString().substring(index, o.toString().length() - 1).trim();
            //       target.add(funcionFacade.find(new Integer(key_clean)));
        }
    //    perfil.setFuncionList(target);
        //   perfil.setUsuarioList(new ArrayList<Usuario>());

    //    perfilFacade.create(perfil);
        //  listaPerfiles.add(perfil);
        // this.namePerfil = this.descPerfil =  null;
    }

    public void onClickDeleteProfile(Perfil perfil1) {
        perfilFacade.remove(perfil1);
        listaPerfiles = perfilFacade.findAll();
        if (listaPerfiles == null) {
            listaPerfiles = new ArrayList<>();
        }
    }

    public String getDescPerfil() {
        return descPerfil;
    }

    public void setDescPerfil(String descPerfil) {
        this.descPerfil = descPerfil;
    }

    public String getNamePerfil() {
        return namePerfil;
    }

    public void setNamePerfil(String namePerfil) {
        this.namePerfil = namePerfil;
    }

    public DualListModel<Funcion> getListFuncion() {
        return listFuncion;
    }

    public void setListFuncion(DualListModel<Funcion> listFuncion) {
        this.listFuncion = listFuncion;
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

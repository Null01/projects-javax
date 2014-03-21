/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import entities.Perfil;
import entities.Recurso;
import entities.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import session.PerfilFacadeLocal;
import util.FacesUtil;

/**
 *
 * @author duran
 */
public class BeanAdminPerfiles {

    @EJB
    private PerfilFacadeLocal perfilFacade;

    private List<Perfil> listaPerfiles;
    private Recurso recursoSelected;
    private String pathView;

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

    public List<Perfil> getListaPerfiles() {
        return listaPerfiles;
    }

    public void setListaPerfiles(List<Perfil> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }

    public Recurso getRecursoSelected() {
        return recursoSelected;
    }

    public void setRecursoSelected(Recurso recursoSelected) {
        this.recursoSelected = recursoSelected;
    }

    public String getPathView() {
        return pathView;
    }

    public void setPathView(String pathView) {
        this.pathView = pathView;
    }

    public void onClickShowDialog(Perfil perfil) {
        //setPathView("adminPerfiles/vistaPerfiles.xhtml");
        HttpServletRequest httpServletRequest = FacesUtil.getFacesUtil().getHttpServletRequest();
        //httpServletRequest.setAttribute("ViewProfile", perfil);
        System.out.println("Llega");
        Map<String, Object> options = new HashMap<>();

        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 320);

        FacesUtil.getFacesUtil().openDialog("asd", null);
    }
}

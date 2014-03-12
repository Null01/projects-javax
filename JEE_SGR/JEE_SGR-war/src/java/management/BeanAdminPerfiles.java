/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import entities.Perfil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import session.PerfilFacadeLocal;

/**
 *
 * @author duran
 */
public class BeanAdminPerfiles {

    @EJB
    private PerfilFacadeLocal perfilFacade;

    private List<Perfil> listaPerfiles;

    public BeanAdminPerfiles() {
    }

    @PostConstruct
    public void initialize() {
        List<Perfil> findAll = perfilFacade.findAll();
        if (findAll != null) {
            listaPerfiles = new ArrayList<>(findAll);
        }
    }

    public List<Perfil> getListaPerfiles() {
        return listaPerfiles;
    }

    public void setListaPerfiles(List<Perfil> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }

}

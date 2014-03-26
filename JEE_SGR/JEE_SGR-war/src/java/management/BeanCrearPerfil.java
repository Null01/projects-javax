/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import entities.Funcion;
import entities.Perfil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.model.DualListModel;
import session.FuncionFacadeLocal;
import util.FacesUtil;

/**
 *
 * @author duran
 */
public class BeanCrearPerfil implements Serializable {
    
    @EJB
    private FuncionFacadeLocal funcionFacade;
    
    private DualListModel<Funcion> listFuncion;
    private Perfil perfil;
    
    public BeanCrearPerfil() {
        perfil = new Perfil();
    }
    
    @PostConstruct
    public void initialize() {
        List<Funcion> source = funcionFacade.findAll();
        List<Funcion> target = new ArrayList<>();
        if (source != null) {
            listFuncion = new DualListModel<>(source, target);
        } else {
            listFuncion = new DualListModel<>();
        }
    }
    
    public void onClickCreateProfile() {
        System.out.println("crea perfil");
        System.out.println(getPerfil().getNamePerfil());
        System.out.println(getPerfil().getDescPerfil());
        System.out.println(getListFuncion().getSource());
        System.out.println(getListFuncion().getTarget());
        FacesUtil.getFacesUtil().update("");
        FacesUtil.getFacesUtil().closeDialog(this);
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
}

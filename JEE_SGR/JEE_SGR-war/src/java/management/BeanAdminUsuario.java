/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import entities.Perfil;
import entities.Usuario;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import org.primefaces.event.RowEditEvent;
import session.PerfilFacadeLocal;
import session.UsuarioFacadeLocal;

/**
 *
 * @author duran
 */
public class BeanAdminUsuario implements Serializable {

    @EJB
    private PerfilFacadeLocal perfilFacade;

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    private List<Usuario> listaUsuarios;
    private String nameProfile;
    private String username;
    private String firstNameUser;
    private String lastNameUser;
    private String email;
    private Date age;

    public BeanAdminUsuario() {
    }

    @PostConstruct
    public void initialize() {
        if (listaUsuarios == null) {
            listaUsuarios = usuarioFacade.findAll();
        }
    }

    public List<String> completarListaPerfiles() {
        List<Perfil> findAll = perfilFacade.findAll();
        List<String> list = new ArrayList<>();
        if (findAll != null) {
            for (Perfil p : findAll) {
                list.add(p.getNamePerfil());
            }
        }
        return list;
    }

    public void onClickEditAccept(RowEditEvent event) {

    }

    public void onClickCreateUser(ActionEvent event) {

    }

    public String obtenerFormatoEdad(Date date) {
        return new SimpleDateFormat("d/MM/yyyy", new Locale("es", "ES")).format(date).toString();
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String getNameProfile() {
        return nameProfile;
    }

    public void setNameProfile(String nameProfile) {
        this.nameProfile = nameProfile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstNameUser() {
        return firstNameUser;
    }

    public void setFirstNameUser(String firstNameUser) {
        this.firstNameUser = firstNameUser;
    }

    public String getLastNameUser() {
        return lastNameUser;
    }

    public void setLastNameUser(String lastNameUser) {
        this.lastNameUser = lastNameUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getAge() {
        return age;
    }

    public void setAge(Date age) {
        this.age = age;
    }

}

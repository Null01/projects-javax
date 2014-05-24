package management;

import entities.Funcion;
import entities.Perfil;
import entities.Usuario;
import enumeration.ELabelsCommon;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DualListModel;
import session.FuncionFacadeLocal;
import session.LoginFacadeLocal;
import session.PerfilFacadeLocal;

/**
 *
 * @author duran
 */
public class BeanAdminPerfiles implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(BeanAdminPerfiles.class);

    @EJB
    private LoginFacadeLocal loginFacade;
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
        LOGGER.info(ELabelsCommon.END.getString() + ELabelsCommon.UPDATE.getString()+" DE UN PERFIL DE USUARIO");
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
        LOGGER.info(ELabelsCommon.INIT.getString() + ELabelsCommon.CREATE.getString());
        Perfil perfil = new Perfil();
        perfil.setNamePerfil(namePerfil.trim().toUpperCase());
        perfil.setDescPerfil(descPerfil.trim().toUpperCase());

        // REVISAR, no existe coherencia en el objeto Funcion y la lista de target del DualListModel
        List<Funcion> target = new ArrayList<>();
        if (listFuncion != null) {
            for (int i = 0; i < listFuncion.getTarget().size(); i++) {
                Object object = listFuncion.getTarget().get(i);
                String nameFuncion = problemPickList(object.toString());
                if (!nameFuncion.isEmpty()) {
                    Funcion funcion = funcionFacade.findByNameFuncion(nameFuncion);
                    if (funcion != null) {
                        target.add(funcionFacade.getReference(funcion.getIdFuncion()));
                    }
                }
            }
        }

        perfil.setFuncionList(target);
        perfil.setUsuarioList(new ArrayList<Usuario>());

        perfilFacade.create(perfil);
        listaPerfiles.add(perfil);
        this.namePerfil = this.descPerfil = null;

        LOGGER.info(ELabelsCommon.END.getString() + ELabelsCommon.CREATE.getString());
    }

    public void onClickDeleteProfile(Perfil perfil1) {
        LOGGER.info(ELabelsCommon.INIT.getString() + ELabelsCommon.DELETE.getString() + "DE UN PERFIL DE USUARIO");
        List<Usuario> usuarioList = perfil1.getUsuarioList();
        for (Usuario usuario : usuarioList) {
            loginFacade.remove(usuario.getLogin());
        }

        perfilFacade.remove(perfil1);
        listaPerfiles = perfilFacade.findAll();
        if (listaPerfiles == null) {
            listaPerfiles = new ArrayList<>();
        }
    }

    // This is a problem of framework - PickList - itemLabel()
    // Funciona a agregando el parametro nameFuncion al metodo @overide toString() de funcion
    public String problemPickList(String bug) {
        String key = "nameFuncion=";
        if (bug.contains(key)) {
            int index = bug.lastIndexOf(key) + key.length();
            return bug.subSequence(index, bug.length() - 1).toString();
        } else {
            return "<none>";
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

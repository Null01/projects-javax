package management;

import entities.Perfil;
import entities.Recurso;
import java.io.Serializable;
import java.math.BigInteger;
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
    private String codigoBarras;
    private String descripcion;
    private String nombre;

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
        System.out.println("edit");

    }

    public void onClickCreateResource(ActionEvent event) {
        Recurso recurso = new Recurso();
        recurso.setCodigoBarras(new BigInteger(codigoBarras));
        recurso.setDescripcion(descripcion);
        recurso.setNombre(nombre);
        recursoFacade.create(recurso);
        listaRecursos.add(recurso);
    }

    public void onClickDeleteProfile(Perfil perfil) {
        System.out.println("delete");
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

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}

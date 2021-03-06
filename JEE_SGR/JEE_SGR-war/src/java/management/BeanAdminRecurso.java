package management;

import entities.Recurso;
import enumeration.ELabelsCommon;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;
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

    private static final Logger LOGGER = Logger.getLogger(BeanAdminRecurso.class);

    public BeanAdminRecurso() {
    }

    @PostConstruct
    public void initialize() {
        List<Recurso> findAll = recursoFacade.findAll();
        if (findAll != null) {
            listaRecursos = new ArrayList<>(findAll);
        }
        codigoBarras = descripcion = nombre = "";
    }

    public void onClickEditAccept(RowEditEvent event) {
        Recurso recurso = (Recurso) event.getObject();
        recurso.setDescripcion(recurso.getDescripcion().trim());
        recurso.setNombre(recurso.getNombre().trim());
        recursoFacade.edit(recurso);
        LOGGER.info(ELabelsCommon.END.getString() + ELabelsCommon.UPDATE.getString() + " de un recurso");

    }

    public void onClickCreateResource(ActionEvent event) {
        Recurso recurso = new Recurso();
        LOGGER.info(ELabelsCommon.INIT.getString() + ELabelsCommon.CREATE.getString() + " de un recurso");
        recurso.setCodigoBarras(new BigInteger(codigoBarras));
        recurso.setDescripcion(descripcion);
        recurso.setNombre(nombre);
        recursoFacade.create(recurso);
        listaRecursos.add(recurso);
        LOGGER.info(ELabelsCommon.END.getString() + ELabelsCommon.CREATE.getString() + "del recurso " + recurso.getNombre());
        initialize();
    }

    public void onClickDeleteResource(Recurso recurso) {

        if (recurso != null) {
            boolean resourceIsEnable = recursoFacade.resourceIsEnable(recurso.getIdRecurso());
            if (!resourceIsEnable) {
                recursoFacade.remove(recurso);
                listaRecursos.remove(recurso);
            }
        }
        LOGGER.info(ELabelsCommon.END.getString() + ELabelsCommon.DELETE.getString() + " de un recurso");
    }

    public String obtenerEstadoDelRecurso(Recurso recurso) {
        boolean estado = recursoFacade.resourceIsEnable(recurso.getIdRecurso());
        return (estado) ? "PRESTADO" : "LIBRE";
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

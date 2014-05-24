package management;

import entities.Prestamo;
import entities.PrestamoPK;
import entities.Recurso;
import entities.Usuario;
import enumeration.ELabelsCommon;
import enumeration.ELabelsError;
import enumeration.ELabelsMessages;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;
import session.PrestamoFacadeLocal;
import util.FacesUtil;

/**
 *
 * @author duran
 */
public class BeanPrestamoUsuario implements Serializable {

    @EJB
    private PrestamoFacadeLocal prestamoFacade;

    private static final Logger LOGGER = Logger.getLogger(BeanPrestamoUsuario.class);

    private List<Recurso> listaRecursoDisponible = null;
    private List<Recurso> misRecursosEnPrestamo = null;

    private List<String> fechasPrestamosHabiles;
    private Map<Integer, Recurso> resourceSelected;

    private String fechaPrestamo = "";
    private boolean seleccionItem;
    private Date horaPrestamo = null;
    private Usuario usuario = null;

    private final int MAX_LOAD = 3;

    public BeanPrestamoUsuario() {
    }

    @PostConstruct
    public void initialize() {
        LOGGER.info(ELabelsCommon.INIT.getString() + ELabelsCommon.READ.getString() + " DE RECURSOS DISPONIBLES");
        if (usuario == null) {
            this.usuario = (Usuario) FacesUtil.getFacesUtil().getSession().getAttribute("session");
        }
        if (listaRecursoDisponible == null) {
            this.listaRecursoDisponible = prestamoFacade.getResourceEnable();
        }
        if (resourceSelected == null) {
            this.resourceSelected = new HashMap<>();
        }
        if (this.fechasPrestamosHabiles == null) {
            fechasPrestamosHabiles = new ArrayList<>();
            // El formato de la fecha queda supeditado a cambios asi que hay que tener en cuenta el metodo
            // buildDate a la hora de convertirlo en un dato legible para la persistencia.
            SimpleDateFormat format = new SimpleDateFormat("EEEE - d/MM/yyyy", new Locale("es", "ES"));
            for (int i = 0; i < 5; i++) {
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.add(Calendar.DATE, i);
                fechasPrestamosHabiles.add(format.format(calendar.getTime()).toUpperCase());
            }
        }
        if (horaPrestamo == null) {
            horaPrestamo = new Date();
        }
        if (misRecursosEnPrestamo == null) {
            this.misRecursosEnPrestamo = prestamoFacade.getMyResourceLoan(usuario.getNameUser());
        }
        LOGGER.info(ELabelsCommon.END.getString() + ELabelsCommon.READ.getString() + " DE RECURSOS DISPONIBLES");
    }

    public void onClickCreateLoad(ActionEvent event) {

        if (resourceSelected != null && !resourceSelected.isEmpty()) {
            int count = prestamoFacade.countResourceLoanByUser(usuario.getNameUser()) + ((resourceSelected != null) ? resourceSelected.size() : 0);
            if (count <= MAX_LOAD) {
                if (fechaPrestamo != null && !fechaPrestamo.isEmpty() && horaPrestamo != null) {
                    for (Recurso recurso : resourceSelected.values()) {
                        Prestamo prestamo = new Prestamo();
                        prestamo.setPrestamoPK(new PrestamoPK(recurso.getIdRecurso(), usuario.getNameUser(), buildDate()));
                        Date currentDate = Calendar.getInstance().getTime();
                        currentDate.setTime(System.currentTimeMillis());
                        prestamo.setFechaSolicitud(currentDate);
                        prestamo.setFechaDevolucion(null);
                        prestamoFacade.create(prestamo);
                        LOGGER.info(ELabelsCommon.END.getString() + ELabelsCommon.CREATE.getString() + " DE UN PRÉSTAMO");
                    }
                    FacesUtil.getFacesUtil().addMessage(FacesMessage.SEVERITY_INFO, ELabelsMessages.SUCCESSFULL_LOAN.getString(), "");
                } else {
                    FacesUtil.getFacesUtil().addMessage(FacesMessage.SEVERITY_INFO, ELabelsError.ERROR_PRESTAMO_CAMPOS_INCORRECTOS.getString(), "");
                    LOGGER.warn(ELabelsError.ERROR_PRESTAMO_CAMPOS_INCORRECTOS.getString());
                }
            } else {
                FacesUtil.getFacesUtil().addMessage(FacesMessage.SEVERITY_INFO, ELabelsError.ERROR_PRESTAMO_MAX_ARTICULOS.getString(), "");
                LOGGER.warn(ELabelsError.ERROR_PRESTAMO_MAX_ARTICULOS.getString());
            }
        } else {
            FacesUtil.getFacesUtil().addMessage(FacesMessage.SEVERITY_INFO, ELabelsError.ERROR_PRESTAMO_SELECCION_VACIA.getString(), "");
            LOGGER.warn(ELabelsError.ERROR_PRESTAMO_SELECCION_VACIA.getString());
        }

        resourceSelected.clear();
        listaRecursoDisponible = prestamoFacade.getResourceEnable();
        misRecursosEnPrestamo = prestamoFacade.getMyResourceLoan(usuario.getNameUser());
        seleccionItem = false;
        horaPrestamo = new Date();
        fechaPrestamo = "";
    }

    @Deprecated
    private Date buildDate() {
        StringTokenizer st = new StringTokenizer(fechaPrestamo, " -/");
        List<Integer> l = new ArrayList<>();
        while (st.hasMoreTokens()) {
            String string = st.nextToken();
            try {
                l.add(Integer.parseInt(string));
            } catch (NumberFormatException exception) {
            }
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(horaPrestamo);
        if (l.size() == 3) {
            calendar.set(l.get(2), l.get(1) - 1, l.get(0));
        }
        return calendar.getTime();
    }

    public void seleccionarRecurso(Recurso prestamo) {
        if (prestamo != null) {
            if (resourceSelected.get(prestamo.getIdRecurso()) == null) {
                resourceSelected.put(prestamo.getIdRecurso(), prestamo);
            } else {
                resourceSelected.remove(prestamo.getIdRecurso());
            }
        }
    }

    // Parsing of Map
    @Deprecated
    public List<Recurso> getResourceSelectedList() {
        Collection<Recurso> values = resourceSelected.values();
        if (values != null) {
            return new ArrayList(values);
        }
        return new ArrayList<>();
    }

    public String obtenerFechaPrestamo(Recurso recurso) {
        Prestamo prestamo = prestamoFacade.getLoanByIdRecurso(recurso.getIdRecurso(), usuario.getNameUser());
        return new SimpleDateFormat("d/MM/yyyy - HH:mm", new Locale("es", "ES")).format(prestamo.getPrestamoPK().getFechaPrestamo());
    }

    public String obtenerFechaSolicitud(Recurso recurso) {
        Prestamo prestamo = prestamoFacade.getLoanByIdRecurso(recurso.getIdRecurso(), usuario.getNameUser());
        return new SimpleDateFormat("d/MM/yyyy - HH:mm", new Locale("es", "ES")).format(prestamo.getFechaSolicitud());
    }

    public List<Recurso> getMisRecursosEnPrestamo() {
        return misRecursosEnPrestamo;
    }

    public void setMisRecursosEnPrestamo(List<Recurso> misRecursosEnPrestamo) {
        this.misRecursosEnPrestamo = misRecursosEnPrestamo;
    }

    public List<String> completarFechasPrestamo(String string) {
        return fechasPrestamosHabiles;
    }

    public List<Recurso> getListaRecursoDisponible() {
        return listaRecursoDisponible;
    }

    public void setListaRecursoDisponible(List<Recurso> listaRecursoDisponible) {
        this.listaRecursoDisponible = listaRecursoDisponible;
    }

    public boolean isSeleccionItem() {
        return seleccionItem;
    }

    public void setSeleccionItem(boolean seleccionItem) {
        this.seleccionItem = seleccionItem;
    }

    public Map<Integer, Recurso> getResourceSelected() {
        return resourceSelected;
    }

    public void setResourceSelected(Map<Integer, Recurso> resourceSelected) {
        this.resourceSelected = resourceSelected;
    }

    public List<String> getFechasPrestamosHabiles() {
        return fechasPrestamosHabiles;
    }

    public void setFechasPrestamosHabiles(List<String> fechasPrestamosHabiles) {
        this.fechasPrestamosHabiles = fechasPrestamosHabiles;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getHoraPrestamo() {
        return horaPrestamo;
    }

    public void setHoraPrestamo(Date horaPrestamo) {
        this.horaPrestamo = horaPrestamo;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import entities.Prestamo;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import session.PrestamoFacadeLocal;

/**
 *
 * @author duran
 */
public class BeanAdminPrestamo implements Serializable {

    @EJB
    private PrestamoFacadeLocal prestamoFacade;

    private List<Prestamo> listaPrestamos;

    private List<Prestamo> listFilteredLoan;

    public BeanAdminPrestamo() {
    }

    @PostConstruct
    public void initialize() {
        if (listaPrestamos == null) {
            listaPrestamos = prestamoFacade.findAll();
            listFilteredLoan = listaPrestamos;
        }
    }

    public String obtenerFormatoFecha(Date date) {
        return new SimpleDateFormat("d/MM/yyyy - HH:mm", new Locale("es", "ES")).format(date).toString();
    }

    public List<Prestamo> getListaPrestamos() {
        return listaPrestamos;
    }

    public void setListaPrestamos(List<Prestamo> listaPrestamos) {
        this.listaPrestamos = listaPrestamos;
    }

    public List<Prestamo> getListFilteredLoan() {
        return listFilteredLoan;
    }

    public void setListFilteredLoan(List<Prestamo> listFilteredLoan) {
        this.listFilteredLoan = listFilteredLoan;
    }

}

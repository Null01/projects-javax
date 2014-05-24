/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author duran
 * @version 1.0
 */
@Entity
@Table(name = "prestamo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestamo.findAll", query = "SELECT p FROM Prestamo p"),
    @NamedQuery(name = "Prestamo.findByIdRecurso", query = "SELECT p FROM Prestamo p WHERE p.prestamoPK.idRecurso = :idRecurso"),
    @NamedQuery(name = "Prestamo.findByIdUsuario", query = "SELECT p FROM Prestamo p WHERE p.prestamoPK.idUsuario = :idUsuario"),
    @NamedQuery(name = "Prestamo.findByFechaPrestamo", query = "SELECT p FROM Prestamo p WHERE p.prestamoPK.fechaPrestamo = :fechaPrestamo"),
    @NamedQuery(name = "Prestamo.findByFechaDevolucion", query = "SELECT p FROM Prestamo p WHERE p.fechaDevolucion = :fechaDevolucion"),
    @NamedQuery(name = "Prestamo.findByFechaSolicitud", query = "SELECT p FROM Prestamo p WHERE p.fechaSolicitud = :fechaSolicitud"),
    @NamedQuery(name = "Prestamo.findByVersion", query = "SELECT p FROM Prestamo p WHERE p.version = :version")})
public class Prestamo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrestamoPK prestamoPK;
    @Column(name = "fecha_devolucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDevolucion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_solicitud")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "version")
    private int version;

    public Prestamo() {
    }

    public Prestamo(PrestamoPK prestamoPK) {
        this.prestamoPK = prestamoPK;
    }

    public Prestamo(PrestamoPK prestamoPK, Date fechaSolicitud, int version) {
        this.prestamoPK = prestamoPK;
        this.fechaSolicitud = fechaSolicitud;
        this.version = version;
    }

    public Prestamo(int idRecurso, String idUsuario, Date fechaPrestamo) {
        this.prestamoPK = new PrestamoPK(idRecurso, idUsuario, fechaPrestamo);
    }

    public PrestamoPK getPrestamoPK() {
        return prestamoPK;
    }

    public void setPrestamoPK(PrestamoPK prestamoPK) {
        this.prestamoPK = prestamoPK;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prestamoPK != null ? prestamoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestamo)) {
            return false;
        }
        Prestamo other = (Prestamo) object;
        if ((this.prestamoPK == null && other.prestamoPK != null) || (this.prestamoPK != null && !this.prestamoPK.equals(other.prestamoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Prestamo[ prestamoPK=" + prestamoPK + " ]";
    }

}


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
    @NamedQuery(name = "Prestamo.findByHoraPrestamo", query = "SELECT p FROM Prestamo p WHERE p.horaPrestamo = :horaPrestamo"),
    @NamedQuery(name = "Prestamo.findByHoraEntrega", query = "SELECT p FROM Prestamo p WHERE p.horaEntrega = :horaEntrega"),
    @NamedQuery(name = "Prestamo.findByVersion", query = "SELECT p FROM Prestamo p WHERE p.version = :version")})
public class Prestamo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrestamoPK prestamoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_prestamo")
    @Temporal(TemporalType.TIME)
    private Date horaPrestamo;
    @Column(name = "hora_entrega")
    @Temporal(TemporalType.TIME)
    private Date horaEntrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "version")
    private int version;

    public Prestamo() {
    }

    public Prestamo(PrestamoPK prestamoPK) {
        this.prestamoPK = prestamoPK;
    }

    public Prestamo(PrestamoPK prestamoPK, Date horaPrestamo, int version) {
        this.prestamoPK = prestamoPK;
        this.horaPrestamo = horaPrestamo;
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

    public Date getHoraPrestamo() {
        return horaPrestamo;
    }

    public void setHoraPrestamo(Date horaPrestamo) {
        this.horaPrestamo = horaPrestamo;
    }

    public Date getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(Date horaEntrega) {
        this.horaEntrega = horaEntrega;
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

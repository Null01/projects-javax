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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author duran
 * @version 1.0
 */
@Embeddable
public class PrestamoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_recurso")
    private int idRecurso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_articulo")
    private int idArticulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "id_usuario")
    private String idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_prestamo")
    @Temporal(TemporalType.DATE)
    private Date fechaPrestamo;

    public PrestamoPK() {
    }

    public PrestamoPK(int idRecurso, int idArticulo, String idUsuario, Date fechaPrestamo) {
        this.idRecurso = idRecurso;
        this.idArticulo = idArticulo;
        this.idUsuario = idUsuario;
        this.fechaPrestamo = fechaPrestamo;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idRecurso;
        hash += (int) idArticulo;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        hash += (fechaPrestamo != null ? fechaPrestamo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestamoPK)) {
            return false;
        }
        PrestamoPK other = (PrestamoPK) object;
        if (this.idRecurso != other.idRecurso) {
            return false;
        }
        if (this.idArticulo != other.idArticulo) {
            return false;
        }
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        if ((this.fechaPrestamo == null && other.fechaPrestamo != null) || (this.fechaPrestamo != null && !this.fechaPrestamo.equals(other.fechaPrestamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PrestamoPK[ idRecurso=" + idRecurso + ", idArticulo=" + idArticulo + ", idUsuario=" + idUsuario + ", fechaPrestamo=" + fechaPrestamo + " ]";
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author duran
 * @version 1.0
 */
@Entity
@Table(name = "ID_ESTADO_SOLICITUD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IdEstadoSolicitud.findAll", query = "SELECT i FROM IdEstadoSolicitud i"),
    @NamedQuery(name = "IdEstadoSolicitud.findByIdEstadoSolicitud", query = "SELECT i FROM IdEstadoSolicitud i WHERE i.idEstadoSolicitud = :idEstadoSolicitud"),
    @NamedQuery(name = "IdEstadoSolicitud.findByNombre", query = "SELECT i FROM IdEstadoSolicitud i WHERE i.nombre = :nombre")})
public class IdEstadoSolicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESTADO_SOLICITUD")
    private Integer idEstadoSolicitud;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoSolicitud", fetch = FetchType.LAZY)
    private List<Solicitud> solicitudList;

    public IdEstadoSolicitud() {
    }

    public IdEstadoSolicitud(Integer idEstadoSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
    }

    public IdEstadoSolicitud(Integer idEstadoSolicitud, String nombre) {
        this.idEstadoSolicitud = idEstadoSolicitud;
        this.nombre = nombre;
    }

    public Integer getIdEstadoSolicitud() {
        return idEstadoSolicitud;
    }

    public void setIdEstadoSolicitud(Integer idEstadoSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Solicitud> getSolicitudList() {
        return solicitudList;
    }

    public void setSolicitudList(List<Solicitud> solicitudList) {
        this.solicitudList = solicitudList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoSolicitud != null ? idEstadoSolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IdEstadoSolicitud)) {
            return false;
        }
        IdEstadoSolicitud other = (IdEstadoSolicitud) object;
        if ((this.idEstadoSolicitud == null && other.idEstadoSolicitud != null) || (this.idEstadoSolicitud != null && !this.idEstadoSolicitud.equals(other.idEstadoSolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.IdEstadoSolicitud[ idEstadoSolicitud=" + idEstadoSolicitud + " ]";
    }

}

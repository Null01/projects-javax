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
 * @author Duran
 */
@Entity
@Table(name = "estado_mascota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoMascota.findAll", query = "SELECT e FROM EstadoMascota e"),
    @NamedQuery(name = "EstadoMascota.findByIdEstadoMascota", query = "SELECT e FROM EstadoMascota e WHERE e.idEstadoMascota = :idEstadoMascota"),
    @NamedQuery(name = "EstadoMascota.findByNombre", query = "SELECT e FROM EstadoMascota e WHERE e.nombre = :nombre")})
public class EstadoMascota implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_estado_mascota")
    private Integer idEstadoMascota;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoMascota", fetch = FetchType.LAZY)
    private List<Mascota> mascotaList;

    public EstadoMascota() {
    }

    public EstadoMascota(Integer idEstadoMascota) {
        this.idEstadoMascota = idEstadoMascota;
    }

    public EstadoMascota(Integer idEstadoMascota, String nombre) {
        this.idEstadoMascota = idEstadoMascota;
        this.nombre = nombre;
    }

    public Integer getIdEstadoMascota() {
        return idEstadoMascota;
    }

    public void setIdEstadoMascota(Integer idEstadoMascota) {
        this.idEstadoMascota = idEstadoMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Mascota> getMascotaList() {
        return mascotaList;
    }

    public void setMascotaList(List<Mascota> mascotaList) {
        this.mascotaList = mascotaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoMascota != null ? idEstadoMascota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoMascota)) {
            return false;
        }
        EstadoMascota other = (EstadoMascota) object;
        if ((this.idEstadoMascota == null && other.idEstadoMascota != null) || (this.idEstadoMascota != null && !this.idEstadoMascota.equals(other.idEstadoMascota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.EstadoMascota[ idEstadoMascota=" + idEstadoMascota + " ]";
    }
    
}

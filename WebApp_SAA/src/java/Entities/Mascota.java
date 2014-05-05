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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "mascota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mascota.findAll", query = "SELECT m FROM Mascota m"),
    @NamedQuery(name = "Mascota.findByIdMascota", query = "SELECT m FROM Mascota m WHERE m.idMascota = :idMascota"),
    @NamedQuery(name = "Mascota.findByNombre", query = "SELECT m FROM Mascota m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Mascota.findByEdad", query = "SELECT m FROM Mascota m WHERE m.edad = :edad"),
    @NamedQuery(name = "Mascota.findByOtraRaza", query = "SELECT m FROM Mascota m WHERE m.otraRaza = :otraRaza"),
    @NamedQuery(name = "Mascota.findByOtroTipoMascota", query = "SELECT m FROM Mascota m WHERE m.otroTipoMascota = :otroTipoMascota")})
public class Mascota implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MASCOTA")
    private Integer idMascota;
    @Size(max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EDAD")
    private int edad;
    @Size(max = 45)
    @Column(name = "OTRA_RAZA")
    private String otraRaza;
    @Size(max = 45)
    @Column(name = "OTRO_TIPO_MASCOTA")
    private String otroTipoMascota;
    @JoinColumn(name = "ID_TIPO_MASCOTA", referencedColumnName = "ID_TIPO_MASCOTA")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoMascota idTipoMascota;
    @JoinColumn(name = "ID_RAZA", referencedColumnName = "ID_RAZA")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Raza idRaza;
    @JoinColumn(name = "ID_ESTADO_MASCOTA", referencedColumnName = "ID_ESTADO_MASCOTA")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoMascota idEstadoMascota;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMascota", fetch = FetchType.LAZY)
    private List<Solicitud> solicitudList;

    public Mascota() {
    }

    public Mascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public Mascota(Integer idMascota, int edad) {
        this.idMascota = idMascota;
        this.edad = edad;
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getOtraRaza() {
        return otraRaza;
    }

    public void setOtraRaza(String otraRaza) {
        this.otraRaza = otraRaza;
    }

    public String getOtroTipoMascota() {
        return otroTipoMascota;
    }

    public void setOtroTipoMascota(String otroTipoMascota) {
        this.otroTipoMascota = otroTipoMascota;
    }

    public TipoMascota getIdTipoMascota() {
        return idTipoMascota;
    }

    public void setIdTipoMascota(TipoMascota idTipoMascota) {
        this.idTipoMascota = idTipoMascota;
    }

    public Raza getIdRaza() {
        return idRaza;
    }

    public void setIdRaza(Raza idRaza) {
        this.idRaza = idRaza;
    }

    public EstadoMascota getIdEstadoMascota() {
        return idEstadoMascota;
    }

    public void setIdEstadoMascota(EstadoMascota idEstadoMascota) {
        this.idEstadoMascota = idEstadoMascota;
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
        hash += (idMascota != null ? idMascota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mascota)) {
            return false;
        }
        Mascota other = (Mascota) object;
        if ((this.idMascota == null && other.idMascota != null) || (this.idMascota != null && !this.idMascota.equals(other.idMascota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Mascota[ idMascota=" + idMascota + " ]";
    }
    
}

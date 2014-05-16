/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author duran
 * @version 1.0
 */
@Entity
@Table(name = "recurso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recurso.findAll", query = "SELECT r FROM Recurso r"),
    @NamedQuery(name = "Recurso.findByIdRecurso", query = "SELECT r FROM Recurso r WHERE r.idRecurso = :idRecurso"),
    @NamedQuery(name = "Recurso.findByCodigoBarras", query = "SELECT r FROM Recurso r WHERE r.codigoBarras = :codigoBarras"),
    @NamedQuery(name = "Recurso.findByNombre", query = "SELECT r FROM Recurso r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Recurso.findByDescripcion", query = "SELECT r FROM Recurso r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "Recurso.findByVersion", query = "SELECT r FROM Recurso r WHERE r.version = :version")})
public class Recurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_recurso")
    private Integer idRecurso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_barras")
    private BigInteger codigoBarras;
    @Size(max = 30)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 256)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "version")
    private int version;

    public Recurso() {
    }

    public Recurso(Integer idRecurso) {
        this.idRecurso = idRecurso;
    }

    public Recurso(Integer idRecurso, BigInteger codigoBarras, int version) {
        this.idRecurso = idRecurso;
        this.codigoBarras = codigoBarras;
        this.version = version;
    }

    public Integer getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(Integer idRecurso) {
        this.idRecurso = idRecurso;
    }

    public BigInteger getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(BigInteger codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idRecurso != null ? idRecurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recurso)) {
            return false;
        }
        Recurso other = (Recurso) object;
        if ((this.idRecurso == null && other.idRecurso != null) || (this.idRecurso != null && !this.idRecurso.equals(other.idRecurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Recurso[ idRecurso=" + idRecurso + " ]";
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
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
 * @author Diana Vasquez
 */
@Entity
@Table(name = "auditor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Auditor.findAll", query = "SELECT a FROM Auditor a"),
    @NamedQuery(name = "Auditor.findByIdauditoria", query = "SELECT a FROM Auditor a WHERE a.idauditoria = :idauditoria"),
    @NamedQuery(name = "Auditor.findByNameclass", query = "SELECT a FROM Auditor a WHERE a.nameclass = :nameclass"),
    @NamedQuery(name = "Auditor.findByNamemethod", query = "SELECT a FROM Auditor a WHERE a.namemethod = :namemethod"),
    @NamedQuery(name = "Auditor.findByNamed", query = "SELECT a FROM Auditor a WHERE a.named = :named")})
public class Auditor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idauditoria")
    private Integer idauditoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nameclass")
    private String nameclass;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "namemethod")
    private String namemethod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "named")
    private String named;

    public Auditor() {
    }

    public Auditor(Integer idauditoria) {
        this.idauditoria = idauditoria;
    }

    public Auditor(Integer idauditoria, String nameclass, String namemethod, String named) {
        this.idauditoria = idauditoria;
        this.nameclass = nameclass;
        this.namemethod = namemethod;
        this.named = named;
    }

    public Integer getIdauditoria() {
        return idauditoria;
    }

    public void setIdauditoria(Integer idauditoria) {
        this.idauditoria = idauditoria;
    }

    public String getNameclass() {
        return nameclass;
    }

    public void setNameclass(String nameclass) {
        this.nameclass = nameclass;
    }

    public String getNamemethod() {
        return namemethod;
    }

    public void setNamemethod(String namemethod) {
        this.namemethod = namemethod;
    }

    public String getNamed() {
        return named;
    }

    public void setNamed(String named) {
        this.named = named;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idauditoria != null ? idauditoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auditor)) {
            return false;
        }
        Auditor other = (Auditor) object;
        if ((this.idauditoria == null && other.idauditoria != null) || (this.idauditoria != null && !this.idauditoria.equals(other.idauditoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Auditor[ idauditoria=" + idauditoria + " ]";
    }
    
}

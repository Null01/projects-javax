/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lab.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andresfelipegarciaduran
 */
@Entity
@Table(name = "publish")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publish.findAll", query = "SELECT p FROM Publish p"),
    @NamedQuery(name = "Publish.findByIdpublish", query = "SELECT p FROM Publish p WHERE p.idpublish = :idpublish"),
    @NamedQuery(name = "Publish.findByTittle", query = "SELECT p FROM Publish p WHERE p.tittle = :tittle"),
    @NamedQuery(name = "Publish.findByDescription", query = "SELECT p FROM Publish p WHERE p.description = :description"),
    @NamedQuery(name = "Publish.findByDatecreated", query = "SELECT p FROM Publish p WHERE p.datecreated = :datecreated")})
public class Publish implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpublish")
    private Integer idpublish;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "tittle")
    private String tittle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datecreated")
    @Temporal(TemporalType.DATE)
    private Date datecreated;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpublish", fetch = FetchType.LAZY)
    private List<Commentspublish> commentspublishList;
    @JoinColumn(name = "email", referencedColumnName = "email")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario email;

    public Publish() {
    }

    public Publish(Integer idpublish) {
        this.idpublish = idpublish;
    }

    public Publish(Integer idpublish, String tittle, String description, Date datecreated) {
        this.idpublish = idpublish;
        this.tittle = tittle;
        this.description = description;
        this.datecreated = datecreated;
    }

    public Integer getIdpublish() {
        return idpublish;
    }

    public void setIdpublish(Integer idpublish) {
        this.idpublish = idpublish;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    @XmlTransient
    public List<Commentspublish> getCommentspublishList() {
        return commentspublishList;
    }

    public void setCommentspublishList(List<Commentspublish> commentspublishList) {
        this.commentspublishList = commentspublishList;
    }

    public Usuario getEmail() {
        return email;
    }

    public void setEmail(Usuario email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpublish != null ? idpublish.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publish)) {
            return false;
        }
        Publish other = (Publish) object;
        if ((this.idpublish == null && other.idpublish != null) || (this.idpublish != null && !this.idpublish.equals(other.idpublish))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.lab.entities.Publish[ idpublish=" + idpublish + " ]";
    }
    
}

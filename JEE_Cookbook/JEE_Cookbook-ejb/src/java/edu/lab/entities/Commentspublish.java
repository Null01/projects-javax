/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lab.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andresfelipegarciaduran
 */
@Entity
@Table(name = "commentspublish")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Commentspublish.findAll", query = "SELECT c FROM Commentspublish c"),
    @NamedQuery(name = "Commentspublish.findByIdcomment", query = "SELECT c FROM Commentspublish c WHERE c.idcomment = :idcomment"),
    @NamedQuery(name = "Commentspublish.findByIdcommenttoothercomment", query = "SELECT c FROM Commentspublish c WHERE c.idcommenttoothercomment = :idcommenttoothercomment"),
    @NamedQuery(name = "Commentspublish.findByCommentspublish", query = "SELECT c FROM Commentspublish c WHERE c.commentspublish = :commentspublish"),
    @NamedQuery(name = "Commentspublish.findByDatecreated", query = "SELECT c FROM Commentspublish c WHERE c.datecreated = :datecreated")})
public class Commentspublish implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcomment")
    private Integer idcomment;
    @Basic(optional = false)
    @Column(name = "idcommenttoothercomment")
    private int idcommenttoothercomment;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "commentspublish")
    private String commentspublish;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    @JoinColumn(name = "email", referencedColumnName = "email")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario email;
    @JoinColumn(name = "idpublish", referencedColumnName = "idpublish")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Publish idpublish;

    public Commentspublish() {
    }

    public Commentspublish(Integer idcomment) {
        this.idcomment = idcomment;
    }

    public Commentspublish(Integer idcomment, int idcommenttoothercomment, String commentspublish, Date datecreated) {
        this.idcomment = idcomment;
        this.idcommenttoothercomment = idcommenttoothercomment;
        this.commentspublish = commentspublish;
        this.datecreated = datecreated;
    }

    public Integer getIdcomment() {
        return idcomment;
    }

    public void setIdcomment(Integer idcomment) {
        this.idcomment = idcomment;
    }

    public int getIdcommenttoothercomment() {
        return idcommenttoothercomment;
    }

    public void setIdcommenttoothercomment(int idcommenttoothercomment) {
        this.idcommenttoothercomment = idcommenttoothercomment;
    }

    public String getCommentspublish() {
        return commentspublish;
    }

    public void setCommentspublish(String commentspublish) {
        this.commentspublish = commentspublish;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public Usuario getEmail() {
        return email;
    }

    public void setEmail(Usuario email) {
        this.email = email;
    }

    public Publish getIdpublish() {
        return idpublish;
    }

    public void setIdpublish(Publish idpublish) {
        this.idpublish = idpublish;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcomment != null ? idcomment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commentspublish)) {
            return false;
        }
        Commentspublish other = (Commentspublish) object;
        if ((this.idcomment == null && other.idcomment != null) || (this.idcomment != null && !this.idcomment.equals(other.idcomment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.lab.entities.Commentspublish[ idcomment=" + idcomment + " ]";
    }
    
}

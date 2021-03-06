/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lab.entities;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andresfelipegarciaduran
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "Usuario.findByFname", query = "SELECT u FROM Usuario u WHERE u.fname = :fname"),
    @NamedQuery(name = "Usuario.findByLname", query = "SELECT u FROM Usuario u WHERE u.lname = :lname")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "fname")
    private String fname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "lname")
    private String lname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "email", fetch = FetchType.LAZY)
    private List<Commentspublish> commentspublishList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private Login login;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "email", fetch = FetchType.LAZY)
    private List<Publish> publishList;

    public Usuario() {
    }

    public Usuario(String email) {
        this.email = email;
    }

    public Usuario(String email, String fname, String lname) {
        this.email = email;
        this.fname = fname;
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    @XmlTransient
    public List<Commentspublish> getCommentspublishList() {
        return commentspublishList;
    }

    public void setCommentspublishList(List<Commentspublish> commentspublishList) {
        this.commentspublishList = commentspublishList;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    @XmlTransient
    public List<Publish> getPublishList() {
        return publishList;
    }

    public void setPublishList(List<Publish> publishList) {
        this.publishList = publishList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.lab.entities.Usuario[ email=" + email + " ]";
    }
    
}

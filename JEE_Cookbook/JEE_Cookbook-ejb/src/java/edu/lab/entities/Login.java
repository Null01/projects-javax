/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lab.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andresfelipegarciaduran
 */
@Entity
@Table(name = "login")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l"),
    @NamedQuery(name = "Login.findByEmail", query = "SELECT l FROM Login l WHERE l.loginPK.email = :email"),
    @NamedQuery(name = "Login.findByPass", query = "SELECT l FROM Login l WHERE l.loginPK.pass = :pass"),
    @NamedQuery(name = "Login.findByTypeuser", query = "SELECT l FROM Login l WHERE l.typeuser = :typeuser")})
public class Login implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LoginPK loginPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "typeuser")
    private String typeuser;

    public Login() {
    }

    public Login(LoginPK loginPK) {
        this.loginPK = loginPK;
    }

    public Login(LoginPK loginPK, String typeuser) {
        this.loginPK = loginPK;
        this.typeuser = typeuser;
    }

    public Login(String email, String pass) {
        this.loginPK = new LoginPK(email, pass);
    }

    public LoginPK getLoginPK() {
        return loginPK;
    }

    public void setLoginPK(LoginPK loginPK) {
        this.loginPK = loginPK;
    }

    public String getTypeuser() {
        return typeuser;
    }

    public void setTypeuser(String typeuser) {
        this.typeuser = typeuser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loginPK != null ? loginPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if ((this.loginPK == null && other.loginPK != null) || (this.loginPK != null && !this.loginPK.equals(other.loginPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.lab.entities.Login[ loginPK=" + loginPK + " ]";
    }
    
}

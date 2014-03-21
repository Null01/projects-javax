/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AGarcia
 */
@Entity
@Table(name = "LOGIN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l"),
    @NamedQuery(name = "Login.findByNameUser", query = "SELECT l FROM Login l WHERE l.loginPK.nameUser = :nameUser"),
    @NamedQuery(name = "Login.findByPassUser", query = "SELECT l FROM Login l WHERE l.loginPK.passUser = :passUser")})
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LoginPK loginPK;

    public Login() {
    }

    public Login(LoginPK loginPK) {
        this.loginPK = loginPK;
    }

    public Login(String nameUser, String passUser) {
        this.loginPK = new LoginPK(nameUser, passUser);
    }

    public LoginPK getLoginPK() {
        return loginPK;
    }

    public void setLoginPK(LoginPK loginPK) {
        this.loginPK = loginPK;
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
        return "entities.Login[ loginPK=" + loginPK + " ]";
    }

}

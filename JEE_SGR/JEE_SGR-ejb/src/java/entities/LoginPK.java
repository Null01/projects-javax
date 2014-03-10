/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author duran
 * @version 1.0
 */
@Embeddable
public class LoginPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "name_user")
    private String nameUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "pass_user")
    private String passUser;

    public LoginPK() {
    }

    public LoginPK(String nameUser, String passUser) {
        this.nameUser = nameUser;
        this.passUser = passUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPassUser() {
        return passUser;
    }

    public void setPassUser(String passUser) {
        this.passUser = passUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nameUser != null ? nameUser.hashCode() : 0);
        hash += (passUser != null ? passUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoginPK)) {
            return false;
        }
        LoginPK other = (LoginPK) object;
        if ((this.nameUser == null && other.nameUser != null) || (this.nameUser != null && !this.nameUser.equals(other.nameUser))) {
            return false;
        }
        if ((this.passUser == null && other.passUser != null) || (this.passUser != null && !this.passUser.equals(other.passUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.LoginPK[ nameUser=" + nameUser + ", passUser=" + passUser + " ]";
    }

}

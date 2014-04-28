/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author duran
 * @version 1.0
 */
@Entity
@Table(name = "login")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l"),
    @NamedQuery(name = "Login.findByNameUser", query = "SELECT l FROM Login l WHERE l.loginPK.nameUser = :nameUser"),
    @NamedQuery(name = "Login.findByPassUser", query = "SELECT l FROM Login l WHERE l.loginPK.passUser = :passUser"),
    @NamedQuery(name = "Login.findByCountTrys", query = "SELECT l FROM Login l WHERE l.countTrys = :countTrys"),
    @NamedQuery(name = "Login.findByDateLastTry", query = "SELECT l FROM Login l WHERE l.dateLastTry = :dateLastTry")})
public class Login implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LoginPK loginPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "count_trys")
    private short countTrys;
    @Column(name = "date_last_try")
    @Temporal(TemporalType.DATE)
    private Date dateLastTry;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "login", fetch = FetchType.LAZY)
    private List<Usuario> usuarioList;

    public Login() {
    }

    public Login(LoginPK loginPK) {
        this.loginPK = loginPK;
    }

    public Login(LoginPK loginPK, short countTrys) {
        this.loginPK = loginPK;
        this.countTrys = countTrys;
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

    public short getCountTrys() {
        return countTrys;
    }

    public void setCountTrys(short countTrys) {
        this.countTrys = countTrys;
    }

    public Date getDateLastTry() {
        return dateLastTry;
    }

    public void setDateLastTry(Date dateLastTry) {
        this.dateLastTry = dateLastTry;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
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

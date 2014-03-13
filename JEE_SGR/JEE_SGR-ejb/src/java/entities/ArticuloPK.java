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

/**
 *
 * @author AGarcia
 */
@Embeddable
public class ArticuloPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_ARTICULO")
    private int idArticulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_RECURSO")
    private int idRecurso;

    public ArticuloPK() {
    }

    public ArticuloPK(int idArticulo, int idRecurso) {
        this.idArticulo = idArticulo;
        this.idRecurso = idRecurso;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idArticulo;
        hash += (int) idRecurso;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArticuloPK)) {
            return false;
        }
        ArticuloPK other = (ArticuloPK) object;
        if (this.idArticulo != other.idArticulo) {
            return false;
        }
        if (this.idRecurso != other.idRecurso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ArticuloPK[ idArticulo=" + idArticulo + ", idRecurso=" + idRecurso + " ]";
    }
    
}

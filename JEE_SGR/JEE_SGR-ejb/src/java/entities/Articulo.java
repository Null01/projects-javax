/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author duran
 * @version 1.0
 */
@Entity
@Table(name = "articulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Articulo.findAll", query = "SELECT a FROM Articulo a"),
    @NamedQuery(name = "Articulo.findByIdArticulo", query = "SELECT a FROM Articulo a WHERE a.articuloPK.idArticulo = :idArticulo"),
    @NamedQuery(name = "Articulo.findByIdRecurso", query = "SELECT a FROM Articulo a WHERE a.articuloPK.idRecurso = :idRecurso"),
    @NamedQuery(name = "Articulo.findByMarca", query = "SELECT a FROM Articulo a WHERE a.marca = :marca"),
    @NamedQuery(name = "Articulo.findByDisponible", query = "SELECT a FROM Articulo a WHERE a.disponible = :disponible")})
public class Articulo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ArticuloPK articuloPK;
    @Size(max = 30)
    @Column(name = "marca")
    private String marca;
    @Column(name = "disponible")
    private Boolean disponible;
    @JoinColumn(name = "id_recurso", referencedColumnName = "id_recurso", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Recurso recurso;

    public Articulo() {
    }

    public Articulo(ArticuloPK articuloPK) {
        this.articuloPK = articuloPK;
    }

    public Articulo(int idArticulo, int idRecurso) {
        this.articuloPK = new ArticuloPK(idArticulo, idRecurso);
    }

    public ArticuloPK getArticuloPK() {
        return articuloPK;
    }

    public void setArticuloPK(ArticuloPK articuloPK) {
        this.articuloPK = articuloPK;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (articuloPK != null ? articuloPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        if ((this.articuloPK == null && other.articuloPK != null) || (this.articuloPK != null && !this.articuloPK.equals(other.articuloPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Articulo[ articuloPK=" + articuloPK + " ]";
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author duran
 * @version 1.0
 */
@Entity
@Table(name = "funcion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcion.findAll", query = "SELECT f FROM Funcion f"),
    @NamedQuery(name = "Funcion.findByIdFuncion", query = "SELECT f FROM Funcion f WHERE f.idFuncion = :idFuncion"),
    @NamedQuery(name = "Funcion.findByNameFuncion", query = "SELECT f FROM Funcion f WHERE f.nameFuncion = :nameFuncion"),
    @NamedQuery(name = "Funcion.findByUrlFuncion", query = "SELECT f FROM Funcion f WHERE f.urlFuncion = :urlFuncion"),
    @NamedQuery(name = "Funcion.findByIdFuncionPadre", query = "SELECT f FROM Funcion f WHERE f.idFuncionPadre = :idFuncionPadre")})
public class Funcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_funcion")
    private Integer idFuncion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "name_funcion")
    private String nameFuncion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "url_funcion")
    private String urlFuncion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_funcion_padre")
    private int idFuncionPadre;
    @JoinTable(name = "funcion_usuario", joinColumns = {
        @JoinColumn(name = "id_funcion", referencedColumnName = "id_funcion")}, inverseJoinColumns = {
        @JoinColumn(name = "id_perfil", referencedColumnName = "id_perfil")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Perfil> perfilList;

    public Funcion() {
    }

    public Funcion(Integer idFuncion) {
        this.idFuncion = idFuncion;
    }

    public Funcion(Integer idFuncion, String nameFuncion, String urlFuncion, int idFuncionPadre) {
        this.idFuncion = idFuncion;
        this.nameFuncion = nameFuncion;
        this.urlFuncion = urlFuncion;
        this.idFuncionPadre = idFuncionPadre;
    }

    public Integer getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(Integer idFuncion) {
        this.idFuncion = idFuncion;
    }

    public String getNameFuncion() {
        return nameFuncion;
    }

    public void setNameFuncion(String nameFuncion) {
        this.nameFuncion = nameFuncion;
    }

    public String getUrlFuncion() {
        return urlFuncion;
    }

    public void setUrlFuncion(String urlFuncion) {
        this.urlFuncion = urlFuncion;
    }

    public int getIdFuncionPadre() {
        return idFuncionPadre;
    }

    public void setIdFuncionPadre(int idFuncionPadre) {
        this.idFuncionPadre = idFuncionPadre;
    }

    @XmlTransient
    public List<Perfil> getPerfilList() {
        return perfilList;
    }

    public void setPerfilList(List<Perfil> perfilList) {
        this.perfilList = perfilList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFuncion != null ? idFuncion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcion)) {
            return false;
        }
        Funcion other = (Funcion) object;
        if ((this.idFuncion == null && other.idFuncion != null) || (this.idFuncion != null && !this.idFuncion.equals(other.idFuncion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Funcion[ idFuncion=" + idFuncion + " nameFuncion=" + nameFuncion + "]";
    }

}

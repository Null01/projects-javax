/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author andresfelipegarciaduran
 */
@Entity
@Table(name = "CLASSIFICATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Classification.findAll", query = "SELECT c FROM Classification c"),
    @NamedQuery(name = "Classification.findByClassificationId", query = "SELECT c FROM Classification c WHERE c.classificationId = :classificationId"),
    @NamedQuery(name = "Classification.findByCode", query = "SELECT c FROM Classification c WHERE c.code = :code")})
public class Classification implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLASSIFICATION_ID")
    private BigDecimal classificationId;
    @Size(max = 10)
    @Column(name = "CODE")
    private String code;
    @JoinTable(name = "CLASSIFICATION_PATENT", joinColumns = {
        @JoinColumn(name = "CLASSIFICATION_ID", referencedColumnName = "CLASSIFICATION_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PATENT_ID", referencedColumnName = "PATENT_ID")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Patent> patentList;

    public Classification() {
    }

    public Classification(BigDecimal classificationId) {
        this.classificationId = classificationId;
    }

    public BigDecimal getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(BigDecimal classificationId) {
        this.classificationId = classificationId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlTransient
    public List<Patent> getPatentList() {
        return patentList;
    }

    public void setPatentList(List<Patent> patentList) {
        this.patentList = patentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (classificationId != null ? classificationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Classification)) {
            return false;
        }
        Classification other = (Classification) object;
        if ((this.classificationId == null && other.classificationId != null) || (this.classificationId != null && !this.classificationId.equals(other.classificationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Classification[ classificationId=" + classificationId + " ]";
    }
    
}

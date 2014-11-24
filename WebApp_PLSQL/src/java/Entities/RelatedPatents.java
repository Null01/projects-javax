/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "RELATED_PATENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RelatedPatents.findAll", query = "SELECT r FROM RelatedPatents r"),
    @NamedQuery(name = "RelatedPatents.findByRelatedPatentId", query = "SELECT r FROM RelatedPatents r WHERE r.relatedPatentId = :relatedPatentId"),
    @NamedQuery(name = "RelatedPatents.findByRelationDir", query = "SELECT r FROM RelatedPatents r WHERE r.relationDir = :relationDir")})
public class RelatedPatents implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RELATED_PATENT_ID")
    private Long relatedPatentId;
    @Size(max = 1)
    @Column(name = "RELATION_DIR")
    private String relationDir;
    @JoinColumn(name = "PATENT_DST", referencedColumnName = "PATENT_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Patent patentDst;
    @JoinColumn(name = "PATENT_SRC", referencedColumnName = "PATENT_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Patent patentSrc;

    public RelatedPatents() {
    }

    public RelatedPatents(Long relatedPatentId) {
        this.relatedPatentId = relatedPatentId;
    }

    public Long getRelatedPatentId() {
        return relatedPatentId;
    }

    public void setRelatedPatentId(Long relatedPatentId) {
        this.relatedPatentId = relatedPatentId;
    }

    public String getRelationDir() {
        return relationDir;
    }

    public void setRelationDir(String relationDir) {
        this.relationDir = relationDir;
    }

    public Patent getPatentDst() {
        return patentDst;
    }

    public void setPatentDst(Patent patentDst) {
        this.patentDst = patentDst;
    }

    public Patent getPatentSrc() {
        return patentSrc;
    }

    public void setPatentSrc(Patent patentSrc) {
        this.patentSrc = patentSrc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (relatedPatentId != null ? relatedPatentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelatedPatents)) {
            return false;
        }
        RelatedPatents other = (RelatedPatents) object;
        if ((this.relatedPatentId == null && other.relatedPatentId != null) || (this.relatedPatentId != null && !this.relatedPatentId.equals(other.relatedPatentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.RelatedPatents[ relatedPatentId=" + relatedPatentId + " ]";
    }
    
}

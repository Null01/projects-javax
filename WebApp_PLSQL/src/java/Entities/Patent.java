/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author duran
 */
@Entity
@Table(name = "PATENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Patent.findAll", query = "SELECT p FROM Patent p"),
    @NamedQuery(name = "Patent.findByPatentId", query = "SELECT p FROM Patent p WHERE p.patentId = :patentId"),
    @NamedQuery(name = "Patent.findByPatentTitle", query = "SELECT p FROM Patent p WHERE p.patentTitle = :patentTitle"),
    @NamedQuery(name = "Patent.findByPublicDate", query = "SELECT p FROM Patent p WHERE p.publicDate = :publicDate")})
public class Patent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PATENT_ID")
    private String patentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PATENT_TITLE")
    private String patentTitle;
    @Column(name = "PUBLIC_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publicDate;
    @Lob
    @Column(name = "DOCUMENT")
    private Serializable document;
    @Lob
    @Column(name = "DESCRIPTION")
    private String description;
    @ManyToMany(mappedBy = "patentList", fetch = FetchType.LAZY)
    private List<Inventor> inventorList;
    @ManyToMany(mappedBy = "patentList", fetch = FetchType.LAZY)
    private List<Classification> classificationList;
    @ManyToMany(mappedBy = "patentList", fetch = FetchType.LAZY)
    private List<Assignee> assigneeList;
    @OneToMany(mappedBy = "patentDst", fetch = FetchType.LAZY)
    private List<RelatedPatents> relatedPatentsList;
    @OneToMany(mappedBy = "patentSrc", fetch = FetchType.LAZY)
    private List<RelatedPatents> relatedPatentsList1;

    public Patent() {
    }

    public Patent(String patentId) {
        this.patentId = patentId;
    }

    public Patent(String patentId, String patentTitle) {
        this.patentId = patentId;
        this.patentTitle = patentTitle;
    }

    public String getPatentId() {
        return patentId;
    }

    public void setPatentId(String patentId) {
        this.patentId = patentId;
    }

    public String getPatentTitle() {
        return patentTitle;
    }

    public void setPatentTitle(String patentTitle) {
        this.patentTitle = patentTitle;
    }

    public Date getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(Date publicDate) {
        this.publicDate = publicDate;
    }

    public Serializable getDocument() {
        return document;
    }

    public void setDocument(Serializable document) {
        this.document = document;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<Inventor> getInventorList() {
        return inventorList;
    }

    public void setInventorList(List<Inventor> inventorList) {
        this.inventorList = inventorList;
    }

    @XmlTransient
    public List<Classification> getClassificationList() {
        return classificationList;
    }

    public void setClassificationList(List<Classification> classificationList) {
        this.classificationList = classificationList;
    }

    @XmlTransient
    public List<Assignee> getAssigneeList() {
        return assigneeList;
    }

    public void setAssigneeList(List<Assignee> assigneeList) {
        this.assigneeList = assigneeList;
    }

    @XmlTransient
    public List<RelatedPatents> getRelatedPatentsList() {
        return relatedPatentsList;
    }

    public void setRelatedPatentsList(List<RelatedPatents> relatedPatentsList) {
        this.relatedPatentsList = relatedPatentsList;
    }

    @XmlTransient
    public List<RelatedPatents> getRelatedPatentsList1() {
        return relatedPatentsList1;
    }

    public void setRelatedPatentsList1(List<RelatedPatents> relatedPatentsList1) {
        this.relatedPatentsList1 = relatedPatentsList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (patentId != null ? patentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patent)) {
            return false;
        }
        Patent other = (Patent) object;
        if ((this.patentId == null && other.patentId != null) || (this.patentId != null && !this.patentId.equals(other.patentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Patent[ patentId=" + patentId + " ]";
    }
    
}

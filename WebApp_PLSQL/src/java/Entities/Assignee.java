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
 * @author duran
 */
@Entity
@Table(name = "ASSIGNEE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assignee.findAll", query = "SELECT a FROM Assignee a"),
    @NamedQuery(name = "Assignee.findByAssigneeId", query = "SELECT a FROM Assignee a WHERE a.assigneeId = :assigneeId"),
    @NamedQuery(name = "Assignee.findByName", query = "SELECT a FROM Assignee a WHERE a.name = :name")})
public class Assignee implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ASSIGNEE_ID")
    private BigDecimal assigneeId;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @JoinTable(name = "INVENTOR_ASSIGNEE", joinColumns = {
        @JoinColumn(name = "ASSIGNEE_ID", referencedColumnName = "ASSIGNEE_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "INVENTOR_ID", referencedColumnName = "INVENTOR_ID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Inventor> inventorList;
    @JoinTable(name = "ASSIGNEE_PATENT", joinColumns = {
        @JoinColumn(name = "ASSIGNEE_ID", referencedColumnName = "ASSIGNEE_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PATENT_ID", referencedColumnName = "PATENT_ID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Patent> patentList;

    public Assignee() {
    }

    public Assignee(BigDecimal assigneeId) {
        this.assigneeId = assigneeId;
    }

    public BigDecimal getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(BigDecimal assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Inventor> getInventorList() {
        return inventorList;
    }

    public void setInventorList(List<Inventor> inventorList) {
        this.inventorList = inventorList;
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
        hash += (assigneeId != null ? assigneeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assignee)) {
            return false;
        }
        Assignee other = (Assignee) object;
        if ((this.assigneeId == null && other.assigneeId != null) || (this.assigneeId != null && !this.assigneeId.equals(other.assigneeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Assignee[ assigneeId=" + assigneeId + " ]";
    }
    
}

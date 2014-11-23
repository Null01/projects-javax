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
@Table(name = "INVENTOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inventor.findAll", query = "SELECT i FROM Inventor i"),
    @NamedQuery(name = "Inventor.findByInventorId", query = "SELECT i FROM Inventor i WHERE i.inventorId = :inventorId"),
    @NamedQuery(name = "Inventor.findByName", query = "SELECT i FROM Inventor i WHERE i.name = :name")})
public class Inventor implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "INVENTOR_ID")
    private BigDecimal inventorId;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @ManyToMany(mappedBy = "inventorList", fetch = FetchType.LAZY)
    private List<Assignee> assigneeList;
    @JoinTable(name = "INVENTOR_PATENT", joinColumns = {
        @JoinColumn(name = "INVENTOR_ID", referencedColumnName = "INVENTOR_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PATENT_ID", referencedColumnName = "PATENT_ID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Patent> patentList;

    public Inventor() {
    }

    public Inventor(BigDecimal inventorId) {
        this.inventorId = inventorId;
    }

    public BigDecimal getInventorId() {
        return inventorId;
    }

    public void setInventorId(BigDecimal inventorId) {
        this.inventorId = inventorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Assignee> getAssigneeList() {
        return assigneeList;
    }

    public void setAssigneeList(List<Assignee> assigneeList) {
        this.assigneeList = assigneeList;
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
        hash += (inventorId != null ? inventorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventor)) {
            return false;
        }
        Inventor other = (Inventor) object;
        if ((this.inventorId == null && other.inventorId != null) || (this.inventorId != null && !this.inventorId.equals(other.inventorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Inventor[ inventorId=" + inventorId + " ]";
    }
    
}

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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "EXCEPTIONS_PATENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExceptionsPatents.findAll", query = "SELECT e FROM ExceptionsPatents e"),
    @NamedQuery(name = "ExceptionsPatents.findByExceptionId", query = "SELECT e FROM ExceptionsPatents e WHERE e.exceptionId = :exceptionId"),
    @NamedQuery(name = "ExceptionsPatents.findByExceptionName", query = "SELECT e FROM ExceptionsPatents e WHERE e.exceptionName = :exceptionName"),
    @NamedQuery(name = "ExceptionsPatents.findByExceptionDesc", query = "SELECT e FROM ExceptionsPatents e WHERE e.exceptionDesc = :exceptionDesc")})
public class ExceptionsPatents implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXCEPTION_ID")
    private BigDecimal exceptionId;
    @Size(max = 100)
    @Column(name = "EXCEPTION_NAME")
    private String exceptionName;
    @Size(max = 200)
    @Column(name = "EXCEPTION_DESC")
    private String exceptionDesc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exceptionId", fetch = FetchType.EAGER)
    private List<ErrorLog> errorLogList;

    public ExceptionsPatents() {
    }

    public ExceptionsPatents(BigDecimal exceptionId) {
        this.exceptionId = exceptionId;
    }

    public BigDecimal getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(BigDecimal exceptionId) {
        this.exceptionId = exceptionId;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public String getExceptionDesc() {
        return exceptionDesc;
    }

    public void setExceptionDesc(String exceptionDesc) {
        this.exceptionDesc = exceptionDesc;
    }

    @XmlTransient
    public List<ErrorLog> getErrorLogList() {
        return errorLogList;
    }

    public void setErrorLogList(List<ErrorLog> errorLogList) {
        this.errorLogList = errorLogList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (exceptionId != null ? exceptionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExceptionsPatents)) {
            return false;
        }
        ExceptionsPatents other = (ExceptionsPatents) object;
        if ((this.exceptionId == null && other.exceptionId != null) || (this.exceptionId != null && !this.exceptionId.equals(other.exceptionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.ExceptionsPatents[ exceptionId=" + exceptionId + " ]";
    }
    
}

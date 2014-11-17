/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author duran
 */
@Entity
@Table(name = "ERROR_LOG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ErrorLog.findAll", query = "SELECT e FROM ErrorLog e"),
    @NamedQuery(name = "ErrorLog.findByErrorDate", query = "SELECT e FROM ErrorLog e WHERE e.errorDate = :errorDate"),
    @NamedQuery(name = "ErrorLog.findByIssuedTable", query = "SELECT e FROM ErrorLog e WHERE e.issuedTable = :issuedTable"),
    @NamedQuery(name = "ErrorLog.findByIssuedColumn", query = "SELECT e FROM ErrorLog e WHERE e.issuedColumn = :issuedColumn"),
    @NamedQuery(name = "ErrorLog.findByRegId", query = "SELECT e FROM ErrorLog e WHERE e.regId = :regId"),
    @NamedQuery(name = "ErrorLog.findByUserIpAdd", query = "SELECT e FROM ErrorLog e WHERE e.userIpAdd = :userIpAdd"),
    @NamedQuery(name = "ErrorLog.findByErrorLog", query = "SELECT e FROM ErrorLog e WHERE e.errorLog = :errorLog")})
public class ErrorLog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "ERROR_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date errorDate;
    @Size(max = 10)
    @Column(name = "ISSUED_TABLE")
    private String issuedTable;
    @Size(max = 30)
    @Column(name = "ISSUED_COLUMN")
    private String issuedColumn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REG_ID")
    private BigInteger regId;
    @Size(max = 10)
    @Column(name = "USER_IP_ADD")
    private String userIpAdd;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ERROR_LOG")
    private Long errorLog;
    @JoinColumn(name = "EXCEPTION_ID", referencedColumnName = "EXCEPTION_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ExceptionsPatents exceptionId;

    public ErrorLog() {
    }

    public ErrorLog(Long errorLog) {
        this.errorLog = errorLog;
    }

    public ErrorLog(Long errorLog, BigInteger regId) {
        this.errorLog = errorLog;
        this.regId = regId;
    }

    public Date getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(Date errorDate) {
        this.errorDate = errorDate;
    }

    public String getIssuedTable() {
        return issuedTable;
    }

    public void setIssuedTable(String issuedTable) {
        this.issuedTable = issuedTable;
    }

    public String getIssuedColumn() {
        return issuedColumn;
    }

    public void setIssuedColumn(String issuedColumn) {
        this.issuedColumn = issuedColumn;
    }

    public BigInteger getRegId() {
        return regId;
    }

    public void setRegId(BigInteger regId) {
        this.regId = regId;
    }

    public String getUserIpAdd() {
        return userIpAdd;
    }

    public void setUserIpAdd(String userIpAdd) {
        this.userIpAdd = userIpAdd;
    }

    public Long getErrorLog() {
        return errorLog;
    }

    public void setErrorLog(Long errorLog) {
        this.errorLog = errorLog;
    }

    public ExceptionsPatents getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(ExceptionsPatents exceptionId) {
        this.exceptionId = exceptionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (errorLog != null ? errorLog.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ErrorLog)) {
            return false;
        }
        ErrorLog other = (ErrorLog) object;
        if ((this.errorLog == null && other.errorLog != null) || (this.errorLog != null && !this.errorLog.equals(other.errorLog))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.ErrorLog[ errorLog=" + errorLog + " ]";
    }
    
}

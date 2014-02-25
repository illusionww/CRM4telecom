package com.crm4telecom.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PHONE_NUMBER", catalog = "", schema = "CRM4TELECOM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PhoneNumber.findAll", query = "SELECT p FROM PhoneNumber p"),
    @NamedQuery(name = "PhoneNumber.findByPhoneNumber", query = "SELECT p FROM PhoneNumber p WHERE p.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "PhoneNumber.findByStatus", query = "SELECT p FROM PhoneNumber p WHERE p.status = :status"),
    @NamedQuery(name = "PhoneNumber.findByStatusComment", query = "SELECT p FROM PhoneNumber p WHERE p.statusComment = :statusComment")})
public class PhoneNumber implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PHONE_NUMBER", nullable = false, length = 20)
    private String phoneNumber;
    
    @Size(max = 30)
    @Column(length = 30)
    private String status;
    
    @Size(max = 30)
    @Column(name = "STATUS_COMMENT", length = 30)
    private String statusComment;
    
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID", nullable = false)
    @ManyToOne(optional = false)
    private Customer customerId;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "phoneNumber1")
    private PhoneNumbersHistory phoneNumbersHistory;

    public PhoneNumber() {
    }

    public PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusComment() {
        return statusComment;
    }

    public void setStatusComment(String statusComment) {
        this.statusComment = statusComment;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public PhoneNumbersHistory getPhoneNumbersHistory() {
        return phoneNumbersHistory;
    }

    public void setPhoneNumbersHistory(PhoneNumbersHistory phoneNumbersHistory) {
        this.phoneNumbersHistory = phoneNumbersHistory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhoneNumber)) {
            return false;
        }
        PhoneNumber other = (PhoneNumber) object;
        if ((this.phoneNumber == null && other.phoneNumber != null) || (this.phoneNumber != null && !this.phoneNumber.equals(other.phoneNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crm4telecom.jpa.PhoneNumber[ phoneNumber=" + phoneNumber + " ]";
    }
    
}

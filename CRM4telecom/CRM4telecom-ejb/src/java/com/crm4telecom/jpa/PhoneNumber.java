package com.crm4telecom.jpa;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PHONE_NUMBER", catalog = "", schema = "CRM4TELECOM")
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
        if (!(object instanceof PhoneNumber)) {
            return false;
        }
        PhoneNumber other = (PhoneNumber) object;
        return this.phoneNumber.equals(other.phoneNumber);
    }

    @Override
    public String toString() {
        return "com.crm4telecom.jpa.PhoneNumber[ phoneNumber=" + phoneNumber + " ]";
    }

}

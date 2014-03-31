package com.crm4telecom.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class CustomerProductsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "PRODUCT_ID")
    private Long productId;

    public CustomerProductsPK() {
    }

    public CustomerProductsPK(Long customerId, Long productId) {
        this.customerId = customerId;
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerId != null ? customerId.hashCode() : 0);
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CustomerProductsPK)) {
            return false;
        }
        CustomerProductsPK other = (CustomerProductsPK) object;
        if (!this.customerId.equals(other.customerId)) {
            return false;
        }
        return this.productId.equals(other.productId);
    }

    @Override
    public String toString() {
        return "com.crm4telecom.jpa.CustomerProductsPK[ customerId=" + customerId + ", productId=" + productId + " ]";
    }

}

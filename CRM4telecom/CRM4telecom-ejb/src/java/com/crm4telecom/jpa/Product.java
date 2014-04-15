package com.crm4telecom.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "PRODUCT_ID", nullable = false, precision = 22, scale = 0)
    private Long productId;

    @Size(max = 30)
    @Column(length = 30)
    private String name;

    @Size(max = 300)
    @Column(length = 300)
    private String description;

    @Column(name = "SALES_PERIOD_START")
    @Temporal(TemporalType.TIMESTAMP)
    private Date salesPeriodStart;

    @Column(name = "SALES_PERIOD_END")
    @Temporal(TemporalType.TIMESTAMP)
    private Date salesPeriodEnd;

    @Column(name = "BASELINE_PRICE")
    private Long baselinePrice;

    @Size(max = 30)
    @Column(length = 30)
    private String properties;

    @OneToMany(mappedBy = "productId")
    private List<Order> ordersList;

    public Product() {
    }

    public Product(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getSalesPeriodStart() {
        return salesPeriodStart;
    }

    public void setSalesPeriodStart(Date salesPeriodStart) {
        this.salesPeriodStart = salesPeriodStart;
    }

    public Date getSalesPeriodEnd() {
        return salesPeriodEnd;
    }

    public void setSalesPeriodEnd(Date salesPeriodEnd) {
        this.salesPeriodEnd = salesPeriodEnd;
    }

    public Long getBaselinePrice() {
        return baselinePrice;
    }

    public void setBaselinePrice(Long baselinePrice) {
        this.baselinePrice = baselinePrice;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public List<Order> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        return this.productId.equals(other.productId);
    }

    @Override
    public String toString() {
        return "#" + productId + " " + name;
    }

}

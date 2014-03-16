package com.crm4telecom.jpa;

import com.crm4telecom.enums.OrderProduct;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(catalog = "", schema = "CRM4TELECOM")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "PRODUCT_ID", nullable = false, precision = 38, scale = 0)
    private Long productId;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private OrderProduct name;

    @Column(length = 30)
    private String description;

    @Column(name = "SALES_PERIOD_START")
    @Temporal(TemporalType.TIMESTAMP)
    private Date salesPeriodStart;

    @Column(name = "SALES_PERIOD_END")
    @Temporal(TemporalType.TIMESTAMP)
    private Date salesPeriodEnd;

    
    @Column(name = "BASELINE_PRICE")
    private Long baselinePrice;

    @OneToMany(mappedBy = "productId")
    private List<MarketProducts> marketProductsList;

    @OneToMany(mappedBy = "productId")
    private List<Order> ordersList;

    @OneToMany(mappedBy = "productId")
    private List<CustomerProducts> customerProductsList;

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

    public OrderProduct getName() {
        return name;
    }

    public void setName(OrderProduct name) {
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

    @XmlTransient
    public List<MarketProducts> getMarketProductsList() {
        return marketProductsList;
    }

    public void setMarketProductsList(List<MarketProducts> marketProductsList) {
        this.marketProductsList = marketProductsList;
    }

    @XmlTransient
    public List<Order> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
    }

    @XmlTransient
    public List<CustomerProducts> getCustomerProductsList() {
        return customerProductsList;
    }

    public void setCustomerProductsList(List<CustomerProducts> customerProductsList) {
        this.customerProductsList = customerProductsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "#" + productId + " " + name.name();
    }

}

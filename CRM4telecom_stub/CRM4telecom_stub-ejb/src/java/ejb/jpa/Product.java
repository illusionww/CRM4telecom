package ejb.jpa;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "Products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Products.findByProductId", query = "SELECT p FROM Product p WHERE p.productId = :productId"),
    @NamedQuery(name = "Products.findByOnetimePrice", query = "SELECT p FROM Product p WHERE p.onetimePrice = :onetimePrice"),
    @NamedQuery(name = "Products.findByMonthlyPrice", query = "SELECT p FROM Product p WHERE p.monthlyPrice = :monthlyPrice")})
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRODUCT_ID")
    private Long productId;
    @Column(name = "ONETIME_PRICE")
    private BigInteger onetimePrice;
    @Column(name = "MONTHLY_PRICE")
    private BigInteger monthlyPrice;
    @ManyToMany(mappedBy = "productsList")
    private List<Customer> customersList;

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

    public BigInteger getOnetimePrice() {
        return onetimePrice;
    }

    public void setOnetimePrice(BigInteger onetimePrice) {
        this.onetimePrice = onetimePrice;
    }

    public BigInteger getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(BigInteger monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    @XmlTransient
    public List<Customer> getCustomersList() {
        return customersList;
    }

    public void setCustomersList(List<Customer> customersList) {
        this.customersList = customersList;
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
        return "com.crm4telecom.stub.jpa.Products[ productId=" + productId + " ]";
    }
    
}
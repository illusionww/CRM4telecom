package com.crm4telecom.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Order_Processing", catalog = "", schema = "CRM4TELECOM")
public class OrderProcessing implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = "SEC_STEP_ID", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEC_STEP_ID", sequenceName = "SEC_STEP_ID", allocationSize = 1)
    @Column(name = "STEP_ID")
    private Long stepId;
    
    @NotNull
    @Column(name = "ORDER_ID")
    private Long orderId;
    
    @Size(max = 30)
    @Column(name = "STEP_NAME")
    private String stepName;
    
    @Size(max = 30)
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    
    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    
    @Column(name = "END_DATE_HARD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateHard;
    
    @JoinColumn(name = "EQUIPMENT_ID", referencedColumnName = "EQUIPMENT_ID")
    @ManyToOne
    private Equipment equipmentId;

    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID")
    @ManyToOne
    private Employee employeeId;

    @ManyToOne()
    @PrimaryKeyJoinColumn(name= "ORDER_ID" ,referencedColumnName="ID")
    private Order orders;

    public OrderProcessing() {
    }
    
    public OrderProcessing(Long orderId) {
        this.orderId = orderId;
    }

    public Long getStepId() {
        return stepId;
    }

    public void setStepId(Long stepId) {
        this.stepId = stepId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDateHard() {
        return endDateHard;
    }

    public void setEndDateHard(Date endDateHard) {
        this.endDateHard = endDateHard;
    }

    public Equipment getEquipmentId() {
        return equipmentId;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEquipmentId(Equipment equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stepId != null ? stepId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderProcessing)) {
            return false;
        }
        OrderProcessing other = (OrderProcessing) object;
        if ((this.stepId == null && other.stepId != null) || (this.stepId != null && !this.stepId.equals(other.stepId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crm4telecom.jpa.OrderProcessing[ stepId=" + stepId + " ]";
    }

}

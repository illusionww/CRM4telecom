package com.crm4telecom.stub.beans;

import ejb.jpa.Customers;
import ejb.beans.CustomerManager;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class CustomerManagedBean {
    @EJB
    private CustomerManager customerManager;

    public List<Customers> getCustomers() {
        return customerManager.getCustomersList();
    }
           
    public void merge(Customers customer) {
        customerManager.merge(customer);
    }    
    
    public CustomerManagedBean() {
    }
    
}

package com.crm4telecom.stub.ejb.beans;

import com.crm4telecom.stub.beans.enums.CustomerStatus;
import java.util.List;
import com.crm4telecom.stub.ejb.jpa.Customer;
import com.crm4telecom.stub.ejb.jpa.Product;
import java.util.Map;
import javax.ejb.Local;

@Local
public interface CustomerManagerInterface {
    
    List<Object> getItems(String databaseName);
    
    List<Customer> getCustomersList();
    
    List<Product> getProductsList();
    
    Customer getCustomer(Long customerID);
    
    public String addCustomer();
    
    public void withdraw(Long customerID, Double cash);
    
    public void setStatus(Long customerID, CustomerStatus status);
    
    public String addProduct(Long customerID, Long productID);
    
    public String removeProduct(Long customerID, Long productID);
    
    public double getBalance(Long customerID);
    
    Map<Long, CustomerStatus> getStatuses();

    public void setCustomers(List<Customer> customers);
    
}
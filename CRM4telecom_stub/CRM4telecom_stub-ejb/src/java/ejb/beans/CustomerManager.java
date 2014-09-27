/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.beans;

import ejb.jpa.Customers;
import ejb.jpa.Products;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author root
 */
@Stateless
public class CustomerManager implements CustomerManagerInterface{

    @PersistenceContext
    private EntityManager em;
    
    //Names of Data Bases
    private String customersBase = "Customers";
    private String productsBase = "Products";
    //
    
    
    @Override
    public List<Object> getItems(String databaseName) {
        return em.createQuery("SELECT c FROM " + databaseName + " c").getResultList();
    }

    @Override
    public void merge(Object object) {
        em.merge(object);
    }

    @Override
    public String addCustomer() {
        Customers customer = new Customers();
        try {
            this.merge(customer);
            return "ok";
        } catch (Exception e){
            return("no");
        }    
    }

    @Override
    public String addProduct(Long customerID, String productName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String removeProduct(Long customerID, String productName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getBalance(Long customerID) {
        Query query = em.createQuery("SELECT c FROM " + customersBase + " c" + "WHERE c.customerId = :customerID").setParameter("customerID", customerID);
        List<Customers> resultList = query.getResultList();
        Customers customer = resultList.get(0);
        return customer.getBalance();
    }

    @Override
    public Map<Long, String> getStatuses() {
        List<Customers> list = this.getCustomersList();
        Map<Long, String> resultMap = new HashMap<Long, String>();
        
        for(Customers c : list){
            resultMap.put(c.getCustomerId(), c.getStatus());
        }
        return resultMap;
    }

    @Override
    public List<Customers> getCustomersList() {
        return em.createQuery("SELECT c FROM " + customersBase + " c").getResultList();
    }

    @Override
    public List<Products> getProductsList() {
        return em.createQuery("SELECT c FROM " + productsBase + " c").getResultList();
    }

    
    
}

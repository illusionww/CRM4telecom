package com.crm4telecom.web.beans;

import com.crm4telecom.ejb.CustomerManagerLocal;
import com.crm4telecom.jpa.Customer;
import com.crm4telecom.web.beans.util.LazyCustomerDataModel;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.model.LazyDataModel;

@ManagedBean
@SessionScoped
public class CustomerBean implements Serializable {

    private LazyDataModel<Customer> lazyModel;
    private Customer customer;

    @EJB
    private CustomerManagerLocal cm;

    @Inject
    private CustomerValidationBean cv;

    @PostConstruct
    public void init() {
        lazyModel = new LazyCustomerDataModel(cm);
    }

    public LazyDataModel<Customer> getCustomers() {
        return lazyModel;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        cv.init(customer);
    }

    public void onRowSelect() {
        forvardNavigation("customer_info");
    }
    
    public void create() {
        Customer customer = new Customer();
        cv.fillCustomer(customer);
        cm.createCustomer(customer);

        forvardNavigation("customer_list");
    }

    public void modify() {
        cv.fillCustomer(customer);
        cm.modifyCustomer(customer);
        
        forvardNavigation("customer_info");
    }

    public CustomerValidationBean getCv() {
        return cv;
    }

    public void setCv(CustomerValidationBean cv) {
        this.cv = cv;
    }

    public List<String> completeCustomer(String customer) {
        return cm.completeCustomer(customer);
    }
    
    private void forvardNavigation(String outcome) {
        ConfigurableNavigationHandler configurableNavigationHandler
                = (ConfigurableNavigationHandler) FacesContext.
                getCurrentInstance().getApplication().getNavigationHandler();

        configurableNavigationHandler.performNavigation(outcome + "?faces-redirect=true");
    }
}

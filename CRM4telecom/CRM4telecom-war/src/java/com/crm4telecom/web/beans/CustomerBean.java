package com.crm4telecom.web.beans;

import com.crm4telecom.ejb.CustomerManagerLocal;
import com.crm4telecom.ejb.GetManagerLocal;
import com.crm4telecom.enums.CustomerStatus;
import com.crm4telecom.jpa.Customer;
import com.crm4telecom.jpa.Market;
import com.crm4telecom.web.beans.util.LazyCustomerDataModel;
import com.crm4telecom.web.util.JSFHelper;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import org.primefaces.model.LazyDataModel;

@ManagedBean
@SessionScoped
public class CustomerBean implements Serializable {

    private LazyCustomerDataModel lazyModel;
    private Customer customer;

    @EJB
    private CustomerManagerLocal cm;

    @EJB
    private GetManagerLocal gm;

    @Inject
    private OrderBean ob;

    @Inject
    private CustomerValidationBean cv;

    @Inject
    private CustomerSearchBean search;

    @PostConstruct
    public void init() {
        lazyModel = new LazyCustomerDataModel(cm);
    }

    public LazyDataModel<Customer> getCustomers() {
        lazyModel.setSearch(search);
        return lazyModel;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CustomerSearchBean getSearch() {
        return search;
    }

    public void setSearch(CustomerSearchBean search) {
        this.search = search;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        cv.init(customer);
    }

    public void onRowSelect() {
        JSFHelper helper = new JSFHelper();
        helper.redirect("customer_info");
    }

    public void create() {
        Customer customerNew = new Customer();
        cv.fillCustomer(customerNew);
        cm.createCustomer(customerNew);

        customer = customerNew;
        
        JSFHelper helper = new JSFHelper();
        helper.redirect("customer_info");
    }

    public void modify() {
        cv.fillCustomer(customer);
        cm.modifyCustomer(customer);

        JSFHelper helper = new JSFHelper();
        helper.redirect("customer_info");
    }

    public CustomerValidationBean getCv() {
        return cv;
    }

    public void setCv(CustomerValidationBean cv) {
        this.cv = cv;
    }

    public List<String> completeCustomer(String customer) {
        return gm.completeCustomer(customer);
    }

    public void toAddOrder() {
        ob.getValidation().init();
        ob.getValidation().setCustomer(customer.toString());

        JSFHelper helper = new JSFHelper();
        helper.redirect("order_add");
    }

    public void toAddCustomer() {
        cv.init();

        JSFHelper helper = new JSFHelper();
        helper.redirect("customer_add");
    }

    public void toCustomer() {
        if (ob.getOrder() != null) {
            setCustomer(ob.getOrder().getCustomer());

            JSFHelper helper = new JSFHelper();
            helper.redirect("customer_info");
        }
    }
    
    public CustomerStatus[] getStatus() {
        return CustomerStatus.values();
    }
    
    public List<Market> getMarkets() {
        return cm.getMarkets(customer);
    }
    
    public long getBalance() {
    //    Services service = new Services();
    //    Billing port = service.getBillingPort();
    //    long result = port.getBalance(customer.getCustomerId());

    //    System.out.println("balance " + result);

        return 12; //result;
    }
}

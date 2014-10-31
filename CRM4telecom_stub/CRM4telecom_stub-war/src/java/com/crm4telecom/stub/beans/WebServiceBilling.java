package com.crm4telecom.stub.beans;

import com.crm4telecom.stub.ejb.beans.CustomerManagerInterface;
import java.util.Map;
import javax.ejb.EJB;
import javax.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebService(serviceName = "services", portName = "BillingPort", endpointInterface = "com.crm4telecom.soap.BillingWebService", targetNamespace = "http://soap.crm4telecom.com/", wsdlLocation = "WEB-INF/wsdl/WebServiceBilling/schema.wsdl")
public class WebServiceBilling {

    private final Logger logger = LoggerFactory.getLogger(WebServiceBilling.class);

    @EJB
    CustomerManagerInterface cm;

    public Boolean addMoney(double cash, long id) {
        return true;
    }

    public double getBalance(long id) {
        return cm.getBalance(id);
    }

    public Boolean addCustomer(Long customerID, Double balance, String status) {
        return cm.addCustomer(customerID, balance, status);
    }

    public Boolean removeProduct(java.lang.Long customerID, Long productID) {
        return cm.removeProduct(customerID, productID);
    }

    public Boolean withdraw(double cash, long id) {
        try {
            logger.info("Withdraw in stub");
            return cm.withdraw(id, cash);
        } catch (Throwable e) {
            return false;
        }
    }

    public Boolean addProduct(Long customerID, Long productID) {
        return cm.addProduct(customerID, productID);
    }

    public Map<Long, com.crm4telecom.stub.beans.enums.CustomerStatus> getStatuses() {
        return cm.getStatuses();
    }

}

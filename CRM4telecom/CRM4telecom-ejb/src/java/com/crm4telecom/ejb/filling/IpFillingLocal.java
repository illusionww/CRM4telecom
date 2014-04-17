package com.crm4telecom.ejb.filling;

import com.crm4telecom.jpa.Customer;
import javax.ejb.Local;

@Local
public interface IpFillingLocal {

    public void allocateItem(Customer customer);

    public void freeItem(Customer customer);
}

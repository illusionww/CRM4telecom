package com.crm4telecom.web.beans.util;

import com.crm4telecom.ejb.CustomerManagerLocal;
import com.crm4telecom.jpa.Customer;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyCustomerDataModel extends LazyDataModel<Customer> {

    private CustomerManagerLocal cm;
    private List<Customer> datasource;

    public LazyCustomerDataModel(CustomerManagerLocal cm) {
        this.cm = cm;
    }

    @Override
    public Customer getRowData(String rowKey) {
        for (Customer customer : datasource) {
            Long customerId = customer.getCustomerId();
            if (customerId.toString().equals(rowKey)) {
                return customer;
            }
        }

        return null;
    }

    @Override
    public String getRowKey(Customer customer) {
        return customer.getCustomerId().toString();
    }

    @Override
    public List<Customer> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        setRowCount(cm.getCustomersCount(filters).intValue());
        datasource = cm.getCustomersList(first, pageSize, sortField, sortOrder.name(), filters);

        return datasource;
    }
}
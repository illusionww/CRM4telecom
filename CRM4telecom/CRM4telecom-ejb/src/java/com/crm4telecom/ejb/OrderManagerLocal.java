package com.crm4telecom.ejb;

import com.crm4telecom.enums.OrderEvent;
import com.crm4telecom.enums.OrderStatus;
import com.crm4telecom.jpa.Order;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

@Local
public interface OrderManagerLocal {

    Order createOrder(Order order);

    void modifyOrder(Order order);

    Order setCustomer(Order order, Long customerId);

    Order getOrder(Long orderId);
    
    List<Order> getOrdersList(int first, int pageSize, String sortField, String sortOrder, Map<String, String> filters,Map<String,List<String>> parametrs);

    Long getOrdersCount();

    Long getOrdersCount(Map<String, String> filters,Map<String,List<String>> parametrs);
    
    void changeOrderState(Order order, OrderEvent event);

    OrderStatus getOrderState(Long orderId);
    
    List<Order> search(Map<String,List<String>> parametrs);
    
    List<String> completeOrder(String rawOrder);
}

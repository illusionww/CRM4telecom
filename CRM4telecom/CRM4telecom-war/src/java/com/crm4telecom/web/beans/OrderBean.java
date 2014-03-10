package com.crm4telecom.web.beans;

import com.crm4telecom.ejb.OrderManagerLocal;
import com.crm4telecom.ejb.util.OrderEvent;
import com.crm4telecom.ejb.util.OrderPriority;
import com.crm4telecom.ejb.util.OrderState;
import com.crm4telecom.jpa.Order;
import com.crm4telecom.jpa.OrderProcessing;
import com.crm4telecom.web.beans.util.LazyOrderDataModel;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class OrderBean implements Serializable {

    private LazyOrderDataModel lazyModel;
    Order order;
    OrderEvent event;

    @EJB
    private OrderManagerLocal om;

    @Inject
    private OrderValidationBean validation;

    @Inject
    private OrderSearchBean search;
    
    @Inject
    private OrderCommentBean comment;
    
    private Map<String, List<String>> parametrs;
    
    @PostConstruct
    public void init() {
        lazyModel = new LazyOrderDataModel(om);
        parametrs = new HashMap();
    }

    public OrderValidationBean getValidation() {
        return validation;
    }

    public OrderSearchBean getSearch() {
        return search;
    }

    public OrderCommentBean getComment() {
        return comment;
    }
    
    public List<OrderProcessing> getOrderProcessing() {
        for(OrderProcessing temp : order.getOrderProcessing()) {
            System.out.println("ee:" + temp.getStepName());
        }
        return order.getOrderProcessing();
    }   
    
    public LazyDataModel<Order> getOrders() {
        lazyModel.setSearch(search);
        return lazyModel;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
        validation.init(order);
        comment.init(order);
    }

    public void onRowSelect() throws IOException {
        ConfigurableNavigationHandler configurableNavigationHandler
                = (ConfigurableNavigationHandler) FacesContext.
                getCurrentInstance().getApplication().getNavigationHandler();

        configurableNavigationHandler.performNavigation("order_info?faces-redirect=true");
    }

    public OrderPriority[] getPriorities() {
        return OrderPriority.values();
    }

    public OrderState[] getStatuses() {
        return OrderState.values();
    }

    public void create() {
        Order order = new Order();
        validation.fillOrder(order);
        om.createOrder(order);

        ConfigurableNavigationHandler configurableNavigationHandler
                = (ConfigurableNavigationHandler) FacesContext.
                getCurrentInstance().getApplication().getNavigationHandler();

        configurableNavigationHandler.performNavigation("order_list?faces-redirect=true");
    }

    public void modify() {
        validation.fillOrder(order);
        om.modifyOrder(order);
    }

    public List<OrderEvent> getEvents() {
        List<OrderEvent> events = new ArrayList<>();
        if (order != null) {
            String status = order.getStatus();
            if (status != null) {
                events = OrderState.valueOf(status).possibleEvents();
            }
        }
        return events;
    }

    public OrderEvent getEvent() {
        return event;
    }

    public void setEvent(OrderEvent event) {
        this.event = event;
    }

    public void changeState() {
        System.out.println(order + " " + event);
        om.changeOrderState(order, event);
    }

    public List<String> completeOrder(String order) {
        return om.completeOrder(order);
    }
}

package com.crm4telecom.ejb;

import com.crm4telecom.jpa.Customer;
import com.crm4telecom.jpa.Market;
import com.crm4telecom.jpa.MarketsCustomers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;

@Stateless
public class CustomerManager implements CustomerManagerLocal {

    private transient final Logger log = Logger.getLogger(getClass().getName());

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createCustomer(Customer customer) {
        if (customer != null) {
            persist(customer);

        } else {
            throw new IllegalArgumentException("Customer can't be null");
        }
    }

    @Override
    public void modifyCustomer(Customer customer) {
        em.merge(customer);
    }

    @Override
    public Customer getCustomer(Long customerId) {
        Customer customer;
        if (customerId == null) {
            throw new IllegalArgumentException("CustomerId can't be null");
        }
        if (customerId > 0) {
            customer = find(customerId);
        } else {
            return null;
        }

        return customer;
    }

    @Override
    public void addMarket(MarketsCustomers mc) {
        em.persist(mc);
    }

    @Override
    public List<Market> getMarkets(Customer customer) {

        if (customer != null) {
            String sqlQuery = "SELECT c.marketsCustomersPK.marketId FROM MarketsCustomers c WHERE c.marketsCustomersPK.customerId = :customerId";
            Query query = em.createQuery(sqlQuery).setParameter("customerId", customer.getCustomerId());

            List<Long> market_ids = query.getResultList();
            List<Market> markets = new ArrayList<Market>();
            for (Long temp : market_ids) {
                Query query2 = em.createQuery("SELECT u FROM Market u WHERE u.marketId = :id").setParameter("id", temp);
                markets.add((Market) query2.getResultList().get(0));
            }
            return markets;
        } else {
            throw new IllegalArgumentException("Customer cannot be null");
        }
    }

    @Override
    public List<Customer> getCustomersList(int first, int pageSize, String sortField, String sortOrder, Map<String, String> filters, Map<String, List<String>> parametrs) {
        String sqlQuery = "SELECT c FROM Customer c";
        if (!parametrs.isEmpty()) {
            sqlQuery += " WHERE";
            for (String paramProperty : parametrs.keySet()) {
                List<String> val = (List<String>) parametrs.get(paramProperty);
                if (val.size() > 1) {
                    sqlQuery += " ( ";
                    for (String val1 : val) {
                        sqlQuery += " LOWER(c." + paramProperty + ") REGEXP LOWER('" + val1 + "') OR";
                    }
                    sqlQuery = sqlQuery.substring(0, sqlQuery.length() - "OR".length());
                    sqlQuery += " ) AND";
                } else {
                    sqlQuery += "   LOWER( c." + paramProperty + " ) REGEXP LOWER('" + val.get(0) + "')   AND";
                }
            }
        }
        if (filters != null && !filters.isEmpty()) {
            sqlQuery += " WHERE";
            for (String filterProperty : filters.keySet()) {
                String filterValue = filters.get(filterProperty);
                sqlQuery += "  LOWER( c." + filterProperty + ") like LOWER( \'%" + filterValue + "%\')  AND";
            }
        }
        if (sqlQuery.endsWith("WHERE")) {
            sqlQuery = sqlQuery.substring(0, sqlQuery.length() - "WHERE".length());
        }
        if (sqlQuery.endsWith("AND")) {
            sqlQuery = sqlQuery.substring(0, sqlQuery.length() - "AND".length());
        }
        if (sortField != null && !"".equals(sortField)) {
            sqlQuery += " ORDER BY c." + sortField;
        }
        if ("DESCENDING".endsWith(sortOrder)) {
            sqlQuery += " DESC";
        }
        Query query = em.createQuery(sqlQuery, Customer.class);
        query.setFirstResult(first);
        query.setMaxResults(pageSize);
        if (log.isInfoEnabled()) {
            log.info("Make query in Customer table " + sqlQuery);
        }
        return query.getResultList();
    }

    @Override
    public Long getCustomersCount() {
        String sqlQuery = "SELECT COUNT(c) FROM Customer c";
        Query query = em.createQuery(sqlQuery, Customer.class);
        return (Long) query.getSingleResult();
    }

    @Override
    public Long getCustomersCount(Map<String, String> filters, Map<String, List<String>> parametrs) {
        String sqlQuery = "SELECT COUNT(c) FROM Customer c";
        if (!parametrs.isEmpty()) {
            sqlQuery += " WHERE";
            for (String paramProperty : parametrs.keySet()) {
                List<String> val = (List<String>) parametrs.get(paramProperty);
                if (val.size() > 1) {
                    sqlQuery += " ( ";
                    for (String val1 : val) {
                        sqlQuery += "  LOWER(c." + paramProperty + ") REGEXP LOWER('" + val1 + "') OR";
                    }
                    sqlQuery = sqlQuery.substring(0, sqlQuery.length() - "OR".length());
                    sqlQuery += " ) AND";
                } else {
                    sqlQuery += "   LOWER( c." + paramProperty + " ) REGEXP LOWER('" + val.get(0) + "')   AND";
                }
            }
        }
        if (filters != null && !filters.isEmpty()) {
            sqlQuery += " WHERE";
            for (String filterProperty : filters.keySet()) {
                String filterValue = filters.get(filterProperty);
                sqlQuery += "   LOWER( c." + filterProperty + ") like LOWER( \'%" + filterValue + "%\' ) AND";
            }
        }
        if (sqlQuery.endsWith("WHERE")) {
            sqlQuery = sqlQuery.substring(0, sqlQuery.length() - "WHERE".length());
        }
        if (sqlQuery.endsWith("AND")) {
            sqlQuery = sqlQuery.substring(0, sqlQuery.length() - "AND".length());
        }
        Query query = em.createQuery(sqlQuery, Customer.class);
        return (Long) query.getSingleResult();
    }

    @Override
    public List<Customer> search(Map<String, String> parameter) {
        String sqlQuery = "SELECT c FROM Customer c     ";
        if (!parameter.isEmpty()) {
            sqlQuery += " WHERE";
            Iterator it = parameter.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry) it.next();
                sqlQuery += "  LOWER(c." + pairs.getKey() + ") REGEXP LOWER('" + pairs.getValue() + "') AND";
                it.remove();
            }
            sqlQuery = sqlQuery.substring(0, sqlQuery.length() - " AND".length());
        }
        if (log.isInfoEnabled()) {
            log.info("Make query in Customer table " + sqlQuery);
        }
        return em.createQuery(sqlQuery).getResultList();
    }

    @Override
    public void persist(Customer c) {
        em.persist(c);
    }

    @Override
    public Customer find(long customerId) {
        Customer customer = em.find(Customer.class, customerId);
        em.refresh(customer);
        return customer;
    }
}

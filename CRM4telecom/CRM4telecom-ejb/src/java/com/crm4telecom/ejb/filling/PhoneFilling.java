package com.crm4telecom.ejb.filling;

import com.crm4telecom.enums.IpStatus;
import com.crm4telecom.jpa.Customer;
import com.crm4telecom.jpa.PhoneNumber;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class PhoneFilling extends FillingDatabase implements PhoneFillingRemote {

    private final Logger logger = LoggerFactory.getLogger(PhoneFilling.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    protected Boolean getDataAndAlloc(Customer customer) {
        String sqlQuery = "SELECT i FROM PHONE_NUMBERS i WHERE i.customerId IS NULL";
        Query query = em.createQuery(sqlQuery);
        List<PhoneNumber> phoneList = query.getResultList();
        if (phoneList.size() > 0) {
            PhoneNumber phoneNumber = phoneList.get(0);
            phoneNumber.setCustomerId(customer);
            phoneNumber.setStatus(IpStatus.RESEREVED);
            em.merge(phoneNumber);
            em.flush();

            logger.info("Customer : " + customer + " now resereve phone number : " + phoneNumber.getPhoneNumber());
            return true;
        } else {
            logger.warn("All phone numbers is locked, so customer : " + customer + " can't get new phone number");
            return false;
        }
    }

    @Override
    protected Boolean getDataAndActivate(Customer customer) {
        String sqlQuery = "SELECT i FROM PHONE_NUMBERS i WHERE i.customerId IS NULL AND i.status ='RESEREVED' ";
        Query query = em.createQuery(sqlQuery);
        List<PhoneNumber> phoneList = query.getResultList();
        if (phoneList.size() > 0) {
            PhoneNumber phoneNumber = phoneList.get(0);
            phoneNumber.setCustomerId(customer);
            phoneNumber.setStatus(IpStatus.ACTIVE);
            em.merge(phoneNumber);
            em.flush();
            logger.info("Customer : " + customer + " now get phone number : " + phoneNumber.getPhoneNumber());
            return true;
        } else {
            logger.warn("All phone numbers is locked, so customer : " + customer + " can't get new phone number");
            return false;
        }
    }

    @Override
    protected Boolean getDataAndFree(Customer customer) {
        String sqlQuery = "SELECT i FROM PhoneNumber i WHERE i.customerId = :customer";
        Query query = em.createQuery(sqlQuery).setParameter("customer", customer);
        List<PhoneNumber> ipList = query.getResultList();
        if (ipList.size() > 0) {
            PhoneNumber phoneNumber = ipList.get(0);
            phoneNumber.setCustomerId(null);
            phoneNumber.setStatus(IpStatus.UNPLUGGED);
            em.merge(phoneNumber);
            em.flush();
            logger.info("Customer : " + customer + " now get out phone number: " + phoneNumber.getPhoneNumber());
            return true;
        } else {
            logger.warn("No phone numbers of " + customer);
            return false;
        }
    }
}

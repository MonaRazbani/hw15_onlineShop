package dataAccess;

import models.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerDao extends DataBaseAccess{

    public void saveNewCustomer(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
    }
    public void updateCustomer(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(customer);
        transaction.commit();
        session.close();
    }
    }


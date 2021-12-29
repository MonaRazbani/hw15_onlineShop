package dataAccess.ProductDaos;

import dataAccess.DataBaseAccess;
import models.products.Electronic;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ElectronicProductsDao extends DataBaseAccess {

    public List<Electronic> displayElectronicProduct(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Electronic> query = session.createQuery("from Electronic fetch all properties ");
        List<Electronic> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }
    public void saveNewElectronicProduct(Electronic electronic) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(electronic);
        transaction.commit();
        session.close();
    }
    public void updateElectronic(Electronic electronic) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(electronic);
        transaction.commit();
        session.close();
    }

}


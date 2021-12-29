package dataAccess.ProductDaos;

import dataAccess.DataBaseAccess;
import models.products.ReadableItems;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ReadableItemProductsDao extends DataBaseAccess {

    public List<ReadableItems> displayReadableItemsProduct(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<ReadableItems> query = session.createQuery("from ReadableItems fetch all properties ");
        List<ReadableItems> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    public void saveNewReadableItemProduct(ReadableItems readableItems) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(readableItems);
        transaction.commit();
        session.close();
    }
}


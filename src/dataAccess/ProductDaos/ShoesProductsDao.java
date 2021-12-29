package dataAccess.ProductDaos;

import dataAccess.DataBaseAccess;
import models.enums.ProductStatus;
import models.enums.ShoesType;
import models.products.ReadableItems;
import models.products.Shoes;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoesProductsDao extends DataBaseAccess {

    public void saveNewShoesProduct(Shoes shoes) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(shoes);
        transaction.commit();
        session.close();
    }

    public List<Shoes> displayShoesProduct(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Shoes> query = session.createQuery("from Shoes fetch all properties ");
        List<Shoes> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }
}

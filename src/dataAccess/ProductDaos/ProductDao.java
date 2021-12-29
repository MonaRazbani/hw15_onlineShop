package dataAccess.ProductDaos;

import dataAccess.DataBaseAccess;
import models.products.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProductDao extends DataBaseAccess {


    public Product findProductById (int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Product product =session.get(Product.class,id);
        transaction.commit();
        session.close();
        return product;
    }


}

package dataAccess;

import models.Cart;
import models.CartItem;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PurchasesDao extends DataBaseAccess {


    public void addNewPurchase (CartItem cartItem) {
      Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(cartItem);
        transaction.commit();
        session.close();
    }

    public void updatePurchase (CartItem cartItem) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(cartItem);
        transaction.commit();
        session.close();
    }


    public List<CartItem> getPurchasesByCart(Cart cart) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<CartItem> query = session.createQuery("from Purchase purchase where purchase.cart=:cart");
        query.setParameter("cart",cart);
        List<CartItem> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    public void deletePurchase (CartItem cartItem) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(cartItem);
        transaction.commit();
        session.close();
    }

    public CartItem findPurchaseById (int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        CartItem cartItem =session.get(CartItem.class,id);
        transaction.commit();
        session.close();
        return cartItem;
    }


    public CartItem findPurchaseByIdAndCart(int purchaseId, Cart cart) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<CartItem> query = session.createQuery("FROM Purchase purchase WHERE purchase.id=:id and purchase.cart=:cart");
        query.setParameter("id", purchaseId);
        query.setParameter("cart", cart);
        CartItem result = query.getSingleResult();
        transaction.commit();
        session.close();
        return result;
    }

    public void updateGroupOfPurchases(List<CartItem> cartItemList) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        for (CartItem cartItem : cartItemList) {
            session.saveOrUpdate(cartItem);
        }
        transaction.commit();
        session.close();
    }
}





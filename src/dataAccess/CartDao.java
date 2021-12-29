package dataAccess;

import models.Cart;
import models.Customer;
import models.enums.CartStatus;
import models.enums.PaymentStatus;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class CartDao extends DataBaseAccess {

    public void UpdateCart(Cart cart){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(cart);
        transaction.commit();
        session.close();
    }

    public Cart findCartByCustomer(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Cart> query = session.createQuery("FROM Cart cart WHERE cart.customer=:customer ");
        query.setParameter("customer", customer);
        Cart result = query.getSingleResult();
        transaction.commit();
        transaction.commit();
        session.close();
        return result;
    }

    public void confirm(Cart cart, double totalCast) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        cart.setStatus(CartStatus.CONFIRM);
        cart.setPaymentCartStatus(PaymentStatus.PAID);
        cart.setTotalCost(totalCast);
        cart.getCustomer().setWallet(cart.getCustomer().getWallet()-totalCast);
        session.merge(cart.getCustomer());
        session.merge(cart);
        transaction.commit();
        session.close();
    }
    }


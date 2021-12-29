package services;

import controlValidValue.ControlValidValue;
import dataAccess.CartDao;
import dataAccess.CustomerDao;
import dataAccess.ProductDaos.ProductDao;
import dataAccess.PurchasesDao;
import models.Cart;
import models.CartItem;
import models.enums.PaymentStatus;
import models.enums.PurchaseStatus;
import models.Customer;
import models.products.Product;

import java.util.List;

public class CartOrderService {
    private ProductDao productDao = new ProductDao();
    private CartDao cartDao = new CartDao();
    private CustomerDao customerDao = new CustomerDao();
    private PurchasesDao purchasesDao = new PurchasesDao();
    private ControlValidValue controlValidValue = new ControlValidValue();

    public void addNewPurchaseToOrder(Customer customer, int productId, int numberOfOrder) {

        Product product = productDao.findProductById(productId);
        if (product != null) {
            if (numberOfOrder <= product.getWiki()) {
                CartItem cartItem = new CartItem();
                cartItem.setCart(customer.getCart());
               // cartItem.setPurchaseStatus(PurchaseStatus.NOT_CONFIRM);
                cartItem.setNumOfOrder(numberOfOrder);
                cartItem.setPurchasePrice(product.getCost() * numberOfOrder);
                customer.getCart().getListCartItems().add(cartItem);
                product.setWiki(product.getWiki() - numberOfOrder);
                customer.getCart().setPaymentCartStatus(PaymentStatus.NOT_PAID);
                customer.getCart().setTotalCost(customer.getCart().getTotalCost() + numberOfOrder * product.getCost());
                cartDao.UpdateCart(customer.getCart());
               // customerDao.updateCustomer(customer);
                System.out.println(product + "insert into cart ");
            } else System.out.println("productWiki is not enough ");
        } else System.out.println("id is wrong ");
    }

    public CartItem findPurchaseByIdAndCart(int purchaseId, Cart cart) {
        return purchasesDao.findPurchaseByIdAndCart(purchaseId, cart);
    }

    public void deletePurchaseFromCart(Customer customer, CartItem cartItem) {
        if (cartItem != null) {
            customer.getCart().setNumOfCartPurchases(cartItem.getNumOfOrder() - 1);
            customer.getCart().getListCartItems().remove(cartItem);
            customer.getCart().setTotalCost(customer.getCart().getTotalCost() - cartItem.getPurchasePrice());
            purchasesDao.deletePurchase(cartItem);
         //   cartDao.UpdateCart(customer.getCart());
            System.out.println("done");
        }
        System.out.println("find cartItem fail");
    }

    public double calculateCartTotalPrice(Customer customer) {
        return cartDao.findCartByCustomer(customer).getTotalCost();
    }

    public void confirmCart(Customer customer) {

        if (customer.getCart().getTotalCost() <= customer.getWallet()) {
            cartDao.confirm(customer.getCart(),customer.getWallet() - customer.getCart().getTotalCost());
            System.out.println("Done");
        } else
            System.out.println(" wallet balance is not enough \t \t wallet balance =" + customer.getWallet() + " but cart total price = " +
                    customer.getCart().getTotalCost());
    }

    public void displayPurchases(Customer customer) {
        List<CartItem> cartItemList = purchasesDao.getPurchasesByCart(customer.getCart());
        for (CartItem cartItem : cartItemList) {
            System.out.println(cartItem);
        }
    }
}



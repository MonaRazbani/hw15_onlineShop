package models;

import lombok.Data;
import models.enums.CartStatus;
import models.enums.PaymentStatus;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "cart")
    private List<CartItem> listCartItems = new ArrayList<>();
    private double totalCost ;
    private int numOfCartPurchases;
    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private CartStatus status;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentCartStatus;
    @UpdateTimestamp
    private Date confirmDate;

    @Override
    public String toString() {
        return "Cart{" +
                "totalCost=" + totalCost +
                ", numOfCartPurchases=" + numOfCartPurchases +
                ", customer=" + customer +
                ", status=" + status +
                ", paymentCartStatus=" + paymentCartStatus +
                '}';
    }
}

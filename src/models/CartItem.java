package models;

import lombok.Data;
import models.enums.PaymentStatus;
import models.enums.PurchaseStatus;

import javax.persistence.*;

@Data
@Entity

public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @ManyToOne(cascade = CascadeType.ALL)
    private Cart cart;
   // @Enumerated(EnumType.STRING)
   // private PurchaseStatus purchaseStatus;//delete it not necessary
   // @Enumerated(EnumType.STRING)
   // private PaymentStatus paymentStatus ;//delete it not necessary
    private int numOfOrder = 0 ;
    private double purchasePrice = 0 ;

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", numOfOrder=" + numOfOrder +
                ", purchasePrice=" + purchasePrice +
                '}';
    }
}

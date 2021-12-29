package models.products;

import lombok.Data;
import models.enums.ProductStatus;
import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private double cost ;
    private String name ;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    private String companyName ;
    private int wiki ;
    private int numOfPurchases= 0 ;



    @Override
    public String toString() {
        return
                " name : " + name +"  "+
                 "cost : " + cost + "  " + status +"  "+
                "company : " + companyName + "type : " ;
    }
}

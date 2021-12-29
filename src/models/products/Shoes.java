package models.products;

import lombok.Data;
import models.enums.ProductStatus;
import models.enums.ShoesType;

import javax.persistence.Entity;

@Data
@Entity
public class Shoes extends Product{
    private int size ;
    private ShoesType type;
    @Override
    public String toString() {
        return "Shoes :" + super.toString()+
                "  size :  " + size +
                "  " + type +
                '\n' ;
    }
}

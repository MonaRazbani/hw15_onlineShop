package models.products;

import lombok.Data;
import models.enums.ProductStatus;
import models.enums.ReadableItemsType;

import javax.persistence.Entity;

@Data
@Entity
public class ReadableItems extends Product{
    private ReadableItemsType type ;
    private String subject ;

    @Override
    public String toString() {
        return "ReadableItems :" + super.toString()+
                "  " + type + "  "+
                "subject : " + subject + '\'' +
                '\n';
    }
}

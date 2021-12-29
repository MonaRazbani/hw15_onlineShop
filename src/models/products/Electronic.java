package models.products;

import lombok.Data;
import models.enums.ElectronicType;
import models.enums.ProductStatus;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
@Data
@Entity
public class Electronic extends Product{
    private ElectronicType type;
    @Override
    public String toString() {
        return "Electronic :  " + super.toString()+"  "+
                type +
                '\n';
    }
}

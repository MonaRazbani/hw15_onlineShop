package models;

import lombok.Data;
import models.Cart;
import models.enums.CartStatus;
import models.enums.Gender;
import models.enums.PaymentStatus;

import javax.persistence.*;

@Data
@Entity
public class Customer   {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id ;
        private String firstName ;
        private String lastName ;
        private String userName ;
        private String password ;
        private String nationalCode ;
        private String phoneNumber ;
        @OneToOne(cascade = CascadeType.ALL)
        private Address address;
        @Enumerated(EnumType.STRING)
        private Gender gender;
        @OneToOne (cascade = CascadeType.ALL)
        private Cart cart;
        private double wallet ;

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address=" + address +
                ", gender=" + gender +
                ", cart=" + cart +
                ", wallet=" + wallet +
                '}';
    }
}

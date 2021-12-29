package services;

import controlValidValue.ControlValidValue;
import dataAccess.CartDao;
import dataAccess.CustomerDao;
import dataAccess.ProductDaos.ElectronicProductsDao;
import dataAccess.ProductDaos.ProductDao;
import dataAccess.ProductDaos.ReadableItemProductsDao;
import dataAccess.ProductDaos.ShoesProductsDao;
import models.Customer;
import models.products.Electronic;
import models.products.ReadableItems;
import models.products.Shoes;

import java.util.List;


public class OnlineShopService {

    private ControlValidValue controlValidValue = new ControlValidValue();
    private CustomerDao customerDao = new CustomerDao();
    private ElectronicProductsDao electronicProductsDao = new ElectronicProductsDao();
    private ShoesProductsDao shoesProductsDao = new ShoesProductsDao();
    private ReadableItemProductsDao readableItemProductsDao = new ReadableItemProductsDao();
    private CartDao cartDao = new CartDao();
    private ProductDao productDao = new ProductDao();



    public void addNewProductInDB(Electronic electronic) {
        electronicProductsDao.saveNewElectronicProduct(electronic);
    }

    public void addNewProductInDB(Shoes shoes) {
        shoesProductsDao.saveNewShoesProduct(shoes);
    }

    public void addNewCustomerInDB(Customer customer){
        customerDao.saveNewCustomer(customer);
    }


    public void DisplayProducts() {

        System.out.println("******* ELECTRONIC *******");
        List<Electronic> electronicProduct = electronicProductsDao.displayElectronicProduct();
        if (!electronicProduct.isEmpty()) {
            for (Electronic electronic : electronicProduct) {
                System.out.println("id :" + electronic.getId() + electronic);
            }
        } else System.out.println(" searching fail");


        System.out.println("******* SHOES *******");
        List<Shoes> shoesProduct = shoesProductsDao.displayShoesProduct();
        if (!shoesProduct.isEmpty()) {
            for (Shoes shoes : shoesProduct) {
                System.out.println("id :" + shoes.getId() + shoes);
            }
        } else System.out.println(" searching fail  ");


        System.out.println("******* READABLE ITEMS *******");
        List<ReadableItems> readableproduct = readableItemProductsDao.displayReadableItemsProduct();
        if (!readableproduct.isEmpty()) {
            for (ReadableItems readableItems : readableproduct) {
                System.out.println("id :" + readableItems.getId() + readableItems);
            }
        } else System.out.println(" searching fail ");
    }



}





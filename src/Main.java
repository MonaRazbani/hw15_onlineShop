import controlValidValue.ControlValidValue;
import exceptions.ExceptionInput;
import models.Address;
import models.Cart;
import models.CartItem;
import models.enums.*;
import models.Customer;
import models.products.Electronic;
import models.products.Shoes;
import services.CartOrderService;
import services.OnlineShopService;

import java.util.Scanner;


public class Main {
    final static Scanner scanner = new Scanner(System.in);
    final static OnlineShopService onlineShopService = new OnlineShopService();
    final static CartOrderService cartOrderService = new CartOrderService();
    final static ControlValidValue controlValidValue = new ControlValidValue();

    public static void main(String[] args) {
        mainMenu:
        while (true) {
            System.out.println("1: manager\n2:customer\n3:exit ");
            try {
                exitUserMenu:
                while (true) {
                    try {
                        int type = scanner.nextInt();
                        useRoleMenu:
                        switch (type) {
                            case 1: {
                                managerMenu:
                                while (true) {
                                    System.out.println("1:add new product \n2:exit");
                                    try {
                                        int managerType = scanner.nextInt();
                                        switch (managerType) {
                                            case 1: {
                                                addNewProductInOnlineShop();
                                                break;
                                            }
                                            case 2: {
                                                break exitUserMenu;

                                            }
                                            default: {
                                                System.out.println("wrong number");
                                                break useRoleMenu;
                                            }
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("enter valid number ");
                                        break managerMenu;
                                    }
                                }
                            }
                            case 2: {
                                customerSignUp :
                                while (true) {
                                    Customer customer = customerSignUp();
                                    if (customer != null) {
                                        buyMenu:
                                        while (true) {
                                            System.out.println("1:add new purchase \n2:delete purchase \n3:get total cost of cart \n4:confirm cart \n5:show cart \n6:exit ");
                                            try {
                                                int customerMenuType = scanner.nextInt();
                                                switch (customerMenuType) {
                                                    case 1: {
                                                        onlineShopService.DisplayProducts();
                                                        System.out.println("enter id : ");
                                                        int productId = scanner.nextInt();
                                                        System.out.println("number of order : ");
                                                        int numberOfOrder = scanner.nextInt();
                                                        cartOrderService.addNewPurchaseToOrder(customer, productId, numberOfOrder);
                                                        break;
                                                    }
                                                    case 2: {
                                                        cartOrderService.displayPurchases(customer);
                                                        System.out.println("enter id product : ");
                                                        int productId = scanner.nextInt();
                                                        CartItem cartItem = cartOrderService.findPurchaseByIdAndCart(productId, customer.getCart());
                                                        System.out.println("delete " + cartItem + "\n from cart are you sure? Y/N ");
                                                        char deleteIt = controlValidValue.getValidChar();
                                                        if (deleteIt == 'y') {
                                                            cartOrderService.deletePurchaseFromCart(customer, cartItem);
                                                        }
                                                        break;
                                                    }
                                                    case 3: {
                                                        cartOrderService.calculateCartTotalPrice(customer);
                                                        System.out.println(customer.getCart().getTotalCost());
                                                        break;
                                                    }
                                                    case 4: {
                                                        System.out.println("Are sure you want confirm carts ?Y/N");
                                                        char isConfirm = controlValidValue.getValidChar();
                                                        if (isConfirm == 'y') {
                                                            cartOrderService.confirmCart(customer);
                                                        }
                                                        break;
                                                    }
                                                    case 5: {
                                                        cartOrderService.displayPurchases(customer);
                                                    }
                                                    case 6: {
                                                        break useRoleMenu;
                                                    }
                                                    default: {
                                                        System.out.println("enter valid number");
                                                    }
                                                }

                                            } catch (NumberFormatException e) {
                                                System.out.println("enter valid number ");
                                                break;
                                            }
                                        }
                                    } else {
                                        System.out.println("sign up fail ");
                                        break customerSignUp;
                                    }
                                }
                            }
                            case 3: {
                                break exitUserMenu;
                            }
                            default: {
                                System.out.println("enter valid number ");
                                break exitUserMenu;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("enter valid number ");
                        break exitUserMenu;
                    }
                }
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private static void addNewElectronicProduct() throws ExceptionInput {
        boolean isContinue = true;
        boolean isValidInfo = true;
        Electronic electronic = new Electronic();
        while (isContinue) {
            try {
                if (isValidInfo) {
                    System.out.println("enter product info like this \n name,cost,companyName,wiki,Type(TELEVISION or RADIO)");
                    scanner.nextLine();
                    String productInfo = scanner.nextLine();
                    String[] arrInfo = productInfo.split(",");
                    String name = arrInfo[0];
                    double cost = Double.parseDouble(arrInfo[1]);
                    String companyName = arrInfo[2];
                    int wiki = Integer.parseInt(arrInfo[3]);
                    ElectronicType electronicType = ElectronicType.valueOf(arrInfo[4].toUpperCase());
                    electronic.setName(name);
                    electronic.setCompanyName(companyName);
                    electronic.setCost(cost);
                    electronic.setWiki(wiki);
                    if (wiki != 0) electronic.setStatus(ProductStatus.EXIST);
                    else electronic.setStatus(ProductStatus.NOT_EXIST);
                    onlineShopService.addNewProductInDB(electronic);
                }
                isContinue = !isValidInfo;
            } catch (IllegalArgumentException e) {
                System.out.println("type is not valid ");
                isValidInfo = false;
            }
        }
    }

    private static void addNewShoesProduct() throws ExceptionInput {
        boolean isContinue = true;
        boolean isValidInfo = true;
        Shoes shoes = new Shoes();

        while (isContinue) {
            try {
                if (isValidInfo) {

                    System.out.println("enter product info like this \n name,cost,companyName,wiki,Type(SPORT or FORMAL)");
                    scanner.nextLine();
                    String productInfo = scanner.nextLine();
                    String[] arrInfo = productInfo.split(",");
                    String name = arrInfo[0];
                    double cost = Double.parseDouble(arrInfo[1]);
                    String companyName = arrInfo[2];
                    int wiki = Integer.parseInt(arrInfo[3]);
                    ShoesType shoesType = ShoesType.valueOf(arrInfo[4].toUpperCase());
                    shoes.setName(name);
                    shoes.setCompanyName(companyName);
                    shoes.setCost(cost);
                    shoes.setWiki(wiki);
                    if (wiki != 0) shoes.setStatus(ProductStatus.EXIST);
                    else shoes.setStatus(ProductStatus.NOT_EXIST);

                    onlineShopService.addNewProductInDB(shoes);
                }
                isContinue = !isValidInfo;
            } catch (IllegalArgumentException exception) {
                System.out.println("type is not valid ");
                isValidInfo = false;
            }
        }
    }

    private static void addNewReadableItemProduct() throws ExceptionInput {

        boolean isValidInfo = true;
        Shoes shoes = new Shoes();
        while (true) {
            try {
                if (isValidInfo) {
                    System.out.println("enter product info like this \n name,cost,companyName,wiki, Type(JOURNAL or BOOK)");
                    scanner.nextLine();
                    String productInfo = scanner.nextLine();
                    String[] arrInfo = productInfo.split(",");
                    String name = arrInfo[0];
                    double cost = Double.parseDouble(arrInfo[1]);
                    String companyName = arrInfo[2];
                    int wiki = Integer.parseInt(arrInfo[3]);
                    ShoesType shoesType = ShoesType.valueOf(arrInfo[4].toUpperCase());
                    shoes.setName(name);
                    shoes.setCompanyName(companyName);
                    shoes.setCost(cost);
                    shoes.setWiki(wiki);
                    shoes.setType(shoesType);
                    if (wiki != 0) shoes.setStatus(ProductStatus.EXIST);
                    else shoes.setStatus(ProductStatus.NOT_EXIST);
                    onlineShopService.addNewProductInDB(shoes);
                }
            } catch (IllegalArgumentException exception) {
                System.out.println("type is not valid ");
                isValidInfo = false;
            }
        }
    }

    public static void addNewProductInOnlineShop() {
        System.out.println("add new product \n enter type :\n 1.electronic product \n 2. readable item product \n 3.shoes product");
        try {
            int type = scanner.nextInt();
            switch (type) {
                case 1: {
                    addNewElectronicProduct();
                    break;
                }
                case 2: {
                    addNewReadableItemProduct();
                    break;
                }
                case 3: {
                    addNewShoesProduct();
                    break;
                }
                default:
                    System.out.println("wrong number");
            }
        } catch (NumberFormatException | ExceptionInput e) {
            System.out.println("enter number");
        }
    }

    private static Customer customerSignUp() {

        boolean isContinue = true;
        boolean isValidInfo = true;
        boolean isValidAddress = true;
        Customer newCustomer = null;

        while (isContinue) {
            try {
                System.out.println("Please insert your Signup information like this :\n " +
                        "  first_name,last_name,username,password,national_code,phone,gender");
                scanner.nextLine();
                String info = scanner.nextLine();
                String[] arrInfo = info.split(",", 7);
                String firstName = arrInfo[0];
                String lastName = arrInfo[1];

                if (!controlValidValue.isValidName(firstName) && !controlValidValue.isValidName(lastName)) {
                    isValidInfo = false;
                   throw new ExceptionInput("first name or last name is not valid ");
                }
                String userName = arrInfo[2];
                if (!controlValidValue.isValidUserName(userName)) {
                    isValidInfo = false;
                    throw new ExceptionInput("username most include more then 3 words ");
                }
                String password = arrInfo[3];
                if (!controlValidValue.isValidPassword(password)) {
                    isValidInfo = false;
                    throw new ExceptionInput("password most include more than 3  number ");
                }
                String nationalCode = arrInfo[4];
                if (!controlValidValue.isValidUserNationalCode(nationalCode)) {
                    isValidInfo = false;
                    throw new ExceptionInput("nationalCode is not valid ");
                }
                String phoneNumber = arrInfo[5];
                if (!controlValidValue.isValidPhoneNumber(phoneNumber)) {
                    isValidInfo = false;
                   throw new ExceptionInput("wrong phone number");
                }
                Gender gender = Gender.valueOf(arrInfo[6]);
                if (gender.getVal(arrInfo[6]).equals("NOT_SET")) {
                    isValidInfo = false;
                   throw  new ExceptionInput("enter valid gender");
                }
                System.out.println("Please enter your address like this :\n " +
                        "city,Street,alley,house number,post_code");

                String addressInfo = scanner.nextLine();
                String[] arrAddressInfo = addressInfo.split(",", 5);
                String city = arrAddressInfo[0];

                if (!controlValidValue.isValidName(city)) {
                    isValidAddress = false;
                   throw  new ExceptionInput("wrong city");
                }
                String street = arrAddressInfo[1];
                String allay = arrAddressInfo[2];
                int houseNumber = Integer.parseInt(arrAddressInfo[3]);
                String postCode = arrAddressInfo[4];
                if (!controlValidValue.isValidPostCode(postCode)) {
                    isValidAddress = false;
                   throw  new ExceptionInput("wrong post code ");
                }

                if (isValidInfo && isValidAddress) {
                    newCustomer= new Customer();
                    newCustomer.setFirstName(firstName);
                    newCustomer.setLastName(lastName);
                    newCustomer.setNationalCode(nationalCode);
                    newCustomer.setGender(gender);
                    newCustomer.setPhoneNumber(phoneNumber);
                    newCustomer.setPassword(password);
                    newCustomer.setUserName(userName);
                        Address customerAddress = new Address();
                        customerAddress.setCity(city);
                        customerAddress.setStreet(street);
                        customerAddress.setAlley(allay);
                        customerAddress.setHouseNumber(houseNumber);
                        customerAddress.setPostCode(postCode);
                        newCustomer.setAddress(customerAddress);
                        Cart cart = new Cart();
                        cart.setStatus(CartStatus.NOT_CONFIRM);
                        cart.setNumOfCartPurchases(0);
                        cart.setTotalCost(0);
                        newCustomer.setCart(cart);
                        cart.setCustomer(newCustomer);
                        newCustomer.setWallet(10000);
                        onlineShopService.addNewCustomerInDB(newCustomer);
                        break;
                    }
            } catch (RuntimeException e) {
                System.out.println("wrong info");
            }
            isContinue = !isValidAddress && isValidInfo;
        }
        return newCustomer;
    }


}






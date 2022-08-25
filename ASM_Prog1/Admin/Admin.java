package ASM_Prog1.Admin;

import ASM_Prog1.Order.OrderList;
import ASM_Prog1.Product.*;

import java.util.Scanner;

public class Admin {
    private String username;
    private String password;

    public Admin(){
        this.username = "Team925";
        this.password = "123456";
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public void viewProducts(ProductList productList) {
        productList.displayProductList();
    }

    public void addProduct(ProductList productList){
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter the product name");
        String productName = inputObj.nextLine();
        System.out.println("Enter the product category");
        String category = inputObj.nextLine();
        System.out.println("Enter the product unit");
        String unit = inputObj.nextLine();
        System.out.println("Enter the product quantity");
        int quantity = inputObj.nextInt();
        System.out.println("Enter the product price");
        int price = inputObj.nextInt();
        Product newProduct = new Product(productName, category, unit, quantity, price);
        productList.addNewProduct(newProduct);
    }

    public void removeProduct(ProductList productList){
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter the product ID");
        String productID = inputObj.nextLine();
        productList.removeProduct(productID);
    }

    public void viewOrders(OrderList orderList){
        orderList.displayOrderList();
    }
}

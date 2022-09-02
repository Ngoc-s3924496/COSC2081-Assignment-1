package ASM_Prog1.Customer;

import ASM_Prog1.Product.*;

import java.util.*;

public class Customer {

    public Customer() {}
    public void viewProducts(ProductList productList) {
        productList.displayProductList();
    }
    public void searchProduct(ProductList productList){
        Product product = new Product("0", "0");
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter the product name");
        String productName = inputObj.nextLine();
        product.searchProduct(productName, productList);
    }
    public void sortPrice(ProductList productList){
        Product product = new Product("0", "0");
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter 1 for ascending order and 2 for descending order");
        int choice = inputObj.nextInt();
        while (choice > 2 || choice < 1){
            choice = inputObj.nextInt();
        }
        if (choice == 1){
            product.ascPrice(productList);
        }
        else{
            product.descPrice(productList);
        }
    }
}
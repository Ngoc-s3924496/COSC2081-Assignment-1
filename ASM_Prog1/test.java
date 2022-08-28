package ASM_Prog1;

import ASM_Prog1.Admin.Admin;
import ASM_Prog1.Order.OrderList;
import ASM_Prog1.Product.*;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class test {

    public static void main(String[] args) throws IOException, URISyntaxException {
        ProductList productList = new ProductList();
        OrderList orderList = new OrderList();
        pageStart(productList, orderList);
    }

    public static void pageStart(ProductList productList, OrderList orderList) throws IOException, URISyntaxException {
        System.out.println("COSC2081 GROUP ASSIGNMENT");
        System.out.println("STORE ORDER MANAGEMENT SYSTEM");
        System.out.println("Instructor: Mr. Minh Vu");
        System.out.println("Group: Team 925");
        System.out.println("Duong Vu Thanh Ngoc");
        System.out.println("Ngo Tran Bao Thach");
        System.out.println("Nguyen Minh Quan");
        System.out.println("Doan Khanh Luan");
        lineBreak();
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Choose your status: ");
        System.out.println("[1]: Admin");
        System.out.println("[2]: Customer");
        System.out.println("[3]: Log out");
        int input = inputObj.nextInt();  // Read user input
        while (input > 3 || input < 1) {
            System.out.println("Input is only from 1 to 4");
            System.out.println("Please retype your input: ");
            input = inputObj.nextInt();
        }
        switch (input) {
            case 1 -> pageAdmin(productList, orderList, 0);
            case 2 -> System.out.println("Hello");
            case 3 -> pageEnd(productList);
        }
    }

    public static void pageAdmin(ProductList productList, OrderList orderList, int verified) throws IOException, URISyntaxException {
        Admin admin = new Admin();
        Scanner inputObj = new Scanner(System.in);
        if (verified == 0){
            System.out.println(admin.getUsername() + " " + admin.getPassword());
            System.out.println("Enter admin username: ");
            String username = inputObj.nextLine();
            System.out.println("Enter admin password: ");
            String password = inputObj.nextLine();
            if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())){
                verified = 1;
            }
        }
        if (verified == 1){
            lineBreak();
            System.out.println("Welcome Admin!");
            System.out.println("[1]: View all orders");
            System.out.println("[2]: View user orders");
            System.out.println("[3]: View all products");
            System.out.println("[4]: View all members");
            System.out.println("[5]: View event");
            System.out.println("[6]: View today revenue");
            System.out.println("[7]: Add product");
            System.out.println("[8]: Remove product");
            System.out.println("[9: Add event");
            System.out.println("[10]: Update product price");
            System.out.println("[11]: Change order status");
            System.out.println("[12]: Log out");
            System.out.println("Please enter your action: ");
            int action = inputObj.nextInt();
            while (action >  4 ||  action < 1){
                System.out.println("Please enter your action: ");
                action = inputObj.nextInt();
            }
            switch (action) {
                case 1 -> {
                    admin.viewProducts(productList);
                    goBackAdmin(productList, orderList);
                }
                case 2 -> {
                    admin.addProduct(productList);
                    goBackAdmin(productList, orderList);
                }
                case 3 -> {
                    admin.removeProduct(productList);
                    goBackAdmin(productList, orderList);
                }
                case 4 -> {
                    admin.viewOrders(orderList);
                }
            }

        }
        else{
            System.out.println("Wrong username or password");
            lineBreak();
            pageStart(productList, orderList);
        }
    }

    public static void pageEnd(ProductList productList) throws IOException {
        productList.saveToCSV();
        lineBreak();
        System.out.println("Thank you for visiting us!");
    }

    public static void lineBreak(){
        for(int clear = 0; clear < 2; clear++)
        {
            System.out.println("\b") ;
        }
    }

    public static void goBackAdmin(ProductList productList, OrderList orderList) throws IOException, URISyntaxException {
        Scanner inputObj = new Scanner(System.in);
        System.out.println("[1] Go back     [2] Log out");
        int choice = inputObj.nextInt();
        while (choice > 2 || choice < 1) {
            System.out.println("Input is only 1 or 2");
            System.out.println("Please retype your input: ");
            choice = inputObj.nextInt();
        }

        if (choice == 1) {
            pageAdmin(productList, orderList, 1);
        }
        else{
            lineBreak();
            pageStart(productList, orderList);
        }
    }
}


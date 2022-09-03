package ASM_Prog1;

import ASM_Prog1.Admin.Admin;
import ASM_Prog1.Customer.Customer;
import ASM_Prog1.Event.EventList;
import ASM_Prog1.Member.*;
import ASM_Prog1.Order.OrderList;
import ASM_Prog1.Product.*;


import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException, ParseException {
        ProductList productList = new ProductList();
        OrderList orderList = new OrderList();
        EventList eventList = new EventList();
        MemberList memberList = new MemberList();
        pageStart(productList, orderList, eventList, memberList);
    }

    public static void pageStart(ProductList productList, OrderList orderList, EventList eventList,
                                 MemberList memberList) throws IOException, URISyntaxException, ParseException {
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
            case 1 -> pageAdmin(productList, orderList, eventList, memberList, 0);
            case 2 -> pageCustomer(productList, orderList, eventList, memberList);
            case 3 -> pageEnd(productList, orderList, eventList, memberList);
        }
    }

    public static void pageAdmin(ProductList productList, OrderList orderList, EventList eventList,
                                 MemberList memberList, int verified) throws IOException, URISyntaxException, ParseException {
        Admin admin = new Admin();
        Scanner inputObj = new Scanner(System.in);
        if (verified == 0){
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
            System.out.println("[6]: View daily revenue");
            System.out.println("[7]: Add product");
            System.out.println("[8]: Add event");
            System.out.println("[9]: Remove product");
            System.out.println("[10]: Remove event");
            System.out.println("[11]: Remove order");
            System.out.println("[12]: Update product price");
            System.out.println("[13]: Change order status");
            System.out.println("[14]: Log out");
            System.out.println("Please enter your action: ");
            int action = inputObj.nextInt();
            while (action >  14 ||  action < 1){
                System.out.println("Please enter your action: ");
                action = inputObj.nextInt();
            }
            switch (action) {
                case 1 -> {
                    admin.viewOrders(orderList);
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 2 -> {
                    admin.viewUserOrders();
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 3 -> {
                    admin.viewProducts(productList);
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 4 -> {
                    admin.viewMember(memberList);
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 5 -> {
                    admin.viewEvent(eventList);
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 6 -> {
                    admin.getDailyRevenue(orderList);
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 7 -> {
                    admin.addProduct(productList);
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 8 -> {
                    admin.addEvent(eventList);
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 9 -> {
                    admin.removeProduct(productList);
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 10 -> {
                    admin.removeEvent(eventList);
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 11 -> {
                    admin.removeOrder(orderList);
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 12 -> {
                    admin.updateProductPrice(productList);
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 13 -> {
                    admin.updateOrderStatus(orderList);
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 14 -> pageStart(productList, orderList, eventList, memberList);
            }

        }
        else{
            System.out.println("Wrong username or password");
            lineBreak();
            pageStart(productList, orderList, eventList, memberList);
        }
    }

    public static void pageCustomer(ProductList productList, OrderList orderList, EventList eventList,
                                    MemberList memberList) throws IOException, URISyntaxException, ParseException {
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Welcome to Team 925 shop!");
        System.out.println("[1]: Register membership");
        System.out.println("[2]: Login");
        System.out.println("[3]: View all products");
        System.out.println("[4]: Search product");
        System.out.println("[5]: Sort product by price");
        System.out.println("[6]: Log out");
        System.out.println("Choose your action");
        int action = inputObj.nextInt();
        while (action < 1 || action > 6){
            System.out.println("Enter your action: ");
            action = inputObj.nextInt();
        }
        Customer customer = new Customer();
        switch (action){
            case 1 -> {
                customer.registerMembership(memberList);
                goBackCustomer(productList, orderList, eventList, memberList);
            }
            case 2 -> {
                Member member = customer.logIn();
                if (Objects.equals(member.getUserID(), "0")){
                    System.out.println("Wrong username or password");
                    goBackCustomer(productList, orderList, eventList, memberList);
                }
                else{
                    pageMember(member, productList, orderList, eventList, memberList);
                }
            }
            case 3 -> {
                customer.viewProducts(productList);
                goBackCustomer(productList, orderList, eventList, memberList);
            }
            case 4 -> {
                Category c = new Category();
                c.displayCategory();
                customer.searchProduct();
                goBackCustomer(productList, orderList, eventList, memberList);
            }
            case 5 -> {
                customer.sortPrice();
                goBackCustomer(productList, orderList, eventList, memberList);
            }
            case 6 -> pageStart(productList, orderList, eventList, memberList);
        }
    }
    public static void pageMember(Member member, ProductList productList, OrderList orderList, EventList eventList,
                                  MemberList memberList) throws IOException, URISyntaxException, ParseException {
        Scanner inputObj = new Scanner(System.in);
        System.out.printf("Welcome %s!" + "\n", member.getUserName());
        System.out.println("[1]: View account information");
        System.out.println("[2]: View all products");
        System.out.println("[3]: Search product");
        System.out.println("[4]: Sort product by price");
        System.out.println("[5]: View events");
        System.out.println("[6]: Make an order");
        System.out.println("[7]: View all orders");
        System.out.println("[8]: View order by order ID");
        System.out.println("[9]: Log out");
        System.out.println("Enter your action: ");
        int action = inputObj.nextInt();
        while (action >  9 ||  action < 1){
            System.out.println("Please enter your action: ");
            action = inputObj.nextInt();
        }
        MemberCLI memberCLI = new MemberCLI();
        switch (action){
            case 1 -> {
                memberCLI.viewAccountInfo(member);
                goBackMember(member, productList, orderList, eventList, memberList);
            }
            case 2 -> {
                memberCLI.viewProducts(productList);
                goBackMember(member, productList, orderList, eventList, memberList);
            }
            case 3 -> {
                Category c = new Category();
                c.displayCategory();
                memberCLI.searchProduct();
                goBackMember(member, productList, orderList, eventList, memberList);
            }
            case 4 -> {
                memberCLI.sortPrice();
                goBackMember(member, productList, orderList, eventList, memberList);
            }
            case 5 -> {
                memberCLI.viewEvent(eventList);
                goBackMember(member, productList, orderList, eventList, memberList);
            }
            case 6 -> {
                memberCLI.makeOrder(member, productList, orderList, memberList);
                goBackMember(member, productList, orderList, eventList, memberList);
            }
            case 7 ->{
                memberCLI.viewOrders(member);
                goBackMember(member, productList, orderList, eventList, memberList);
            }
            case 8 ->{
                memberCLI.viewOrderByOrderID(member);
                goBackMember(member, productList, orderList, eventList, memberList);
            }
            case 9 -> pageStart(productList, orderList, eventList, memberList);
        }
    }

    public static void pageEnd(ProductList productList,OrderList orderList,EventList eventList, MemberList memberList)
            throws IOException {
        productList.saveToCSV();
        orderList.saveToCSV();
        eventList.saveToCSV();
        memberList.saveToCSV();
        lineBreak();
        System.out.println("Thank you for visiting us!");
    }

    public static void lineBreak(){
        for(int clear = 0; clear < 2; clear++)
        {
            System.out.println("\b") ;
        }
    }

    public static void goBackAdmin(ProductList productList, OrderList orderList, EventList eventList,
                                   MemberList memberList) throws IOException, URISyntaxException, ParseException {
        Scanner inputObj = new Scanner(System.in);
        System.out.println("[1] Go back     [2] Log out");
        int choice = inputObj.nextInt();
        while (choice > 2 || choice < 1) {
            System.out.println("Input is only 1 or 2");
            System.out.println("Please retype your input: ");
            choice = inputObj.nextInt();
        }

        if (choice == 1) {
            pageAdmin(productList, orderList, eventList, memberList, 1);
        }
        else{
            lineBreak();
            pageStart(productList, orderList, eventList, memberList);
        }
    }
    public static void goBackCustomer(ProductList productList, OrderList orderList, EventList eventList,
                                      MemberList memberList) throws IOException, URISyntaxException, ParseException {
        Scanner inputObj = new Scanner(System.in);
        System.out.println("[1] Go back     [2] Log out");
        int choice = inputObj.nextInt();
        while (choice > 2 || choice < 1) {
            System.out.println("Input is only 1 or 2");
            System.out.println("Please retype your input: ");
            choice = inputObj.nextInt();
        }

        if (choice == 1) {
            pageCustomer(productList, orderList, eventList, memberList);
        }
        else{
            lineBreak();
            pageStart(productList, orderList, eventList, memberList);
        }
    }
    public static void goBackMember(Member member, ProductList productList, OrderList orderList, EventList eventList,
                                      MemberList memberList) throws IOException, URISyntaxException, ParseException {
        Scanner inputObj = new Scanner(System.in);
        System.out.println("[1] Go back     [2] Log out");
        int choice = inputObj.nextInt();
        while (choice > 2 || choice < 1) {
            System.out.println("Input is only 1 or 2");
            System.out.println("Please retype your input: ");
            choice = inputObj.nextInt();
        }

        if (choice == 1) {
            pageMember(member,productList, orderList, eventList, memberList);
        }
        else{
            lineBreak();
            pageStart(productList, orderList, eventList, memberList);
        }
    }
}



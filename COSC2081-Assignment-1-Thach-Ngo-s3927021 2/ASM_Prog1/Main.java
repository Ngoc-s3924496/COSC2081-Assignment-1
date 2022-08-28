/*package ASM_Prog1;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException
    {
        String pathname = "src\\Product.csv";
	    ProductList productList = new ProductList();
        productList.displayProductList();

    }

    public static void pageStart(ProductList productList) throws IOException, URISyntaxException {
        System.out.println("COSC2081 GROUP ASSIGNMENT");
        System.out.println("STORE ORDER MANAGEMENT SYSTEM");
                System.out.println("Instructor: Mr. Minh Vu");
        System.out.println("Group: Team 925");
        System.out.println("Duong Vu Thanh Ngoc");
        System.out.println("Ngo Tran Bao Thach");
        System.out.println("Nguyen Minh Quan");
        System.out.println("Doan Khanh Luan");
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Choose your status: ");
        System.out.println("1: Admin");
        System.out.println("2: Customer");
        System.out.println("3: Log out");
        int input = inputObj.nextInt();  // Read user input
        while (input > 3 || input < 1){
            System.out.println("Input is only from 1 to 4");
            System.out.println("Please retype your input: ");
            input = inputObj.nextInt();
        }
        switch (input){
            case 1:
                pageAdmin(productList);
                break;
            case 2:
                pageCustomer(productList);
                break;
            case 3:
                pageEnd();
                break;
        }
    }

    public static void pageAdmin(ProductList productList) throws IOException, URISyntaxException {
        Admin admin = new Admin();
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter admin password: ");
        String username = inputObj.nextLine();
        String password = inputObj.nextLine();
        if (username == admin.getUsername() && password == admin.getPassword()){
            System.out.println("Welcome Admin!");
            System.out.println("1: View all orders");
            System.out.println("2: View user orders");
            System.out.println("3: View all products");
            System.out.println("4: View all members");
            System.out.println("5: View event");
            System.out.println("6: View today revenue");
            System.out.println("7: Add product");
            System.out.println("8: Remove product");
            System.out.println("9: Add event");
            System.out.println("10: Update product price");
            System.out.println("11: Change order status");
            System.out.println("12: Log out");
            System.out.println("Please enter your action: ");
            int action = inputObj.nextInt();
            while (action >  12 ||  action < 1){
                System.out.println("Please enter your action: ");
                action = inputObj.nextInt();
            }
            switch (action){
                case 1:
                    admin.viewOrders(productList);
                    break;
                case 2:
                    String userID = inputObj.nextLine();
                    admin.viewUserOrders(userID);
                    break;
                case 3:
                    admin.viewProducts();
                    break;
                case 4:
                    admin.viewMembers();
                    break;
                case 5:
                    admin.viewEvent();
                    break;
                case 6:
                    admin .viewDaily();
                    break;
                case 7:
                    admin.addProduct(productList);
                    break;
                case 8:
                    admin.removeProduct();
                    break;
                case 9:
                    admin.addEvent();
                    break;
                case 10:
                    admin.updatePrice();
                    break;
                case 11:
                    admin.changeOrderStatus(Order);
                    break;
                case 12:
                    pageStart(productList);
                    break;
            }

        }
        else{
            System.out.println("Wrong username or password");
            pageStart(productList);
        }
    }

    public static void pageMember(ProductList productList) throws IOException, URISyntaxException {
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter your username");
        String username = inputObj.nextLine();
        System.out.println("Enter your password");
        String password = inputObj.nextLine();
        if (checkuser(username, password)){
            System.out.printf("Welcome %s!", username);
            System.out.println("1: View account information");
            System.out.println("2: View all products");
            System.out.println("3: Make an order");
            System.out.println("4: View order");
            System.out.println("5: Log out");
            System.out.println("Enter your action: ");
            int action = inputObj.nextInt();
            while (action >  5 ||  action < 1){
                System.out.println("Please enter your action: ");
                action = inputObj.nextInt();
            }
            Member member = new Member();
            switch (action){
                case 1:
                    member.viewAccountInformation();
                    break;
                case 2:
                    member.viewProducts();
                    break;
                case 3:
                    member.makeOrder(Order);
                    break;
                case 4:
                    member.viewOrder(orderID);
                    break;
                case 5:
                    pageStart(productList);
                    break;
            }
        }
        else{
            System.out.println("Wrong username or password");
            pageCustomer(productList);
        }


    }

    public static void pageCustomer(ProductList productList) throws IOException, URISyntaxException {
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Welcome to Team 925 shop!");
        System.out.println("1: Register membership");
        System.out.println("2: Login");
        System.out.println("3: View product");
        System.out.println("4: Search product");
        System.out.println("5: Sort product by price");
        System.out.println("6: Log out");
        System.out.println("Choose your action");
        int action = inputObj.nextInt();
        while (action < 1 || action > 6){
            System.out.println("Enter your action: ");
            action = inputObj.nextInt();
        }
        Customer customer = new Customer();
        switch (action){
            case 1:
                customer.registerAccount();
                break;
            case 2:
                //customer.login(username, password)
                pageMember();
                break;
            case 3:
                customer.viewProduct();
                break;
            case 4:
                customer.searchProduct();
                break;
            case 5:
                customer.sortByPrice(Sort);
                break;
            case 6:
                pageStart(productList);
                break;
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

    public static void goBackAdmin(ProductList productList) throws IOException, URISyntaxException {
        Scanner inputObj = new Scanner(System.in);
        System.out.println("[1] Go back     [2] Log out");
        int choice = inputObj.nextInt();
        while (choice > 2 || choice < 1) {
            System.out.println("Input is only 1 or 2");
            System.out.println("Please retype your input: ");
            choice = inputObj.nextInt();
        }

        if (choice == 1) {
            pageAdmin(productList, 1);
        }
        else{
            lineBreak();
            pageStart(productList);
        }
    }
}*/

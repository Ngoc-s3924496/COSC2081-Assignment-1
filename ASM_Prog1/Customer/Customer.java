package ASM_Prog1.Customer;

import ASM_Prog1.Member.*;
import ASM_Prog1.Product.*;

import java.io.IOException;
import java.util.*;

public class Customer {

    public Customer() {}
    public void registerMembership(MemberList memberList) throws IOException {
        Member member = new Member("0");
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter your username");
        String username = inputObj.nextLine();
        while (member.usernameCheck(username, memberList)){
            System.out.println("Username already exist");
            System.out.println("Enter a different username");
            username = inputObj.nextLine();
        }
        System.out.println("Enter your password");
        String password = inputObj.nextLine();
        System.out.println("Re enter your password");
        String reEnteredPassword = inputObj.nextLine();
        while (reEnteredPassword.equalsIgnoreCase(password) != true){
            System.out.println("Password does not match");
            System.out.println("Re enter your password");
            reEnteredPassword = inputObj.nextLine();
        }
        System.out.println("Enter your full name");
        String fullName = inputObj.nextLine();
        while (member.validateFullName(fullName) == false){
            System.out.println("Full name contain only word");
            fullName = inputObj.nextLine();
        }
        System.out.println("Enter your phone number");
        String phoneNumber = inputObj.nextLine();
        while (member.validatePhoneNumber(phoneNumber) == false){
            System.out.println("Phone number contain only number and is longer than 5 digit");
            phoneNumber = inputObj.nextLine();
        }
        Member newMember = new Member(username,password,fullName,phoneNumber,"None", 0, memberList);
        memberList.addNewMember(newMember);
    }
    public Member logIn(MemberList memberList){
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter your username");
        String username = inputObj.nextLine();
        System.out.println("Enter your password");
        String password = inputObj.nextLine();
        Member member = new Member("0");
        member = member.logIn(username, password, memberList);
        return member;
    }
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
package ASM_Prog1.Customer;

import ASM_Prog1.Member.*;
import ASM_Prog1.Product.*;

import java.util.*;

public class Customer {

    public Customer() {}
    public void registerMembership(MemberList memberList) {
        Member member = new Member("0");
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter your username");
        String username = inputObj.nextLine();
        while (member.usernameCheck(username)){
            System.out.println("Username already exist");
            System.out.println("Enter a different username");
            username = inputObj.nextLine();
        }
        System.out.println("Enter your password");
        String password = inputObj.nextLine();
        System.out.println("Re enter your password");
        String reEnteredPassword = inputObj.nextLine();
        while (!reEnteredPassword.equalsIgnoreCase(password)){
            System.out.println("Password does not match");
            System.out.println("Re enter your password");
            reEnteredPassword = inputObj.nextLine();
        }
        System.out.println("Enter your full name");
        String fullName = inputObj.nextLine();
        while (!member.validateFullName(fullName)){
            System.out.println("Full name contain only word");
            fullName = inputObj.nextLine();
        }
        System.out.println("Enter your phone number");
        String phoneNumber = inputObj.nextLine();
        while (!member.validatePhoneNumber(phoneNumber)){
            System.out.println("Phone number contain only number and is longer than 5 digit");
            phoneNumber = inputObj.nextLine();
        }
        Member newMember = new Member(username,password,fullName,phoneNumber,"None", 0);
        memberList.addNewMember(newMember);
    }
    public Member logIn(){
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter your username");
        String username = inputObj.nextLine();
        System.out.println("Enter your password");
        String password = inputObj.nextLine();
        Member member = new Member("0");
        member = member.logIn(username, password);
        return member;
    }
    public void viewProducts(ProductList productList) {
        productList.displayProductList();
    }
    public void searchProduct(){
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter the product name or the product category");
        String productInfo = inputObj.nextLine();
        Product.searchProduct(productInfo);
    }
    public void sortPrice(){
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter 1 for ascending order and 2 for descending order");
        int choice = inputObj.nextInt();
        while (choice > 2 || choice < 1){
            choice = inputObj.nextInt();
        }
        if (choice == 1){
            Product.ascPrice();
        }
        else{
            Product.descPrice();
        }
    }
}
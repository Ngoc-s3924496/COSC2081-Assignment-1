package ASM_Prog1.Member;

import ASM_Prog1.Customer.Customer;
import ASM_Prog1.Event.Event;
import ASM_Prog1.Event.EventList;
import ASM_Prog1.Order.Order;
import ASM_Prog1.Order.OrderList;
import ASM_Prog1.Product.Product;
import ASM_Prog1.Product.ProductList;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class MemberCLI extends Customer {
    public void viewAccountInfo(Member member){
        System.out.println("UserID: " + member.getUserID());
        System.out.println("Username : " + member.getUserName());
        System.out.println("Full name: " + member.getFullName());
        System.out.println("Phone number: " + member.getPhoneNumber());
        System.out.println("Rank: " + member.getMembershipRanking());
        System.out.println("Total paid: " + member.getTotalPaid());
    }
    public void viewEvent(EventList eventList){
        eventList.displayEventList();
    }
    public void viewOrders(Member member) {
        String userID = member.getUserID();
        Order.getOrderByUser(userID);
    }
    public void viewOrderByOrderID(Member member) {
        String userID = member.getUserID();
        Order.getOrderByOrderID(userID);
    }
    public void makeOrder(Member member, ProductList productList, OrderList orderList,
                          MemberList memberList){
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter the product ID you want to buy");
        String productID = inputObj.nextLine();
        Product product = new Product("0", "0");
        product = product.getProductByID(productID);
        while (product.getProductID().equalsIgnoreCase("0")){
            System.out.println("No product found");
            System.out.println("Enter the product ID you want to buy");
            productID = inputObj.nextLine();
            product = product.getProductByID(productID);
        }
        System.out.println("Enter the amount you want to buy");
        int quantity = Integer.parseInt(inputObj.nextLine());
        while (quantity > product.getQuantity()){
            System.out.println("There is not enough available product");
            System.out.println("Enter the amount you want to buy");
            quantity = Integer.parseInt(inputObj.nextLine());
        }
        productList.updateQuantity(productID, product.getQuantity() - quantity);
        int eventDiscount = 0;
        boolean eventEffect = false;
        for (Event event: EventList.getEventList()){
            if (event.getEventStatus()){
                eventDiscount = event.getPercentageDiscount();
                eventEffect = true;
                break;
            }
        }
        int totalPaid = product.getPrice() * quantity * (100 - eventDiscount) / 100;
        if (Objects.equals(member.getMembershipRanking(), "Silver")){
            totalPaid = totalPaid * 95 / 100;
        }
        if (Objects.equals(member.getMembershipRanking(), "Gold")){
            totalPaid = totalPaid * 90 / 100;
        }
        if (Objects.equals(member.getMembershipRanking(), "Platinum")){
            totalPaid = totalPaid * 85 / 100;
        }
        String date = new SimpleDateFormat("d/M/yyyy").format(new Date());
        Order newOrder = new Order("delivering", member.getUserID(), member.getUserName(), product.getProductName(),
                quantity, totalPaid, eventEffect, date);
        orderList.addNewOrder(newOrder);
        member.setTotalPaid(member.getTotalPaid() + totalPaid);
        member.updateRank();
        memberList.updateMember(member);
    }
}

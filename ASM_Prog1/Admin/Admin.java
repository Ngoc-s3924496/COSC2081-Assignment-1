package ASM_Prog1.Admin;

import ASM_Prog1.Event.EventList;
import ASM_Prog1.Order.Order;
import ASM_Prog1.Order.OrderList;
import ASM_Prog1.Product.*;
import ASM_Prog1.Event.*;

import java.text.ParseException;
import java.util.Date;
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
    public void viewUserOrders(OrderList orderList){
        Order order = new Order();
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter User ID: ");
        String userID = inputObj.nextLine();
        order.getOrderbyUser(userID, orderList);
    }
    public void viewEvent(EventList eventList){
        eventList.displayEventList();
    }
    public void addEvent(EventList eventList) throws ParseException {
        Event event = new Event();
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter the event name");
        String eventName = inputObj.nextLine();
        System.out.println("Enter the percentage discount");
        int discount = Integer.parseInt(inputObj.nextLine());
        System.out.println("Enter the event start date");
        String startDate = inputObj.nextLine();
        System.out.println("Enter the event end date");
        String endDate = inputObj.nextLine();
        System.out.println("Enter the event description");
        String description = inputObj.nextLine();
        Event newEvent = new Event(eventName, discount, event.stringToDate(startDate), event.stringToDate(endDate), description);
        eventList.addNewEvent(newEvent);
    }
    public void removeEvent(EventList eventList){
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter the event name");
        String eventName = inputObj.nextLine();
        eventList.removeEvent(eventName);
    }
    public void updateProductPrice(ProductList productList){
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter the product ID");
        String productID = inputObj.nextLine();
        System.out.println("Enter the new price");
        int price = inputObj.nextInt();
        productList.updatePrice(productID, price);
    }
}

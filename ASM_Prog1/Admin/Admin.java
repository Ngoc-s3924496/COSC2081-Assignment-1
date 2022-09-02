package ASM_Prog1.Admin;

import ASM_Prog1.Event.EventList;
import ASM_Prog1.Member.MemberList;
import ASM_Prog1.Order.*;
import ASM_Prog1.Product.*;
import ASM_Prog1.Event.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Admin {
    private final String username;
    private final String password;

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
        Product product = new Product("0","0");
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter the product name");
        String productName = inputObj.nextLine();
        while (product.checkName(productName)) {
            System.out.println("Name already exist");
            System.out.println("Enter a different name");
            productName = inputObj.nextLine();
        }
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
    public void viewUserOrders(){
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter User ID: ");
        String userID = inputObj.nextLine();
        Order.getOrderByUser(userID);
    }
    public void viewEvent(EventList eventList){
        eventList.displayEventList();
    }
    public void viewMember(MemberList memberList){memberList.displayMemberList();}
    public void addEvent(EventList eventList) throws ParseException {
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter the event name");
        String eventName = inputObj.nextLine();
        System.out.println("Enter the percentage discount");
        int discount = Integer.parseInt(inputObj.nextLine());
        while (discount < 0 || discount > 100){
            System.out.println("Enter a valid percentage discount");
            discount = Integer.parseInt(inputObj.nextLine());
        }
        System.out.println("Enter the event start date");
        String startDate = inputObj.nextLine();
        while (dateValidate(startDate)){
            System.out.println("Enter a valid start date");
            startDate = inputObj.nextLine();
        }
        startDate = dateInput(startDate);
        System.out.println("Enter the event end date");
        String endDate = inputObj.nextLine();
        while (dateValidate(endDate)){
            System.out.println("Enter a valid end date");
            endDate = inputObj.nextLine();
        }
        endDate = dateInput(endDate);
        System.out.println("Enter the event description");
        String description = inputObj.nextLine();
        Event newEvent = new Event(eventName, discount, startDate, endDate, description);
        eventList.addNewEvent(newEvent);
    }
    public void removeEvent(EventList eventList){
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter the event ID");
        String eventID = inputObj.nextLine();
        eventList.removeEvent(eventID);
    }
    public void removeOrder(OrderList orderList){
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter the order ID");
        String orderID = inputObj.nextLine();
        orderList.removeOrder(orderID);
    }
    public void updateProductPrice(ProductList productList){
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter the product ID");
        String productID = inputObj.nextLine();
        System.out.println("Enter the new price");
        int price = inputObj.nextInt();
        productList.updatePrice(productID, price);
    }
    public void updateOrderStatus(OrderList orderList){
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter the order ID");
        String orderID = inputObj.nextLine();
        System.out.println("Enter the update status");
        String status = inputObj.nextLine();
        orderList.updateStatus(orderID, status);
    }
    public void getDailyRevenue(OrderList orderList) {
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter the date to get the daily revenue");
        String date = inputObj.nextLine();
        while (dateValidate(date)){
            System.out.println("Enter a valid date to get the daily revenue");
            date = inputObj.nextLine();
        }
        date = dateInput(date);
        Daily daily = new Daily(date, orderList);
        System.out.println(daily.getDailyRevenue());
    }
    public boolean dateValidate(String date) {
        String[] dateComponent = date.split("/");
        String day = dateComponent[0].replaceFirst("^0*", "");
        String month = dateComponent[1].replaceFirst("^0*", "");
        String year = dateComponent[2].replaceFirst("^0*", "");
        if (Integer.parseInt(day) < 10){
            date = String.format("0%s" + "/" + "%s" + "/" + "%s", day, month, year);
        }
        else if(Integer.parseInt(month) < 10){
            date = String.format("%s" + "/" + "0%s" + "/" + "%s", day, month, year);
        }
        else if(Integer.parseInt(day) < 10 && Integer.parseInt(month) < 10){
            date = String.format("0%s" + "/" + "0%s" + "/" + "%s", day, month, year);
        }
        else{
            date = String.format("%s" + "/" + "%s" + "/" + "%s", day, month, year);
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        try{
            format.parse(date);
        } catch (ParseException e){
            return true;
        }
        return false;
    }
    public String dateInput(String date){
        String[] dateComponent = date.split("/");
        String day = dateComponent[0].replaceFirst("^0*", "");
        String month = dateComponent[1].replaceFirst("^0*", "");
        String year = dateComponent[2].replaceFirst("^0*", "");
        date = day + "/" + month + "/" + year;
        return date;
    }
}

package ASM_Prog1.Order;

import java.util.ArrayList;
import java.util.Scanner;

public class Order {
    private final String orderID, userID;
    private String status, product, userName, dateTime;
    private int totalPaid, quantity;
    private boolean eventEffect;

    public Order(String orderID, String userID){
        this.orderID = orderID;
        this.userID = userID;
    }
    public Order(String status, String userID, String userName, String product, int quantity,
                 int totalPaid, boolean eventEffect, String dateTime) {
        String orderID = "O" + ((int) (Math.random() * 999999) + 1);
        while (checkID(orderID)){
            orderID = "O" + ((int) (Math.random() * 999999) + 1);
        }
        this.orderID = orderID;
        this.status = status;
        this.userID = userID;
        this.userName = userName;
        this.product = product;
        this.quantity = quantity;
        this.totalPaid = totalPaid;
        this.eventEffect = eventEffect;
        this.dateTime = dateTime;
    }
    public boolean checkID(String ID) {
        for (Order order : OrderList.getOrderList()) {
            if (ID.equals(order.getOrderID())) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Order> getOrderByDate(String date){
        ArrayList<Order> dailyOrder = new ArrayList<>();
        for (Order strings : OrderList.getOrderList()) {
            String orderDate = strings.getOrderDateTime();
            if (date.equalsIgnoreCase(orderDate)) {
                dailyOrder.add(strings);
            }
        }
        return dailyOrder;
    }

    public static void getOrderByUser(String userID){
        ArrayList<Order> userOrder = new ArrayList<>();
        for (Order order: OrderList.getOrderList()){
            if(userID.equalsIgnoreCase(order.getOrderUserID())){
                userOrder.add(order);
            }
        }
        if (userOrder.size() == 0){
            System.out.println("User had no order or wrong User ID");
        }
        else{
            System.out.println("[ORDER_ID,STATUS,USER_ID,USERNAME,PRODUCT,QUANTITY,TOTAL_PAID,EVENT_EFFECT,DATETIME]");
            for(Order order: userOrder){
                System.out.println(order);
            }
        }
    }

    public static void getOrderByOrderID(String userID) {
        Scanner inputObj = new Scanner(System.in);
        ArrayList<Order> userOrder = new ArrayList<>();
        for (Order order: OrderList.getOrderList()){
            if(userID.equalsIgnoreCase(order.getOrderUserID())){
                userOrder.add(order);
            }
        }
        System.out.println("Enter the order ID to search");
        String orderID = inputObj.next();
        if (userOrder.size() == 0){
            System.out.println("User had no order or wrong User ID");
        } else {
            boolean existed = false;
            for (Order order: userOrder) {
                if (order.getOrderID().equals(orderID)) {
                    existed = true;
                    System.out.println("[ORDER_ID,STATUS,USER_ID,USERNAME,PRODUCT,QUANTITY,TOTAL_PAID,EVENT_EFFECT,DATETIME]");
                    System.out.println(order);
                }
            }
            if (!existed) {
                System.out.println("There are no orders having such order ID");
            }
        }
    }

    public static int getRevenueByDate(String date){
        int dailyRevenue = 0;
        for (Order strings : OrderList.getOrderList()) {
            String orderDate = strings.getOrderDateTime();
            if (date.equalsIgnoreCase(orderDate)) {
                int orderTotal = strings.getOrderTotalPaid();
                dailyRevenue += orderTotal;
            }
        }
        return dailyRevenue;
    }

    public String CSVString()
    {
        return String.format("%s,%s,%s,%s,%s,%d,%d,%b,%s", getOrderID(), getOrderStatus(), getOrderUserID(), getOrderUserName(),
                getOrderProduct(), getProductQuantity(), getOrderTotalPaid(), getOrderEventEffect(), getOrderDateTime());
    }
    @Override
    public String toString()
    {
        return "[" +
                orderID + ", " + status + ", " + userID + ", " + userName +
                ", " + product + ", " + quantity + ", " + totalPaid + ", " + eventEffect + ", " + dateTime + ']';
    }

    public String getOrderID(){
        return this.orderID;
    }
    public String getOrderStatus(){
        return this.status;
    }
    public void setOrderStatus(String status){
        this.status = status;
    }
    public String getOrderUserID(){
        return this.userID;
    }
    public String getOrderUserName(){
        return this.userName;
    }
    public void setOrderUserName(String userName){
        this.userName = userName;
    }
    public String getOrderProduct(){
        return this.product;
    }
    public void setOrderProduct(String product){
        this.product = product;
    }
    public int getProductQuantity(){return this.quantity;}
    public void setProductQuantity(int quantity){this.quantity = quantity;}
    public int getOrderTotalPaid(){
        return this.totalPaid;
    }
    public void setOrderTotalPaid(int totalPaid){
        this.totalPaid = totalPaid;
    }
    public boolean getOrderEventEffect(){
        return this.eventEffect;
    }
    public void setOrderEventEffect(boolean eventEffect){
        this.eventEffect = eventEffect;
    }
    public String getOrderDateTime(){
        return this.dateTime;
    }
    public void setOrderDateTime(String dateTime){
        this.dateTime = dateTime;
    }
}

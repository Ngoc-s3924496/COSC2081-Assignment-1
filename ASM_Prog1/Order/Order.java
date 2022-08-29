package ASM_Prog1.Order;

import ASM_Prog1.Product.Product;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderID, status, userID, product, userName, dateTime;
    private int totalPaid;
    private boolean eventEffect;

    public Order(){}
    public Order(String orderID, String status, String userID, String userName, String product, int totalPaid, boolean eventEffect, String dateTime) {
        this.orderID = orderID;
        this.status = status;
        this.userID = userID;
        this.userName = userName;
        this.product = product;
        this.totalPaid = totalPaid;
        this.eventEffect = eventEffect;
        this.dateTime = dateTime;
    }

    public static ArrayList<Order> getOrderByDate(String day, String month, String year){
        String date = day + "/" + month + "/" + year;
        ArrayList<Order> dailyOrder = new ArrayList<>();
        ArrayList<Order> orderList = OrderList.getOrderList();
        for (Order strings : orderList) {
            String orderDate = strings.getOrderDateTime();
            if (date.equalsIgnoreCase(orderDate)) {
                dailyOrder.add(strings);
            }
        }
        return dailyOrder;
    }

    public static void getOrderbyUser(String userID, OrderList orderList){
        ArrayList<Order> userOrder = new ArrayList<>();
        for (Order order: orderList.getOrderList()){
            if(userID.equalsIgnoreCase(order.getOrderUserID())){
                userOrder.add(order);
            }
        }
        if (userOrder.size() == 0){
            System.out.println("User had no order or wrong User ID");
        }
        else{
            for(Order order: userOrder){
                System.out.println(order);
            }
        }
    }

    public static int getRevenueByDate(String day, String month, String year){
        String date = day + "/" + month + "/" + year;
        int dailyRevenue = 0;
        ArrayList<Order> orderList = OrderList.getOrderList();
        for (Order strings : orderList) {
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
        return String.format("%s, %s, %s, %s, %d, %d", getOrderID(), getOrderStatus(), getOrderUserID(), getOrderUserName(),
                getOrderProduct(), getOrderTotalPaid(), getOrderEventEffect(), getOrderDateTime());
    }
    @Override
    public String toString()
    {
        return "[" +
                orderID + ", " + status + ", " + userID + ", " + userName +
                ", " + totalPaid + ", " + eventEffect + ", " + dateTime + ']';
    }

    public String getOrderID(){
        return this.orderID;
    }
    public void setOrderID(String ID){
        this.orderID = ID;
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
    public void setOrderUserID(String userID){
        this.userID = userID;
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

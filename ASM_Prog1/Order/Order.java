package ASM_Prog1.Order;

import java.util.ArrayList;

public class Order {
    private final String orderID, userID;
    private String status, product, userName, dateTime;
    private int totalPaid;
    private boolean eventEffect;

    public Order(String orderID, String userID){
        this.orderID = orderID;
        this.userID = userID;
    }
    public Order(String status, String userID, String userName, String product, int totalPaid, boolean eventEffect, String dateTime) {
        String orderID = "O" + ((int) (Math.random() * 999999) + 1);
        this.orderID = orderID;
        this.status = status;
        this.userID = userID;
        this.userName = userName;
        this.product = product;
        this.totalPaid = totalPaid;
        this.eventEffect = eventEffect;
        this.dateTime = dateTime;
    }

    public static ArrayList<Order> getOrderByDate(String date, OrderList orderList){
        ArrayList<Order> dailyOrder = new ArrayList<>();
        for (Order strings : orderList.getOrderList()) {
            String orderDate = strings.getOrderDateTime();
            if (date.equalsIgnoreCase(orderDate)) {
                dailyOrder.add(strings);
            }
        }
        return dailyOrder;
    }

    public static void getOrderByUser(String userID, OrderList orderList){
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
            System.out.println("[ORDER_ID,STATUS,USER_ID,USERNAME,PRODUCT,TOTAL_PAID,EVENT_EFFECT,DATETIME]");
            for(Order order: userOrder){
                System.out.println(order);
            }
        }
    }

    public static int getRevenueByDate(String date, OrderList orderList){
        int dailyRevenue = 0;
        for (Order strings : orderList.getOrderList()) {
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
        return String.format("%s,%s,%s,%s,%s,%d,%b,%s", getOrderID(), getOrderStatus(), getOrderUserID(), getOrderUserName(),
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

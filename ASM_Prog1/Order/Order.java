package ASM_Prog1.Order;

import ASM_Prog1.Product.Product;

import java.util.List;

public class Order {
    private String orderID, status, userID, userName, dateTime;
    private List<Product> products;
    private int totalPaid;
    private boolean eventEffect;
    public Order(String orderID, String status, String userID, String userName, List<Product> products, int totalPaid, boolean eventEffect, String dateTime) {
        this.orderID = orderID;
        this.status = status;
        this.userID = userID;
        this.userName = userName;
        this.products = products;
        this.totalPaid = totalPaid;
        this.eventEffect = eventEffect;
        this.dateTime = dateTime;
    }

    @Override
    public String toString()
    {
        return "Order{" +
                "OrderID='" + orderID + '\'' +
                ", status='" + status + '\'' +
                ", userID='" + userID + '\'' +
                ", username='" + userName + '\'' +
                ", total paid=" + totalPaid +
                ", event effect=" + eventEffect + '\'' +
                ", date=" + dateTime +
                '}';
    }
}

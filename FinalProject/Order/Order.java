package FinalProject.Order;

import FinalProject.Product.Product;

import java.util.List;

public class Order {
    private String orderID, status, userID, userName, dateTime;
    private List<Product> products;
    private int totalPaid;
    private boolean eventEffect;
    public Order(String orderID, String status, String userID, String userName, List<Product> products, int totalPaid, boolean eventEffect, String dateTime) {
    }
}

package ASM_Prog1.Order;

import ASM_Prog1.Product.Product;

import java.util.List;

public class Order {
    private String orderID, status, userID, userName, dateTime;
    private List<Product> products;
    private double totalPaid;
    private boolean eventEffect;
    public Order(String orderID, String status, String userID, String userName, List<Product> products, double totalPaid, boolean eventEffect, String dateTime) {
        this.orderID = orderID;
        this.status = status;
        this.userID = userID;
        this.userName = userName;
        this.products = products;
        this.totalPaid = totalPaid;
        this.eventEffect = eventEffect;
        this.dateTime = dateTime;
    }

    public static List<String> getOrderByDate(String day, String month, String year){
        String date = day + "/" + month + "/" + year;
        List<String> dailyOrder = null;
        List<List<String>> orderList = OrderList.getOrderList();
        for (List<String> strings : orderList) {
            String orderDate = strings.get(7);
            if (date.equalsIgnoreCase(orderDate)) {
                dailyOrder.add(strings.get(1));
            }
        }
        return dailyOrder;
    }

    public static int getRevenueByDate(String day, String month, String year){
        String date = day + "/" + month + "/" + year;
        int dailyRevenue = 0;
        List<List<String>> orderList = OrderList.getOrderList();
        for (List<String> strings : orderList) {
            String orderDate = strings.get(7);
            if (date.equalsIgnoreCase(orderDate)) {
                String orderTotalString = strings.get(5);
                int orderTotalInt = Integer.parseInt(orderTotalString);
                dailyRevenue += orderTotalInt;
            }
        }
        return dailyRevenue;
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

package ASM_Prog1.Order;

import java.util.ArrayList;

public class Daily {

    private final int revenue;

    public Daily(String date, OrderList orderList){

        ArrayList<Order> executedOrder = Order.getOrderByDate(date);

        this.revenue = Order.getRevenueByDate(date);
    }
    public int getDailyRevenue(){return revenue;}
    }

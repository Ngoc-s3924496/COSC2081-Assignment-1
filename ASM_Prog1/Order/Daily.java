package ASM_Prog1.Order;

import java.util.ArrayList;
import java.util.List;

public class Daily {

    private int revenue;

    private ArrayList<Order> executedOrder;

    public Daily(String date, OrderList orderList){

        this.executedOrder = Order.getOrderByDate(date, orderList);

        this.revenue = Order.getRevenueByDate(date, orderList);
    }
    public int getDailyRevenue(){return revenue;}
    }

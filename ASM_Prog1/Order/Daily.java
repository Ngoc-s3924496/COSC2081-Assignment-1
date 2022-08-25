package ASM_Prog1.Order;

import java.util.List;

public class Daily {

    private String date;

    private double revenue;

    private List<String> executedOrder;

    public Daily(String day, String month, String year){

        this.date = day + "/" + month + "/" + year;

        this.executedOrder = Order.getOrderByDate(day, month, year);

        this.revenue = Order.getRevenueByDate(day, month, year);
    }
}

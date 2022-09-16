/*
Author: Doan Khanh Luan
Student ID: s3926375
Instructor: Dr. Minh Vu
Version: OpenJDK 1.8 (Java 18)
Date: 05/09/2022
Purpose: Daily class contain methods returning daily revenue of the shop
 */
package ASM_Prog1.Order;

import java.util.ArrayList;

public class Daily {

    private final int revenue;

    //This method return the daily revenue of the shop
    public Daily(String date, OrderList orderList){

        ArrayList<Order> executedOrder = Order.getOrderByDate(date);

        this.revenue = Order.getRevenueByDate(date);
    }
    public int getDailyRevenue(){return revenue;}
    }

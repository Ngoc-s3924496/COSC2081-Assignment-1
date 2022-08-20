package ASM_Prog1.Order;

import ASM_Prog1.Product.ProductList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderList {
    private static List<List<String>> orderList;

    private List<List<String>> loadOrderList() throws IOException
    {
        String filePath = "src\\Data\\orders.csv";
        return ProductList.read(filePath);
    }

    public OrderList() throws IOException
    {
        this.orderList = loadOrderList();
    }

    public static List<List<String>> getOrderList() {
        return orderList;
    }


    public void displayOrderList()
    {
        for (List<String> strings : this.orderList)
        {
            System.out.println(strings);
        }
    }
}

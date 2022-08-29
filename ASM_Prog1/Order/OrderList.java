package ASM_Prog1.Order;

import ASM_Prog1.Product.Product;
import ASM_Prog1.Product.ProductList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderList {
    private static ArrayList<Order> orderList = new ArrayList<>();
    private static final String delimiter = ",";
    private ArrayList<Order> setOrderList() throws IOException
    {
        String filePath = "src/Data/orders.csv";
        return OrderList.readFile(filePath);
    }

    public OrderList() throws IOException
    {
        this.orderList = setOrderList();
    }

    public static ArrayList<Order> getOrderList() {
        return orderList;
    }
    private static ArrayList<Order> readFile(String csvFile) throws IOException
    {
        ArrayList<Order> finalArr = new ArrayList<>();
        File file = new File(csvFile);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        List<String> arr;
        bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null)
        {
            arr = List.of(line.split(delimiter));
            Order order = new Order();
            order.setOrderID(arr.get(0));
            order.setOrderStatus(arr.get(1));
            order.setOrderUserID(arr.get(2));
            order.setOrderUserName(arr.get(3));
            order.setOrderProduct(arr.get(4));
            order.setOrderTotalPaid(Integer.parseInt(arr.get(5)));
            order.setOrderEventEffect(Boolean.parseBoolean(arr.get(6)));
            order.setOrderDateTime(arr.get(7));
            finalArr.add(order);
        }
        bufferedReader.close();
        return finalArr;
    }

    public void displayOrderList()
    {
        System.out.println("[ORDERID, STATUS, USERID, USERNAME, PRODUCT, TOTALPAID, EVENTEFFECT, DATETIME]");
        for (Order strings : this.orderList)
        {
            System.out.println(strings);
        }
    }
}
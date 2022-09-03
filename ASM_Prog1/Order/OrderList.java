package ASM_Prog1.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderList {
    private static ArrayList<Order> orderList = new ArrayList<>();
    private static final String delimiter = ",";
    private ArrayList<Order> setOrderList() throws IOException
    {
        String filePath = "Data/orders.csv";
        return OrderList.readFile(filePath);
    }

    public OrderList() throws IOException
    {
        orderList = setOrderList();
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
            Order order = new Order(arr.get(0), arr.get(2));
            order.setOrderStatus(arr.get(1));
            order.setOrderUserName(arr.get(3));
            order.setOrderProduct(arr.get(4));
            order.setProductQuantity(Integer.parseInt(arr.get(5)));
            order.setOrderTotalPaid(Integer.parseInt(arr.get(6)));
            order.setOrderEventEffect(Boolean.parseBoolean(arr.get(7)));
            order.setOrderDateTime(arr.get(8));
            finalArr.add(order);
        }
        bufferedReader.close();
        return finalArr;
    }

    public void displayOrderList()
    {
        System.out.println("[ORDER_ID,STATUS,USER_ID,USERNAME,PRODUCT,QUANTITY,TOTAL_PAID,EVENT_EFFECT,DATETIME]");
        for (Order strings : orderList)
        {
            System.out.println(strings);
        }
    }
    public void addNewOrder(Order order){
        orderList.add(order);
        System.out.println("Order added successfully");
    }
    public void removeOrder(String orderID){
        for (Order order : orderList)
        {
            if (Objects.equals(order.getOrderID(), orderID))
            {
                orderList.remove(order);
                System.out.println("Order remove successfully");
                return;
            }
        }
        System.out.println("No order found.");
    }
    public void updateStatus(String orderID, String status)
    {
        for (Order order : orderList)
        {
            if (Objects.equals(order.getOrderID(), orderID))
            {
                order.setOrderStatus(status);
                System.out.println("Status updated successfully");
                return;
            }
        }
        System.out.println("No order found.");
    }
    public void saveToCSV() throws IOException
    {
        File fileSrc = new File("Data/orders.csv");
        FileWriter fileWriterSrc = new FileWriter(fileSrc);
        fileWriterSrc.write("ORDER_ID,STATUS,USER_ID,USERNAME,PRODUCT,QUANTITY,TOTAL_PAID,EVENT_EFFECT,DATETIME" + "\n");
        for (Order order : orderList)
        {
            fileWriterSrc.write(order.CSVString() + "\n");
        }
        fileWriterSrc.close();
    }
}
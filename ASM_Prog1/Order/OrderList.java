package ASM_Prog1.Order;

import ASM_Prog1.Product.ProductList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderList {
    private static final String delimiter = ",";
    private List<List<String>> orderList = new ArrayList<>();

    private List<List<String>> loadOrderList() throws IOException
    {
        String filePath = "src\\Data\\orders.csv";
        return ProductList.read(filePath);
    }

    public OrderList() throws IOException
    {
        this.orderList = loadOrderList();
    }

    public List<List<String>> getOrderListList()
    {
        return orderList;
    }


    private static List<List<String>> read(String csvFile) throws IOException
    {
        List<List<String>> finalArr = new ArrayList<>();
        File file = new File(csvFile);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        List<String> arr;
        while ((line = bufferedReader.readLine()) != null)
        {
            arr = List.of(line.split(delimiter));
            finalArr.add(arr);
        }
        bufferedReader.close();
        return finalArr;
    }

    public void displayOrderList()
    {
        for (List<String> strings : this.orderList)
        {
            System.out.println(strings);
        }
    }
}

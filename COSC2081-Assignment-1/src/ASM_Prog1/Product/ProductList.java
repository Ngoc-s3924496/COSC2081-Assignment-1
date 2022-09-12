/*
Author: Nguyen Minh Quan
Student ID: s3927181
Instructor: Dr. Minh Vu
Version: OpenJDK 1.8 (Java 18)
Date: 02/09/2022
Purpose: ProductList class contains methods that work with list of product in the database
 */
package ASM_Prog1.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductList
{
    private static final String delimiter = ",";
    private static ArrayList<Product> productList = new ArrayList<>();

    private ArrayList<Product> setProductList() throws IOException
    {
        String filePath = "src/Data/products.csv";
        return ProductList.readFile(filePath);
    }

    public ProductList() throws IOException
    {
        productList = setProductList();
    }

    public static ArrayList<Product> getProductList()
    {
        return productList;
    }


    private static ArrayList<Product> readFile(String csvFile) throws IOException
    {
        ArrayList<Product> finalArr = new ArrayList<>();
        File file = new File(csvFile);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        List<String> arr;
        bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null)
        {
            arr = List.of(line.split(delimiter));
            Product product = new Product(arr.get(0), arr.get(1));
            product.setCategory(arr.get(2));
            product.setUnit(arr.get(3));
            product.setQuantity(Integer.parseInt(arr.get(4)));
            product.setPrice(Integer.parseInt(arr.get(5)));
            finalArr.add(product);
        }
        bufferedReader.close();
        return finalArr;
    }

    public void displayProductList()
    {
        System.out.println("[PRODUCT_ID,PRODUCT_NAME,CATEGORY,UNIT,QUANTITY,PRICE_PER_UNIT(VND)]");
        for (Product strings : productList)
        {
            System.out.println(strings);
        }
    }

    public void addNewProduct(Product productInput)
    {
        productList.add(productInput);
        System.out.println("Product add successfully");
    }

    public void removeProduct(String productID)
    {
        for (Product products : productList)
        {
            if (Objects.equals(products.getProductID(), productID))
            {
                productList.remove(products);
                System.out.println("Product remove successfully");
                return;
            }
        }
        System.out.println("No product found.");
    }
    public void updatePrice(String productID, int price)
    {
        for (Product products : productList)
        {
            if (Objects.equals(products.getProductID(), productID))
            {
                products.setPrice(price);
                System.out.println("Price updated successfully");
                return;
            }
        }
        System.out.println("No product found.");
    }
    public void updateQuantity(String productID, int quantity)
    {
        for (Product products : productList)
        {
            if (Objects.equals(products.getProductID(), productID))
            {
                products.setQuantity(quantity);
                return;
            }
        }
    }
    public void saveToCSV() throws IOException
    {
        File fileSrc = new File("src/Data/products.csv");
        FileWriter fileWriterSrc = new FileWriter(fileSrc);
        fileWriterSrc.write("PRODUCT_ID,PRODUCT_NAME,CATEGORY,UNIT,QUANTITY,PRICE_PER_UNIT(VND)" + "\n");
        for (Product product : productList)
        {
            fileWriterSrc.write(product.CSVString() + "\n");
        }
        fileWriterSrc.close();
    }
}


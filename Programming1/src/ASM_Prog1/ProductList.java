package ASM_Prog1;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductList
{
    private static final String delimiter = ",";
    private List<List<String>> productList = new ArrayList<>();

    private List<List<String>> loadProductList() throws IOException, URISyntaxException
    {
        String filePath = "src\\Product.csv";
        return ProductList.read(filePath);
    }

    public ProductList() throws IOException, URISyntaxException
    {
        this.productList = loadProductList();
    }

    public List<List<String>> getProductList()
    {
        return productList;
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

    public void displayProductList()
    {
        for (List<String> strings : this.productList)
        {
            System.out.println(strings);
        }
    }

    public static List<String> enterProduct()
    {
        Category category = new Category();
        Scanner scanner = new Scanner(System.in);
        List<String> product = new ArrayList<>();
        String productID = "P" + ((int) (Math.random() * 999999) + 1);
        System.out.println("--ProductID is " + productID + "--");
        System.out.println("--Product Name--");
        String productName = scanner.nextLine();
        System.out.println("--Category--");
        category.displayCategory();
        System.out.println("Please enter corresponding category");
        String categoryInput = scanner.nextLine();
        while (!category.checkValidCategory(categoryInput))
        {
            System.out.println("Invalid input, please re-enter");
            category.displayCategory();
            categoryInput = scanner.nextLine();
        }
        System.out.println("--Unit--");
        String unit = scanner.nextLine();
        System.out.println("--Quantity--");
        String quantity = scanner.nextLine();
        System.out.println("--Price--");
        String price = scanner.nextLine();
        product.add(productID);
        product.add(productName);
        product.add(categoryInput);
        product.add(unit);
        product.add(quantity);
        product.add(price);
        return product;
    }

    public void addNewProduct()
    {
        List<String> newProduct = ProductList.enterProduct();
        this.productList.add(newProduct);
    }

    public void saveToCSV(String pathname) throws IOException
    {
        File file = new File(pathname);
        FileWriter fileWriter = new FileWriter(file);
        for (List<String> strings : this.productList)
        {
            String newProductString = String.valueOf(strings);
            newProductString = newProductString.replace("[", "");
            newProductString = newProductString.replace("]", "");
            fileWriter.write(newProductString + "\n");
        }
        fileWriter.close();
    }
}


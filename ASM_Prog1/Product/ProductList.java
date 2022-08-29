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
        this.productList = setProductList();
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
            Product product = new Product();
            product.setProductID(arr.get(0));
            product.setProductName(arr.get(1));
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
        for (Product strings : this.productList)
        {
            System.out.println(strings);
        }
    }

    public void addNewProduct(Product productInput)
    {
        this.productList.add(productInput);
    }

    public void removeProduct(String productID)
    {
        ArrayList<Product> productList = getProductList();
        for (Product products : productList)
        {
            if (Objects.equals(products.getProductID(), productID))
            {
                productList.remove(products);
                break;
            }
        }
    }

    public void saveToCSV() throws IOException
    {
        File fileSrc = new File("src/Data/products.csv");
        File fileOut = new File("out/production/untitled/Product.csv");
        FileWriter fileWriterSrc = new FileWriter(fileSrc);
        FileWriter fileWriterOut = new FileWriter(fileOut);
        for (Product product : this.productList)
        {
            fileWriterSrc.write(product.CSVString() + "\n");
            fileWriterOut.write(product.CSVString() + "\n");
        }
        fileWriterSrc.close();
        fileWriterOut.close();
    }
}


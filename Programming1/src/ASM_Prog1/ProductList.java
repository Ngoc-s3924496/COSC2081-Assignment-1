package ASM_Prog1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductList
{
    private static final String delimiter = ",";
    private List<List<String>> productList = new ArrayList<>();

    private List<List<String>> loadProductList() throws IOException
    {
        String filePath = "src\\Product.csv";
        return ProductList.read(filePath);
    }

    public ProductList() throws IOException
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

    public void addNewProduct(Product productInput)
    {
        Product product = new Product();
        List<String> newProduct = productInput.convertToListString();
        this.productList.add(newProduct);
    }

    public void removeProduct(String productID)
    {
        List<List<String>> productList = getProductList();
        for (List<String> products : productList)
        {
            if (Objects.equals(products.get(0), productID))
            {
                productList.remove(products);
                break;
            }
        }
    }

    public void saveToCSV() throws IOException
    {
        File fileSrc = new File("src\\Product.csv");
        File fileOut = new File("out\\production\\untitled\\Product.csv");
        FileWriter fileWriterSrc = new FileWriter(fileSrc);
        FileWriter fileWriterOut = new FileWriter(fileOut);
        for (List<String> strings : this.productList)
        {
            String newProductString = String.valueOf(strings);

            newProductString = newProductString.replace("[", "");
            newProductString = newProductString.replace("]", "");
            newProductString = newProductString.replace(", ", ",");
            fileWriterSrc.write(newProductString + "\n");
            fileWriterOut.write(newProductString + "\n");
        }
        fileWriterSrc.close();
        fileWriterOut.close();
    }
}


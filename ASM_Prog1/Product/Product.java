package ASM_Prog1.Product;

import java.util.ArrayList;
import java.util.List;

public class Product
{
    private String productID;
    private String productName;
    private String category;
    private String unit;
    private int quantity;
    private int price;

    public Product()
    {
    }

    public Product(String productName, String category, String unit, int quantity, int price)
    {
        this.productID = "P" + ((int) (Math.random() * 999999) + 1);
        this.productName = productName;
        this.category = category;
        this.unit = unit;
        this.quantity = quantity;
        this.price = price;
    }

    public List<String> convertToListString()
    {
        List<String> product = new ArrayList<>();
        product.add(productID);
        product.add(productName);
        product.add(category);
        product.add(unit);
        product.add(String.valueOf(quantity));
        product.add(String.valueOf(price));
        return product;
    }

    @Override
    public String toString()
    {
        return "Product{" +
                "productID='" + productID + '\'' +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", unit='" + unit + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public String getProductID()
    {
        return productID;
    }

    public void setProductID(String productID)
    {
        this.productID = productID;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }


}

package ASM_Prog1.Product;

import java.util.ArrayList;

public class Product
{
    private final String productID;
    private final String productName;
    private String category;
    private String unit;
    private int quantity;
    private int price;

    public Product(String productID, String productName)
    {
        this.productID = productID;
        this.productName = productName;
    }

    public Product(String productName, String category, String unit, int quantity, int price, ProductList productList)
    {
        String ID = "P" + ((int) (Math.random() * 999999) + 1);
        while (checkID(ID, productList)){
            ID ="P" + ((int) (Math.random() * 999999) + 1);
        }
        this.productID = ID;
        this.productName = productName;
        this.category = category;
        this.unit = unit;
        this.quantity = quantity;
        this.price = price;
    }
    public static void searchProduct(String productName, ProductList productList){
        ArrayList<Product> productSearch = new ArrayList<>();
        for (Product product: productList.getProductList()){
            if(product.getProductName().toLowerCase().contains(productName.toLowerCase())){
                productSearch.add(product);
            }
        }
        if (productSearch.size() == 0){
            System.out.println("No product found");
        }
        else{
            System.out.println("[PRODUCT_ID,PRODUCT_NAME,CATEGORY,UNIT,QUANTITY,PRICE_PER_UNIT(VND)]");
            for(Product product : productSearch){
                System.out.println(product);
            }
        }
    }
    public static void ascPrice(ProductList productList){
        ArrayList<Product> productDes = productList.getProductList();
        for (int i = 0; i < productDes.size(); ++i){
            for(int j = 0; j < productDes.size() - i - 1; ++j){
                if(productDes.get(j+1).getPrice() < productDes.get(j).getPrice()){
                    Product temp = productDes.get(j);
                    productDes.set(j, productDes.get(j+1));
                    productDes.set(j+1, temp);
                }
            }
        }
        for (Product strings : productDes)
        {
            System.out.println(strings);
        }
    }
    public static void descPrice(ProductList productList){
        ArrayList<Product> productDes = productList.getProductList();
        for (int i = 0; i < productDes.size(); ++i){
            for(int j = 0; j < productDes.size() - i - 1; ++j){
                if(productDes.get(j+1).getPrice() > productDes.get(j).getPrice()){
                    Product temp = productDes.get(j);
                    productDes.set(j, productDes.get(j+1));
                    productDes.set(j+1, temp);
                }
            }
        }
        for (Product strings : productDes)
        {
            System.out.println(strings);
        }
    }
    public boolean checkID(String ID, ProductList productList){
        for (Product product : productList.getProductList()){
            if (ID.equals(product.getProductID())){
                return true;
            }
        }
        return false;
    }
    public boolean checkName(String Name, ProductList productList){
        for (Product product : productList.getProductList()){
            if (Name.equals(product.getProductName())){
                return true;
            }
        }
        return false;
    }
    public String CSVString()
    {
        return String.format("%s,%s,%s,%s,%d,%d", getProductID(), getProductName(), getCategory(), getUnit(),
                getQuantity(), getPrice());
    }

    @Override
    public String toString()
    {
        return "[" + productID + ", " +  productName + ", " + category
                + ", " + unit + ", " + quantity + ", " + price + ']';
    }

    public String getProductID()
    {
        return productID;
    }

    public String getProductName()
    {
        return productName;
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

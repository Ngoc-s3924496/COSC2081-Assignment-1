package ASM_Prog1.Order;

import ASM_Prog1.Product.ProductList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ShoppingCart {
    private final Map<String, CartEntry> Cart;

    public ShoppingCart(){
        this.Cart = new HashMap<>();
    }

    public void addProduct(String productId, int amount ){
        CartEntry cartEntry;
        if(Cart.containsKey(productId)){
            cartEntry = Cart.get(productId);
            cartEntry.addAmount(amount);
        } else {
            cartEntry = new CartEntry(amount);
        }
        Cart.put(productId, cartEntry);
    }

    public void reduceProduct(String productId, int amount ){
        CartEntry cartEntry = Cart.get(productId);
        if(cartEntry.getAmount() > 0){
            cartEntry.reduceAmount(amount);
        }
    }

    public void viewCartItem(){
        System.out.println("\t Product ID \t\t Amount");
        for(String key: Cart.keySet()){
            String amount = Cart.get(key).toString();
            System.out.println(key+"\t\t"+amount);
        }
    }

    private int getItemPrice(String itemId){
        List<List<String>> orderList = ProductList.getProductList();
        int productPrice = 0;
        for (List<String> productInfo : orderList) {
            if (Objects.equals(productInfo.get(0), itemId)) {
                 String productPriceString = productInfo.get(5);
                 productPrice = Integer.parseInt(productPriceString);
                 break;
            }
        }
        return productPrice;
    }

    public int viewCartTotal(){
        int totalPrice = 0;
        for(String key: Cart.keySet()){
            String amount = Cart.get(key).toString();
            int amountInt = Integer.parseInt(amount);
            totalPrice += getItemPrice(key) * amountInt;
        }
        return totalPrice;
    }
}



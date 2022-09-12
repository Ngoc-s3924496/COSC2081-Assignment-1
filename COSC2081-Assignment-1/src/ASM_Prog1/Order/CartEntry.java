/*
Author: Doan Khanh Luan
Student ID: s3926375
Instructor: Dr. Minh Vu
Version: OpenJDK 1.8 (Java 18)
Date: 05/09/2022
Purpose: Cart Entry class contain methods representing action with shopping cart
 */
package ASM_Prog1.Order;

public class CartEntry {
    private int amount;

    public int getAmount() {
        return amount;
    }

    public CartEntry(int amount) {
        this.amount = amount;
    }

    public void addAmount(int amountIncreased){
        this.amount = amount + amountIncreased;
    }

    public void reduceAmount(int amountDecreased){
        this.amount = amount - amountDecreased;
    }
}

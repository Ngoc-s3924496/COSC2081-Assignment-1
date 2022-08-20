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

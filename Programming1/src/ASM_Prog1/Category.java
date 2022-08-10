package ASM_Prog1;

import java.util.ArrayList;
import java.util.List;

public class Category
{
    private final List<String> availableCategory = new ArrayList<>();

    public Category()
    {
        availableCategory.add("Medicine");
        availableCategory.add("Dietary supplement");
        availableCategory.add("Health care product");
        availableCategory.add("Personal care product");
        availableCategory.add("Medical equipment");
    }

    public void addCategory(String newCategory)
    {
        this.availableCategory.add(newCategory);
    }

    public List<String> getAvailableCategory()
    {
        return availableCategory;
    }

    public void displayCategory()
    {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < availableCategory.size() + 1; i++)
        {
            result.append(i).append(" ").append(availableCategory.get(i - 1)).append("\n");
        }
        result.deleteCharAt(result.length() - 1);
        System.out.println(result);
    }

}

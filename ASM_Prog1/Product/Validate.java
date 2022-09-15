package ASM_Prog1.Product;

import java.util.List;

public class Validate
{
    public Validate() {}
    public static boolean checkCategory(String input)
    {
        boolean categoryCheck = false;
        Category category = new Category();
        List<String> availableCategory = category.getAvailableCategory();
        for (String s : availableCategory)
        {
            if (input.matches(s))
            {
                categoryCheck = true;
                break;
            }
        }
        return categoryCheck;
    }

    public static boolean checkUnit(String input)
    {
        boolean unitCheck = false;
        String[] unitList = {"item", "box", "tube", "packet", "bottle", "can"};
        for (String s : unitList)
        {
            if (input.matches(s))
            {
                unitCheck = true;
                break;
            }
        }
        return unitCheck;
    }

    public static boolean checkNumber(String input)
    {
        String numericList = "[0-9]*";
        return !input.matches(numericList);
    }
}

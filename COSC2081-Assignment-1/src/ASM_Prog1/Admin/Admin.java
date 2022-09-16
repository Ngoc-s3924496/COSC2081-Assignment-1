/*
Author: Duong Vu Thanh Ngoc
Student ID: s3924496
Instructor: Dr. Minh Vu
Version: OpenJDK 1.8 (Java 18)
Date: 02/09/2022
Purpose: Admin class with admin methods for the program
 */
package ASM_Prog1.Admin;

import ASM_Prog1.Event.Event;
import ASM_Prog1.Event.EventList;
import ASM_Prog1.Member.MemberList;
import ASM_Prog1.Order.Daily;
import ASM_Prog1.Order.Order;
import ASM_Prog1.Order.OrderList;
import ASM_Prog1.Product.Category;
import ASM_Prog1.Product.Product;
import ASM_Prog1.Product.ProductList;
import ASM_Prog1.Product.Validate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Admin
{
    private final String username;
    private final String password;

    public Admin()
    {
        this.username = "Team925";
        this.password = "123456";
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public void viewProducts(ProductList productList)
    {
        productList.displayProductList();
    }

    //this method allow admin to input a new product as well as its information
    public void addProduct(ProductList productList)
    {
        Category ctg = new Category();
        Product product = new Product("0", "0");
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter the product name");
        String productName = inputObj.nextLine();
        while (product.checkName(productName))
        {
            System.out.println("Name already exist");
            System.out.println("Enter a different name");
            productName = inputObj.nextLine();
        }
        System.out.println("Enter the product category");
        ctg.displayCategory();
        System.out.println("These are currently valid inputs");
        String category = inputObj.nextLine();
        while (!Validate.checkCategory(category))
        {
            System.out.println("Invalid input, please try again");
            ctg.displayCategory();
            System.out.println("These are currently valid inputs");
            category = inputObj.nextLine();
        }
        System.out.println("Enter the product unit");
        System.out.println("1 item \n2 tube \n3 box \n4 packet \n5 bottle \n6 can");
        System.out.println("These are currently valid inputs");
        String unit = inputObj.nextLine();
        while (!Validate.checkUnit(unit))
        {
            System.out.println("Invalid input, please try again");
            System.out.println("1 item \n2 tube \n3 box \n4 packet \n5 bottle \n6 can");
            System.out.println("These are currently valid inputs");
            unit = inputObj.nextLine();
        }
        System.out.println("Enter the product quantity");
        String quantity = inputObj.nextLine();
        while (Validate.checkNumber(quantity))
        {
            System.out.println("Valid input is INTEGGER, please try again");
            quantity = inputObj.nextLine();
        }

        System.out.println("Enter the product price");
        String price = inputObj.nextLine();
        while (Validate.checkNumber(price))
        {
            System.out.println("Valid input is INTEGGER, please try again");
            price = inputObj.nextLine();
        }
        Product newProduct = new Product(productName, category, unit, Integer.parseInt(quantity),
                Integer.parseInt(price));
        productList.addNewProduct(newProduct);
    }

    // this method removes a product from the product list
    public void removeProduct(ProductList productList)
    {
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter the product ID");
        String productID = inputObj.nextLine();
        productList.removeProduct(productID);
    }

    public void viewOrders(OrderList orderList)
    {
        orderList.displayOrderList();
    }

    public void viewUserOrders()
    {
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter User ID: ");
        String userID = inputObj.nextLine();
        Order.getOrderByUser(userID);
    }

    public void viewEvent(EventList eventList)
    {
        eventList.displayEventList();
    }

    public void viewMember(MemberList memberList)
    {
        memberList.displayMemberList();
    }

    // this method allows admin to add event and its effect to the shop
    public void addEvent(EventList eventList) throws ParseException
    {
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter the event name");
        String eventName = inputObj.nextLine();
        System.out.println("Enter the percentage discount (1 - 100)");
        int discount = -1;
        while (discount < 0 || discount > 100)
        {
            try
            {
                discount = Integer.parseInt(inputObj.nextLine());
            } catch (Exception e)
            {
                System.out.println("Invalid input, please try again");
            }
        }
        System.out.println("Enter the event start date");
        String startDate = inputObj.nextLine();
        while (dateValidate(startDate))
        {
            System.out.println("Enter a valid start date");
            startDate = inputObj.nextLine();
        }
        startDate = dateInput(startDate);
        System.out.println("Enter the event end date");
        String endDate = inputObj.nextLine();
        while (dateValidate(endDate))
        {
            System.out.println("Enter a valid end date");
            endDate = inputObj.nextLine();
        }
        endDate = dateInput(endDate);
        System.out.println("Enter the event description");
        String description = inputObj.nextLine();
        Event newEvent = new Event(eventName, discount, startDate, endDate, description);
        eventList.addNewEvent(newEvent);
    }


    public void removeEvent(EventList eventList)
    {
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter the event ID");
        String eventID = inputObj.nextLine();
        eventList.removeEvent(eventID);
    }

    public void removeOrder(OrderList orderList)
    {
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter the order ID");
        String orderID = inputObj.nextLine();
        orderList.removeOrder(orderID);
    }

    //this method allows admin to access the database and change the product price
    public void updateProductPrice(ProductList productList)
    {
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter the product ID");
        String productID = inputObj.nextLine();
        System.out.println("Enter the new price");
        int price = inputObj.nextInt();
        productList.updatePrice(productID, price);
    }

    //this method allows admin to access and update status of any order
    public void updateOrderStatus(OrderList orderList)
    {
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter the order ID");
        String orderID = inputObj.nextLine();
        System.out.println("Enter the update status");
        String status = inputObj.nextLine();
        orderList.updateStatus(orderID, status);
    }

    //this method allows admin to get the daily revenue of the exact day
    public void getDailyRevenue(OrderList orderList)
    {
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Enter the date to get the daily revenue (dd/mm/yyyy)");
        String date = inputObj.nextLine();
        while (dateValidate(date))
        {
            System.out.println("Enter the date to get the daily revenue (dd/mm/yyyy)");
            date = inputObj.nextLine();
        }
        date = dateInput(date);
        Daily daily = new Daily(date, orderList);
        System.out.println(daily.getDailyRevenue());
    }

    public boolean dateValidate(String date)
    {
        String[] dateComponent = date.split("/");
        String day = dateComponent[0].replaceFirst("^0*", "");
        String month = dateComponent[1].replaceFirst("^0*", "");
        String year = dateComponent[2].replaceFirst("^0*", "");
        if (Integer.parseInt(day) < 10)
        {
            date = String.format("0%s" + "/" + "%s" + "/" + "%s", day, month, year);
        } else if (Integer.parseInt(month) < 10)
        {
            date = String.format("%s" + "/" + "0%s" + "/" + "%s", day, month, year);
        } else if (Integer.parseInt(day) < 10 && Integer.parseInt(month) < 10)
        {
            date = String.format("0%s" + "/" + "0%s" + "/" + "%s", day, month, year);
        } else
        {
            date = String.format("%s" + "/" + "%s" + "/" + "%s", day, month, year);
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        try
        {
            format.parse(date);
        } catch (ParseException e)
        {
            return true;
        }
        return false;
    }

    public String dateInput(String date)
    {
        String[] dateComponent = date.split("/");
        String day = dateComponent[0].replaceFirst("^0*", "");
        String month = dateComponent[1].replaceFirst("^0*", "");
        String year = dateComponent[2].replaceFirst("^0*", "");
        date = day + "/" + month + "/" + year;
        return date;
    }
}

package ASM_Prog1;

import ASM_Prog1.Admin.Admin;
import ASM_Prog1.Customer.Customer;
import ASM_Prog1.Event.EventList;
import ASM_Prog1.Member.Member;
import ASM_Prog1.Member.MemberCLI;
import ASM_Prog1.Member.MemberList;
import ASM_Prog1.Order.OrderList;
import ASM_Prog1.Product.Category;
import ASM_Prog1.Product.ProductList;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Objects;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args) throws IOException, URISyntaxException, ParseException
    {
        ProductList productList = new ProductList();
        OrderList orderList = new OrderList();
        EventList eventList = new EventList();
        MemberList memberList = new MemberList();
        pageStart(productList, orderList, eventList, memberList);
    }

    public static void pageStart(ProductList productList, OrderList orderList, EventList eventList,
                                 MemberList memberList) throws IOException, URISyntaxException, ParseException
    {
        int input = -1;
        System.out.println("COSC2081 GROUP ASSIGNMENT");
        System.out.println("STORE ORDER MANAGEMENT SYSTEM");
        System.out.println("Instructor: Mr. Minh Vu");
        System.out.println("Group: Team 925");
        System.out.println("Duong Vu Thanh Ngoc");
        System.out.println("Ngo Tran Bao Thach");
        System.out.println("Nguyen Minh Quan");
        System.out.println("Doan Khanh Luan");
        lineBreak();
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Choose your status: ");
        System.out.println("[1]: Admin");
        System.out.println("[2]: Customer");
        System.out.println("[3]: Log out");
        while (input < 1 || input > 3)
        {
            try
            {
                System.out.print("Log in as : ");
                input = Integer.parseInt(inputObj.nextLine());
            } catch (Exception e)
            {
                System.out.println("Choose your status: ");
                System.out.println("[1]: Admin");
                System.out.println("[2]: Customer");
                System.out.println("[3]: Log out");
                System.out.println("Invalid data type (1 - 3)");
                System.out.println("Please try again");
            }
        }
        switch (input)
        {
            case 1 -> pageAdmin(productList, orderList, eventList, memberList, 0);
            case 2 -> pageCustomer(productList, orderList, eventList, memberList);
            case 3 -> pageEnd();
        }
    }

    public static void pageAdmin(ProductList productList, OrderList orderList, EventList eventList,
                                 MemberList memberList, int verified) throws IOException, URISyntaxException,
            ParseException
    {
        int action = -1;
        Admin admin = new Admin();
        Scanner inputObj = new Scanner(System.in);
        if (verified == 0)
        {
            System.out.println("Enter admin username: ");
            String username = inputObj.nextLine();
            System.out.println("Enter admin password: ");
            String password = inputObj.nextLine();
            if (username.equals(admin.getUsername()) && password.equals(admin.getPassword()))
            {
                verified = 1;
            }
        }
        if (verified == 1)
        {
            lineBreak();
            System.out.println("Welcome Admin!");
            System.out.println("[1]: View all orders");
            System.out.println("[2]: View user orders");
            System.out.println("[3]: View all products");
            System.out.println("[4]: View all members");
            System.out.println("[5]: View event");
            System.out.println("[6]: View daily revenue");
            System.out.println("[7]: Add product");
            System.out.println("[8]: Add event");
            System.out.println("[9]: Remove product");
            System.out.println("[10]: Remove event");
            System.out.println("[11]: Remove order");
            System.out.println("[12]: Update product price");
            System.out.println("[13]: Change order status");
            System.out.println("[14]: Log out");
            while (action < 0 || action > 14)
            {
                try
                {
                    System.out.print("Please enter your action: ");
                    action = Integer.parseInt(inputObj.nextLine());
                } catch (Exception e)
                {
                    System.out.println("Invalid data type (1 - 14)");
                    System.out.println("Please try again");
                }
            }
            switch (action)
            {
                case 1 -> {
                    admin.viewOrders(orderList);
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 2 -> {
                    admin.viewUserOrders();
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 3 -> {
                    admin.viewProducts(productList);
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 4 -> {
                    admin.viewMember(memberList);
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 5 -> {
                    admin.viewEvent(eventList);
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 6 -> {
                    admin.getDailyRevenue(orderList);
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 7 -> {
                    admin.addProduct(productList);
                    productList.saveToCSV();
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 8 -> {
                    admin.addEvent(eventList);
                    eventList.saveToCSV();
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 9 -> {
                    admin.removeProduct(productList);
                    productList.saveToCSV();
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 10 -> {
                    admin.removeEvent(eventList);
                    eventList.saveToCSV();
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 11 -> {
                    admin.removeOrder(orderList);
                    orderList.saveToCSV();
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 12 -> {
                    admin.updateProductPrice(productList);
                    productList.saveToCSV();
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 13 -> {
                    admin.updateOrderStatus(orderList);
                    orderList.saveToCSV();
                    goBackAdmin(productList, orderList, eventList, memberList);
                }
                case 14 -> pageStart(productList, orderList, eventList, memberList);
            }

        } else
        {
            System.out.println("Wrong username or password");
            lineBreak();
            pageStart(productList, orderList, eventList, memberList);
        }
    }

    public static void pageCustomer(ProductList productList, OrderList orderList, EventList eventList,
                                    MemberList memberList) throws IOException, URISyntaxException, ParseException
    {
        int action = -1;
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Welcome to Team 925 shop!");
        System.out.println("[1]: Register membership");
        System.out.println("[2]: Login");
        System.out.println("[3]: View all products");
        System.out.println("[4]: Search product");
        System.out.println("[5]: Sort product by price");
        System.out.println("[6]: Log out");
        while (action < 0 || action > 6)
        {
            try
            {
                System.out.print("Choose your action : ");
                action = Integer.parseInt(inputObj.nextLine());
            } catch (Exception e)
            {
                System.out.println("Invalid data type (1 - 6)");
                System.out.println("Please try again");
            }
        }
        Customer customer = new Customer();
        switch (action)
        {
            case 1 -> {
                customer.registerMembership(memberList);
                memberList.saveToCSV();
                goBackCustomer(productList, orderList, eventList, memberList);
            }
            case 2 -> {
                Member member = customer.logIn();
                if (Objects.equals(member.getUserID(), "0"))
                {
                    System.out.println("Wrong username or password");
                    goBackCustomer(productList, orderList, eventList, memberList);
                } else
                {
                    pageMember(member, productList, orderList, eventList, memberList);
                }
            }
            case 3 -> {
                customer.viewProducts(productList);
                goBackCustomer(productList, orderList, eventList, memberList);
            }
            case 4 -> {
                Category c = new Category();
                c.displayCategory();
                customer.searchProduct();
                goBackCustomer(productList, orderList, eventList, memberList);
            }
            case 5 -> {
                customer.sortPrice();
                goBackCustomer(productList, orderList, eventList, memberList);
            }
            case 6 -> pageStart(productList, orderList, eventList, memberList);
        }
    }

    public static void pageMember(Member member, ProductList productList, OrderList orderList, EventList eventList,
                                  MemberList memberList) throws IOException, URISyntaxException, ParseException
    {
        int action = -1;
        Scanner inputObj = new Scanner(System.in);
        System.out.printf("Welcome %s!" + "\n", member.getUserName());
        System.out.println("[1]: View account information");
        System.out.println("[2]: View all products");
        System.out.println("[3]: Search product");
        System.out.println("[4]: Sort product by price");
        System.out.println("[5]: View events");
        System.out.println("[6]: Make an order");
        System.out.println("[7]: View all orders");
        System.out.println("[8]: View order by order ID");
        System.out.println("[9]: Log out");
        while (action < 0 || action > 9)
        {
            try
            {
                System.out.print("Choose your action : ");
                action = Integer.parseInt(inputObj.nextLine());
            } catch (Exception e)
            {
                System.out.println("Invalid data type (1 - 9)");
                System.out.println("Please try again");
            }
        }
        MemberCLI memberCLI = new MemberCLI();
        switch (action)
        {
            case 1 -> {
                memberCLI.viewAccountInfo(member);
                goBackMember(member, productList, orderList, eventList, memberList);
            }
            case 2 -> {
                memberCLI.viewProducts(productList);
                goBackMember(member, productList, orderList, eventList, memberList);
            }
            case 3 -> {
                Category c = new Category();
                c.displayCategory();
                memberCLI.searchProduct();
                goBackMember(member, productList, orderList, eventList, memberList);
            }
            case 4 -> {
                memberCLI.sortPrice();
                goBackMember(member, productList, orderList, eventList, memberList);
            }
            case 5 -> {
                memberCLI.viewEvent(eventList);
                goBackMember(member, productList, orderList, eventList, memberList);
            }
            case 6 -> {
                memberCLI.makeOrder(member, productList, orderList, memberList);
                productList.saveToCSV();
                orderList.saveToCSV();
                memberList.saveToCSV();
                goBackMember(member, productList, orderList, eventList, memberList);
            }
            case 7 -> {
                memberCLI.viewOrders(member);
                goBackMember(member, productList, orderList, eventList, memberList);
            }
            case 8 -> {
                memberCLI.viewOrderByOrderID(member);
                goBackMember(member, productList, orderList, eventList, memberList);
            }
            case 9 -> pageStart(productList, orderList, eventList, memberList);
        }
    }

    public static void pageEnd()
    {
        lineBreak();
        System.out.println("Thank you for visiting us!");
    }

    public static void lineBreak()
    {
        for (int clear = 0; clear < 2; clear++)
        {
            System.out.println("\b");
        }
    }

    public static void goBackAdmin(ProductList productList, OrderList orderList, EventList eventList,
                                   MemberList memberList) throws IOException, URISyntaxException, ParseException
    {
        Scanner inputObj = new Scanner(System.in);
        System.out.println("[1] Go back     [2] Log out");
        int choice = inputObj.nextInt();
        while (choice > 2 || choice < 1)
        {
            System.out.println("Input is only 1 or 2");
            System.out.println("Please retype your input: ");
            choice = inputObj.nextInt();
        }

        if (choice == 1)
        {
            pageAdmin(productList, orderList, eventList, memberList, 1);
        } else
        {
            lineBreak();
            pageStart(productList, orderList, eventList, memberList);
        }
    }

    public static void goBackCustomer(ProductList productList, OrderList orderList, EventList eventList,
                                      MemberList memberList) throws IOException, URISyntaxException, ParseException
    {
        int choice = -1;
        Scanner inputObj = new Scanner(System.in);
        System.out.println("[1] Go back     [2] Log out");
        while (choice < 0 || choice > 2)
        {
            try
            {
                System.out.print("Choose your action : ");
                choice = Integer.parseInt(inputObj.nextLine());
            } catch (Exception e)
            {
                System.out.println("Invalid data type (1 - 2)");
                System.out.println("Please try again");
            }
        }

        if (choice == 1)
        {
            pageCustomer(productList, orderList, eventList, memberList);
        } else
        {
            lineBreak();
            pageStart(productList, orderList, eventList, memberList);
        }
    }

    public static void goBackMember(Member member, ProductList productList, OrderList orderList, EventList eventList,
                                    MemberList memberList) throws IOException, URISyntaxException, ParseException
    {
        int choice = -1;
        Scanner inputObj = new Scanner(System.in);
        System.out.println("[1] Go back     [2] Log out");
        while (choice < 0 || choice > 2)
        {
            try
            {
                System.out.print("Choose your action : ");
                choice = Integer.parseInt(inputObj.nextLine());
            } catch (Exception e)
            {
                System.out.println("Invalid data type (1 - 2)");
                System.out.println("Please try again");
            }
        }
        if (choice == 1)
        {
            pageMember(member, productList, orderList, eventList, memberList);
        } else
        {
            lineBreak();
            pageStart(productList, orderList, eventList, memberList);
        }
    }
}



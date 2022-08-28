package ASM_Prog1.Member;

import ASM_Prog1.Customer.Customer;
import ASM_Prog1.Event.Event;
import ASM_Prog1.Order.Order;
import ASM_Prog1.Product.Product;

import java.io.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Member extends Customer {
    private String userID;
    private final String userName, password, reEnteredPassword;
    private String fullName, phoneNumber, membershipRanking;

    public Member(String userName, String password, String reEnteredPassword, String fullName, String phoneNumber) throws IOException {
        setUserID();
        this.userName = userName;
        this.password = password;
        this.reEnteredPassword = reEnteredPassword;
        setFullName(fullName);
        setPhoneNumber(phoneNumber);
        this.membershipRanking = "None";
    }

    private static final String membersDataFile = File.separatorChar + "Users" + File.separatorChar + "thachngo" + File.separatorChar + "Documents" + File.separatorChar+ "java" + File.separatorChar+ "COSC2081" + File.separatorChar+ "src" + File.separatorChar+ "FinalProject" + File.separatorChar+ "Data" + File.separatorChar+ "members.csv";
    private static final String productsDataFile = File.separatorChar + "Users" + File.separatorChar + "thachngo" + File.separatorChar + "Documents" + File.separatorChar+ "java" + File.separatorChar+ "COSC2081" + File.separatorChar+ "src" + File.separatorChar+ "FinalProject" + File.separatorChar+ "Data" + File.separatorChar+ "products.csv";
    private static final String ordersDataFile = File.separatorChar + "Users" + File.separatorChar + "thachngo" + File.separatorChar + "Documents" + File.separatorChar+ "java" + File.separatorChar+ "COSC2081" + File.separatorChar+ "src" + File.separatorChar+ "FinalProject" + File.separatorChar+ "Data" + File.separatorChar+ "orders.csv";

    public boolean logIn(String userName, String password) throws IOException {
        boolean checked = false;
        BufferedReader reader = new BufferedReader(new FileReader(membersDataFile));
        String line;
        ArrayList<String[]> userInfoList = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            userInfoList.add(line.split(","));
        }
        for (int i = 1; i < userInfoList.size(); i++) {
            if (userInfoList.get(i)[1].equals(userName) && userInfoList.get(i)[2].equals(password)) {
                checked = true;
                break;
            }
        }
        reader.close();
        return checked;
    }


    public void registerAccountMember() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(membersDataFile, true));
        if (this.verifyAccount()) {
            writer.printf("%s,%s,%s,%s,%s,%s\n", userID, userName, password, fullName, phoneNumber, membershipRanking);
        }
        writer.flush();
        writer.close();
    }

    public void updateMembershipInfo() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(membersDataFile));
        String line;
        ArrayList<String[]> memberInfoList = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            memberInfoList.add(line.split(","));
        }
        reader.close();
        for (String[] i : memberInfoList) {
            if (i[0].equals(userID)) {
                i[0] = userID;
                i[1] = userName;
                i[2] = password;
                i[3] = fullName;
                i[4] = phoneNumber;
                i[5] = membershipRanking;
            }
        }
        PrintWriter writer = new PrintWriter(new FileWriter(membersDataFile, false));
        for (String[] i : memberInfoList) {
            writer.printf("%s,%s,%s,%s,%s,%s\n", i[0], i[1], i[2], i[3], i[4], i[5]);
        }
        writer.flush();
        writer.close();
    }

    public void updateMembershipRanking() throws IOException {
        String membershipRanking = "None";
        int totalSpend = 0;
        BufferedReader reader = new BufferedReader(new FileReader(ordersDataFile));
        String line;
        ArrayList<String[]> orderInfoList = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            orderInfoList.add(line.split(","));
        }
        reader.close();
        for (String[] i : orderInfoList) {
            if (i[2].equals(userID)) {
                totalSpend += Integer.parseInt(i[5]);
            }
        }
        if (totalSpend >= 5000000 && totalSpend < 10000000) {
            membershipRanking = "Silver";
        } else if (totalSpend >= 10000000 && totalSpend < 25000000) {
            membershipRanking = "Gold";
        } else if (totalSpend >= 25000000) {
            membershipRanking = "Platinum";
        } else {
            membershipRanking = "None";
        }
        this.membershipRanking = membershipRanking;
        updateMembershipInfo();
    }

    public Order makeOrder(ArrayList<Product> products) throws IOException, ParseException {
        String orderID = randomID("O");
        while (verifyOrderID(orderID)) {
            orderID = randomID("O");
        }
        String status = "delivering";
        double totalPaid = 0;

        boolean eventEffect = false;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();
        String dateTime = formatter.format(currentDateTime);

        boolean isLoggedIn = this.logIn(userName, password);

        if (isLoggedIn) {
            for (Product i : products) {
                totalPaid += (i.getQuantity() * i.getPrice());
            }
//        Check eventEffect
        Event event = new Event();
        Event currentEvent;
        if ((currentEvent = event.checkEvent(dateTime.substring(0,10))) != null) {
            totalPaid *= ((double) (100 - currentEvent.getPercentageDiscount()) / 100);
        }
//        Check membership rank
            totalPaid = switch (membershipRanking.toLowerCase()) {
                case "silver" -> totalPaid * 0.95;
                case "gold" -> totalPaid * 0.9;
                case "platinum" -> totalPaid * 0.85;
                default -> totalPaid;
            };
        }
        return new Order(orderID, status, userID, userName, products, totalPaid, eventEffect, dateTime);
    }

    ArrayList<String[]> listYourOrder(String userID) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(ordersDataFile));
        String line;
        ArrayList<String[]> orderList = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            orderList.add(line.split(","));
        }
        for (String[] order : orderList) {
            if (order[2].equals(userID)) {
                orderList.add(order);
            }
        }
        reader.close();
        return orderList;
    }

    public void viewYourOrders() throws IOException {
        ArrayList<String[]> orderList = listYourOrder(this.userID);
        for (String[] order : orderList) {
            System.out.println(order.toString());
        }
    }

    public void viewOrder(String orderID) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(ordersDataFile));
        String line;
        ArrayList<String[]> orderInfoList = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            orderInfoList.add(line.split(","));
        }
        for (String[] order : orderInfoList) {
            if (order[0].equals(orderID)) {
                System.out.println(order.toString());
                break;
            }
        }
        reader.close();
    }

    public boolean validateFullName(String fullName) {
        boolean checked = true;
        String[] fullNameArray = fullName.split("");
        if (fullNameArray.length < 2) {
            return false;
        }
        for (String i : fullNameArray) {
            if (!i.matches("\\w|\\s")) {
                checked = false;
                break;
            }
        }
        return checked;
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        boolean checked = true;
        String[] phoneNumberArray = phoneNumber.split("");
        if (phoneNumberArray.length < 6) {
            return false;
        }
        for (String i : phoneNumberArray) {
            if (!i.matches("\\d")) {
                checked = false;
                break;
            }
        }
        return checked;
    }

    public boolean verifyAccount() throws IOException {
        boolean verified = true;
        BufferedReader reader = new BufferedReader(new FileReader(membersDataFile));
        String line;
        ArrayList<String[]> userInfoList = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            userInfoList.add(line.split(","));
        }
        for (int i = 1; i < userInfoList.size(); i++) {
            if (userInfoList.get(i)[1].equals(userName)) {
                verified = false;
                System.out.println("Username \"" + userName + "\" is already existed, please choose another username!");
                break;
            }
        }
        if (!password.equals(reEnteredPassword)) {
            System.out.println("Please enter your password again");
            verified = false;
        }
        if (verified) {
            System.out.println("Account \"" + userName + "\" has been successfully created!");
        }
        reader.close();
        return verified;
    }

    public boolean verifyID(String id) throws IOException {
        boolean verified = true;
        BufferedReader reader = new BufferedReader(new FileReader(membersDataFile));
        String line;
        ArrayList<String[]> userInfoList = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            userInfoList.add(line.split(","));
        }
        for (int i = 1; i < userInfoList.size(); i++) {
            if ((userInfoList.get(i)[0]).equals(id)) {
                verified = false;
                break;
            }
        }
        reader.close();
        return verified;
    }

    public static String randomID(String subject) {
        Random random = new Random();
        return subject + random.nextInt(0,10)+ random.nextInt(0,10)+ random.nextInt(0,10)+ random.nextInt(0,10)+ random.nextInt(0,10)+ random.nextInt(0,10);
    }

    public boolean verifyOrderID(String id) throws IOException {
        boolean verified = true;
        BufferedReader reader = new BufferedReader(new FileReader(ordersDataFile));
        String line;
        ArrayList<String[]> orderInfoList = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            orderInfoList.add(line.split(","));
        }
        for (int i = 1; i < orderInfoList.size(); i++) {
            if ((orderInfoList.get(i)[0]).equals(id)) {
                verified = false;
                break;
            }
        }
        reader.close();
        return verified;
    }

    void setUserID() throws IOException {
        String userID = randomID("C");
        while (!verifyID(userID)) {
            userID = randomID("C");
        }
        this.userID = userID;
    }


    public String getUserName() {
        return userName;
    }

    public String getUserID() {
        return userID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMembershipRanking() {
        return membershipRanking;
    }

    public void setFullName(String fullName) {
        if (validateFullName(fullName)) {
            this.fullName = fullName;
        }
    }


    public void setPhoneNumber(String phoneNumber) {
        if (validatePhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        }
    }

    public void viewAccountInformation() throws IOException {
        updateMembershipRanking();
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Member{" +
                "userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", membershipRanking='" + membershipRanking + '\'' +
                '}';
    }
}

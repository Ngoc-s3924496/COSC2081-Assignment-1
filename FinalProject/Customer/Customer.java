package FinalProject.Customer;

import FinalProject.Member.Member;
import FinalProject.Product.Product;

import java.io.*;
import java.util.*;

public class Customer {
    private String userID, userName;
    private final String password, reEnteredPassword;
    private final String status = "Customer";

    public Customer() {
        this.userID = "nothing";
        this.userName = "nothing";
        this.password = "nothing";
        this.reEnteredPassword = "nothing";
    }


    public Customer(String userName, String password, String reEnteredPassword) throws IOException {
        setUserID();
        this.userName = userName;
        this.password = password;
        this.reEnteredPassword = reEnteredPassword;
    }

    private static final String membersDataFile = File.separatorChar + "Users" + File.separatorChar + "thachngo" + File.separatorChar + "Documents" + File.separatorChar+ "java" + File.separatorChar+ "COSC2081" + File.separatorChar+ "src" + File.separatorChar+ "FinalProject" + File.separatorChar+ "Data" + File.separatorChar+ "members.csv";
    private static final String customersDataFile = File.separatorChar + "Users" + File.separatorChar + "thachngo" + File.separatorChar + "Documents" + File.separatorChar+ "java" + File.separatorChar+ "COSC2081" + File.separatorChar+ "src" + File.separatorChar+ "FinalProject" + File.separatorChar+ "Data" + File.separatorChar+ "customers.csv";
    private static final String productsDataFile = File.separatorChar + "Users" + File.separatorChar + "thachngo" + File.separatorChar + "Documents" + File.separatorChar+ "java" + File.separatorChar+ "COSC2081" + File.separatorChar+ "src" + File.separatorChar+ "FinalProject" + File.separatorChar+ "Data" + File.separatorChar+ "products.csv";

    public boolean verifyAccount() throws IOException {
        boolean verified = true;
        BufferedReader reader = new BufferedReader(new FileReader(customersDataFile));
        String line;
        ArrayList<String[]> userInfoList = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            userInfoList.add(line.split(","));
        }
        for (int i = 1; i < userInfoList.size(); i++) {
            if (userInfoList.get(i)[2].equals(userName)) {
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
        BufferedReader reader1 = new BufferedReader(new FileReader(customersDataFile));
        String line;
        ArrayList<String[]> customerInfoList = new ArrayList<>();
        while ((line = reader1.readLine()) != null) {
            customerInfoList.add(line.split(","));
        }
        for (int i = 1; i < customerInfoList.size(); i++) {
            if ((customerInfoList.get(i)[0]).equals(id)) {
                verified = false;
                break;
            }
        }
        reader1.close();
        BufferedReader reader2 = new BufferedReader(new FileReader(membersDataFile));
        ArrayList<String[]> memberInfoList = new ArrayList<>();
        while ((line = reader2.readLine()) != null) {
            memberInfoList.add(line.split(","));
        }
        for (int i = 1; i < memberInfoList.size(); i++) {
            if ((memberInfoList.get(i)[0]).equals(id)) {
                verified = false;
                break;
            }
        }
        reader2.close();
        return verified;
    }

    public static String randomID(String subject) {
        Random random = new Random();
        return subject + random.nextInt(0,10)+ random.nextInt(0,10)+ random.nextInt(0,10)+ random.nextInt(0,10)+ random.nextInt(0,10)+ random.nextInt(0,10);
    }

    public void registerAccountCustomer() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(customersDataFile, true));
        if (this.verifyAccount()) {
            writer.printf("%s,%s,%s,%s\n", userID, this.status, userName, password);
        }
        writer.flush();
        writer.close();
    }

    public void viewAccountInformation() throws IOException {
        System.out.println(this.toString());
    }

    public boolean logIn(String userName, String password) throws IOException {
        boolean checked = false;
        BufferedReader reader = new BufferedReader(new FileReader(customersDataFile));
        String line;
        ArrayList<String[]> userInfoList = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            userInfoList.add(line.split(","));
        }
        for (int i = 1; i < userInfoList.size(); i++) {
            if (userInfoList.get(i)[2].equals(userName) && userInfoList.get(i)[3].equals(password)) {
                checked = true;
                break;
            }
        }
        reader.close();
        return checked;
    }


    public void upgradeToMember(String fullName, String phoneNumber) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(customersDataFile));
        String line;
        ArrayList<String[]> customerInfoList = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            customerInfoList.add(line.split(","));
        }
        reader.close();
        int index = 0;
        for (String[] i : customerInfoList) {
            if (i[0].equals(userID)) {
                index = customerInfoList.indexOf(i);
                break;
            }
        }
        customerInfoList.remove(index);
        PrintWriter writer = new PrintWriter(new FileWriter(customersDataFile, false));
        for (String[] i : customerInfoList) {
            writer.printf("%s,%s,%s,%s\n", i[0], i[1], i[2], i[3]);
        }
        writer.flush();
        writer.close();
        Member member = new Member(this, fullName, phoneNumber);
        member.registerAccountMember();
    }

    public void listAllProducts() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(productsDataFile));
        String line;
        ArrayList<String[]> productInfoList = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            productInfoList.add(line.split(","));
        }
        System.out.println("-".repeat(177));
        System.out.printf("|%20s\t|\t%50s\t|\t%20s\t|\t%15s\t|\t%15s\t|\t%20s\t|\n", productInfoList.get(0)[0], productInfoList.get(0)[1], productInfoList.get(0)[2], productInfoList.get(0)[3], productInfoList.get(0)[4], productInfoList.get(0)[5]);
        System.out.println("-".repeat(177));
        for (int i = 1; i < productInfoList.size(); i++) {
            System.out.printf("|%20s\t|\t%50s\t|\t%20s\t|\t%15s\t|\t%15s\t|\t%20s\t|\n", productInfoList.get(i)[0], productInfoList.get(i)[1], productInfoList.get(i)[2], productInfoList.get(i)[3], productInfoList.get(i)[4], productInfoList.get(i)[5]);
        }
        System.out.println("-".repeat(177));
        reader.close();
    }

    public void searchProducts(String keyword) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(productsDataFile));
        String line;
        ArrayList<String[]> productInfoList = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            productInfoList.add(line.split(","));
        }
        System.out.println("-".repeat(177));
        System.out.printf("|%20s\t|\t%50s\t|\t%20s\t|\t%15s\t|\t%15s\t|\t%20s\t|\n", productInfoList.get(0)[0], productInfoList.get(0)[1], productInfoList.get(0)[2], productInfoList.get(0)[3], productInfoList.get(0)[4], productInfoList.get(0)[5]);
        System.out.println("-".repeat(177));
        for (int i = 1; i < productInfoList.size(); i++) {
            if ((productInfoList.get(i)[1]).toLowerCase().contains(keyword.toLowerCase()) || (productInfoList.get(i)[2]).toLowerCase().contains(keyword.toLowerCase())) {
                System.out.printf("|%20s\t|\t%50s\t|\t%20s\t|\t%15s\t|\t%15s\t|\t%20s\t|\n", productInfoList.get(i)[0], productInfoList.get(i)[1], productInfoList.get(i)[2], productInfoList.get(i)[3], productInfoList.get(i)[4], productInfoList.get(i)[5]);
            }
        }
        System.out.println("-".repeat(177));
        reader.close();
    }

    public void sortByPriceDesc() throws IOException {
        ArrayList<String[]> productIDList = new ArrayList<>();
        ArrayList<Integer> sortedProductIDList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(productsDataFile));
        String line;
        while ((line = reader.readLine()) != null) {
            productIDList.add(line.split(","));
        }
        for (int i = 1; i < productIDList.size(); i++) {
            sortedProductIDList.add(Integer.parseInt(productIDList.get(i)[5]));
        }
        sortedProductIDList.sort(Collections.reverseOrder());
        System.out.println("-".repeat(177));
        System.out.printf("|%20s\t|\t%50s\t|\t%20s\t|\t%15s\t|\t%15s\t|\t%20s\t|\n", productIDList.get(0)[0], productIDList.get(0)[1], productIDList.get(0)[2], productIDList.get(0)[3], productIDList.get(0)[4], productIDList.get(0)[5]);
        System.out.println("-".repeat(177));
        for (int j : sortedProductIDList) {
            for (int i = 1; i < productIDList.size(); i++) {
                if (Integer.parseInt(productIDList.get(i)[5]) == j) {
                    System.out.printf("|%20s\t|\t%50s\t|\t%20s\t|\t%15s\t|\t%15s\t|\t%20s\t|\n", productIDList.get(i)[0], productIDList.get(i)[1], productIDList.get(i)[2], productIDList.get(i)[3], productIDList.get(i)[4], productIDList.get(i)[5]);
                }
            }
        }
        System.out.println("-".repeat(177));
        reader.close();
    }

    public void sortByPriceAsc() throws IOException {
        ArrayList<String[]> productIDList = new ArrayList<>();
        ArrayList<Integer> sortedProductIDList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(productsDataFile));
        String line;
        while ((line = reader.readLine()) != null) {
            productIDList.add(line.split(","));
        }
        for (int i = 1; i < productIDList.size(); i++) {
            sortedProductIDList.add(Integer.parseInt(productIDList.get(i)[5]));
        }
        Collections.sort(sortedProductIDList);
        System.out.println("-".repeat(177));
        System.out.printf("|%20s\t|\t%50s\t|\t%20s\t|\t%15s\t|\t%15s\t|\t%20s\t|\n", productIDList.get(0)[0], productIDList.get(0)[1], productIDList.get(0)[2], productIDList.get(0)[3], productIDList.get(0)[4], productIDList.get(0)[5]);
        System.out.println("-".repeat(177));
        for (int j : sortedProductIDList) {
            for (int i = 1; i < productIDList.size(); i++) {
                if (Integer.parseInt(productIDList.get(i)[5]) == j) {
                    System.out.printf("|%20s\t|\t%50s\t|\t%20s\t|\t%15s\t|\t%15s\t|\t%20s\t|\n", productIDList.get(i)[0], productIDList.get(i)[1], productIDList.get(i)[2], productIDList.get(i)[3], productIDList.get(i)[4], productIDList.get(i)[5]);
                }
            }
        }
        System.out.println("-".repeat(177));
        reader.close();
    }


    void setUserID() throws IOException {
        String userID = randomID("C");
        while (!verifyID(userID)) {
            userID = randomID("C");
        }
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userID='" + userID + '\'' +
                ", status='" + status + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

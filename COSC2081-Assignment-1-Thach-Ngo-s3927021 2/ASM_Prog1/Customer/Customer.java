package ASM_Prog1.Customer;

import ASM_Prog1.Member.Member;
import ASM_Prog1.Product.Product;

import java.io.*;
import java.util.*;

public class Customer {

    public Customer() {}

    private static final String productsDataFile = File.separatorChar + "Users" + File.separatorChar + "thachngo" + File.separatorChar + "Documents" + File.separatorChar+ "java" + File.separatorChar+ "COSC2081" + File.separatorChar+ "src" + File.separatorChar+ "FinalProject" + File.separatorChar+ "Data" + File.separatorChar+ "products.csv";

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
}

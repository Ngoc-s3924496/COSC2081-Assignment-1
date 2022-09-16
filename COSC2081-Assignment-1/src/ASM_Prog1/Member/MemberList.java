/*
Author: Ngo Tran Bao Thach
Student ID: s3927021
Instructor: Dr. Minh Vu
Version: OpenJDK 1.8 (Java 18)
Date: 01/09/2022
Purpose: MemberList class contains methods that work with list of members in database
 */
package ASM_Prog1.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MemberList {
    private static final String delimiter = ",";
    private static ArrayList<Member> memberList = new ArrayList<>();
    private ArrayList<Member> setMemberList() throws IOException {
        String filePath = "src/Data/members.csv";
        return MemberList.readFile(filePath);
    }
    public MemberList() throws IOException {
        memberList = setMemberList();
    }
    public static ArrayList<Member> getMemberList() {
        return memberList;
    }

    //This method allows us to read data from a CSV file and store it in an array list of that corresponding class.
    private static ArrayList<Member> readFile(String csvFile) throws IOException {
        ArrayList<Member> finalArr = new ArrayList<>();
        File file = new File(csvFile);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        List<String> arr;
        bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null)
        {
            arr = List.of(line.split(delimiter));
            Member member = new Member(arr.get(0));
            member.setUserName(arr.get(1));
            member.setPassword(arr.get(2));
            member.setFullName(arr.get(3));
            member.setPhoneNumber(arr.get(4));
            member.setMembershipRanking(arr.get(5));
            member.setTotalPaid(Integer.parseInt(arr.get(6)));
            finalArr.add(member);
        }
        bufferedReader.close();
        return finalArr;
    }
    public void displayMemberList()
    {
        System.out.println("[USER_ID,USERNAME,PASSWORD,FULL_NAME,PHONE_NUMBER,MEMBERSHIP_RANK,TOTAL_PAID]");
        for (Member strings : memberList)
        {
            System.out.println(strings);
        }
    }
    public void addNewMember(Member member){
        memberList.add(member);
        System.out.println("Account " + member.getUserName() + " created successfully");
    }
    public void updateMember(Member member)
    {
        for (Member members : memberList)
        {
            if (Objects.equals(member.getUserID(), members.getUserID()))
            {
                members.setTotalPaid(member.getTotalPaid());
                members.setMembershipRanking(member.getMembershipRanking());
                return;
            }
        }
    }
    public void saveToCSV() throws IOException
    {
        File fileSrc = new File("src/Data/members.csv");
        FileWriter fileWriterSrc = new FileWriter(fileSrc);
        fileWriterSrc.write("USER_ID,USERNAME,PASSWORD,FULL_NAME,PHONE_NUMBER,MEMBERSHIP_RANK,TOTAL_PAID" + "\n");
        for (Member member : memberList)
        {
            fileWriterSrc.write(member.CSVString() + "\n");
        }
        fileWriterSrc.close();
    }
}
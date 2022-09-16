/*
Author: Ngo Tran Bao Thach
Student ID: s3927021
Instructor: Dr. Minh Vu
Version: OpenJDK 1.8 (Java 18)
Date: 01/09/2022
Purpose: Member class for program's member functions
 */
package ASM_Prog1.Member;

import ASM_Prog1.Customer.Customer;

public class Member extends Customer {
    private final String userID;
    private String userName, password;
    private String fullName, phoneNumber, membershipRanking;
    private int totalPaid;

    public Member(String userID) {
        this.userID = userID;
    }

    //This method supports registering a member
    public Member(String userName, String password, String fullName, String phoneNumber, String membershipRanking,
                  int totalPaid) {
        String ID = "U" + ((int) (Math.random() * 999999) + 1);
        while (IDCheck(ID)){
            ID = "U" + ((int) (Math.random() * 999999) + 1);
        }
        this.userID = ID;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.membershipRanking = membershipRanking;
        this.totalPaid = totalPaid;
    }
    public boolean IDCheck(String ID) {
        for (Member member : MemberList.getMemberList()) {
            if (ID.equals(member.getUserID())) {
                return true;
            }
        }
        return false;
    }
    public boolean usernameCheck(String userName) {
        for (Member member : MemberList.getMemberList()) {
            if (userName.equals(member.getUserName())) {
                return true;
            }
        }
        return false;
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
    public Member logIn(String username, String password){
        Member logInMember = new Member("0");
        for (Member member : MemberList.getMemberList()){
            if(username.equalsIgnoreCase(member.getUserName()) && password.equalsIgnoreCase(member.getPassword())){
                logInMember = member;
                return logInMember;
            }
        }
        return logInMember;

    }
    public void updateRank() {
        if (totalPaid >= 5000000 && totalPaid < 10000000) {
            membershipRanking = "Silver";
        } else if (totalPaid >= 10000000 && totalPaid < 25000000) {
            membershipRanking = "Gold";
        } else if (totalPaid >= 25000000) {
            membershipRanking = "Platinum";
        } else {
            membershipRanking = "None";
        }
    }
    public String CSVString()
    {
        return String.format("%s,%s,%s,%s,%s,%s,%d", getUserID(), getUserName(), getPassword(), getFullName(),
                getPhoneNumber(), getMembershipRanking(), getTotalPaid());
    }
    @Override
    public String toString() {
        return "[" + userID + ", " + userName + ", " + password + ", " + fullName + ", " + phoneNumber + ", " +
                membershipRanking + ", " + totalPaid + "]";
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword(){
        return password;
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
    public int getTotalPaid(){
        return totalPaid;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void setMembershipRanking(String membershipRanking){
        this.membershipRanking = membershipRanking;
    }
    public void setTotalPaid(int totalPaid){
        this.totalPaid = totalPaid;
    }
}
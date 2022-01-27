package com.microfinance.hsmicrofinance.Network.models;

public class DashboardData {
    int uid;
    String id;
    String name;
    String email;
    String KRAPin;
    String phone;
    String accNo;
    String accBallance;
    String pin;
    String usertoken;
    int verificationStatus;

    public DashboardData(int uid, String id,String name, String email, String KRAPin, String phone, String accNo, String accBallance, String pin, String usertoken, int verificationStatus) {
        this.uid = uid;
        this.id = id;
        this.name = name;
        this.email = email;
        this.KRAPin = KRAPin;
        this.phone = phone;
        this.accNo = accNo;
        this.accBallance = accBallance;
        this.pin = pin;
        this.usertoken = usertoken;
        this.verificationStatus = verificationStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKRAPin() {
        return KRAPin;
    }

    public void setKRAPin(String KRAPin) {
        this.KRAPin = KRAPin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getAccBallance() {
        return accBallance;
    }

    public void setAccBallance(String accBallance) {
        this.accBallance = accBallance;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getUsertoken() {
        return usertoken;
    }

    public void setUsertoken(String usertoken) {
        this.usertoken = usertoken;
    }

    public int getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(int verificationStatus) {
        this.verificationStatus = verificationStatus;
    }
}

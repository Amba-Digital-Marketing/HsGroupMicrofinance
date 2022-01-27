package com.microfinance.hsmicrofinance.Network.models;

public class PhoneNumbers {
    private String name;
    private String phone;
    String accountno;

    public PhoneNumbers(String name, String phone,String accountno) {
        this.name = name;
        this.phone = phone;
        this.accountno = accountno;
    }

    public PhoneNumbers() {
    }

    @Override
    public String toString() {
        return "PhoneNumbers{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

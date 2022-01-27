package com.microfinance.hsmicrofinance.Network.entity;

public class OtherBankTransferDetails {
    String country;
    String currency;
    int bank;
    String branch;
    String account_no;
    double amount;
    String account_holder;

    public OtherBankTransferDetails(String country, String currency, int bank, String branch,String  account_holder, String account_no, double amount ) {
        this.country = country;
        this.currency = currency;
        this.bank = bank;
        this.branch = branch;
        this.account_no = account_no;
        this.amount = amount;
        this.account_holder = account_holder;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getBank() {
        return bank;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAccount_holder() {
        return account_holder;
    }

    public void setAccount_holder(String account_holder) {
        this.account_holder = account_holder;
    }
}

package com.microfinance.hsmicrofinance.Network.entity;

public class UserAccountDetails {

    String account_number;
    String account_holder_name;
    String  branch;

    public UserAccountDetails( String account_number,String account_holder_name, String branch) {
        this.account_holder_name = account_holder_name;
        this.account_number = account_number;
        this.branch = branch;
    }

    public String getAccount_holder_name() {
        return account_holder_name;
    }

    public void setAccount_holder_name(String account_holder_name) {
        this.account_holder_name = account_holder_name;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}

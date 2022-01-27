package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.SerializedName;

public class BillPayment {

    @SerializedName("biller_name")
    public String biller_name;

    @SerializedName("biller_code")
    public String biller_code;

    @SerializedName("biller_category")
    public String biller_category;

    @SerializedName("minimum_balance")
    public String minimum_balance;

    @SerializedName("secondary_account")
    public String secondary_account;

    @SerializedName("url")
    public String url;

    @SerializedName("amount")
    public String amount;

    @SerializedName("phone")
    public String phone;

    @SerializedName("account")
    public String account;


    public BillPayment(String biller_name, String biller_code, String url) {
        this.biller_name = biller_name;
        this.biller_code = biller_code;
        this.url = url;
    }
}

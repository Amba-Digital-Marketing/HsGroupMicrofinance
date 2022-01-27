package com.microfinance.hsmicrofinance.Network.entity;

import android.icu.text.DecimalFormat;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtherBankTransferResponse {

    public OtherBankTransferResponse() {
    }

    @SerializedName("Data Sent in Session")
    @Expose
    public DataInSession dataInSession;

    public DataInSession getDataInSession() {
        return dataInSession;
    }

    public void setDataInSession(DataInSession dataInSession) {
        this.dataInSession = dataInSession;
    }

    public OtherBankTransferResponse(DataInSession dataInSession) {
        this.dataInSession = dataInSession;
    }

    public static class DataInSession {

        public DataInSession() {
        }

        public DataInSession(String bank, String branch, String account_holder_name, String currency_id, String currency_name, String currency_rate, String amount, String final_amount, String account_no, String charge_type, String total_charge, String bank_name) {
            this.bank = bank;
            this.branch = branch;
            this.account_holder_name = account_holder_name;
            this.currency_id = currency_id;
            this.currency_name = currency_name;
            this.currency_rate = currency_rate;
            this.amount = amount;
            this.final_amount = final_amount;
            this.account_no = account_no;
            this.charge_type = charge_type;
            this.total_charge = total_charge;
            this.bank_name = bank_name;
        }


        @SerializedName("bank")
        @Expose
        public String bank;

        @SerializedName("branch")
        @Expose
        public String branch;

        @SerializedName("account_holder_name")
        @Expose
        public String account_holder_name;

        @SerializedName("currency_id")
        @Expose
        public String currency_id;

        @SerializedName("currency_name")
        @Expose
        public String currency_name;
        @SerializedName("currency_rate")
        @Expose
        public String currency_rate;
        @SerializedName("amount")
        @Expose
        public String amount;
        @SerializedName("final_amount in USD")
        @Expose
        public String final_amount;
        @SerializedName("account_no")
        @Expose
        public String account_no;
        @SerializedName("charge_type")
        @Expose
        public String charge_type;
        @SerializedName("total_charge")
        @Expose
        public String total_charge;

        @SerializedName("bank_name")
        @Expose
        public String bank_name;

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getBranch() {
            return branch;
        }

        public void setBranch(String branch) {
            this.branch = branch;
        }

        public String getAccount_holder_name() {
            return account_holder_name;
        }

        public void setAccount_holder_name(String account_holder_name) {
            this.account_holder_name = account_holder_name;
        }

        public String getCurrency_id() {
            return currency_id;
        }

        public void setCurrency_id(String currency_id) {
            this.currency_id = currency_id;
        }

        public String getCurrency_name() {
            return currency_name;
        }

        public void setCurrency_name(String currency_name) {
            this.currency_name = currency_name;
        }

        public String getCurrency_rate() {
            return currency_rate;
        }

        public void setCurrency_rate(String currency_rate) {
            this.currency_rate = currency_rate;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getFinal_amount() {
            return final_amount;
        }

        public void setFinal_amount(String final_amount) {
            this.final_amount = final_amount;
        }

        public String getAccount_no() {
            return account_no;
        }

        public void setAccount_no(String account_no) {
            this.account_no = account_no;
        }

        public String getCharge_type() {
            return charge_type;
        }

        public void setCharge_type(String charge_type) {
            this.charge_type = charge_type;
        }

        public String getTotal_charge() {
            return total_charge;
        }

        public void setTotal_charge(String total_charge) {
            this.total_charge = total_charge;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }
    }



}

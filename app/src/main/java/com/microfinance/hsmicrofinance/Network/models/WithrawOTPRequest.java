package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WithrawOTPRequest {
    @SerializedName("0")
    @Expose
    public String _0;
    @SerializedName("Details")
    @Expose
    public Details details;



    public class Details {

        @SerializedName("Email")
        @Expose
        public String email;
//        @SerializedName("name")
//        @Expose
//        public String name;
//        @SerializedName("account_number")
//        @Expose
//        public String accountNumber;
//        @SerializedName("amount")
//        @Expose
//        public Double amount;
//        @SerializedName("charge")
//        @Expose
//        public Double charge;
//        @SerializedName("final_amount")
//        @Expose
//        public Double finalAmount;
        @SerializedName("Verification Code")
        @Expose
        public String verificationCode;
//        @SerializedName("type")
//        @Expose
//        public String type;
//        @SerializedName("transfer_type")
//        @Expose
//        public String transferType;
//
//        @Override
//        public String toString() {
//            return "Details{" +
//                    "email='" + email + '\'' +
//                    ", name='" + name + '\'' +
//                    ", accountNumber='" + accountNumber + '\'' +
//                    ", amount=" + amount +
//                    ", charge=" + charge +
//                    ", finalAmount=" + finalAmount +
//                    ", verificationCode=" + verificationCode +
//                    ", type='" + type + '\'' +
//                    ", transferType='" + transferType + '\'' +
//                    '}';
//        }


    }
}

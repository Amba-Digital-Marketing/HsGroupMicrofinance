package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Nullable;

public class WithDrawOTPResponse {

    @SerializedName("0")
    @Expose
    public String _0;
    @SerializedName("Inserting Data to Withdraw Table")
    @Expose
    public InsertingDataToWithdrawTable insertingDataToWithdrawTable;
    @SerializedName("Inserting Data to Transaction Table")
    @Expose
    public InsertingDataToTransactionTable insertingDataToTransactionTable;



    public class InsertingDataToTransactionTable {

        @SerializedName("user_id")
        @Expose
        public Integer userId;
        /*@SerializedName("trxid")
        @Expose
        public String trxid;
        @SerializedName("amount")
        @Expose
        public Double amount;
        @SerializedName("balance")
        @Expose
        public Double balance;
        @SerializedName("fee")
        @Expose
        public Double fee;
        @SerializedName("status")
        @Expose
        public Integer status;
        @SerializedName("info")
        @Expose
        public String info;
        @SerializedName("type")
        @Expose
        public String type;
        @SerializedName("updated_at")
        @Expose
        public String updatedAt;
        @SerializedName("created_at")
        @Expose
        public String createdAt;
        @SerializedName("id")
        @Expose
        public Integer id;*/

        /*@Override
        public String toString() {
            return "InsertingDataToTransactionTable{" +
                    "userId=" + userId +
                    ", trxid='" + trxid + '\'' +
                    ", amount=" + amount +
                    ", balance=" + balance +
                    ", fee=" + fee +
                    ", status=" + status +
                    ", info='" + info + '\'' +
                    ", type='" + type + '\'' +
                    ", updatedAt='" + updatedAt + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", id=" + id +
                    '}';
        }*/

//        public Integer getUserId() {
//            return userId;
//        }
//
//        public String getTrxid() {
//            return trxid;
//        }
//
//        public Double getAmount() {
//            return amount;
//        }
//
//        public Double getBalance() {
//            return balance;
//        }
//
//        public Double getFee() {
//            return fee;
//        }
//
//        public Integer getStatus() {
//            return status;
//        }
//
//        public String getInfo() {
//            return info;
//        }
//
//        public String getType() {
//            return type;
//        }
//
//        public String getUpdatedAt() {
//            return updatedAt;
//        }
//
//        public String getCreatedAt() {
//            return createdAt;
//        }
//
//        public Integer getId() {
//            return id;
//        }
    }
    public class InsertingDataToWithdrawTable {

        @SerializedName("withdrawmethod_id")
        @Expose
        public String withdrawmethodId;
//        @SerializedName("term_id")
//        @Expose
//        public String termId;
//        @SerializedName("user_id")
//        @Expose
//        public Integer userId;
//        @SerializedName("amount_withdraw")
//        @Expose
//        public Double amountWithdraw;
//        @SerializedName("amount_usd")
//        @Expose
//        public Double amountUsd;
//        @SerializedName("fee")
//        @Expose
//        public Double fee;
//        @SerializedName("account")
//        @Expose
//        @Nullable
//        public String account;
//        @SerializedName("status")
//        @Expose
//        public Integer status;
//        @SerializedName("updated_at")
//        @Expose
//        public String updatedAt;
//        @SerializedName("created_at")
//        @Expose
//        public String createdAt;
//        @SerializedName("id")
//        @Expose
//        public Integer id;
//
//        @Override
//        public String toString() {
//            return "InsertingDataToWithdrawTable{" +
//                    "withdrawmethodId='" + withdrawmethodId + '\'' +
//                    ", termId='" + termId + '\'' +
//                    ", userId=" + userId +
//                    ", amountWithdraw=" + amountWithdraw +
//                    ", amountUsd=" + amountUsd +
//                    ", fee=" + fee +
//                    ", account='" + account + '\'' +
//                    ", status=" + status +
//                    ", updatedAt='" + updatedAt + '\'' +
//                    ", createdAt='" + createdAt + '\'' +
//                    ", id=" + id +
//                    '}';
//        }
//
//        public String getWithdrawmethodId() {
//            return withdrawmethodId;
//        }
//
//        public String getTermId() {
//            return termId;
//        }
//
//        public Integer getUserId() {
//            return userId;
//        }
//
//        public Double getAmountWithdraw() {
//            return amountWithdraw;
//        }
//
//        public Double getAmountUsd() {
//            return amountUsd;
//        }
//
//        public Double getFee() {
//            return fee;
//        }
//
//        @Nullable
//        public String getAccount() {
//            return account;
//        }
//
//        public Integer getStatus() {
//            return status;
//        }
//
//        public String getUpdatedAt() {
//            return updatedAt;
//        }
//
//        public String getCreatedAt() {
//            return createdAt;
//        }
//
//        public Integer getId() {
//            return id;
//        }
    }
}

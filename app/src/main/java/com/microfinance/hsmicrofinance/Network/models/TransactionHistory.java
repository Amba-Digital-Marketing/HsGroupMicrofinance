package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Nullable;

public class TransactionHistory {

    @SerializedName("Transaction History Details")
    @Expose
    private List<TransactionHistoryDetail> transactionHistoryDetails = null;

    public List<TransactionHistoryDetail> getTransactionHistoryDetails() {
        return transactionHistoryDetails;
    }

    public void setTransactionHistoryDetails(List<TransactionHistoryDetail> transactionHistoryDetails) {
        this.transactionHistoryDetails = transactionHistoryDetails;
    }

    public class TransactionHistoryDetail {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("user_id")
        @Expose
        public Integer userId;
        @SerializedName("trxid")
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
        @SerializedName("mpesa_trans_id")
        @Expose
        @Nullable
        public String mpesaTransId;
        @SerializedName("phone")
        @Expose
        @Nullable
        public String phone;
        @SerializedName("Trans_reference")
        @Expose
        @Nullable
        public String transReference;
        @SerializedName("created_at")
        @Expose
        public String createdAt;
        @SerializedName("status_ipay")
        @Expose
        public Integer statusIpay;
        @SerializedName("updated_at")
        @Expose
        public String updatedAt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getTrxid() {
            return trxid;
        }

        public void setTrxid(String trxid) {
            this.trxid = trxid;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public Double getBalance() {
            return balance;
        }

        public void setBalance(Double balance) {
            this.balance = balance;
        }

        public Double getFee() {
            return fee;
        }

        public void setFee(Double fee) {
            this.fee = fee;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Nullable
        public String getMpesaTransId() {
            return mpesaTransId;
        }

        public void setMpesaTransId(@Nullable String mpesaTransId) {
            this.mpesaTransId = mpesaTransId;
        }

        @Nullable
        public String getPhone() {
            return phone;
        }

        public void setPhone(@Nullable String phone) {
            this.phone = phone;
        }

        @Nullable
        public String getTransReference() {
            return transReference;
        }

        public void setTransReference(@Nullable String transReference) {
            this.transReference = transReference;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Integer getStatusIpay() {
            return statusIpay;
        }

        public void setStatusIpay(Integer statusIpay) {
            this.statusIpay = statusIpay;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        @Override
        public String toString() {
            return "TransactionHistoryDetail{" +
                    "id=" + id +
                    ", userId=" + userId +
                    ", trxid='" + trxid + '\'' +
                    ", amount=" + amount +
                    ", balance=" + balance +
                    ", fee=" + fee +
                    ", status=" + status +
                    ", info='" + info + '\'' +
                    ", type='" + type + '\'' +
                    ", mpesaTransId='" + mpesaTransId + '\'' +
                    ", phone='" + phone + '\'' +
                    ", transReference='" + transReference + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", statusIpay=" + statusIpay +
                    ", updatedAt='" + updatedAt + '\'' +
                    '}';
        }
    }
}

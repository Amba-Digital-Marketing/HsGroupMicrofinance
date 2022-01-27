package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EdepositStatement {
    @SerializedName("E-deposit Statement")
    @Expose
    private List<EDepositStatement> eDepositStatement = null;

    public List<EDepositStatement> getEDepositStatement() {
        return eDepositStatement;
    }

    public void setEDepositStatement(List<EDepositStatement> eDepositStatement) {
        this.eDepositStatement = eDepositStatement;
    }
    public class EDepositStatement {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("trxid")
        @Expose
        private String trxid;
        @SerializedName("amount")
        @Expose
        private Double amount;
        @SerializedName("balance")
        @Expose
        private Double balance;
        @SerializedName("fee")
        @Expose
        private Double fee;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("info")
        @Expose
        private String info;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        @Override
        public String toString() {
            return "EDepositStatement{" +
                    "id=" + id +
                    ", userId=" + userId +
                    ", trxid='" + trxid + '\'' +
                    ", amount=" + amount +
                    ", balance=" + balance +
                    ", fee=" + fee +
                    ", status=" + status +
                    ", info='" + info + '\'' +
                    ", type='" + type + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", updatedAt='" + updatedAt + '\'' +
                    '}';
        }

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

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

    }

}

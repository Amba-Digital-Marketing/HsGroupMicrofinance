package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ManualDeposit {

    @SerializedName("0")
    @Expose
    public String _0;
    @SerializedName("Entering Transaction Details in Deposit Table")
    @Expose
    public EnteringTransactionDetailsInDepositTable enteringTransactionDetailsInDepositTable;

    public String get_0() {
        return _0;
    }

    public void set_0(String _0) {
        this._0 = _0;
    }

    public EnteringTransactionDetailsInDepositTable getEnteringTransactionDetailsInDepositTable() {
        return enteringTransactionDetailsInDepositTable;
    }

    public void setEnteringTransactionDetailsInDepositTable(EnteringTransactionDetailsInDepositTable enteringTransactionDetailsInDepositTable) {
        this.enteringTransactionDetailsInDepositTable = enteringTransactionDetailsInDepositTable;
    }

    public class EnteringTransactionDetailsInDepositTable {

        @SerializedName("trx")
        @Expose
        public String trx;
        @SerializedName("user_id")
        @Expose
        public Integer userId;
        @SerializedName("getway_id")
        @Expose
        public String getwayId;
        @SerializedName("type")
        @Expose
        public String type;
        @SerializedName("amount")
        @Expose
        public Double amount;
        @SerializedName("charge")
        @Expose
        public Double charge;
        @SerializedName("status")
        @Expose
        public Integer status;
        @SerializedName("updated_at")
        @Expose
        public String updatedAt;
        @SerializedName("created_at")
        @Expose
        public String createdAt;
        @SerializedName("id")
        @Expose
        public Integer id;

        @Override
        public String toString() {
            return "EnteringTransactionDetailsInDepositTable{" +
                    "trx='" + trx + '\'' +
                    ", userId=" + userId +
                    ", getwayId='" + getwayId + '\'' +
                    ", type='" + type + '\'' +
                    ", amount=" + amount +
                    ", charge=" + charge +
                    ", status=" + status +
                    ", updatedAt='" + updatedAt + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", id=" + id +
                    '}';
        }

        public String getTrx() {
            return trx;
        }

        public void setTrx(String trx) {
            this.trx = trx;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getGetwayId() {
            return getwayId;
        }

        public void setGetwayId(String getwayId) {
            this.getwayId = getwayId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public Double getCharge() {
            return charge;
        }

        public void setCharge(Double charge) {
            this.charge = charge;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }



}

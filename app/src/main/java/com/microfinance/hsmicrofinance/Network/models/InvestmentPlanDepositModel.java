package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InvestmentPlanDepositModel {
    @SerializedName("0")
    @Expose
    private String _0;
    @SerializedName("Transaction Details")
    @Expose
    private TransactionDetails transactionDetails;
    @SerializedName("investment plan request details")
    @Expose
    private InvestmentPlanRequestDetails investmentPlanRequestDetails;

    public InvestmentPlanDepositModel() {
    }

    public InvestmentPlanDepositModel(String _0, TransactionDetails transactionDetails, InvestmentPlanRequestDetails investmentPlanRequestDetails) {
        this._0 = _0;
        this.transactionDetails = transactionDetails;
        this.investmentPlanRequestDetails = investmentPlanRequestDetails;
    }

    public String get0() {
        return _0;
    }

    public void set0(String _0) {
        this._0 = _0;
    }

    public TransactionDetails getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(TransactionDetails transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public InvestmentPlanRequestDetails getInvestmentPlanRequestDetails() {
        return investmentPlanRequestDetails;
    }

    public void setInvestmentPlanRequestDetails(InvestmentPlanRequestDetails investmentPlanRequestDetails) {
        this.investmentPlanRequestDetails = investmentPlanRequestDetails;
    }

//InvestmentPlanRequestDetails
    public class InvestmentPlanRequestDetails {

        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("fdrplan_id")
        @Expose
        private String fdrplanId;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("return_percent")
        @Expose
        private Integer returnPercent;
        @SerializedName("return_total")
        @Expose
        private Double returnTotal;
        @SerializedName("return_date")
        @Expose
        private String returnDate;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("id")
        @Expose
        private Integer id;

    public InvestmentPlanRequestDetails(Integer userId, String fdrplanId, String amount, Integer returnPercent, Double returnTotal, String returnDate, Integer status, String updatedAt, String createdAt, Integer id) {
        this.userId = userId;
        this.fdrplanId = fdrplanId;
        this.amount = amount;
        this.returnPercent = returnPercent;
        this.returnTotal = returnTotal;
        this.returnDate = returnDate;
        this.status = status;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.id = id;
    }

    public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getFdrplanId() {
            return fdrplanId;
        }

        public void setFdrplanId(String fdrplanId) {
            this.fdrplanId = fdrplanId;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public Integer getReturnPercent() {
            return returnPercent;
        }

        public void setReturnPercent(Integer returnPercent) {
            this.returnPercent = returnPercent;
        }

        public Double getReturnTotal() {
            return returnTotal;
        }

        public void setReturnTotal(Double returnTotal) {
            this.returnTotal = returnTotal;
        }

        public String getReturnDate() {
            return returnDate;
        }

        public void setReturnDate(String returnDate) {
            this.returnDate = returnDate;
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
// Transaction Details Inner class
    public class TransactionDetails {

        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("trxid")
        @Expose
        private String trxid;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("balance")
        @Expose
        private Double balance;
        @SerializedName("fee")
        @Expose
        private Integer fee;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("info")
        @Expose
        private String info;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("id")
        @Expose
        private Integer id;

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

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public Double getBalance() {
            return balance;
        }

        public void setBalance(Double balance) {
            this.balance = balance;
        }

        public Integer getFee() {
            return fee;
        }

        public void setFee(Integer fee) {
            this.fee = fee;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
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

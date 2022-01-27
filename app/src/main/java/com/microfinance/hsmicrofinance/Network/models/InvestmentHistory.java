package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InvestmentHistory {

    @SerializedName("Investement History Details")
    @Expose
    private List<InvestementHistoryDetail> investementHistoryDetails = null;

    public List<InvestementHistoryDetail> getInvestementHistoryDetails() {
        return investementHistoryDetails;
    }

    public void setInvestementHistoryDetails(List<InvestementHistoryDetail> investementHistoryDetails) {
        this.investementHistoryDetails = investementHistoryDetails;
    }

    public class InvestementHistoryDetail {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("fdrplan_id")
        @Expose
        private Integer fdrplanId;
        @SerializedName("amount")
        @Expose
        private Integer amount;
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
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

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

        public Integer getFdrplanId() {
            return fdrplanId;
        }

        public void setFdrplanId(Integer fdrplanId) {
            this.fdrplanId = fdrplanId;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
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

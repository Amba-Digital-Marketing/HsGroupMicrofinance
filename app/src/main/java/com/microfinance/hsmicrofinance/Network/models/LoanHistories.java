package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoanHistories {
    @SerializedName("loan_history")
    @Expose
    private List<LoanHistory> loanHistory = null;

    public List<LoanHistory> getLoanHistory() {
        return loanHistory;
    }

    public void setLoanHistory(List<LoanHistory> loanHistory) {
        this.loanHistory = loanHistory;
    }
    public static class LoanHistory {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("loanplan_id")
        @Expose
        private Integer loanplanId;
        @SerializedName("days")
        @Expose
        private Integer days;
        @SerializedName("charge")
        @Expose
        private Integer charge;
        @SerializedName("amount")
        @Expose
        private Integer amount;
        @SerializedName("return_date")
        @Expose
        private Object returnDate;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        @Override
        public String toString() {
            return "LoanHistory{" +
                    "id=" + id +
                    ", userId=" + userId +
                    ", loanplanId=" + loanplanId +
                    ", days=" + days +
                    ", charge=" + charge +
                    ", amount=" + amount +
                    ", returnDate=" + returnDate +
                    ", status=" + status +
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

        public Integer getLoanplanId() {
            return loanplanId;
        }

        public void setLoanplanId(Integer loanplanId) {
            this.loanplanId = loanplanId;
        }

        public Integer getDays() {
            return days;
        }

        public void setDays(Integer days) {
            this.days = days;
        }

        public Integer getCharge() {
            return charge;
        }

        public void setCharge(Integer charge) {
            this.charge = charge;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public Object getReturnDate() {
            return returnDate;
        }

        public void setReturnDate(Object returnDate) {
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

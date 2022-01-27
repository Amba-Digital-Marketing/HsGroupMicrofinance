package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoanRequest {
    @SerializedName("0")
    @Expose
    private String _0;
    @SerializedName("Loan Details")
    @Expose
    private LoanDetails loanDetails;

    public String get0() {
        return _0;
    }

    public void set0(String _0) {
        this._0 = _0;
    }

    public LoanDetails getLoanDetails() {
        return loanDetails;
    }

    public void setLoanDetails(LoanDetails loanDetails) {
        this.loanDetails = loanDetails;
    }
    public class LoanDetails {

        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("loanplan_id")
        @Expose
        private String loanplanId;
        @SerializedName("days")
        @Expose
        private Integer days;
        @SerializedName("charge")
        @Expose
        private Integer charge;
        @SerializedName("amount")
        @Expose
        private String amount;
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

        @Override
        public String toString() {
            return "LoanDetails{" +
                    "userId=" + userId +
                    ", loanplanId='" + loanplanId + '\'' +
                    ", days=" + days +
                    ", charge=" + charge +
                    ", amount='" + amount + '\'' +
                    ", status=" + status +
                    ", updatedAt='" + updatedAt + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", id=" + id +
                    '}';
        }
    }

}

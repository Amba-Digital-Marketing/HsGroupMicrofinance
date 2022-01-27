package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailsForWithdraw {
    @SerializedName("0")
    @Expose
    public String _0;
    @SerializedName("Details")
    @Expose
    public Details details;

    public String get_0() {
        return _0;
    }

    public void set_0(String _0) {
        this._0 = _0;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public class Details {

        @SerializedName("withdraw_method_id")
        @Expose
        public String withdrawMethodId;
        @SerializedName("term_id")
        @Expose
        public String termId;
        @SerializedName("amount_ksh")
        @Expose
        public Integer amountUsd;
        @SerializedName("account_number")
        @Expose
        public String accountNumber;
        @SerializedName("charge_type")
        @Expose
        public String chargeType;
        @SerializedName("fee")
        @Expose
        public Double fee;
        @SerializedName("amount_withdraw")
        @Expose
        public Double amountWithdraw;

        public Details(String withdrawMethodId, String termId, Integer amountUsd, String accountNumber, String chargeType, Double fee, Double amountWithdraw) {
            this.withdrawMethodId = withdrawMethodId;
            this.termId = termId;
            this.amountUsd = amountUsd;
            this.accountNumber = accountNumber;
            this.chargeType = chargeType;
            this.fee = fee;
            this.amountWithdraw = amountWithdraw;
        }

        @Override
        public String toString() {
            return "Details{" +
                    "withdrawMethodId='" + withdrawMethodId + '\'' +
                    ", termId='" + termId + '\'' +
                    ", amountUsd=" + amountUsd +
                    ", accountNumber='" + accountNumber + '\'' +
                    ", chargeType='" + chargeType + '\'' +
                    ", fee=" + fee +
                    ", amountWithdraw=" + amountWithdraw +
                    '}';
        }

        public String getWithdrawMethodId() {
            return withdrawMethodId;
        }

        public void setWithdrawMethodId(String withdrawMethodId) {
            this.withdrawMethodId = withdrawMethodId;
        }

        public String getTermId() {
            return termId;
        }

        public void setTermId(String termId) {
            this.termId = termId;
        }

        public Integer getAmountUsd() {
            return amountUsd;
        }

        public void setAmountUsd(Integer amountUsd) {
            this.amountUsd = amountUsd;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getChargeType() {
            return chargeType;
        }

        public void setChargeType(String chargeType) {
            this.chargeType = chargeType;
        }

        public Double getFee() {
            return fee;
        }

        public void setFee(Double fee) {
            this.fee = fee;
        }

        public Double getAmountWithdraw() {
            return amountWithdraw;
        }

        public void setAmountWithdraw(Double amountWithdraw) {
            this.amountWithdraw = amountWithdraw;
        }
    }

}

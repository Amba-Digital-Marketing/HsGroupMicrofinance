package com.microfinance.hsmicrofinance.Network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayLoanResponse {
        @SerializedName("0")
        @Expose
        private String _0;
        @SerializedName("Loan Return Transaction Details")
        @Expose
        private LoanReturnTransactionDetails loanReturnTransactionDetails;

        /**
         * No args constructor for use in serialization
         *
         */
        public PayLoanResponse() {
        }

        /**
         *
         * @param _0
         * @param loanReturnTransactionDetails
         */
        public PayLoanResponse(String _0, LoanReturnTransactionDetails loanReturnTransactionDetails) {
            super();
            this._0 = _0;
            this.loanReturnTransactionDetails = loanReturnTransactionDetails;
        }

        public String get0() {
            return _0;
        }

        public void set0(String _0) {
            this._0 = _0;
        }

        public LoanReturnTransactionDetails getLoanReturnTransactionDetails() {
            return loanReturnTransactionDetails;
        }

        public void setLoanReturnTransactionDetails(LoanReturnTransactionDetails loanReturnTransactionDetails) {
            this.loanReturnTransactionDetails = loanReturnTransactionDetails;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(PayLoanResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("_0");
            sb.append('=');
            sb.append(((this._0 == null)?"<null>":this._0));
            sb.append(',');
            sb.append("loanReturnTransactionDetails");
            sb.append('=');
            sb.append(((this.loanReturnTransactionDetails == null)?"<null>":this.loanReturnTransactionDetails));
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }


    public class LoanReturnTransactionDetails {

        @SerializedName("user_id")
        @Expose
        private int userId;
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
        private int id;

        /**
         * No args constructor for use in serialization
         */
        public LoanReturnTransactionDetails() {
        }

        /**
         * @param createdAt
         * @param amount
         * @param balance
         * @param fee
         * @param id
         * @param type
         * @param userId
         * @param trxid
         * @param status
         * @param info
         * @param updatedAt
         */
        public LoanReturnTransactionDetails(int userId, String trxid, Double amount, Double balance, Double fee, String status, String info, String type, String updatedAt, String createdAt, int id) {
            super();
            this.userId = userId;
            this.trxid = trxid;
            this.amount = amount;
            this.balance = balance;
            this.fee = fee;
            this.status = status;
            this.info = info;
            this.type = type;
            this.updatedAt = updatedAt;
            this.createdAt = createdAt;
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(LoanReturnTransactionDetails.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("userId");
            sb.append('=');
            sb.append(this.userId);
            sb.append(',');
            sb.append("trxid");
            sb.append('=');
            sb.append(((this.trxid == null) ? "<null>" : this.trxid));
            sb.append(',');
            sb.append("amount");
            sb.append('=');
            sb.append(this.amount);
            sb.append(',');
            sb.append("balance");
            sb.append('=');
            sb.append(this.balance);
            sb.append(',');
            sb.append("fee");
            sb.append('=');
            sb.append(this.fee);
            sb.append(',');
            sb.append("status");
            sb.append('=');
            sb.append(((this.status == null) ? "<null>" : this.status));
            sb.append(',');
            sb.append("info");
            sb.append('=');
            sb.append(((this.info == null) ? "<null>" : this.info));
            sb.append(',');
            sb.append("type");
            sb.append('=');
            sb.append(((this.type == null) ? "<null>" : this.type));
            sb.append(',');
            sb.append("updatedAt");
            sb.append('=');
            sb.append(((this.updatedAt == null) ? "<null>" : this.updatedAt));
            sb.append(',');
            sb.append("createdAt");
            sb.append('=');
            sb.append(((this.createdAt == null) ? "<null>" : this.createdAt));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(this.id);
            sb.append(',');
            if (sb.charAt((sb.length() - 1)) == ',') {
                sb.setCharAt((sb.length() - 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }
    }
}

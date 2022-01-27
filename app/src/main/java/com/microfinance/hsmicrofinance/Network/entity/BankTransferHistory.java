package com.microfinance.hsmicrofinance.Network.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BankTransferHistory {


    @SerializedName("Other Bank Transactions")
    @Expose
    private List<OtherBankTransaction> otherBankTransactions = null;

    public BankTransferHistory() {
    }

    public BankTransferHistory(List<OtherBankTransaction> otherBankTransactions) {
        super();
        this.otherBankTransactions = otherBankTransactions;
    }

    public List<OtherBankTransaction> getOtherBankTransactions() {
        return otherBankTransactions;
    }

    public void setOtherBankTransactions(List<OtherBankTransaction> otherBankTransactions) {
        this.otherBankTransactions = otherBankTransactions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(BankTransferHistory.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("otherBankTransactions");
        sb.append('=');
        sb.append(((this.otherBankTransactions == null) ? "<null>" : this.otherBankTransactions));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }


    public class OtherBankTransaction implements Parcelable {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("term_id")
        @Expose
        private String termId;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("bank_id")
        @Expose
        private String bankId;
        @SerializedName("transaction_id")
        @Expose
        private String transactionId;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("currency_rate")
        @Expose
        private String currencyRate;
        @SerializedName("info")
        @Expose
        private String info;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("transaction")
        @Expose
        private Transaction transaction;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("OtherBankTransaction{");
            sb.append("id='").append(id).append('\'');
            sb.append(", termId='").append(termId).append('\'');
            sb.append(", userId='").append(userId).append('\'');
            sb.append(", bankId='").append(bankId).append('\'');
            sb.append(", transactionId='").append(transactionId).append('\'');
            sb.append(", amount='").append(amount).append('\'');
            sb.append(", currencyRate='").append(currencyRate).append('\'');
            sb.append(", info='").append(info).append('\'');
            sb.append(", status='").append(status).append('\'');
            sb.append(", createdAt='").append(createdAt).append('\'');
            sb.append(", updatedAt='").append(updatedAt).append('\'');
            sb.append(", transaction=").append(transaction);
            sb.append('}');
            return sb.toString();
        }

        /**
         * No args constructor for use in serialization
         */


        public OtherBankTransaction() {
        }

        public OtherBankTransaction(String id, String termId, String userId, String bankId, String transactionId, String amount, String currencyRate, String info, String status, String createdAt, String updatedAt, Transaction transaction) {
            this.id = id;
            this.termId = termId;
            this.userId = userId;
            this.bankId = bankId;
            this.transactionId = transactionId;
            this.amount = amount;
            this.currencyRate = currencyRate;
            this.info = info;
            this.status = status;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.transaction = transaction;
        }

        protected OtherBankTransaction(Parcel in) {
            id = in.readString();
            termId = in.readString();
            userId = in.readString();
            bankId = in.readString();
            transactionId = in.readString();
            amount = in.readString();
            currencyRate = in.readString();
            info = in.readString();
            status = in.readString();
            createdAt = in.readString();
            updatedAt = in.readString();
        }

        public final Creator<OtherBankTransaction> CREATOR = new Creator<OtherBankTransaction>() {
            @Override
            public OtherBankTransaction createFromParcel(Parcel in) {
                return new OtherBankTransaction(in);
            }

            @Override
            public OtherBankTransaction[] newArray(int size) {
                return new OtherBankTransaction[size];
            }
        };

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTermId() {
            return termId;
        }

        public void setTermId(String termId) {
            this.termId = termId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getBankId() {
            return bankId;
        }

        public void setBankId(String bankId) {
            this.bankId = bankId;
        }

        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getCurrencyRate() {
            return currencyRate;
        }

        public void setCurrencyRate(String currencyRate) {
            this.currencyRate = currencyRate;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
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

        public Transaction getTransaction() {
            return transaction;
        }

        public void setTransaction(Transaction transaction) {
            this.transaction = transaction;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(termId);
            dest.writeString(userId);
            dest.writeString(bankId);
            dest.writeString(transactionId);
            dest.writeString(amount);
            dest.writeString(currencyRate);
            dest.writeString(info);
            dest.writeString(status);
            dest.writeString(createdAt);
            dest.writeString(updatedAt);
        }
    }


        public class Transaction {

            @SerializedName("id")
            @Expose
            private String id;
            @SerializedName("user_id")
            @Expose
            private String userId;
            @SerializedName("trxid")
            @Expose
            private String trxid;
            @SerializedName("amount")
            @Expose
            private String amount;
            @SerializedName("balance")
            @Expose
            private String balance;
            @SerializedName("fee")
            @Expose
            private String fee;
            @SerializedName("status")
            @Expose
            private String status;
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

            /**
             * No args constructor for use in serialization
             */


            public Transaction() {
            }

            @Override
            public String toString() {
                final StringBuffer sb = new StringBuffer("Transaction{");
                sb.append("id='").append(id).append('\'');
                sb.append(", userId='").append(userId).append('\'');
                sb.append(", trxid='").append(trxid).append('\'');
                sb.append(", amount='").append(amount).append('\'');
                sb.append(", balance='").append(balance).append('\'');
                sb.append(", fee='").append(fee).append('\'');
                sb.append(", status='").append(status).append('\'');
                sb.append(", info='").append(info).append('\'');
                sb.append(", type='").append(type).append('\'');
                sb.append(", createdAt='").append(createdAt).append('\'');
                sb.append(", updatedAt='").append(updatedAt).append('\'');
                sb.append('}');
                return sb.toString();
            }

            public Transaction(String id, String userId, String trxid, String amount, String balance, String fee, String status, String info, String type, String createdAt, String updatedAt) {
                this.id = id;
                this.userId = userId;
                this.trxid = trxid;
                this.amount = amount;
                this.balance = balance;
                this.fee = fee;
                this.status = status;
                this.info = info;
                this.type = type;
                this.createdAt = createdAt;
                this.updatedAt = updatedAt;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
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

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getFee() {
                return fee;
            }

            public void setFee(String fee) {
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

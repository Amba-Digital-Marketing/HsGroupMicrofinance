package com.microfinance.hsmicrofinance.Network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransferSuccesful {

    @SerializedName("0")
    @Expose
    public String message;

    @SerializedName("Entering Data In Transactions Table")
    @Expose
    public TransactionsTable transactionsTable;

    @SerializedName("Entering Data In Other Bank Transactions Table")
    @Expose
    public BankTransactionsTable bankTransactionsTable;

    public static class TransactionsTable {

        @SerializedName("info")
        @Expose
        public String info;
    }

    public static class BankTransactionsTable {

        @SerializedName("amount")
        @Expose
        public String amount;
    }
}

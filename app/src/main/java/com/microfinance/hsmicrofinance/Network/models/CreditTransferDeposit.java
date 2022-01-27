package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreditTransferDeposit {
    @SerializedName("0")
    @Expose
    private String _0;
    @SerializedName("Payment Details")
    @Expose
    private PaymentDetails paymentDetails;

    public String get0() {
        return _0;
    }

    public void set0(String _0) {
        this._0 = _0;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
    public class PaymentDetails {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("amount")
        @Expose
        private Integer amount;
        @SerializedName("charge")
        @Expose
        private Double charge;
        @SerializedName("payable")
        @Expose
        private Double payable;
        @SerializedName("usd_amount")
        @Expose
        private Double usdAmount;
        @SerializedName("currency")
        @Expose
        private Integer currency;

        @Override
        public String toString() {
            return "PaymentDetails{" +
                    "id='" + id + '\'' +
                    ", type='" + type + '\'' +
                    ", amount=" + amount +
                    ", charge=" + charge +
                    ", payable=" + payable +
                    ", usdAmount=" + usdAmount +
                    ", currency=" + currency +
                    '}';
        }

        public PaymentDetails() {
        }

        public PaymentDetails(String id, String type, Integer amount, Double charge, Double payable, Double usdAmount, Integer currency) {
            this.id = id;
            this.type = type;
            this.amount = amount;
            this.charge = charge;
            this.payable = payable;
            this.usdAmount = usdAmount;
            this.currency = currency;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public Double getCharge() {
            return charge;
        }

        public void setCharge(Double charge) {
            this.charge = charge;
        }

        public Double getPayable() {
            return payable;
        }

        public void setPayable(Double payable) {
            this.payable = payable;
        }

        public Double getUsdAmount() {
            return usdAmount;
        }

        public void setUsdAmount(Double usdAmount) {
            this.usdAmount = usdAmount;
        }

        public Integer getCurrency() {
            return currency;
        }

        public void setCurrency(Integer currency) {
            this.currency = currency;
        }
    }
}

package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ManualPaymentDetails {

    @SerializedName("0")
    @Expose
    public String _0;
    @SerializedName("Payment Details")
    @Expose
    public PaymentDetails paymentDetails;

    public String get_0() {
        return _0;
    }

    public void set_0(String _0) {
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
        public String id;
        @SerializedName("type")
        @Expose
        public String type;
        @SerializedName("amount")
        @Expose
        public Double amount;
        @SerializedName("charge")
        @Expose
        public Double charge;
        @SerializedName("payable")
        @Expose
        public Double payable;
        @SerializedName("ksh_amount")
        @Expose
        public Double usdAmount;
        @SerializedName("currency")
        @Expose
        public String currency;

        @Override
        public String toString() {
            return "PaymentDetails{" +
                    "id='" + id + '\'' +
                    ", type='" + type + '\'' +
                    ", amount=" + amount +
                    ", charge=" + charge +
                    ", payable=" + payable +
                    ", usdmount=" + usdAmount +
                    ", currency=" + currency +
                    '}';
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

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }
    }


}

package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MpesaDepositCallBack {
    @SerializedName("Mpesa payment Details")
    @Expose
    public List<MpesaPaymentDetail> mpesaPaymentDetails = null;

    public List<MpesaPaymentDetail> getMpesaPaymentDetails() {
        return mpesaPaymentDetails;
    }

    public void setMpesaPaymentDetails(List<MpesaPaymentDetail> mpesaPaymentDetails) {
        this.mpesaPaymentDetails = mpesaPaymentDetails;
    }

    @Override
    public String toString() {
        return "MpesaDepositCallBack{" +
                "mpesaPaymentDetails=" + mpesaPaymentDetails +
                '}';
    }

    public class MpesaPaymentDetail {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("result_desc")
        @Expose
        public String resultDesc;
        @SerializedName("result_code")
        @Expose
        public String resultCode;
        @SerializedName("merchant_request_id")
        @Expose
        public String merchantRequestId;
        @SerializedName("checkout_request_id")
        @Expose
        public String checkoutRequestId;
        @SerializedName("amount")
        @Expose
        public String amount;
        @SerializedName("mpesa_receipt_number")
        @Expose
        public String mpesaReceiptNumber;
        @SerializedName("transaction_date")
        @Expose
        public String transactionDate;
        @SerializedName("phone_number")
        @Expose
        public String phoneNumber;
        @SerializedName("created_at")
        @Expose
        public String createdAt;

        @Override
        public String toString() {
            return "MpesaPaymentDetail{" +
                    "id=" + id +
                    ", resultDesc='" + resultDesc + '\'' +
                    ", resultCode='" + resultCode + '\'' +
                    ", merchantRequestId='" + merchantRequestId + '\'' +
                    ", checkoutRequestId='" + checkoutRequestId + '\'' +
                    ", amount='" + amount + '\'' +
                    ", mpesaReceiptNumber='" + mpesaReceiptNumber + '\'' +
                    ", transactionDate='" + transactionDate + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    '}';
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getResultDesc() {
            return resultDesc;
        }

        public void setResultDesc(String resultDesc) {
            this.resultDesc = resultDesc;
        }

        public String getResultCode() {
            return resultCode;
        }

        public void setResultCode(String resultCode) {
            this.resultCode = resultCode;
        }

        public String getMerchantRequestId() {
            return merchantRequestId;
        }

        public void setMerchantRequestId(String merchantRequestId) {
            this.merchantRequestId = merchantRequestId;
        }

        public String getCheckoutRequestId() {
            return checkoutRequestId;
        }

        public void setCheckoutRequestId(String checkoutRequestId) {
            this.checkoutRequestId = checkoutRequestId;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getMpesaReceiptNumber() {
            return mpesaReceiptNumber;
        }

        public void setMpesaReceiptNumber(String mpesaReceiptNumber) {
            this.mpesaReceiptNumber = mpesaReceiptNumber;
        }

        public String getTransactionDate() {
            return transactionDate;
        }

        public void setTransactionDate(String transactionDate) {
            this.transactionDate = transactionDate;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
    }


}

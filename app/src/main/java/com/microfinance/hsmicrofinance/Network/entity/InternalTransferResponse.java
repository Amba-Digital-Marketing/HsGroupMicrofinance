package com.microfinance.hsmicrofinance.Network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InternalTransferResponse {


        @SerializedName("0")
        @Expose
        public String _0;
        @SerializedName("Transfer Information")
        @Expose
        public TransferInformation transferInformation;

        /**
         * No args constructor for use in serialization
         */
        public InternalTransferResponse() {
        }

        /**
         * @param transferInformation
         * @param _0
         */
        public InternalTransferResponse(String _0, TransferInformation transferInformation) {
            super();
            this._0 = _0;
            this.transferInformation = transferInformation;
        }

        public String get0() {
            return _0;
        }

        public void set0(String _0) {
            this._0 = _0;
        }

        public TransferInformation getTransferInformation() {
            return transferInformation;
        }

        public void setTransferInformation(TransferInformation transferInformation) {
            this.transferInformation = transferInformation;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(InternalTransferResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("_0");
            sb.append('=');
            sb.append(((this._0 == null) ? "<null>" : this._0));
            sb.append(',');
            sb.append("transferInformation");
            sb.append('=');
            sb.append(((this.transferInformation == null) ? "<null>" : this.transferInformation));
            sb.append(',');
            if (sb.charAt((sb.length() - 1)) == ',') {
                sb.setCharAt((sb.length() - 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }


    public class TransferInformation {

        @SerializedName("amount")
        @Expose
        public double amount;
        @SerializedName("total_amount")
        @Expose
        public double totalAmount;
        @SerializedName("account_no")
        @Expose
        public String accountNo;
        @SerializedName("charge_type")
        @Expose
        public String chargeType;
        @SerializedName("total_charge")
        @Expose
        public double totalCharge;

        /**
         * No args constructor for use in serialization
         */
        public TransferInformation() {
        }

        /**
         * @param totalAmount
         * @param amount
         * @param totalCharge
         * @param accountNo
         * @param chargeType
         */
        public TransferInformation(int amount, double totalAmount, String accountNo, String chargeType, double totalCharge) {
            super();
            this.amount = amount;
            this.totalAmount = totalAmount;
            this.accountNo = accountNo;
            this.chargeType = chargeType;
            this.totalCharge = totalCharge;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getAccountNo() {
            return accountNo;
        }

        public void setAccountNo(String accountNo) {
            this.accountNo = accountNo;
        }

        public String getChargeType() {
            return chargeType;
        }

        public void setChargeType(String chargeType) {
            this.chargeType = chargeType;
        }

        public double getTotalCharge() {
            return totalCharge;
        }

        public void setTotalCharge(double totalCharge) {
            this.totalCharge = totalCharge;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(TransferInformation.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("amount");
            sb.append('=');
            sb.append(this.amount);
            sb.append(',');
            sb.append("totalAmount");
            sb.append('=');
            sb.append(this.totalAmount);
            sb.append(',');
            sb.append("accountNo");
            sb.append('=');
            sb.append(((this.accountNo == null) ? "<null>" : this.accountNo));
            sb.append(',');
            sb.append("chargeType");
            sb.append('=');
            sb.append(((this.chargeType == null) ? "<null>" : this.chargeType));
            sb.append(',');
            sb.append("totalCharge");
            sb.append('=');
            sb.append(this.totalCharge);
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
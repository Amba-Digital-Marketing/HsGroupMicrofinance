package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountPasswordChange {
    @SerializedName("0")
    @Expose
    private String _0;
    @SerializedName("Data Sent")
    @Expose
    private DataSent dataSent;

    public String get0() {
        return _0;
    }

    public void set0(String _0) {
        this._0 = _0;
    }

    public DataSent getDataSent() {
        return dataSent;
    }

    public void setDataSent(DataSent dataSent) {
        this.dataSent = dataSent;
    }
    public class DataSent {

        @SerializedName("Email")
        @Expose
        public String email;
        @SerializedName("Type")
        @Expose
        public String type;
        @SerializedName("New Password")
        @Expose
        public String newPassword;
        @SerializedName("verification_code")
        @Expose
        public Integer verificationCode;

        @Override
        public String toString() {
            return "DataSent{" +
                    "email='" + email + '\'' +
                    ", type='" + type + '\'' +
                    ", newPassword='" + newPassword + '\'' +
                    ", verificationCode=" + verificationCode +
                    '}';
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }

        public Integer getVerificationCode() {
            return verificationCode;
        }

        public void setVerificationCode(Integer verificationCode) {
            this.verificationCode = verificationCode;
        }
    }
}

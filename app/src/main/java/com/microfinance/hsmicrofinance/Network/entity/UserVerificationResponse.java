package com.microfinance.hsmicrofinance.Network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserVerificationResponse {

    @SerializedName("0")
    @Expose
    private String _0;
    @SerializedName("Data Sent in Email")
    @Expose
    private DataSentInEmail dataSentInEmail;


    public UserVerificationResponse() {
    }


    public UserVerificationResponse(String _0, DataSentInEmail dataSentInEmail) {
        super();
        this._0 = _0;
        this.dataSentInEmail = dataSentInEmail;
    }

    public String get0() {
        return _0;
    }

    public void set0(String _0) {
        this._0 = _0;
    }

    public DataSentInEmail getDataSentInEmail() {
        return dataSentInEmail;
    }

    public void setDataSentInEmail(DataSentInEmail dataSentInEmail) {
        this.dataSentInEmail = dataSentInEmail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(UserVerificationResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("_0");
        sb.append('=');
        sb.append(((this._0 == null)?"<null>":this._0));
        sb.append(',');
        sb.append("dataSentInEmail");
        sb.append('=');
        sb.append(((this.dataSentInEmail == null)?"<null>":this.dataSentInEmail));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }




    public class DataSentInEmail {

        @SerializedName("otp_number")
        @Expose
        private int otpNumber;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("email")
        @Expose
        private String email;


        public DataSentInEmail() {
        }


        public DataSentInEmail(int otpNumber, String type, String email) {
            super();
            this.otpNumber = otpNumber;
            this.type = type;
            this.email = email;
        }

        public int getOtpNumber() {
            return otpNumber;
        }

        public void setOtpNumber(int otpNumber) {
            this.otpNumber = otpNumber;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(DataSentInEmail.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("otpNumber");
            sb.append('=');
            sb.append(this.otpNumber);
            sb.append(',');
            sb.append("type");
            sb.append('=');
            sb.append(((this.type == null)?"<null>":this.type));
            sb.append(',');
            sb.append("email");
            sb.append('=');
            sb.append(((this.email == null)?"<null>":this.email));
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }

    }

}
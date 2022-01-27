package com.microfinance.hsmicrofinance.Network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotPasswordResponse {

        @SerializedName("status")
        @Expose
        private String status;

        /**
         * No args constructor for use in serialization
         *
         */
        public ForgotPasswordResponse() {
        }

        /**
         *
         * @param status
         */
        public ForgotPasswordResponse(String status) {
            super();
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(ForgotPasswordResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("status");
            sb.append('=');
            sb.append(((this.status == null)?"<null>":this.status));
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }


}

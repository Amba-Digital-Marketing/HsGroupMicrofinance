package com.microfinance.hsmicrofinance.Network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PinChangeResponse {


        @SerializedName("Success")
        @Expose
        private String success;
        @SerializedName("0")
        @Expose
        private String _0;

        /**
         * No args constructor for use in serialization
         *
         */
        public PinChangeResponse() {
        }

        /**
         *
         * @param _0
         * @param success
         */
        public PinChangeResponse(String success, String _0) {
            super();
            this.success = success;
            this._0 = _0;
        }

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

        public String get0() {
            return _0;
        }

        public void set0(String _0) {
            this._0 = _0;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(PinChangeResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("success");
            sb.append('=');
            sb.append(((this.success == null)?"<null>":this.success));
            sb.append(',');
            sb.append("_0");
            sb.append('=');
            sb.append(((this._0 == null)?"<null>":this._0));
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }


}

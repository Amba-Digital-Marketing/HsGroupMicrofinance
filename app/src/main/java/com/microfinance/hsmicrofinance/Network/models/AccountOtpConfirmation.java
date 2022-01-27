package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountOtpConfirmation {
    @SerializedName("Success")
    @Expose
    private String success;
    @SerializedName("0")
    @Expose
    private String _0;

    @Override
    public String toString() {
        return "AccountOtpConfirmation{" +
                "success='" + success + '\'' +
                ", _0='" + _0 + '\'' +
                '}';
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
}

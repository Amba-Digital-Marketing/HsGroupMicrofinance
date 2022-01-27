package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountSettingConfirmation {
    @SerializedName("Session Details")
    @Expose
    private String sessionDetails;
    @SerializedName("0")
    @Expose
    private String _0;

    public String getSessionDetails() {
        return sessionDetails;
    }

    public void setSessionDetails(String sessionDetails) {
        this.sessionDetails = sessionDetails;
    }

    public String get0() {
        return _0;
    }

    public void set0(String _0) {
        this._0 = _0;
    }

    @Override
    public String toString() {
        return "AccountConfirmPassword{" +
                "sessionDetails='" + sessionDetails + '\'' +
                ", _0='" + _0 + '\'' +
                '}';
    }
}

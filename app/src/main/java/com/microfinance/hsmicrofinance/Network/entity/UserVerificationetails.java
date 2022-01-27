package com.microfinance.hsmicrofinance.Network.entity;

public class UserVerificationetails {
    int varificationStatus;
    String email;
    String otp;
    String pin;
    String token;

    public UserVerificationetails() {

    }

    public UserVerificationetails(int varificationStatus, String email, String otp, String pin,String token) {
        this.varificationStatus = varificationStatus;
        this.email = email;
        this.otp = otp;
        this.pin = pin;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getVarificationStatus() {
        return varificationStatus;
    }

    public void setVarificationStatus(int varificationStatus) {
        this.varificationStatus = varificationStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}

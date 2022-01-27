package com.microfinance.hsmicrofinance.Network.entity;

public class Person {
    String email = "";
    String pin ="";
    public Person(String email, String pin) {
        this.email = email;
        this.pin = pin;
    }
    public Person() {

    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPin() {
        return pin;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }


}

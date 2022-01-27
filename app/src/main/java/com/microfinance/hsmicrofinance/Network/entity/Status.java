package com.microfinance.hsmicrofinance.Network.entity;

public class Status {
    String status;
    String color;

    public Status(String status, String color) {
        this.status = status;
        this.color = color;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}


package com.microfinance.hsmicrofinance.Network.models;

import android.graphics.Bitmap;

public class UploadDetails {
    private String amount;
    private String comment;
    private String currency;
    private String id;
    private Bitmap mBitmap;

    public UploadDetails(String amount, String comment, String currency, String id, Bitmap bitmap) {
        this.amount = amount;
        this.comment = comment;
        this.currency = currency;
        this.id = id;
        mBitmap = bitmap;
    }

    @Override
    public String toString() {
        return "UploadDetails{" +
                "amount='" + amount + '\'' +
                ", comment='" + comment + '\'' +
                ", currency=" + currency +
                ", id=" + id +
                ", mBitmap=" + mBitmap +
                '}';
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }
}

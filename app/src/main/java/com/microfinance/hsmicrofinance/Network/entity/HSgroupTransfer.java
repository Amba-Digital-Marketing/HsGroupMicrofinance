package com.microfinance.hsmicrofinance.Network.entity;

public class HSgroupTransfer {
    private String trxId;
    private  double amount;
    private String accNo;
    private double balance;
    private double fee;
    private String date;

    public HSgroupTransfer() {
    }

    public HSgroupTransfer(String trxId, double amount, String accNo, double balance, double fee, String date) {
        this.trxId = trxId;
        this.amount = amount;
        this.accNo = accNo;
        this.balance = balance;
        this.fee = fee;
        this.date = date;
    }

    @Override
    public String toString() {
        return "HSgroupTransfer{" +
                "trxId='" + trxId + '\'' +
                ", amount=" + amount +
                ", accNo=" + accNo +
                ", balance=" + balance +
                ", fee=" + fee +
                ", date='" + date + '\'' +
                '}';
    }

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

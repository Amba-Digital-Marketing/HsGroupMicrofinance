package com.microfinance.hsmicrofinance.Network.data;

public class LoansDescription {
     int id;
       int  userId;
       int days;
       double charge;
      int loanId;
      double amount;
       int  status;

    public LoansDescription(int id, int userId, int days, double charge, int loanId, double amount, int status) {
        this.id = id;
        this.userId = userId;
        this.days = days;
        this.charge = charge;
        this.loanId = loanId;
        this.amount = amount;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

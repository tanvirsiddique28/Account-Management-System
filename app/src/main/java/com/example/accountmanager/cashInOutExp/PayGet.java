package com.example.accountmanager.cashInOutExp;

public class PayGet {

    String identity;
    Double amount;
    String date;

    public PayGet() {
    }

    public PayGet(String identity, Double amount, String date) {
        this.identity = identity;
        this.amount = amount;
        this.date = date;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

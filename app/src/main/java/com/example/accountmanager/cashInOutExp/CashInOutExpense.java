package com.example.accountmanager.cashInOutExp;

public class CashInOutExpense {

    String identity;
    Double amount;
    String description;
    String date;
    String month;

    public CashInOutExpense() {
    }

    public CashInOutExpense(String identity, Double amount, String description, String date, String month) {
        this.identity = identity;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.month = month;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}

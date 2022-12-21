package com.example.accountmanager.buysell;


import java.sql.Date;

public class BuySell  {

    String identity;
    double amount;
    String productname;
    String date;
    String month;

    public BuySell() {
    }

    public BuySell(String identity, double amount, String productname, String date, String month) {
        this.identity = identity;
        this.amount = amount;
        this.productname = productname;
        this.date = date;
        this.month = month;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
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

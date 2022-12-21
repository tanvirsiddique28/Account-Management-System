package com.example.accountmanager.customersupplier;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.accountmanager.R;

public class CustomerAndSupplier {

    private String identity;
    private String name;
    private int number;
    private double amount ;
    private String date;

    public CustomerAndSupplier() {

    }

    public CustomerAndSupplier(String identity, String name, int number, double amount, String date) {
        this.identity = identity;
        this.name = name;
        this.number = number;
        this.amount = amount;
        this.date = date;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}

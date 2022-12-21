package com.example.accountmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.accountmanager.buysell.buyProducts;
import com.example.accountmanager.cashInOutExp.CashInOutExpense;

public class LogIn extends AppCompatActivity {
    String name = "tanvir";
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        username = findViewById(R.id.idEdtUserName);
        password = findViewById(R.id.idEdtPassword);
    }
    public void logIn(View view){

        if(username.getText().toString().equals(name) && password.getText().toString().equals("1234")){

            Intent send = new Intent(LogIn.this, MainActivity.class);
            startActivity(send);
        }else if(!username.equals(name)){
            Toast.makeText(this, "username and password wrong", Toast.LENGTH_LONG).show();
        }


    }
}
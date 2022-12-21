package com.example.accountmanager.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.accountmanager.MainActivity;
import com.example.accountmanager.R;

public class myDatabase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_database);

        // Backspace Click Event
        ImageView imagemenu = findViewById(R.id.imageMenu);
        imagemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent send = new Intent(myDatabase.this, MainActivity.class);
                startActivity(send);
            }


        });
        //----------------------------------------
    }

    public  void allCusSupData(View view){
        Intent send = new Intent(myDatabase.this, CusSupData.class);
        startActivity(send);

    }
    public  void allBuySellData(View view){
        Intent send = new Intent(myDatabase.this, BuySellData.class);
        startActivity(send);

    }

    public  void allCashExpenseData(View view){
        Intent send = new Intent(myDatabase.this, cashInOutExpense.class);
        startActivity(send);

    }
}
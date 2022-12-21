package com.example.accountmanager.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.accountmanager.R;
import com.example.accountmanager.cashInOutExp.CashInOutExpAdapter;
import com.example.accountmanager.cashInOutExp.CashInOutExpDatabase;
import com.example.accountmanager.cashInOutExp.CashInOutExpense;

import java.util.ArrayList;
import java.util.List;

public class cashInOutExpense extends AppCompatActivity {
    // Database
    CashInOutExpDatabase cashInOutExpHelper;
    CashInOutExpense cashInOutExpense;
    List<CashInOutExpense> list;
    ListView listView;
    ArrayList<String> arrayList;
    //-------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_in_out_expense);

        cashInOutExpHelper = new CashInOutExpDatabase(getApplicationContext());
        listView = findViewById(R.id.cashexpenselllist);

        // Backspace Click Event
        ImageView imagemenu = findViewById(R.id.imageMenu);
        imagemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent send = new Intent(cashInOutExpense.this, myDatabase.class);
                startActivity(send);
            }


        });
        //----------------------------------------
    }
    String identity = "input";
    public void showAllCashInData(View view){
        cashInOutExpense = new CashInOutExpense();
        cashInOutExpense.setIdentity(identity);

        list = cashInOutExpHelper.showAllcashInOutExp(cashInOutExpense);

        CashInOutExpAdapter adapter = new CashInOutExpAdapter(getApplicationContext(),list);
        listView.setAdapter(adapter);

    }
    String identity2 = "output";
    public void showAllCashOutData(View view){
        cashInOutExpense = new CashInOutExpense();
        cashInOutExpense.setIdentity(identity2);

        list = cashInOutExpHelper.showAllcashInOutExp(cashInOutExpense);

        CashInOutExpAdapter adapter = new CashInOutExpAdapter(getApplicationContext(),list);
        listView.setAdapter(adapter);

    }
    String identity3 = "expense";
    public void showAllExpenseData(View view){
        cashInOutExpense = new CashInOutExpense();
        cashInOutExpense.setIdentity(identity3);

        list = cashInOutExpHelper.showAllcashInOutExp(cashInOutExpense);

        CashInOutExpAdapter adapter = new CashInOutExpAdapter(getApplicationContext(),list);
        listView.setAdapter(adapter);

    }
}
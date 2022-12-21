package com.example.accountmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.accountmanager.buysell.BuySell;
import com.example.accountmanager.buysell.BuySellDatabase;
import com.example.accountmanager.cashInOutExp.CashInOutExpDatabase;
import com.example.accountmanager.cashInOutExp.CashInOutExpense;
import com.example.accountmanager.cashInOutExp.PayGet;
import com.example.accountmanager.customersupplier.CustomerAndSupplier;
import com.example.accountmanager.customersupplier.PayGetDatabase;
import com.example.accountmanager.database.BuySellData;
import com.example.accountmanager.database.myDatabase;

import java.util.ArrayList;
import java.util.List;

public class CashReport extends AppCompatActivity {
    //---- Database-----------------
    CashInOutExpDatabase cashInOutExpHelper;
    CashInOutExpense cashInOutExpense;
    List<CashInOutExpense> list;

    BuySellDatabase buySellhelper;
    BuySell buySell;
    List<BuySell> list2;

    PayGetDatabase payGethelper;
    PayGet payGet;
    List<PayGet> list3;
    //-----------------------------
    ListView listView;
    ArrayList<String> arrayList;
    //------------------------------------

    String currentdate;
    TextView date;
    EditText cashinhand,buy,sell,cashin,cashout,expense,get,pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_report);

        date = findViewById(R.id.textView5);
        cashinhand = findViewById(R.id.editTextTextPersonName25);
        buy = findViewById(R.id.editTextTextPersonName26);
        sell = findViewById(R.id.editTextTextPersonName27);
        cashin = findViewById(R.id.editTextTextPersonName29);
        cashout = findViewById(R.id.editTextTextPersonName30);
        expense = findViewById(R.id.editTextTextPersonName31);
        get = findViewById(R.id.editTextTextPersonName32);
        pay = findViewById(R.id.editTextTextPersonName33);

        Intent i = getIntent();
        currentdate = i.getStringExtra("currentdate");
        date.setText(currentdate);

        payGethelper = new PayGetDatabase(getApplicationContext());
        buySellhelper = new BuySellDatabase(getApplicationContext());
        cashInOutExpHelper = new CashInOutExpDatabase(getApplicationContext());
        // Backspace Click Event
        ImageView imagemenu = findViewById(R.id.imageMenu);
        imagemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent send = new Intent(CashReport.this, MainActivity.class);
                startActivity(send);
            }


        });
        //----------------------------------------
    }

    String identity = "input";

    public void getCashInHand(View view){
        cashInOutExpense = new CashInOutExpense();
        cashInOutExpense.setIdentity(identity);

        list = cashInOutExpHelper.showAllcashInOutExp(cashInOutExpense);
        CashInOutExpense ce = list.get(list.size()-1);


        cashinhand.setText(String.valueOf(ce.getAmount()));
        getDatebuy(view);
        getDatesell(view);
        getDateinput(view);
        getDateoutput(view);
        getDateexpense(view);
        getDatePay(view);
        getDateGet(view);
    }
    String identity2 = "buy";
    public void  getDatebuy(View view){
        buySell = new BuySell();
        buySell.setIdentity(identity2);
        buySell.setDate(date.getText().toString());

        list2  = buySellhelper.getDate(buySell);


        double sum = 0 ;
        for(BuySell b:list2){
            sum += b.getAmount();
        }
     buy.setText(String.valueOf(sum));
    }
    String identity3 = "sell";
    public void  getDatesell(View view){
        buySell = new BuySell();
        buySell.setIdentity(identity3);
        buySell.setDate(date.getText().toString());

        list2  = buySellhelper.getDate(buySell);


        double sum = 0 ;
        for(BuySell b:list2){
            sum += b.getAmount();
        }
        sell.setText(String.valueOf(sum));
    }
    String identity4 = "input";
    public void  getDateinput(View view){
        cashInOutExpense = new CashInOutExpense();
        cashInOutExpense.setIdentity(identity4);
        cashInOutExpense.setDate(date.getText().toString());

        list  = cashInOutExpHelper.getDatec(cashInOutExpense);


        double sum = 0 ;
        for(CashInOutExpense b:list){
            sum += b.getAmount();
        }
        cashin.setText(String.valueOf(sum));
    }
    String identity5 = "output";
    public void  getDateoutput(View view){
        cashInOutExpense = new CashInOutExpense();
        cashInOutExpense.setIdentity(identity5);
        cashInOutExpense.setDate(date.getText().toString());

        list  = cashInOutExpHelper.getDatec(cashInOutExpense);


        double sum = 0 ;
        for(CashInOutExpense b:list){
            sum += b.getAmount();
        }
        cashout.setText(String.valueOf(sum));
    }
    String identity6 = "expense";
    public void  getDateexpense(View view){
        cashInOutExpense = new CashInOutExpense();
        cashInOutExpense.setIdentity(identity6);
        cashInOutExpense.setDate(date.getText().toString());

        list  = cashInOutExpHelper.getDatec(cashInOutExpense);


        double sum = 0 ;
        for(CashInOutExpense b:list){
            sum += b.getAmount();
        }
       expense.setText(String.valueOf(sum));
    }
    String identity7 = "pay";
    public void  getDatePay(View view){
        payGet = new PayGet();
        payGet.setIdentity(identity7);
        payGet.setDate(date.getText().toString());

        list3  = payGethelper.getDatep(payGet);


        double sum = 0 ;
        for(PayGet b:list3){
            sum += b.getAmount();
        }
        pay.setText(String.valueOf(sum));
    }
    String identity8 = "get";
    public void  getDateGet(View view){
        payGet = new PayGet();
        payGet.setIdentity(identity8);
        payGet.setDate(date.getText().toString());

        list3  = payGethelper.getDatep(payGet);


        double sum = 0 ;
        for(PayGet b:list3){
            sum += b.getAmount();
        }
       get.setText(String.valueOf(sum));
    }
}
package com.example.accountmanager.buysell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.accountmanager.MainActivity;
import com.example.accountmanager.R;
import com.example.accountmanager.cashInOutExp.CashInOutExpDatabase;
import com.example.accountmanager.cashInOutExp.CashInOutExpense;
import com.example.accountmanager.customersupplier.CustomerAndSupplier;
import com.example.accountmanager.customersupplier.CustomerAndSupplierAdapter;
import com.example.accountmanager.customersupplier.CustomerAndSupplierDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class buyProducts extends AppCompatActivity {

    // Database
    BuySellDatabase buySellhelper;
    BuySell buySell;
    List<BuySell> list;

    CashInOutExpDatabase cashInOutExpHelper;
    CashInOutExpense cashInOutExpense;
    List<CashInOutExpense> list2;

    ListView listView;
    ArrayList<String> arrayList;
    //-------------------------------------------
    String currentdate;
    String currentmonth;
    String cashinh;
    EditText getamount,amount,productname,date,month;
    TextView warnmsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_products);

        getamount = findViewById(R.id.editTextTextPersonName24);
        amount = findViewById(R.id.editTextTextPersonName12);
        productname = findViewById(R.id.editTextTextPersonName13);
        date = findViewById(R.id.editTextTextPersonName14);
        month = findViewById(R.id.editTextTextPersonName15);
        warnmsg = findViewById(R.id.textView12);

        Intent i = getIntent();
        cashinh = i.getStringExtra("cashinhand");
        currentdate = i.getStringExtra("currentdate");
        currentmonth = i.getStringExtra("currentmonth");

        getamount.setText(cashinh);
        date.setText(currentdate);
        month.setText(currentmonth);

        // Database

        buySellhelper = new BuySellDatabase(getApplicationContext());
        cashInOutExpHelper = new CashInOutExpDatabase(getApplicationContext());
        //listView = findViewById(R.id.buyselllist);


        // Backspace Click Event
        ImageView imagemenu = findViewById(R.id.imageMenu);
        imagemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent send = new Intent(buyProducts.this, MainActivity.class);
                startActivity(send);
            }


        });
        //----------------------------------------
    }

    String identitysave = "buy";
    Double currentamount;
    Double newamount;
    Double total;
    String warn = "    you don't have sufficient balance";
    String warn2 = "    bought";
    public void saveBuyInfo(View view){
        currentamount = Double.parseDouble(getamount.getText().toString());
        newamount = Double.parseDouble(amount.getText().toString());
        if(newamount < currentamount ){

            total = currentamount-newamount;
            getamount.setText(total.toString());
            buySell = new BuySell(
                    identitysave,
                    newamount,
                    productname.getText().toString(),
                    date.getText().toString(),
                    month.getText().toString()

            );

            buySellhelper.saveBuyAndSell(buySell);
            warnmsg.setText(warn2);
            updateCurrentCashOutAmounts(view);
        }else {
            warnmsg.setText(warn);
        }


    }
    String identitysave2 = "input";
    public void  updateCurrentCashOutAmounts(View view){

        cashInOutExpense = new CashInOutExpense();
        cashInOutExpense.setIdentity(identitysave2);
        cashInOutExpense.setAmount(total);
        cashInOutExpHelper.updateCurrentCapital(cashInOutExpense);

    }



}
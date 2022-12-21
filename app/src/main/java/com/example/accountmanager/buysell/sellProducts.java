package com.example.accountmanager.buysell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.accountmanager.MainActivity;
import com.example.accountmanager.R;
import com.example.accountmanager.cashInOutExp.CashInOutExpDatabase;
import com.example.accountmanager.cashInOutExp.CashInOutExpense;

import java.util.ArrayList;
import java.util.List;

public class sellProducts extends AppCompatActivity {

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
    String cashinh;
    String currentdate;
    String currentmonth;
    EditText getamount,amount,productname,date,month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_products);

        getamount = findViewById(R.id.editTextTextPersonName24);
        amount = findViewById(R.id.editTextTextPersonName12);
        productname = findViewById(R.id.editTextTextPersonName13);
        date = findViewById(R.id.editTextTextPersonName14);
        month = findViewById(R.id.editTextTextPersonName15);

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

        // Backspace Click Event
        ImageView imagemenu = findViewById(R.id.imageMenu);
        imagemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent send = new Intent(sellProducts.this, MainActivity.class);
                startActivity(send);
            }


        });
        //----------------------------------------
    }

    String identitysave = "sell";
    Double currentamount;
    Double newamount;
    Double total;
    public void saveSellInfo(View view){
        currentamount = Double.parseDouble(getamount.getText().toString());
        newamount = Double.parseDouble(amount.getText().toString());
        total = currentamount+newamount;
        getamount.setText(total.toString());
        buySell = new BuySell(
                identitysave,
                newamount,
                productname.getText().toString(),
                date.getText().toString(),
                month.getText().toString()

        );

        buySellhelper.saveBuyAndSell(buySell);
        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
        updateCurrentCashOutAmounts(view);

    }
    String identitysave2 = "input";
    public void  updateCurrentCashOutAmounts(View view){

        cashInOutExpense = new CashInOutExpense();
        cashInOutExpense.setIdentity(identitysave2);
        cashInOutExpense.setAmount(total);
        cashInOutExpHelper.updateCurrentCapital(cashInOutExpense);

    }
}
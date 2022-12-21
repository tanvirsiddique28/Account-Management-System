package com.example.accountmanager.cashInOutExp;

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

import java.util.ArrayList;
import java.util.List;

public class cashInput extends AppCompatActivity {
    // Database
    CashInOutExpDatabase cashInOutExpHelper;
    CashInOutExpense cashInOutExpense;
    List<CashInOutExpense> list;
    ListView listView;
    ArrayList<String> arrayList;
    //-------------------------------------------
    String cashinh;
    String currentdate;
    String currentmonth;
    EditText getamount,amount,decription,date,month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_cash_input);
        getamount = findViewById(R.id.editTextTextPersonName24);
        amount = findViewById(R.id.editTextTextPersonName12);
        decription = findViewById(R.id.editTextTextPersonName13);
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

        cashInOutExpHelper = new CashInOutExpDatabase(getApplicationContext());
        // Backspace Click Event
        ImageView imagemenu = findViewById(R.id.imageMenu);
        imagemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent send = new Intent(cashInput.this, MainActivity.class);
                startActivity(send);
            }


        });
        //----------------------------------------

    }
    String identitysave = "input";
    Double currentamount;
    Double newamount;
    Double total;
    public void saveCashIn(View view){
        currentamount = Double.parseDouble(getamount.getText().toString());
        newamount = Double.parseDouble(amount.getText().toString());
        total = currentamount+newamount;
        getamount.setText(total.toString());
        cashInOutExpense = new CashInOutExpense(
                identitysave,
                total,
                decription.getText().toString(),
                date.getText().toString(),
                month.getText().toString()

        );

        cashInOutExpHelper.saveCashInOutExpense(cashInOutExpense);
        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();


    }
}
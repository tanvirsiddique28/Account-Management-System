package com.example.accountmanager.customersupplier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.accountmanager.R;

import java.util.ArrayList;
import java.util.List;

public class addAmount extends AppCompatActivity {
    // Database
    CustomerAndSupplierDatabase customerandsupplierhelper;
    CustomerAndSupplier customerandsupplier;
    List<CustomerAndSupplier> list;
    ListView listView;
    ArrayList<String> arrayList;

    //-------------------------
    EditText number,currentamount,newamount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_amount);

        customerandsupplierhelper = new CustomerAndSupplierDatabase(getApplicationContext());

        number = findViewById(R.id.editTextTextPersonName);
        currentamount = findViewById(R.id.editTextTextPersonName2);
        newamount = findViewById(R.id.editTextTextPersonName3);

        // Backspace Click Event
        ImageView imagemenu = findViewById(R.id.imageMenu);
        imagemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent send = new Intent(addAmount.this, allCustomerAndSupplier.class);
                startActivity(send);
            }


        });
        //----------------------------------------
    }


    public void  getCurrentAmounts(View view){
        customerandsupplier = new CustomerAndSupplier();
        customerandsupplier.setNumber(Integer.valueOf(number.getText().toString()));

        list  = customerandsupplierhelper.getCurrentAmount(customerandsupplier);


        for(CustomerAndSupplier c:list){
            currentamount.setText(String.valueOf(c.getAmount()));
        }

    }
    public void  updateCurrentAmounts(View view){
        Double na = Double.valueOf(newamount.getText().toString());
        Double ca = Double.valueOf(currentamount.getText().toString());
        Double total = na+ca;
        currentamount.setText(String.valueOf(total));

        customerandsupplier = new CustomerAndSupplier();
        customerandsupplier.setNumber(Integer.valueOf(number.getText().toString()));
        customerandsupplier.setAmount(total);
        customerandsupplierhelper.updateCurrentAmount(customerandsupplier);

    }
}
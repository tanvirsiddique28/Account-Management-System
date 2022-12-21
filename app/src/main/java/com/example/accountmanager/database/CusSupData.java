package com.example.accountmanager.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.accountmanager.R;
import com.example.accountmanager.customersupplier.CustomerAndSupplier;
import com.example.accountmanager.customersupplier.CustomerAndSupplierAdapter;
import com.example.accountmanager.customersupplier.CustomerAndSupplierDatabase;

import java.util.ArrayList;
import java.util.List;

public class CusSupData extends AppCompatActivity {

    // Database
    CustomerAndSupplierDatabase customerandsupplierhelper;

    CustomerAndSupplier customerandsupplier;
    List<CustomerAndSupplier> list;
    ListView listView;
    ArrayList<String> arrayList;

    //----------------
    EditText identity,name,number,amount,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_sup_data);

        customerandsupplierhelper = new CustomerAndSupplierDatabase(getApplicationContext());
        listView = findViewById(R.id.cussuplist);

        //--------------------------------
        identity = findViewById(R.id.editTextTextPersonName7);
        name = findViewById(R.id.editTextTextPersonName8);
        number = findViewById(R.id.editTextTextPersonName9);
        amount = findViewById(R.id.editTextTextPersonName10);
        date = findViewById(R.id.editTextTextPersonName11);

        // Backspace Click Event
        ImageView imagemenu = findViewById(R.id.imageMenu);
        imagemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent send = new Intent(CusSupData.this, myDatabase.class);
                startActivity(send);
            }


        });
        //----------------------------------------
    }

    public void showAllCusAndSup(View view){
        list = customerandsupplierhelper.showAllCustomerAndSupplier();
        CustomerAndSupplierAdapter adapter = new CustomerAndSupplierAdapter(getApplicationContext(), list);
        listView.setAdapter(adapter);

    }
    public void  getCusSup(View view){
        customerandsupplier = new CustomerAndSupplier();
        customerandsupplier.setNumber(Integer.valueOf(number.getText().toString()));

        list  = customerandsupplierhelper.getCurrentAmount(customerandsupplier);


        for(CustomerAndSupplier c:list){
          identity.setText(c.getIdentity());
          name.setText(c.getName());
          amount.setText(String.valueOf(c.getAmount()));
          date.setText(c.getDate());

        }
        showAllCusAndSup(view);
    }



    public void  updateCusSup(View view){


        customerandsupplier = new CustomerAndSupplier();
        customerandsupplier.setIdentity(identity.getText().toString());
        customerandsupplier.setName(name.getText().toString());
        customerandsupplier.setNumber(Integer.valueOf(number.getText().toString()));
        customerandsupplier.setAmount(Double.valueOf(amount.getText().toString()));
        customerandsupplier.setDate(date.getText().toString());
        customerandsupplierhelper.updateAllCustomerSupplier(customerandsupplier);


        getCusSup(view);
        showAllCusAndSup(view);
    }

    public void  delete (View view){

        customerandsupplier = new CustomerAndSupplier();
        customerandsupplier.setNumber(Integer.valueOf(number.getText().toString()));
        customerandsupplierhelper.delete(customerandsupplier);

        showAllCusAndSup(view);
    }


}
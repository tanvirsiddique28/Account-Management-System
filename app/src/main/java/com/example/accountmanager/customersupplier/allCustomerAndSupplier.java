package com.example.accountmanager.customersupplier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.accountmanager.MainActivity;
import com.example.accountmanager.R;

import java.util.ArrayList;
import java.util.List;

public class allCustomerAndSupplier extends AppCompatActivity {
    // Database
    CustomerAndSupplierDatabase customerandsupplierhelper;

    CustomerAndSupplier customerandsupplier;
    List<CustomerAndSupplier> list;
    ListView listView;
    ArrayList<String> arrayList;

    //--------
    TextView receivable,payable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_customer_and_supplier);

        customerandsupplierhelper = new CustomerAndSupplierDatabase(getApplicationContext());
        listView = findViewById(R.id.all);

        receivable = findViewById(R.id.textView);
        payable = findViewById(R.id.textView2);


        // Backspace Click Event
        ImageView imagemenu = findViewById(R.id.imageMenu);
        imagemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent send = new Intent(allCustomerAndSupplier.this, MainActivity.class);
                startActivity(send);
            }


        });
        //----------------------------------------


    }
    public void showAllCustomerAndSupplier(View view){
       list = customerandsupplierhelper.showAllCustomerAndSupplier();
       CustomerAndSupplierAdapter adapter = new CustomerAndSupplierAdapter(getApplicationContext(), list);
        listView.setAdapter(adapter);
        getAllCustomerAmounts(view);
        getAllSupplierAmounts(view);

    }

    String idntity = "Customer";
    public void  getAllCustomerAmounts(View view){
        customerandsupplier = new CustomerAndSupplier();
        customerandsupplier.setIdentity(idntity);

        list  = customerandsupplierhelper.getAllCustomerAmounts(customerandsupplier);

        double sum = 0 ;
        for(CustomerAndSupplier c:list){
            sum += c.getAmount();

        }
        receivable.setText("Total Receivable: "+"\n        "+String.valueOf(sum));
    }

    String idntity2 = "Supplier";
    public void  getAllSupplierAmounts(View view){
        customerandsupplier = new CustomerAndSupplier();
        customerandsupplier.setIdentity(idntity2);

        list  = customerandsupplierhelper.getAllSupplierAmounts(customerandsupplier);

        double sum2 = 0 ;
        for(CustomerAndSupplier c:list){
            sum2 += c.getAmount();

        }
        payable.setText("Total Payable: "+"\n        "+String.valueOf(sum2));
    }

    public  void addAmount(View view){
        Intent send = new Intent(allCustomerAndSupplier.this, addAmount.class);
        startActivity(send);

    }
}
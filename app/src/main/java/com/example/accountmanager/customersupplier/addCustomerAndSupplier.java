package com.example.accountmanager.customersupplier;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.accountmanager.MainActivity;
import com.example.accountmanager.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class addCustomerAndSupplier extends AppCompatActivity {
    // Database
    CustomerAndSupplierDatabase customerandsupplierhelper;
    CustomerAndSupplier customerandsupplier;
    List<CustomerAndSupplier> list;
    ListView listView;
    ArrayList <String> arrayList;

    //------supplier/customer-----
    RadioButton radioButton1,radioButton2;
    RadioGroup radioGroup;
    TextView t1;
    //------name,number,amount----
    EditText editText1,editText2,editText3;

    //------calendar----------
    TextView textView;
    ImageView imageView;
    int year;
    int month;
    int date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer_and_supplier);

        //------supplier/customer-----
        radioButton1 = findViewById(R.id.radioCustomer);
        radioButton2 = findViewById(R.id.radioSupplier);
        radioGroup = findViewById(R.id.radioGroup);


        //------name,number,amount----
        editText1 = findViewById(R.id.editTextTextPersonName14);
        editText2 = findViewById(R.id.editTextTextPersonName5);
        editText3 = findViewById(R.id.editTextTextPersonName6);

        // Select Date Click Event
        textView = findViewById(R.id.textView4);
        imageView = findViewById(R.id.imageView4);

        // Database

        customerandsupplierhelper = new CustomerAndSupplierDatabase(getApplicationContext());
        listView = findViewById(R.id.cslistview);

        final Calendar calendar = Calendar.getInstance();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              year = calendar.get(Calendar.YEAR);
              month = calendar.get(Calendar.MONTH);
              date = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(addCustomerAndSupplier.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        textView.setText(i+"/"+i1+"/"+i2);

                    }
                },year,month,date);
                datePickerDialog.show();
            }
        });
        //-------------------------------

        // Backspace Click Event
       ImageView imagemenu = findViewById(R.id.imageMenu);
        imagemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent send = new Intent(addCustomerAndSupplier.this, MainActivity.class);
                startActivity(send);
            }


        });
        //----------------------------------------

    }

    String  customerOrSupplier;
    public  void saveCustomerSupplier(View view) {

        int x = radioGroup.getCheckedRadioButtonId();

        if (x == R.id.radioCustomer) {
            customerOrSupplier = radioButton1.getText().toString();
        } else if (x == R.id.radioSupplier) {
            customerOrSupplier = radioButton2.getText().toString();
        }

        customerandsupplier = new CustomerAndSupplier(
                customerOrSupplier,
                editText1.getText().toString(),
                Integer.parseInt(editText2.getText().toString()),
                Double.parseDouble(editText3.getText().toString()),
                textView.getText().toString()

        );

        customerandsupplierhelper.saveCustomerAndSupplier(customerandsupplier);
        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
        //showAllCustomerAndSupplier(view);

    }
//    public void showAllCustomerAndSupplier(View view){
//        list = customerandsupplierhelper.showAllCustomerAndSupplier();
//        CustomerAndSupplierAdapter adapter = new CustomerAndSupplierAdapter(getApplicationContext(), list);
//        listView.setAdapter(adapter);
//
//    }




}
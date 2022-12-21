package com.example.accountmanager.cashInOutExp;

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
import com.example.accountmanager.customersupplier.CustomerAndSupplier;
import com.example.accountmanager.customersupplier.CustomerAndSupplierDatabase;
import com.example.accountmanager.customersupplier.PayGetDatabase;

import java.util.ArrayList;
import java.util.List;

public class paymentCustomerAndSupplier extends AppCompatActivity {

    // Database
    CustomerAndSupplierDatabase customerandsupplierhelper;
    CustomerAndSupplier customerandsupplier;
    List<CustomerAndSupplier> list;

    PayGetDatabase payGethelper;
    PayGet payGet;
    List<PayGet> list2;

    CashInOutExpDatabase cashInOutExpHelper;
    CashInOutExpense cashInOutExpense;
    List<CashInOutExpense> list3;

    ListView listView;
    ArrayList<String> arrayList;
    //-------------------------
    EditText getamount,number,currentamount,newamount,date;
    //-------------------------------------------
    String currentdate;
    String cashinh;
    String receivable;
    String payable;

    TextView receivablee,payablee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_customer_and_supplier2);

        payGethelper = new PayGetDatabase(getApplicationContext());
        cashInOutExpHelper = new CashInOutExpDatabase(getApplicationContext());
        customerandsupplierhelper = new CustomerAndSupplierDatabase(getApplicationContext());

        getamount = findViewById(R.id.editTextTextPersonName24);
        number = findViewById(R.id.editTextTextPersonName);
        currentamount = findViewById(R.id.editTextTextPersonName2);
        newamount = findViewById(R.id.editTextTextPersonName3);
        date = findViewById(R.id.editTextTextPersonName14);
        receivablee = findViewById(R.id.textView);
        payablee = findViewById(R.id.textView2);

        Intent i = getIntent();
        cashinh = i.getStringExtra("cashinhand");
        currentdate = i.getStringExtra("currentdate");
        receivable = i.getStringExtra("receivable");
        payable = i.getStringExtra("payable");

        getamount.setText(cashinh);
        date.setText(currentdate);
        receivablee.setText("Total Receivable: "+"\n        "+receivable);
        payablee.setText("Total Payable: "+"\n        "+payable);


        // Backspace Click Event
        ImageView imagemenu = findViewById(R.id.imageMenu);
        imagemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent send = new Intent(paymentCustomerAndSupplier.this, MainActivity.class);
                startActivity(send);
            }


        });
        //----------------------------------------
    }

    public void  getPayRecAmounts(View view){
        customerandsupplier = new CustomerAndSupplier();
        customerandsupplier.setNumber(Integer.valueOf(number.getText().toString()));

        list  = customerandsupplierhelper.getCurrentAmount(customerandsupplier);


        for(CustomerAndSupplier c:list){
            currentamount.setText(String.valueOf(c.getAmount()));
        }

    }
    Double cashinhand;
    Double newcashinhand;

    Double recable;
    Double newrecable;

    Double payble;
    Double npayble;
    public void  updateReceivedAmounts(View view){
        Double na = Double.valueOf(newamount.getText().toString());
        Double ca = Double.valueOf(currentamount.getText().toString());
        Double total = ca-na;
        currentamount.setText(String.valueOf(total));
        //-----------------------------------------------------------------
        cashinhand = Double.valueOf(getamount.getText().toString());
        newcashinhand = cashinhand+na;
        getamount.setText(String.valueOf(newcashinhand));
        //-----------------------------------------------------------------
        recable = Double.valueOf(receivable);
        newrecable = recable-na;
        receivablee.setText("Total Receivable: "+"\n        "+newrecable);

        customerandsupplier = new CustomerAndSupplier();
        customerandsupplier.setNumber(Integer.valueOf(number.getText().toString()));
        customerandsupplier.setAmount(total);
        customerandsupplierhelper.updateCurrentAmount(customerandsupplier);
        saveGet(view);

        updateCurrentCashAmounts(view);

    }

    public void  updatePaymentAmounts(View view){
        Double na = Double.valueOf(newamount.getText().toString());
        Double ca = Double.valueOf(currentamount.getText().toString());
        Double total = ca-na;
        currentamount.setText(String.valueOf(total));
        //-----------------------------------------------------------------
        cashinhand = Double.valueOf(getamount.getText().toString());
        newcashinhand = cashinhand-na;
        getamount.setText(String.valueOf(newcashinhand));
        //-----------------------------------------------------------------
        payble = Double.valueOf(payable);
        npayble = payble-na;
        payablee.setText("Total Payable: "+"\n        "+ npayble);

        customerandsupplier = new CustomerAndSupplier();
        customerandsupplier.setNumber(Integer.valueOf(number.getText().toString()));
        customerandsupplier.setAmount(total);
        customerandsupplierhelper.updateCurrentAmount(customerandsupplier);
        savePay(view);

        updateCurrentCashAmounts(view);
    }

    String identitysave = "pay";

    public void savePay(View view){


        payGet = new PayGet(
                identitysave,
                Double.valueOf(newamount.getText().toString()),
                date.getText().toString()

        );

        payGethelper.savePayAndGet(payGet);
        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();



    }

    String identitysave2 = "get";

    public void saveGet(View view){


        payGet = new PayGet(
                identitysave2,
                Double.valueOf(newamount.getText().toString()),
                date.getText().toString()

        );

        payGethelper.savePayAndGet(payGet);
        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();



    }

    String identitysave3 = "input";
    public void  updateCurrentCashAmounts(View view){

        cashInOutExpense = new CashInOutExpense();
        cashInOutExpense.setIdentity(identitysave3);
        cashInOutExpense.setAmount(Double.parseDouble(getamount.getText().toString()));
        cashInOutExpHelper.updateCurrentCapital(cashInOutExpense);

    }

//    public void calculationPay(View view){
//        cashinhand = Double.parseDouble(getamount.getText().toString());
//        namount = Double.parseDouble(newamount.getText().toString());
//        newcashinhand = cashinhand - namount;
//        getamount.setText(String.valueOf(newcashinhand));
//
//
//        payble = Double.parseDouble(payablee.getText().toString());
//        newpayble = Double.parseDouble(newamount.getText().toString());
//        npayble = payble - newpayble;
//        payablee.setText(String.valueOf(npayble));
//    }
//    public void calculationGet(View view){
//        cashinhand = Double.parseDouble(getamount.getText().toString());
//        namount = Double.parseDouble(newamount.getText().toString());
//        newcashinhand = cashinhand + namount;
//        getamount.setText(String.valueOf(newcashinhand));
//
//
//        recable = Double.parseDouble(payablee.getText().toString());
//        newrecable = Double.parseDouble(newamount.getText().toString());
//        nrecable = recable - newrecable;
//        receivablee.setText(String.valueOf(nrecable));
//    }

}
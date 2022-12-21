package com.example.accountmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.accountmanager.buysell.buyProducts;
import com.example.accountmanager.buysell.sellProducts;
import com.example.accountmanager.cashInOutExp.CashInOutExpDatabase;
import com.example.accountmanager.cashInOutExp.CashInOutExpense;
import com.example.accountmanager.cashInOutExp.cashInput;
import com.example.accountmanager.cashInOutExp.cashOutput;
import com.example.accountmanager.cashInOutExp.expenses;
import com.example.accountmanager.customersupplier.CustomerAndSupplier;
import com.example.accountmanager.customersupplier.CustomerAndSupplierDatabase;
import com.example.accountmanager.customersupplier.addCustomerAndSupplier;
import com.example.accountmanager.customersupplier.allCustomerAndSupplier;
import com.example.accountmanager.cashInOutExp.paymentCustomerAndSupplier;
import com.example.accountmanager.database.myDatabase;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //---- Database-----------------
    CashInOutExpDatabase cashInOutExpHelper;
    CashInOutExpense cashInOutExpense;
    List<CashInOutExpense> list;

    CustomerAndSupplierDatabase customerandsupplierhelper;
    CustomerAndSupplier customerandsupplier;
    List<CustomerAndSupplier> list2;
    //-----------------------------
    ListView listView;
    ArrayList<String> arrayList;
    //------------------------------------
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    ImageView imageMenu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_View);
        imageMenu = findViewById(R.id.imageMenu);

        // Database
        customerandsupplierhelper = new CustomerAndSupplierDatabase(getApplicationContext());
        cashInOutExpHelper = new CashInOutExpDatabase(getApplicationContext());

        toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//        // Navigation Bar Click Event
//
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {


                    case R.id.amCashReport:
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy");
                        currentdate = simpleDate.format(calendar.getTime());

                        Intent send1 = new Intent(MainActivity.this, CashReport.class);
                        send1.putExtra("currentdate",currentdate);
                        startActivity(send1);

                        drawerLayout.closeDrawers();
                        break;

                    case R.id.amOwneresReport:
                        Toast.makeText(MainActivity.this, "Clicked5", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.amData:
                        Intent send = new Intent(MainActivity.this, myDatabase.class);
                        startActivity(send);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.amAlertMessage:
                        Toast.makeText(MainActivity.this, "Clicked7", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;


                }

                return false;
            }
        });


        // App Bar Click Event
        imageMenu = findViewById(R.id.imageMenu);

        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code Here
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Backspace Click Event
        ImageView imagemenu = findViewById(R.id.imageNewMenu);
        imagemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent send = new Intent(MainActivity.this, LogIn.class);
                startActivity(send);
            }


        });
        //----------------------------------------

    }

    public  void addCustomerAndSupplier(View view){
        Intent send = new Intent(MainActivity.this, addCustomerAndSupplier.class);
        startActivity(send);

    }

    public  void allCustomerAndSupplier(View view){
        Intent send = new Intent(MainActivity.this, allCustomerAndSupplier.class);
        startActivity(send);

    }
    //------------------PaybleReceivable-------------------------------------
    String idntity = "Customer";
    String receivable;
    public void  getAllCustomerAmounts(View view){
        customerandsupplier = new CustomerAndSupplier();
        customerandsupplier.setIdentity(idntity);

        list2  = customerandsupplierhelper.getAllCustomerAmounts(customerandsupplier);

        double sum = 0 ;
        for(CustomerAndSupplier c:list2){
            sum += c.getAmount();

        }
        receivable = String.valueOf(sum);
    }

    String idntity2 = "Supplier";
    String payable;
    public void  getAllSupplierAmounts(View view){
        customerandsupplier = new CustomerAndSupplier();
        customerandsupplier.setIdentity(idntity2);

        list2  = customerandsupplierhelper.getAllSupplierAmounts(customerandsupplier);

        double sum2 = 0 ;
        for(CustomerAndSupplier c:list2){
            sum2 += c.getAmount();

        }
        payable = String.valueOf(sum2);
    }



    //-------------------DateMonthIntent--------------------------------------

    String currentdate;
    String currentmonth;
    public void getDateMonth(View view){

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy");
       currentdate = simpleDate.format(calendar.getTime());

        SimpleDateFormat simpleMonth = new SimpleDateFormat("MMMM");
       currentmonth = simpleMonth.format(calendar.getTime());

    }

    //--------------------------------------------------------------------------

    //-------------------------------Capital-----------------------------------
    String identity = "input";
    String cashinh;
    public void getCashInHand(View view){

        try{
            cashInOutExpense = new CashInOutExpense();
            cashInOutExpense.setIdentity(identity);

            list = cashInOutExpHelper.showAllcashInOutExp(cashInOutExpense);
            CashInOutExpense ce = list.get(list.size()-1);
            cashinh = String.valueOf(ce.getAmount());
        }catch (Exception e){
            cashinh = String.valueOf(0);
        }




    }

    //---------------------------buysell---------------------------------------
    public  void buyProducts(View view){
        getDateMonth(view);
        getCashInHand(view);
        Intent send = new Intent(MainActivity.this, buyProducts.class);
        send.putExtra("currentdate",currentdate);
        send.putExtra("currentmonth",currentmonth);
        send.putExtra("cashinhand",cashinh);
        startActivity(send);

    }
    public  void sellProducts(View view){
        getDateMonth(view);
        getCashInHand(view);
        Intent send = new Intent(MainActivity.this, sellProducts.class);
        send.putExtra("currentdate",currentdate);
        send.putExtra("currentmonth",currentmonth);
        send.putExtra("cashinhand",cashinh);
        startActivity(send);

    }

    //---------------------------cashInputOutput---------------------------------------
    public  void cashInput(View view){
        getDateMonth(view);
        getCashInHand(view);
        Intent send = new Intent(MainActivity.this, cashInput.class);
        send.putExtra("currentdate",currentdate);
        send.putExtra("currentmonth",currentmonth);
        send.putExtra("cashinhand",cashinh);
        startActivity(send);

    }
    public  void cashOutput(View view){
        getDateMonth(view);
        getCashInHand(view);
        Intent send = new Intent(MainActivity.this, cashOutput.class);
        send.putExtra("currentdate",currentdate);
        send.putExtra("currentmonth",currentmonth);
        send.putExtra("cashinhand",cashinh);
        startActivity(send);

    }

    //---------------------------Expenses---------------------------------------
    public  void expenses(View view){
        getDateMonth(view);
        getCashInHand(view);
        Intent send = new Intent(MainActivity.this, expenses.class);
        send.putExtra("currentdate",currentdate);
        send.putExtra("currentmonth",currentmonth);
        send.putExtra("cashinhand",cashinh);
        startActivity(send);

    }

//--------------------------------------------------------------------------

    public  void allPayment(View view){
        getDateMonth(view);
        getCashInHand(view);
        getAllCustomerAmounts(view);
        getAllSupplierAmounts(view);
        Intent send = new Intent(MainActivity.this, paymentCustomerAndSupplier.class);
        send.putExtra("currentdate",currentdate);
        send.putExtra("cashinhand",cashinh);
        send.putExtra("receivable",receivable);
        send.putExtra("payable",payable);
        startActivity(send);

    }





    }









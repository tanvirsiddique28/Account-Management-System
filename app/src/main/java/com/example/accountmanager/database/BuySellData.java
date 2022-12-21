package com.example.accountmanager.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.accountmanager.R;
import com.example.accountmanager.buysell.BuySell;
import com.example.accountmanager.buysell.BuySellAdapter;
import com.example.accountmanager.buysell.BuySellDatabase;

import java.util.ArrayList;
import java.util.List;

public class BuySellData extends AppCompatActivity {

    // Database
    BuySellDatabase buySellhelper;
    BuySell buySell;
    List<BuySell> list;
    ListView listView;
    ArrayList<String> arrayList;
    //-------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_sell_data);

        buySellhelper = new BuySellDatabase(getApplicationContext());
        listView = findViewById(R.id.buyselllist);


        // Backspace Click Event
        ImageView imagemenu = findViewById(R.id.imageMenu);
        imagemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent send = new Intent(BuySellData.this, myDatabase.class);
                startActivity(send);
            }


        });
        //----------------------------------------
    }

    String identity = "buy";
    public void showAllBuyData(View view){
        buySell = new BuySell();
        buySell.setIdentity(identity);

        list = buySellhelper.showAllBuySellInfo(buySell);

        BuySellAdapter adapter = new BuySellAdapter(getApplicationContext(), list);
        listView.setAdapter(adapter);

    }
    String identity2 = "sell";
    public void showAllSellData(View view){
        buySell = new BuySell();
        buySell.setIdentity(identity2);

        list = buySellhelper.showAllBuySellInfo(buySell);

        BuySellAdapter adapter = new BuySellAdapter(getApplicationContext(), list);
        listView.setAdapter(adapter);

    }


}
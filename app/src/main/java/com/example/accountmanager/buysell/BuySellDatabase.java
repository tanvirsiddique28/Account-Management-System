package com.example.accountmanager.buysell;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.accountmanager.customersupplier.CustomerAndSupplier;

import java.util.ArrayList;
import java.util.List;

public class BuySellDatabase extends SQLiteOpenHelper {
    private String tname="buysellinfo";
    SQLiteDatabase database;
    List<BuySell> list;
    BuySell buySell;

    public BuySellDatabase(@Nullable Context context) {
        super(context, "buyandsell", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ tname + "(id INTEGER PRIMARY KEY AUTOINCREMENT,identity TEXT, amount DOUBLE, productname TEXT, date TEXT,month TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tname);
        onCreate(sqLiteDatabase);
    }

    public void saveBuyAndSell(BuySell buySell){
        database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("identity" ,buySell.getIdentity());
        cv.put("amount" ,buySell.getAmount());
        cv.put("productname" ,buySell.getProductname());
        cv.put("date" ,buySell.getDate());
        cv.put("month" ,buySell.getMonth());
        database.insert(tname, null , cv);
        database.close();

    }

    public List<BuySell> showAllBuySellInfo(BuySell buySell){

        list=new ArrayList<>();
        database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from '"+tname+"' WHERE identity = ?",new String[]{buySell.getIdentity()},null);
        while (cursor.moveToNext()){
            buySell = new BuySell(cursor.getString(1),cursor.getDouble(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
            list.add(buySell);
        }
        return list;
    }

    public List<BuySell> getDate(BuySell buySell) {
        list = new ArrayList<>();
        database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from '"+tname+"' WHERE date = ? AND identity = ?",new String[]{buySell.getDate(),buySell.getIdentity()},null);

        while (cursor.moveToNext()) {
            buySell = new BuySell(cursor.getString(1),cursor.getDouble(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
            list.add(buySell);


        }
        return list;
    }


}

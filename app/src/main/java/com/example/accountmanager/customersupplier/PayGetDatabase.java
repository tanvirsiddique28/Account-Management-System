package com.example.accountmanager.customersupplier;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.accountmanager.cashInOutExp.CashInOutExpense;
import com.example.accountmanager.cashInOutExp.PayGet;

import java.util.ArrayList;
import java.util.List;

public class PayGetDatabase extends SQLiteOpenHelper {
    private String tname= "newpayget";
    SQLiteDatabase database;
    List<PayGet> list;
    PayGet payGet;

    public PayGetDatabase(@Nullable Context context) {
        super(context, "mypayget", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ tname + "(id INTEGER PRIMARY KEY AUTOINCREMENT, identity TEXT,amount DOUBLE,date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tname);
        onCreate(sqLiteDatabase);
    }

    public void savePayAndGet(PayGet payGet){
        database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("identity" ,payGet.getIdentity());
        cv.put("amount" ,payGet.getAmount());
        cv.put("date" ,payGet.getDate());
        database.insert(tname, null , cv);
        database.close();
    }
    public List<PayGet> getDatep(PayGet payGet) {
        list = new ArrayList<>();
        database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from '"+tname+"' WHERE date = ? AND identity = ?",new String[]{payGet.getDate(),payGet.getIdentity()},null);

        while (cursor.moveToNext()) {
            payGet = new PayGet(cursor.getString(1),cursor.getDouble(2),cursor.getString(3));
            list.add(payGet);


        }
        return list;
    }
}

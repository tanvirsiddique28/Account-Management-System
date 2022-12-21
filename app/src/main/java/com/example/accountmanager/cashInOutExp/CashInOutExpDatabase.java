package com.example.accountmanager.cashInOutExp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.accountmanager.buysell.BuySell;
import com.example.accountmanager.customersupplier.CustomerAndSupplier;

import java.util.ArrayList;
import java.util.List;

public class CashInOutExpDatabase extends SQLiteOpenHelper {
    private String tname="cashexp";
    SQLiteDatabase database;
    List<CashInOutExpense> list;
    CashInOutExpense cashinOutExpense;

    public CashInOutExpDatabase(@Nullable Context context) {
        super(context, "cashexpinfo", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ tname + "(id INTEGER PRIMARY KEY AUTOINCREMENT,identity TEXT, amount DOUBLE, description TEXT, date TEXT,month TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tname);
        onCreate(sqLiteDatabase);
    }
    public void saveCashInOutExpense(CashInOutExpense cashinOutExpense){
        database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("identity" ,cashinOutExpense.getIdentity());
        cv.put("amount" ,cashinOutExpense.getAmount());
        cv.put("description" ,cashinOutExpense.getDescription());
        cv.put("date" ,cashinOutExpense.getDate());
        cv.put("month" ,cashinOutExpense.getMonth());
        database.insert(tname, null , cv);
        database.close();

    }
    public List<CashInOutExpense> showAllcashInOutExp(CashInOutExpense cashinOutExpense){

        list = new ArrayList<>();
        database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from '"+tname+"' WHERE identity = ?",new String[]{cashinOutExpense.getIdentity()},null);
        while (cursor.moveToNext()){
            cashinOutExpense = new CashInOutExpense(cursor.getString(1),cursor.getDouble(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
            list.add(cashinOutExpense);
        }
        return list;
    }

    public void updateCurrentCapital(CashInOutExpense cashinOutExpense){
        database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("amount" ,cashinOutExpense.getAmount());
        database.update(tname,cv,"identity = ?",new String[]{cashinOutExpense.getIdentity()});
        database.close();

    }
    public List<CashInOutExpense> getDatec(CashInOutExpense cashinOutExpense) {
        list = new ArrayList<>();
        database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from '"+tname+"' WHERE date = ? AND identity = ?",new String[]{cashinOutExpense.getDate(),cashinOutExpense.getIdentity()},null);

        while (cursor.moveToNext()) {
            cashinOutExpense = new CashInOutExpense(cursor.getString(1),cursor.getDouble(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
            list.add(cashinOutExpense);


        }
        return list;
    }
}

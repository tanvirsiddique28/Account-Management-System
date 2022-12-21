package com.example.accountmanager.customersupplier;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CustomerAndSupplierDatabase extends SQLiteOpenHelper {
    private String tname="customersupplier";
    SQLiteDatabase database;
    List<CustomerAndSupplier> list;
    CustomerAndSupplier c;

    public CustomerAndSupplierDatabase(@Nullable Context context) {
        super(context, "expensemanager", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ tname + "(id INTEGER PRIMARY KEY AUTOINCREMENT, identity TEXT, name TEXT, number INTEGER,amount DOUBLE,date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tname);
        onCreate(sqLiteDatabase);
    }

    public void saveCustomerAndSupplier(CustomerAndSupplier c){
        database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("identity" ,c.getIdentity());
        cv.put("name" ,c.getName());
        cv.put("number" ,c.getNumber());
        cv.put("amount" ,c.getAmount());
        cv.put("date" ,c.getDate());
        database.insert(tname, null , cv);
        database.close();

    }

    public List<CustomerAndSupplier> showAllCustomerAndSupplier(){

        list=new ArrayList<>();
        database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from " + tname, null);
        while (cursor.moveToNext()){
            c = new CustomerAndSupplier(cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getDouble(4),cursor.getString(5));
            list.add(c);
        }
        return list;
    }

    public List<CustomerAndSupplier> getAllCustomerAmounts(CustomerAndSupplier customerandsupplier) {
        list = new ArrayList<>();
        database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from '"+tname+"' WHERE identity = ?",new String[]{customerandsupplier.getIdentity()},null);

        while (cursor.moveToNext()) {
            c = new CustomerAndSupplier(cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getDouble(4), cursor.getString(5));
            list.add(c);


        }
        return list;
    }

    public List<CustomerAndSupplier> getAllSupplierAmounts(CustomerAndSupplier customerandsupplier) {
        list = new ArrayList<>();
        database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from '"+tname+"' WHERE identity = ?",new String[]{customerandsupplier.getIdentity()},null);

        while (cursor.moveToNext()) {
            c = new CustomerAndSupplier(cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getDouble(4), cursor.getString(5));
            list.add(c);


        }
        return list;
    }

    public List<CustomerAndSupplier> getCurrentAmount(CustomerAndSupplier customerandsupplier) {
        list = new ArrayList<>();
        database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from '"+tname+"' WHERE number = ?",new String[]{String.valueOf(customerandsupplier.getNumber())},null);

        while (cursor.moveToNext()) {
            c = new CustomerAndSupplier(cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getDouble(4), cursor.getString(5));
            list.add(c);


        }
        return list;
    }

//    public List<CustomerAndSupplier> getCurrentAmount(CustomerAndSupplier customerandsupplier) {
//
//        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy/MM/D");
//        list = new ArrayList<>();
//        database = this.getWritableDatabase();
//        Cursor cursor = database.rawQuery("Select * from '"+tname+"' WHERE date = ?",new String[]{simpleDate.format(customerandsupplier.getDate())},null);
//
//        while (cursor.moveToNext()) {
//            c = new CustomerAndSupplier(cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getDouble(4), cursor.getString(5));
//            list.add(c);
//
//
//        }
//        return list;
//    }

    public void updateCurrentAmount(CustomerAndSupplier customerandsupplier){
        database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("amount" ,customerandsupplier.getAmount());
        database.update(tname,cv,"number = ?",new String[]{String.valueOf(customerandsupplier.getNumber())});
        database.close();

    }

    public void updateAllCustomerSupplier(CustomerAndSupplier customerandsupplier){
        database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("identity" ,customerandsupplier.getIdentity());
        cv.put("name" ,customerandsupplier.getName());
        cv.put("amount" ,customerandsupplier.getAmount());
        cv.put("date" ,customerandsupplier.getDate());
        database.update(tname,cv,"number = ?",new String[]{String.valueOf(customerandsupplier.getNumber())});
        database.close();

    }



    public void delete(CustomerAndSupplier customerandsupplier ){
        database = this.getWritableDatabase();
        database.delete(tname,"number = ?",new String[]{String.valueOf(customerandsupplier.getNumber())});
        database.close();

    }

}

package com.xeylyne.managementaccount;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "managementAccount.db";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_NAME = "account_table";


    public static final String COL_1 = "ID";
    public static final String COL_2 = "USERNAME";
    public static final String COL_3 = "PASSWORD";
    public static final String COL_4 = "PHONENUMBER";

    public DataHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "USERNAME TEXT UNIQUE," +
                "PASSWORD TEXT," +
                "PHONENUMBER TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean Register(String name, String password, String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, password);
        contentValues.put(COL_4, phonenumber);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            Log.d("onRegis", "GagalInsert");
            return false;
        } else {
            Log.d("onRegis", "Insert Success");
            return true;
        }
    }

    public Cursor Login(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where USERNAME = '" + username + "' AND " + "PASSWORD = '" + password + "'", null);
        return res;
    }

    public Cursor LupaPassword(String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select USERNAME, PASSWORD from " + TABLE_NAME + " where PHONENUMBER = '" + phoneNumber + "'", null);
        return res;
    }

    public ArrayList<Account> getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Account> accounts = new ArrayList<>();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        if (res.getCount() == 0) {
            Log.d("onGetAll", "DB Empty");
        } else {
            if (res.moveToFirst()) {
                do {
                    Account account = new Account();
                    account.setUsername(res.getString(1));
                    account.setPassword(res.getString(2));
                    account.setPhonenumber(res.getString(3));
                    accounts.add(account);
                } while (res.moveToNext());
            }
        }
        return accounts;
    }

    public boolean updateData(int id, String username, String password, String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, password);
        contentValues.put(COL_4, phonenumber);
        db.update(TABLE_NAME, contentValues, "ID = " + id, null);
        return true;
    }

    public boolean deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "ID = " + id, null);
        return true;
    }
}

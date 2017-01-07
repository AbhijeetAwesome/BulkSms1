package com.sphere.sms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Abhijeet on 11-12-2016.
 */
public class dbOperation extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "product_info.db";
    private static final String CREATE_QUERY = "create table "+SmsProductContract.SmsProductEntry.TABLE_NAME
            +"("+SmsProductContract.SmsProductEntry.STARTNUM+" text,"+
            SmsProductContract.SmsProductEntry.TOTALNUM+" integer,"+
            SmsProductContract.SmsProductEntry.DATE+" text);";

    dbOperation(Context ctx){
        super(ctx, DB_NAME, null, DB_VERSION);
        Log.d("Database operation", "databaseCreated");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.d("Database operation", "tableCreated");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addInformation(SQLiteDatabase sq, String startNum, String date, int qty){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SmsProductContract.SmsProductEntry.STARTNUM,startNum);
        contentValues.put(SmsProductContract.SmsProductEntry.DATE,date);
        contentValues.put(SmsProductContract.SmsProductEntry.TOTALNUM,qty);
        sq.insert(SmsProductContract.SmsProductEntry.TABLE_NAME, null, contentValues);
        Log.d("Database","One Row inserted");
    }
    public Cursor getInfo(SQLiteDatabase sdb){
        String[] projections = {SmsProductContract.SmsProductEntry.STARTNUM, SmsProductContract.SmsProductEntry.DATE,
                SmsProductContract.SmsProductEntry.TOTALNUM};
        Cursor cursor = sdb.query(SmsProductContract.SmsProductEntry.TABLE_NAME, projections,
                null, null, null,null, null);
        return cursor;
    }
}


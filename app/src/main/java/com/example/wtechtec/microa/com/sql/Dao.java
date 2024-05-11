package com.example.wtechtec.microa.com.sql;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by WtecHtec on 2018/1/4.
 */

public class Dao extends SQLiteOpenHelper{


    public Dao(Context context) {
        super(context, "microadata", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table login (number text ,password text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package com.example.wtechtec.microa.com.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.wtechtec.microa.com.ifo.UserIfo;

/**
 * Created by WtecHtec on 2018/1/4.
 */

public class LoginSql {
    private  Dao dao;
    public LoginSql(Context context) {
    dao=new Dao(context);
    }
    public long add(UserIfo userIfo){
        SQLiteDatabase sqLiteDatabase=dao.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("number",userIfo.getUserid());
        values.put("password",userIfo.getPassword());
        long id=sqLiteDatabase.insert("login",null,values);
        sqLiteDatabase.close();
        return  id;
    }

    public  int delete(String userid){
        SQLiteDatabase db=dao.getWritableDatabase();
        int number=db.delete("login","number=?",new String[]{userid});
        db.close();
        return  number;
    }
    public  UserIfo find(){
        UserIfo userIfo=null;
        SQLiteDatabase db=dao.getReadableDatabase();
        Cursor cursor=db.query("login",null,null,null,null,null,null);

        while (cursor.moveToNext()){
            String number=cursor.getString(0);
            String password=cursor.getString(1);
            userIfo=new UserIfo(number,password);
        }
        db.close();
        cursor.close();
        return  userIfo ;
    }

}

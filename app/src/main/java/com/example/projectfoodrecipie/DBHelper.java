package com.example.projectfoodrecipie;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Credentials.db";

    public DBHelper(Context context) {
        super(context , "Credentials.db" , null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE USERS (USERNAME TEXT PRIMARY KEY , PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("DROP TABLE IF EXISTS USERS");

    }
    public Boolean insertData (String username , String  password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username" , username);
        contentValues.put("password" , password);
        long result = MyDB.insert("users" , null,contentValues);

        if (result == -1) return false;

        else return true;

    }

    public Boolean checkusername(String username){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM USERS WHERE USERNAME = ?" , new String[]{username});
        if (cursor.getCount() >0) return true;
        else return false;
    }

    public Boolean checkusernamepassword (String username , String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?" , new String[]{username,password});

        if (cursor.getCount() >0) return true;
        else return false;
    }
}
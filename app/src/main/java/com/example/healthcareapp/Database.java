package com.example.healthcareapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "create table users(Username text , Password Text , Email Text)";
        db.execSQL(query1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void register(String Username , String Password , String Email){
        ContentValues cv = new ContentValues();
        cv.put("Username",Username);
        cv.put("Password",Password);
        cv.put("Email",Email);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users" , null,cv);
        db.close();
    }
    public int login(String Username , String Passowrd){
        int Result =0;
        String str[] = new String[2];
        str[0] = Username;
        str[1] = Passowrd;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("Select * from users where Username = ? and Password = ?" , str);
        if(c.moveToFirst()){
            Result = 1;
        }
        return Result;
    }
}

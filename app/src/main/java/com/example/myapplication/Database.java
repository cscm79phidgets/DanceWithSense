package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Database {
    public SQLiteDatabase sqLiteDatabase;
    public MyDBOpenHelper myDBOpenHelper;
    public StringBuilder stringBuilder;
    public Context context;

    public void InsertData(String table,ContentValues contentValues){
        sqLiteDatabase.insert(table,null,contentValues);
    }
    public void CreateTable(String table){
        sqLiteDatabase.execSQL("CREATE TABLE "+table+"(id INTEGER PRIMARY KEY AUTOINCREMENT,a float,b float,c float,d float,e float,f float)");
    }
    public boolean CheckTable(String table){
        String instruct = "select count (*) from sqlite_master where type = 'table' and name = '"+table+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(instruct,null);
        if(cursor.moveToNext()){
            int count = cursor.getInt(0);
            if (count>0){
                return true;
            }
        }
        return false;
    }
    public float[] Find (String table,Integer id){
        float[] a = new float[6];
        sqLiteDatabase = myDBOpenHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+table+" WHERE id ="+id,
                null);
        if(cursor.moveToFirst()){
            a[0]=cursor.getFloat(cursor.getColumnIndex("a"));
            a[1]=cursor.getFloat(cursor.getColumnIndex("b"));
            a[2]=cursor.getFloat(cursor.getColumnIndex("c"));
            a[3]=cursor.getFloat(cursor.getColumnIndex("d"));
            a[4]=cursor.getFloat(cursor.getColumnIndex("e"));
            a[5]=cursor.getFloat(cursor.getColumnIndex("f"));
        }
        return a;
    }
}

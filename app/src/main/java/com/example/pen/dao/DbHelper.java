package com.example.pen.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "PenDb";
    private SQLiteDatabase db;
    private static String sql="";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db=this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        sql += "CREATE TABLE sample1(id INTEGER PRIMARY KEY AUTOINCREMENT, field1 TEXT, field2 TEXT)";
        //https://medium.com/@bhawanthagunawardana/android-sqlite-database-crud-s-with-example-application-4f5a841da8f6
        //agregar tablas
        //agregar tablas
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

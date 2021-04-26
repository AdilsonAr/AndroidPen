package com.example.pen.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.pen.model.Url;

@Database(entities = {Url.class}, version = 1, exportSchema = false)
public abstract class AppDb extends RoomDatabase {
    public abstract UrlDAO urlDAO();
}

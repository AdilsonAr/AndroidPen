package com.example.pen.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.pen.model.SchoolSubject;
import com.example.pen.model.Url;
import com.example.pen.utility.CustomTypeConverters;

@Database(entities = {Url.class, SchoolSubject.class}, version = 1, exportSchema = false)
@TypeConverters({CustomTypeConverters.class})
public abstract class AppDb extends RoomDatabase {
    public abstract UrlDAO urlDAO();
    public abstract SchoolSubjectDao schoolSubjectDao();
}

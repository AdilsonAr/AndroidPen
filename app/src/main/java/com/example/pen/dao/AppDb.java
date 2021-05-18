package com.example.pen.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.pen.model.ApunteKeyValue;
import com.example.pen.model.ApunteSimple;
import com.example.pen.model.KeyValue;
import com.example.pen.model.SchoolSubject;
import com.example.pen.model.Url;

@Database(entities = {
        Url.class, KeyValue.class, ApunteSimple.class, ApunteKeyValue.class,
            SchoolSubject.class}
        , version = 1, exportSchema = false)
public abstract class AppDb extends RoomDatabase {
    public abstract UrlDAO urlDAO();
    public abstract KeyValueDAO keyValueDAO();
    public abstract ApunteSimpleDAO apunteSimpleDAO();
    public abstract ApunteKeyValueDAO apunteKeyValueDAO();
    public abstract SchoolSubjectDao schoolSubjectDao();
}

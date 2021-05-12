package com.example.pen.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pen.model.ApunteSimple;
import com.example.pen.model.KeyValue;

import java.util.List;

@Dao
public interface ApunteSimpleDAO {
    @Query("select * from apunteSimple")
    public abstract List<ApunteSimple> findAll();
    @Insert
    public abstract void insert(ApunteSimple apunteSimple);
    @Update
    public abstract void update(ApunteSimple apunteSimple);
    @Delete
    public abstract void delete(ApunteSimple apunteSimple);
}

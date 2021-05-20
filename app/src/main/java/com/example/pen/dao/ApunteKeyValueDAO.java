package com.example.pen.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pen.model.ApunteKeyValue;
import com.example.pen.model.ApunteSimple;

import java.util.List;

@Dao
public interface ApunteKeyValueDAO {
    @Query("select * from apunteKeyValue")
    public abstract List<ApunteKeyValue> findAll();
    @Query("select * from apunteKeyValue where id=:miid")
    public abstract ApunteKeyValue findById(long miid);
    @Insert
    public abstract long insert(ApunteKeyValue apunteKeyValue);
    @Update
    public abstract void update(ApunteKeyValue apunteKeyValue);
    @Delete
    public abstract void delete(ApunteKeyValue apunteKeyValue);
}

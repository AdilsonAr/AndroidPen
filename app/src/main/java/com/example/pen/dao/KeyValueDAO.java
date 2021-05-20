package com.example.pen.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.pen.model.KeyValue;
import java.util.List;
@Dao
public interface KeyValueDAO {
    @Query("select * from keyValue")
    public abstract List<KeyValue> findAll();
    @Query("select * from keyValue where idApunte=:idApunte")
    public abstract List<KeyValue> findByIdApunte(long idApunte);
    @Query("select * from keyValue where id=:id")
    public abstract KeyValue findById(long id);
    @Insert
    public abstract void insert(KeyValue keyValue);
    @Update
    public abstract void update(KeyValue keyValue);
    @Delete
    public abstract void delete(KeyValue keyValue);
}

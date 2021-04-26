package com.example.pen.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;

import com.example.pen.model.Url;

import java.util.List;

@Dao
public interface UrlDAO {
    @Query("select * from url")
    public abstract List<Url> getAll();
    @Insert
    public abstract void insert(Url url);
    @Update
    public abstract void update(Url url );
    @Delete
    public abstract void delete(Url url );
    @Query("select * from url where url= :myUrl limit 1")
    public abstract Url findByUrl(String myUrl);
}

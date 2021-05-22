package com.example.pen.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pen.model.SchoolSubject;

import java.util.List;

/**
 * version: 1.0.6
 * last: 22/05/2021
 * descripcion: Es el dao para SchoolSubject
 */
@Dao
public interface SchoolSubjectDao {
    @Query("select * from school_subject")
    public List<SchoolSubject> getAll();
    @Insert
    public void insert(SchoolSubject ss);
    @Update
    public void update(SchoolSubject ss);
    @Delete
    public void delete(SchoolSubject ss);
    @Query("select * from school_subject where weekDay = :day")
    public List<SchoolSubject> getByDay(SchoolSubject.WeekDay day);
    @Query("select id from school_subject where id = :id")
    public boolean exists(String id);
    @Query("select id from school_subject where id = :id")
    public long findById(long id);
}

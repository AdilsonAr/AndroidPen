package com.example.pen.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class SchoolSubject {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String dayOfWeek;
    private Date fromTime;
    private Date untilTime;

    //region CONSTRUCTORES
    public SchoolSubject() {
    }

    public SchoolSubject(String name, String dayOfWeek, Date fromTime, Date untilTime) {
        this.name = name;
        this.dayOfWeek = dayOfWeek;
        this.fromTime = fromTime;
        this.untilTime = untilTime;
    }
    //endregion CONSTRUCTORES

    //region PROPIEDADES
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public Date getUntilTime() {
        return untilTime;
    }

    public void setUntilTime(Date untilTime) {
        this.untilTime = untilTime;
    }
//endregion

    //region FUNCIONES_PRUEBA
    //endregion
}

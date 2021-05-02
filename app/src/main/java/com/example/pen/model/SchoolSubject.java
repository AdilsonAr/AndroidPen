package com.example.pen.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "school_subject")
public class SchoolSubject {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private WeekDay weekDay;
    private Date fromTime;
    private Date untilTime;

    //region TIPOS ANIDADOS
    public enum WeekDay {
        LUNES,
        MARTES,
        MIERCOLES,
        JUEVES,
        VIERNES,
        SABADO,
        DOMINGO
    }
    //endregion

    //region CONSTRUCTORES
    public SchoolSubject() {
    }

    public SchoolSubject(String name, WeekDay weekDay, Date fromTime, Date untilTime) {
        this.name = name;
        this.weekDay = weekDay;
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

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
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

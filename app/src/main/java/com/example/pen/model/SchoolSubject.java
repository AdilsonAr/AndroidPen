package com.example.pen.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.RoomWarnings;

import java.util.Date;

/**
 * version 1.0.2
 * last: 22/05/2021
 * descripcion: representa una asignatura del horario de clases
 */
@Entity(tableName = "school_subject")
@SuppressWarnings({RoomWarnings.DEFAULT_CONSTRUCTOR})
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

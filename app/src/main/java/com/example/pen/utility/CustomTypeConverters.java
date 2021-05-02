package com.example.pen.utility;

import androidx.room.TypeConverter;

import com.example.pen.model.SchoolSubject;

import java.util.Date;

public class CustomTypeConverters {
    @TypeConverter
    public String weekDayToString(SchoolSubject.WeekDay day){
        return day.name();
    }

    @TypeConverter
    public SchoolSubject.WeekDay stringToWeekDay(String day){
        return SchoolSubject.WeekDay.valueOf(day);
    }

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}

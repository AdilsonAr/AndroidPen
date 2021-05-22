package com.example.pen.utility;

import com.example.pen.model.SchoolSubject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.example.pen.model.SchoolSubject.WeekDay;

/**
 * version: 1.0.1
 * Esta clase no forma parte del modelo, solo es utilidad para usar
 * el SchoolSubjectDAO
 * ------
 * 1.0.1
 * -cambiada forma de generar datos de prueba para aumentar
 * una hora a la hora until
 */
public class SchoolSubjectDay {
    //region VARIABLES
    private WeekDay dayOfWeek;
    private List<SchoolSubject> subjectsInDay;
    //endregion

    //region CONSTRUCTORES
    public SchoolSubjectDay() {
        subjectsInDay = new ArrayList<>();
    }

    public SchoolSubjectDay(WeekDay dayOfWeek, List<SchoolSubject> subjectsInDay) {
        this.dayOfWeek = dayOfWeek;
        this.subjectsInDay = subjectsInDay;
    }
    //endregion

    //region PROPIEDADES

    public WeekDay getWeekDay() {
        return dayOfWeek;
    }

    public void setWeekDay(WeekDay dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<SchoolSubject> getSubjectsInDay() {
        return subjectsInDay;
    }

    public void setSubjectsInDay(List<SchoolSubject> subjectsInDay) {
        this.subjectsInDay = subjectsInDay;
    }

    //endregion

    //region TIPOS ANIDADOS

    //endregion

    //region FUNCIONES
    public static List<SchoolSubjectDay> generateSchedule(){
        List<SchoolSubjectDay> salida;

        salida = new ArrayList<>();

        salida.add(new SchoolSubjectDay());
        salida.get(0).setWeekDay(WeekDay.LUNES);
        salida.get(0).getSubjectsInDay().add(new SchoolSubject("Matematica",
                WeekDay.LUNES,
                Calendar.getInstance().getTime(),
                Calendar.getInstance().getTime()));
        salida.get(0).getSubjectsInDay().add(new SchoolSubject("Ciencia",
                WeekDay.LUNES, Calendar.getInstance().getTime()
                , Calendar.getInstance().getTime()));

        salida.add(new SchoolSubjectDay());
        salida.get(1).setWeekDay(WeekDay.MARTES);
        salida.get(1).getSubjectsInDay().add(new SchoolSubject("Sociales",
                WeekDay.MARTES,
                Calendar.getInstance().getTime()
                , Calendar.getInstance().getTime()));
        salida.get(1).getSubjectsInDay().add(new SchoolSubject("Religion",
                WeekDay.MARTES,
                Calendar.getInstance().getTime()
                , Calendar.getInstance().getTime()));

        salida.add(new SchoolSubjectDay());
        salida.get(2).setWeekDay(WeekDay.MIERCOLES);
        salida.get(2).getSubjectsInDay().add(new SchoolSubject("Lenguaje",
                WeekDay.MIERCOLES,
                Calendar.getInstance().getTime()
                , Calendar.getInstance().getTime()));

        salida.add(new SchoolSubjectDay());
        salida.get(3).setWeekDay(WeekDay.JUEVES);
        salida.get(3).getSubjectsInDay().add(new SchoolSubject("Artistica",
                WeekDay.JUEVES,
                Calendar.getInstance().getTime()
                , Calendar.getInstance().getTime()));
        salida.get(3).getSubjectsInDay().add(new SchoolSubject("Moral",
                WeekDay.JUEVES,
                Calendar.getInstance().getTime()
                , Calendar.getInstance().getTime()));

        salida.add(new SchoolSubjectDay());
        salida.get(4).setWeekDay(WeekDay.VIERNES);
        salida.get(4).getSubjectsInDay().add(new SchoolSubject("Fisica",
                WeekDay.VIERNES,
                Calendar.getInstance().getTime()
                , Calendar.getInstance().getTime()));

        salida.add(new SchoolSubjectDay());
        salida.get(5).setWeekDay(WeekDay.SABADO);
        /*salida.get(5).getSubjectsInDay().add(new SchoolSubjectDAO("Test", "Sabado", Calendar.getInstance().getTime()
                , Calendar.getInstance().getTime()));*/

        salida.add(new SchoolSubjectDay());
        salida.get(6).setWeekDay(WeekDay.DOMINGO);
        /*salida.get(6).getSubjectsInDay().add(new SchoolSubjectDAO("Test", "Domingo", Calendar.getInstance().getTime()
                , Calendar.getInstance().getTime()));*/

        return salida;
    }
    //endregion
}

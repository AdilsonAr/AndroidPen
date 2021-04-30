package com.example.pen.utility;

import com.example.pen.model.SchoolSubject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Esta clase no forma parte del modelo, solo es utilidad para usar
 * el SchoolSubject
 */
public class SchoolSubjectDay {
    //region VARIABLES
    private DayOfWeek dayOfWeek;
    private List<SchoolSubject> subjectsInDay;
    //endregion

    //region CONSTRUCTORES
    public SchoolSubjectDay() {
        subjectsInDay = new ArrayList<>();
    }

    public SchoolSubjectDay(DayOfWeek dayOfWeek, List<SchoolSubject> subjectsInDay) {
        this.dayOfWeek = dayOfWeek;
        this.subjectsInDay = subjectsInDay;
    }
    //endregion

    //region PROPIEDADES

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
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
    public enum DayOfWeek{
        LUNES,
        MARTES,
        MIERCOLES,
        JUEVES,
        VIERNES,
        SABADO,
        DOMINGO
    }
    //endregion

    //region FUNCIONES
    public static List<SchoolSubjectDay> generateSchedule(){
        List<SchoolSubjectDay> salida;

        salida = new ArrayList<>();

        salida.add(new SchoolSubjectDay());
        salida.get(0).setDayOfWeek(DayOfWeek.LUNES);
        salida.get(0).getSubjectsInDay().add(new SchoolSubject("Matematica", "Lunes", Calendar.getInstance().getTime()
                , Calendar.getInstance().getTime()));
        salida.get(0).getSubjectsInDay().add(new SchoolSubject("Ciencia", "Lunes", Calendar.getInstance().getTime()
                , Calendar.getInstance().getTime()));

        salida.add(new SchoolSubjectDay());
        salida.get(1).setDayOfWeek(DayOfWeek.MARTES);
        salida.get(1).getSubjectsInDay().add(new SchoolSubject("Sociales", "Martes", Calendar.getInstance().getTime()
                , Calendar.getInstance().getTime()));
        salida.get(1).getSubjectsInDay().add(new SchoolSubject("Religion", "Martes", Calendar.getInstance().getTime()
                , Calendar.getInstance().getTime()));

        salida.add(new SchoolSubjectDay());
        salida.get(2).setDayOfWeek(DayOfWeek.MIERCOLES);
        salida.get(2).getSubjectsInDay().add(new SchoolSubject("Lenguaje", "Miercoles", Calendar.getInstance().getTime()
                , Calendar.getInstance().getTime()));

        salida.add(new SchoolSubjectDay());
        salida.get(3).setDayOfWeek(DayOfWeek.JUEVES);
        salida.get(3).getSubjectsInDay().add(new SchoolSubject("Artistica", "Jueves", Calendar.getInstance().getTime()
                , Calendar.getInstance().getTime()));
        salida.get(3).getSubjectsInDay().add(new SchoolSubject("Moral", "Jueves", Calendar.getInstance().getTime()
                , Calendar.getInstance().getTime()));

        salida.add(new SchoolSubjectDay());
        salida.get(4).setDayOfWeek(DayOfWeek.VIERNES);
        salida.get(4).getSubjectsInDay().add(new SchoolSubject("Fisica", "Viernes", Calendar.getInstance().getTime()
                , Calendar.getInstance().getTime()));

        salida.add(new SchoolSubjectDay());
        salida.get(5).setDayOfWeek(DayOfWeek.VIERNES);
        /*salida.get(5).getSubjectsInDay().add(new SchoolSubject("Test", "Sabado", Calendar.getInstance().getTime()
                , Calendar.getInstance().getTime()));*/

        salida.add(new SchoolSubjectDay());
        salida.get(6).setDayOfWeek(DayOfWeek.VIERNES);
        /*salida.get(6).getSubjectsInDay().add(new SchoolSubject("Test", "Domingo", Calendar.getInstance().getTime()
                , Calendar.getInstance().getTime()));*/

        return salida;
    }
    //endregion
}

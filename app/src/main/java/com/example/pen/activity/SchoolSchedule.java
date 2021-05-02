package com.example.pen.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pen.R;
import com.example.pen.dao.AppDb;
import com.example.pen.dao.SchoolSubjectDao;
import com.example.pen.model.SchoolSubject;
import com.example.pen.service.SchoolScheduleAdapter;
import com.example.pen.utility.SchoolSubjectDay;

import java.util.ArrayList;
import java.util.List;

/**
 * No es un adaptador de una entidad, mas bie es un adaptador auxiliar para
 * el funcionamiento de <b>SchoolSubjectAdapter</b>, funcionando la presente clase
 * como un "contenedor"
 */
public class SchoolSchedule extends AppCompatActivity {
    //variables
    /**
     * Mantiene una instancia de this accesible en cualquier contexto
     */
    SchoolSchedule self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_schedule);

        //variables
        RecyclerView rcvSchoolSchedule;
        Button btnTmp;
        AppDb db;

        //inicializacion
        //base de datos
        db = Room.databaseBuilder(SchoolSchedule.this,
                AppDb.class,"db_pen").allowMainThreadQueries().build();

        //este boton e solo para insertar datos de prueba 1 vez
        btnTmp = findViewById(R.id.btnTmp);
        btnTmp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    List<SchoolSubjectDay> listToInsert;
                    SchoolSubjectDao ssDao;

                    listToInsert = SchoolSubjectDay.generateSchedule();
                    ssDao = db.schoolSubjectDao();
                    listToInsert.forEach(schoolSubjectDay -> {
                        schoolSubjectDay.getSubjectsInDay().forEach(sc ->{
                            ssDao.insert(sc);
                        });
                    });
                }
        });//fin boton de prueba

        //reciclerview y elementos relacionados
        rcvSchoolSchedule = findViewById(R.id.rcvSchoolSchedule);
        rcvSchoolSchedule.setLayoutManager(new LinearLayoutManager(this));

        //obtener los datos desde la base
        List<SchoolSubjectDay> schoolSubjectDays;
        List<SchoolSubject> schoolSubjects;;
        SchoolScheduleAdapter schsad;

        //armar los datos y ordenarlos ppor dia atravez de la clase de
        //utilidad SchoolSubjectDays
        schoolSubjectDays = new ArrayList<>();
        for(SchoolSubject.WeekDay day:SchoolSubject.WeekDay.values()){
            SchoolSubjectDay ssday;

            schoolSubjects = db.schoolSubjectDao().getByDay(day);
            ssday = new SchoolSubjectDay(day, schoolSubjects);
            schoolSubjectDays.add(ssday);
        }

        //asignar un adaptador a rcvSchoolSchedule
        schsad = new SchoolScheduleAdapter(schoolSubjectDays);
        schsad.setBtnShowMoreOnClickListener(new SchoolScheduleAdapter.ActionOnViewAtPosstion() {
            @Override
            public void action(View v, int possition) {
                Intent intentShowMoreOfDay;
                Bundle bundleData;

                intentShowMoreOfDay = new Intent(self, SchoolScheduleDay.class);
                intentShowMoreOfDay.putExtra("WeekDay"
                        , schsad.getDaysAndSubjects().get(possition).getWeekDay().name());
                startActivity(intentShowMoreOfDay);
            }
        });
        rcvSchoolSchedule.setAdapter(schsad);

        //cerrar base
        db.close();
    }
}
package com.example.pen.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.pen.R;
import com.example.pen.dao.AppDb;
import com.example.pen.dao.SchoolSubjectDao;
import com.example.pen.model.SchoolSubject;
import com.example.pen.service.SchoolScheduleAdapter;
import com.example.pen.utility.IActionOnViewAtPossition;
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
    RecyclerView rcvSchoolSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_schedule);

        //variables
        //el boton que debe retornar al menu principal
        ImageButton btnRetMainMenu;

        //inicializacion
        //reciclerview y elementos relacionados
        rcvSchoolSchedule = findViewById(R.id.rcvSchoolSchedule);
        rcvSchoolSchedule.setLayoutManager(new LinearLayoutManager(this));
        btnRetMainMenu = findViewById(R.id.btnReturnToMainMenu);

        //obtener los datos
        fetchSchoolScheduleData();

        //btnRetMainMenu para retornar al menu principal
        btnRetMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainMenu.class));
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        fetchSchoolScheduleData();
    }

    //region FUNCIONES
    private void fetchSchoolScheduleData(){
        //obtener los datos desde la base
        List<SchoolSubjectDay> schoolSubjectDays;
        List<SchoolSubject> schoolSubjects;;
        SchoolScheduleAdapter schsad;

        // armar los datos y ordenarlos ppor dia atravez de la clase de
        // utilidad SchoolSubjectDays que contendra los dias y las
        //materias de esos dias
        {
            AppDb db;
            AppDb db2;

            schoolSubjectDays = new ArrayList<>();
            db = Room.databaseBuilder(SchoolSchedule.this,
                    AppDb.class,"db_pen").allowMainThreadQueries().build();

            db2 = Room.databaseBuilder(SchoolSchedule.this,
                    AppDb.class,"db_pen").allowMainThreadQueries().build();

            for(SchoolSubject.WeekDay day:SchoolSubject.WeekDay.values()){
                SchoolSubjectDay ssday;

                schoolSubjects = db.schoolSubjectDao().getByDay(day);
                ssday = new SchoolSubjectDay(day, schoolSubjects);
                schoolSubjectDays.add(ssday);
            }
            //cerrar base
            db.close();
        } //fin de armar los datos y ordenarlos

        //asignar un adaptador a rcvSchoolSchedule
        {

            schsad = new SchoolScheduleAdapter(schoolSubjectDays);
            schsad.setBtnShowMoreOnClickListener(new IActionOnViewAtPossition() {
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

            //establecer el adaptador de los datos
            rcvSchoolSchedule.setAdapter(schsad);

        } //fin asignar un adaptador a rcvScholSchedule
    }
    //endregion
}
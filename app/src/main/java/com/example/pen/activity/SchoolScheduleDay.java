package com.example.pen.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.widget.TextView;

import com.example.pen.R;
import com.example.pen.dao.AppDb;
import com.example.pen.model.SchoolSubject;
import com.example.pen.service.SchoolSubjectAdapter;

public class SchoolScheduleDay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_schedule_day);

        //variables
        TextView txtSchlSchdlDy;
        RecyclerView rcvSchlSchdlAssignments;
        Bundle receivedDataBundle;

        //inicializacion
        receivedDataBundle = getIntent().getExtras();
        txtSchlSchdlDy = findViewById(R.id.txtSchlSchdlDyDay);
        rcvSchlSchdlAssignments = findViewById(R.id.rcvSchlSchdlDyAssingments);

        //establecer valores de los elementos
        txtSchlSchdlDy.setText(receivedDataBundle.getString("WeekDay"));

        //instanciar la base de datos
        AppDb db;

        db = Room.databaseBuilder(this, AppDb.class, "db_pen")
                .allowMainThreadQueries().build();

        //poblar reciclerview
        SchoolSubjectAdapter ssa;

        ssa = new SchoolSubjectAdapter(db.schoolSubjectDao()
                .getByDay(SchoolSubject.WeekDay
                        .valueOf(receivedDataBundle.getString("WeekDay"))));

        rcvSchlSchdlAssignments.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        ));
        rcvSchlSchdlAssignments.setAdapter(ssa);

        db.close();
    }
}
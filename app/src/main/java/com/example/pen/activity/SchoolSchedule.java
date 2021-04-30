package com.example.pen.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewTreeObserver;

import com.example.pen.R;
import com.example.pen.model.SchoolSubject;
import com.example.pen.service.SchoolScheduleDayAdapter;
import com.example.pen.service.SchoolSubjectAdapter;
import com.example.pen.utility.SchoolSubjectDay;

import java.util.ArrayList;
import java.util.List;

public class SchoolSchedule extends AppCompatActivity {
    RecyclerView rcvSchoolSchedule;
    SchoolScheduleDayAdapter schsad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_schedule);

        //variables
        Context contextTmp = this;

        //inicializacion
        rcvSchoolSchedule = findViewById(R.id.rcvSchoolSchedule);
        rcvSchoolSchedule.setLayoutManager(new LinearLayoutManager(this));

        //asignar unadaptador a rcvSchoolSchedule
        schsad = new SchoolScheduleDayAdapter(SchoolSubjectDay.generateSchedule());
        rcvSchoolSchedule.setAdapter(schsad);


    }
}
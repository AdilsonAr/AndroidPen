package com.example.pen.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.pen.R;
import com.example.pen.activity.Apuntes;
import com.example.pen.activity.Search;
import com.example.pen.dao.AppDb;
import com.example.pen.model.ApunteSimple;
import com.example.pen.model.UpsertFragment;
import com.example.pen.service.DateFormats;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FragmentApunteSimpleUpsert extends Fragment implements UpsertFragment {
    private String task;
    private ApunteSimple apunte;
    private AppDb db;
    private EditText txtNombre;
    private EditText txtContenido;

    public void setTask(String task) {
        this.task = task;
    }

    public void setApunte(ApunteSimple apunte) {
        this.apunte = apunte;
    }

    public FragmentApunteSimpleUpsert() {
        // Required empty public constructor
    }

    public static FragmentApunteSimpleUpsert newInstance(String task, ApunteSimple apunte) {
        FragmentApunteSimpleUpsert fragment = new FragmentApunteSimpleUpsert();
        fragment.setApunte(apunte);
        fragment.setTask(task);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_apunte_simple_upsert, container, false);
        txtNombre =v.findViewById(R.id.nombre23424);
        txtContenido =v.findViewById(R.id.contenido23424);
        if(task.equals("update")){
            txtNombre.setText(apunte.getNombre());
            txtContenido.setText(apunte.getContent());
        }
        return v;
    }

    @Override
    public void upsert() {
        db= Room.databaseBuilder(getActivity(), AppDb.class,"apunteSimple").allowMainThreadQueries().build();
        String nombre=txtNombre.getText().toString();
        String content=txtContenido.getText().toString();
        SimpleDateFormat format=DateFormats.DATE_TIME.getFormat();

        if(task.equals("create")){
            ApunteSimple newApunte=new ApunteSimple(format.format(new Date()),nombre,"simple",content);
            db.apunteSimpleDAO().insert(newApunte);
        }else{
            apunte.setContent(content);
            apunte.setNombre(nombre);
            db.apunteSimpleDAO().update(apunte);
        }

        Intent in=new Intent(getActivity(), Apuntes.class);
        startActivity(in);
    }
}
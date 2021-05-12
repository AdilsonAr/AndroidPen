package com.example.pen.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.pen.R;
import com.example.pen.dao.AppDb;
import com.example.pen.fragments.ApuntesFragment;
import com.example.pen.fragments.EmptyFragment;
import com.example.pen.fragments.NoteOption;

public class Apuntes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apuntes);
        ImageButton menu=findViewById(R.id.menu232);
        ImageButton newApunte=findViewById(R.id.newApunte);
        AppDb dbCount= Room.databaseBuilder(Apuntes.this, AppDb.class,"url").allowMainThreadQueries().build();
        int ap1 = dbCount.apunteKeyValueDAO().findAll().size();
        int ap2 = dbCount.apunteSimpleDAO().findAll().size();
        if((ap1+ap2)>0){
            getSupportFragmentManager().beginTransaction().add(R.id.frame242,new ApuntesFragment()).commit();
        }else{
            getSupportFragmentManager().beginTransaction().add(R.id.frame242,new EmptyFragment()).commit();
        }
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Apuntes.this,MainMenu.class);
                startActivity(in);
            }
        });
        newApunte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                NoteOption opt = NoteOption.newInstance();
                opt.show(fm, "fragment_note_option");
            }
        });
    }
}
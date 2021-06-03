package com.example.pen.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.pen.R;
import com.google.android.material.transition.MaterialSharedAxis;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ImageView apuntes=findViewById(R.id.apuntes);
        ImageView search=findViewById(R.id.busca);
        ImageView busquedas=findViewById(R.id.busquedas);
        ImageView horario=findViewById(R.id.horario);
        horario.setOnClickListener(v -> {
            Intent act=new Intent(MainMenu.this,SchoolSchedule.class);
            startActivity(act);
        });
        apuntes.setOnClickListener(v -> {
            Intent act=new Intent(MainMenu.this,Apuntes.class);
            startActivity(act);
        });

        search.setOnClickListener(v -> {
            Intent act=new Intent(MainMenu.this,Search.class);
            startActivity(act);
        });
        busquedas.setOnClickListener(v -> {
            Intent act=new Intent(MainMenu.this,SearchList.class);
            startActivity(act);
        });
    }

    public void irAgenda(View view) {
        Intent intent = new Intent(this, ContenedorAgendas.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
}
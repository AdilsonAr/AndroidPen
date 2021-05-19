package com.example.pen.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.pen.R;
public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ImageButton back=findViewById(R.id.back8778);
        ImageButton apuntes=findViewById(R.id.apuntes);
        ImageButton search=findViewById(R.id.busca);
        ImageButton busquedas=findViewById(R.id.busquedas);

        apuntes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act=new Intent(MainMenu.this,Apuntes.class);
                startActivity(act);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act=new Intent(MainMenu.this,MainActivity.class);
                startActivity(act);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act=new Intent(MainMenu.this,Search.class);
                startActivity(act);
            }
        });
        busquedas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act=new Intent(MainMenu.this,SearchList.class);
                startActivity(act);
            }
        });
    }
}
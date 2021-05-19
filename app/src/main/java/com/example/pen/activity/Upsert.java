package com.example.pen.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.pen.R;
import com.example.pen.fragments.FragmentApunteKeyValueUpsert;
import com.example.pen.fragments.FragmentApunteSimpleUpsert;
import com.example.pen.model.ApunteKeyValue;
import com.example.pen.model.ApunteSimple;
import com.example.pen.model.UpsertFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Upsert extends AppCompatActivity {
    private String task;
    private Object entity;
    private UpsertFragment upsertFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upsert);
        TextView cancelar=findViewById(R.id.cancelId);
        TextView aceptar=findViewById(R.id.upsertId);
        Bundle b=getIntent().getExtras();
        entity=b.get("entity");
        task=b.getString("task");

        switch (task){
            case "ApunteSimple":
                if(entity!=null){
                    FragmentApunteSimpleUpsert fragment=FragmentApunteSimpleUpsert.newInstance("update"
                            ,(ApunteSimple)entity);
                    upsertFragment=fragment;
                    getSupportFragmentManager().beginTransaction().add(R.id.frame3434, fragment).commit();
                }else{
                    FragmentApunteSimpleUpsert fragment=FragmentApunteSimpleUpsert.newInstance("create"
                            ,null);
                    upsertFragment=fragment;
                    getSupportFragmentManager().beginTransaction().add(R.id.frame3434, fragment).commit();
                }
                break;
            case "ApunteKeyValue":
                if(entity!=null){
                    FragmentApunteKeyValueUpsert fragment=FragmentApunteKeyValueUpsert.newInstance("update"
                            ,(ApunteKeyValue)entity);
                    upsertFragment=fragment;
                    getSupportFragmentManager().beginTransaction().add(R.id.frame3434, fragment).commit();
                }else{
                    FragmentApunteKeyValueUpsert fragment=FragmentApunteKeyValueUpsert.newInstance("create"
                            ,null);
                    upsertFragment=fragment;
                    getSupportFragmentManager().beginTransaction().add(R.id.frame3434, fragment).commit();
                }
                break;
            default:
                throw new IllegalArgumentException("parametro de Bundle invalido, Upsert no encontro ningun fragment para cargar");
        }

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upsertFragment.upsert();
            }
        });
    }
}
package com.example.pen.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.pen.R;
import com.example.pen.activity.fragments.AgendaFragment;

public class ContenedorAgendas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor_agendas);

        AgendaFragment homeFragment = new AgendaFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, homeFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
    }
}
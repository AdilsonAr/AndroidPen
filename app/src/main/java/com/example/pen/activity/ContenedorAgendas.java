package com.example.pen.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.pen.R;
import com.example.pen.activity.fragments.AgendaFragment;
import com.example.pen.activity.fragments.AgregarActividadesFragment;
import com.example.pen.model.ActividadesAgenda;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

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

    /*public void validar(){
        if(Objects.requireNonNull(txtactividad_agregar.getText()).toString().trim().isEmpty()){
            txtactividad_agregar.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_error, 0);
        }
    }*/


}
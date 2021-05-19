package com.example.pen.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pen.R;
import com.example.pen.model.ApunteSimple;

public class FragmentApunteSimpleView extends Fragment {
    private ApunteSimple apunte;

    public void setApunte(ApunteSimple apunte) {
        this.apunte = apunte;
    }

    public FragmentApunteSimpleView() {
        // Required empty public constructor
    }

    public static FragmentApunteSimpleView newInstance(ApunteSimple apunte) {
        FragmentApunteSimpleView fragment = new FragmentApunteSimpleView();
        fragment.setApunte(apunte);
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
        View v= inflater.inflate(R.layout.fragment_apunte_simple_view, container, false);
        TextView nombre=v.findViewById(R.id.nombre4547);
        TextView contenido=v.findViewById(R.id.contenido4547);
        nombre.setText(apunte.getNombre());
        contenido.setText(apunte.getContent());
        return v;
    }
}
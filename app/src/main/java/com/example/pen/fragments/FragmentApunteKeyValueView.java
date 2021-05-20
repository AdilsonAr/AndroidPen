package com.example.pen.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pen.R;
import com.example.pen.model.KeyValue;
import com.example.pen.service.KeyValueViewAdapter;

import java.util.List;

public class FragmentApunteKeyValueView extends Fragment {
    private String nombre;
    private List<KeyValue> keyValues;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setKeyValues(List<KeyValue> keyValues) {
        this.keyValues = keyValues;
    }

    public FragmentApunteKeyValueView() {
        // Required empty public constructor
    }

    public static FragmentApunteKeyValueView newInstance(String nombre, List<KeyValue> list) {
        FragmentApunteKeyValueView fragment = new FragmentApunteKeyValueView();
        fragment.setKeyValues(list);
        fragment.setNombre(nombre);
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
        View v  = inflater.inflate(R.layout.fragment_apunte_key_value_view, container, false);
        RecyclerView rv=v.findViewById(R.id.rv1212);
        TextView txt = v.findViewById(R.id.nombre1212);
        txt.setText(nombre);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        KeyValueViewAdapter adapter=new KeyValueViewAdapter();
        adapter.setList(keyValues);
        rv.setAdapter(adapter);
        return v;
    }
}
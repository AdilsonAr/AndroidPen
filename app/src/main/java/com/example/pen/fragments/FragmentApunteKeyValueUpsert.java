package com.example.pen.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pen.R;
import com.example.pen.model.ApunteKeyValue;
import com.example.pen.model.ApunteSimple;
import com.example.pen.model.UpsertFragment;

public class FragmentApunteKeyValueUpsert extends Fragment implements UpsertFragment {
    private String task;
    private ApunteKeyValue apunte;

    public void setTask(String task) {
        this.task = task;
    }

    public void setApunte(ApunteKeyValue apunte) {
        this.apunte = apunte;
    }

    public FragmentApunteKeyValueUpsert() {
        // Required empty public constructor
    }

    public static FragmentApunteKeyValueUpsert newInstance(String task, ApunteKeyValue apunte) {
        FragmentApunteKeyValueUpsert fragment = new FragmentApunteKeyValueUpsert();
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
        return inflater.inflate(R.layout.fragment_apunte_simple_upsert, container, false);
    }

    @Override
    public void upsert() {
    }
}
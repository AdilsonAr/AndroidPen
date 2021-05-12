package com.example.pen.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pen.R;
import com.example.pen.dao.AppDb;
import com.example.pen.model.ApunteFace;
import com.example.pen.service.ApunteAdapter;
import com.example.pen.service.ApunteService;

import java.util.ArrayList;
import java.util.List;

public class ApuntesFragment extends Fragment {

    public ApuntesFragment() {
        // Required empty public constructor
    }

    public static ApuntesFragment newInstance(String param1, String param2) {
        ApuntesFragment fragment = new ApuntesFragment();
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
        View view= inflater.inflate(R.layout.fragment_apuntes, container, false);
        RecyclerView rv=view.findViewById(R.id.rv343);
        ApunteService service=new ApunteService();
        List<ApunteFace> faces=new ArrayList<>();
        AppDb db= Room.databaseBuilder(getActivity(), AppDb.class,"url").allowMainThreadQueries().build();
        try {
            faces=service.getFaces(db.apunteKeyValueDAO().findAll(),db.apunteSimpleDAO().findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //recycler view...
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        ApunteAdapter adapter=new ApunteAdapter();
        adapter.setLista(faces);
        adapter.setOnClickListener(new ApunteAdapter.ApunteAdapterListener() {
            @Override
            public void opcionesOnClick(View v, int position) {
            }
        });
        rv.setAdapter(adapter);
        return view;
    }
}
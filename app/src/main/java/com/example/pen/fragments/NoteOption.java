package com.example.pen.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pen.R;
import com.example.pen.activity.Upsert;
import com.example.pen.model.ApunteKeyValue;
import com.example.pen.model.ApunteSimple;

public class NoteOption extends DialogFragment {

    public NoteOption() {
        // Required empty public constructor
    }

    public static NoteOption newInstance() {
        NoteOption fragment = new NoteOption();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_option, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        TextView simple = view.findViewById(R.id.simpleId);
        TextView keyValue = view.findViewById(R.id.keyValueId);
        simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApunteSimple a=null;
                Intent in=new Intent(getActivity(), Upsert.class);
                in.putExtra("task","ApunteSimple");
                in.putExtra("entity", a);
                startActivity(in);
                dismiss();
            }
        });

        keyValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApunteKeyValue a=null;
                Intent in=new Intent(getActivity(), Upsert.class);
                in.putExtra("task","ApunteKeyValue");
                in.putExtra("entity", a);
                startActivity(in);
                dismiss();
            }
        });
    }
}
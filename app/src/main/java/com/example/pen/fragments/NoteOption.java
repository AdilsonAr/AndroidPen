package com.example.pen.fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pen.R;

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
                Toast.makeText(getActivity(),"Holo simple",Toast.LENGTH_SHORT).show();
            }
        });

        keyValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Holo keyValue",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
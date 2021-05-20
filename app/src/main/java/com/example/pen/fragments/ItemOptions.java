package com.example.pen.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pen.R;
import com.example.pen.activity.Upsert;
import com.example.pen.model.ApunteKeyValue;
import com.example.pen.model.ApunteSimple;
import com.example.pen.service.AlertDialogHandler;

import java.util.function.Function;

public class ItemOptions extends DialogFragment {
    private int p;
    private ApuntesFragment.ApuntesOp op;
    private final Runnable CLOSE=()->dismiss();

    public void setP(int p) {
        this.p = p;
    }

    public void setOp(ApuntesFragment.ApuntesOp op) {
        this.op = op;
    }

    public ItemOptions() {
        // Required empty public constructor
    }

    public static ItemOptions newInstance(int p, ApuntesFragment.ApuntesOp op) {
        ItemOptions fragment = new ItemOptions();
        fragment.setOp(op);
        fragment.setP(p);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_options, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        TextView editar = view.findViewById(R.id.editar678);
        TextView eliminar = view.findViewById(R.id.eliminar678);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                op.editApunte(p);
                CLOSE.run();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                op.deleteApunte(p);
                CLOSE.run();
            }
        });
    }
}
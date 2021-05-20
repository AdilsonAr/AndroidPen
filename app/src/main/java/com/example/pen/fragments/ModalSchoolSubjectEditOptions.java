package com.example.pen.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.pen.R;
import com.example.pen.utility.AsyncTaskCallBack;

/**
 * version: 1.0
 * ultima modificacion: 19/5/21
 * descripcion:
 * Funciona similar al Fragment "NoteOption"
 * para confirmar la eliminaciond e una asignatura
 * del horario de clases
 */
public class ModalSchoolSubjectEditOptions extends DialogFragment {
    private Runnable txvEditOnClickListener;
    private Runnable txvDeleteOnClickListener;

    //region CONSTRUCTORES
    public ModalSchoolSubjectEditOptions() {

    }
    //endregion

    //region PROPIEDADES

    public Runnable getTxvEditOnClickListener() {
        return txvEditOnClickListener;
    }

    public void setTxvEditOnClickListener(Runnable txvEditOnClickListener) {
        this.txvEditOnClickListener = txvEditOnClickListener;
    }

    public Runnable getTxvDeleteOnClickListener() {
        return txvDeleteOnClickListener;
    }

    public void setTxvDeleteOnClickListener(Runnable txvDeleteOnClickListener) {
        this.txvDeleteOnClickListener = txvDeleteOnClickListener;
    }

    //endregion PROPIEDADES

    public static ModalSchoolSubjectEditOptions newInstance() {
        ModalSchoolSubjectEditOptions fragment = new ModalSchoolSubjectEditOptions();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_modal_school_subject_edit_options,
                container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView txvEdit = view.findViewById(R.id.txvFgm_msseoEdit);
        TextView txvDelete = view.findViewById(R.id.txvFgm_msseoDelete);
        //editar
        txvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txvEditOnClickListener.run();
            }
        });

        //eliminar
        txvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txvDeleteOnClickListener.run();
            }
        });
    }
}
package com.example.pen.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.pen.R;
import com.example.pen.model.SchoolSubject;

/**
 * version: 1.0
 * ultima modificacion: 20/5/21
 * descripcion:
 * Funciona similar al Fragment "NoteOption"
 * para insertar y editar un SchoolSubject
 */
public class ModalSchoolSubjectEditForm extends DialogFragment {
    private Runnable txvSaveOnClickListener;
    private Runnable txvCancelOnClickListener;
    private SchoolSubject.WeekDay weekDay;

    //region CONSTRUCTORES
    public ModalSchoolSubjectEditForm() {

    }
    //endregion

    //region PROPIEDADES

    public Runnable getTxvSaveOnClickListener() {
        return txvSaveOnClickListener;
    }

    public void setTxvSaveOnClickListener(Runnable txvSaveOnClickListener) {
        this.txvSaveOnClickListener = txvSaveOnClickListener;
    }

    public Runnable getTxvCancelOnClickListener() {
        return txvCancelOnClickListener;
    }

    public void setTxvCancelOnClickListener(Runnable txvCancelOnClickListener) {
        this.txvCancelOnClickListener = txvCancelOnClickListener;
    }

    public SchoolSubject.WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(SchoolSubject.WeekDay weekDay) {
        this.weekDay = weekDay;
    }
//endregion PROPIEDADES

    public static ModalSchoolSubjectEditForm newInstance() {
        ModalSchoolSubjectEditForm fragment = new ModalSchoolSubjectEditForm();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_modal_school_subject_edit_form,
                container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*TextView txvSave = view.findViewById(R.id.txvFgm_mssefSave);
        TextView txvCancel = view.findViewById(R.id.txvFgm_mssefCancel);*/
        EditText edtWeekDay = view.findViewById(R.id.edtFgm_mssefWeekDay);

        //establecer valores predeterminados
        edtWeekDay.setText(weekDay.name());

        //editar
        /*txvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txvSaveOnClickListener.run();
            }
        });

        //eliminar
        txvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txvCancelOnClickListener.run();
            }
        });*/
    }
}
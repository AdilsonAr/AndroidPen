package com.example.pen.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.pen.R;
import com.example.pen.model.SchoolSubject;
import com.example.pen.utility.VariousUtilities;

import java.text.ParseException;

/**
 * version: 1.0
 * ultima modificacion: 22/5/21
 * descripcion:
 * Funciona similar al Fragment "NoteOption"
 * para insertar y editar un SchoolSubject
 */
public class ModalSchoolSubjectEditForm extends DialogFragment {
    //region variables
    private ModalSchoolSubjectEditForm self = this;
    /**
     * determina si se esta mostrando un timepickerdialog
     */
    private boolean isTimePickerDialogShowing = false;

    private EditText edtSubjectName;
    private EditText edtWeekDay;
    private EditText edtFrom;
    private EditText edtUntil;

    private Runnable txvSaveOnClickListener;
    private Runnable txvCancelOnClickListener;
    private SchoolSubject.WeekDay weekDay;
    private SchoolSubject currentSS;
    //endregion


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

    public SchoolSubject getCurrentSS() {
        return currentSS;
    }

    public void setCurrentSS(SchoolSubject currentSS) {
        this.currentSS = currentSS;
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
        TextView txvSave = view.findViewById(R.id.txvFgm_mssefSave);
        TextView txvCancel = view.findViewById(R.id.txvFgm_mssefCancel);
        edtSubjectName = view.findViewById(R.id.edtFgm_mssefSubject);
        edtFrom = view.findViewById(R.id.edtFgm_mssefFrom);
        edtUntil = view.findViewById(R.id.edtFgm_mssefUntil);
        edtWeekDay = view.findViewById(R.id.edtFgm_mssefWeekDay);

        //establecer valores predeterminados
        edtSubjectName.setText(getCurrentSS().getName());
        edtFrom.setText(VariousUtilities.getHourFormat().format(getCurrentSS().getFromTime()));
        edtUntil.setText(VariousUtilities.getHourFormat().format(getCurrentSS().getUntilTime()));
        edtWeekDay.setText(weekDay.name());

        //region EVENTOS
        //editar
        txvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //asignar los datos al current schoolsubject
                getCurrentSS().setName(edtSubjectName.getText().toString());
                try {
                    getCurrentSS().setFromTime(VariousUtilities.
                            getHourFormat().parse(edtFrom.getText().toString()));
                    getCurrentSS().setUntilTime(VariousUtilities.
                            getHourFormat().parse(edtUntil.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //correr el onClickListener
                txvSaveOnClickListener.run();
            }
        });

        //eliminar
        txvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txvCancelOnClickListener.run();
            }
        });

        //region txvFrom mostrar TimePicker y esconder teclado virtual
        edtFrom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_UP == event.getAction()){
                    VariousUtilities.hideKeyboard(self.getActivity());
                    if(!isTimePickerDialogShowing){
                        VariousUtilities.showTimeDialog(edtFrom,
                                self.getActivity(),
                                ()->{
                                    isTimePickerDialogShowing = false;
                                });
                        isTimePickerDialogShowing = true;
                    }
                }

                return false;
            }
        });
        edtFrom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    VariousUtilities.hideKeyboard(self.getActivity());
                    if(!isTimePickerDialogShowing){
                        isTimePickerDialogShowing = true;
                        VariousUtilities.showTimeDialog(edtFrom,
                                self.getActivity(),
                                ()->{
                                    isTimePickerDialogShowing = false;
                                });
                    }
                }
            }
        });
        //endregion txvFrom mostrar TimePicker y esconder teclado virtual

        //region txvUntil mostrar TimePicker y esconder teclado virtual
        edtUntil.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_UP == event.getAction()){
                    VariousUtilities.hideKeyboard(self.getActivity());
                    if(!isTimePickerDialogShowing){
                        VariousUtilities.showTimeDialog(edtUntil,
                                self.getActivity(),
                                ()->{
                                    isTimePickerDialogShowing = false;
                                });
                        isTimePickerDialogShowing = true;
                    }
                }

                return false;
            }
        });
        edtUntil.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    VariousUtilities.hideKeyboard(self.getActivity());
                    if(!isTimePickerDialogShowing){
                        isTimePickerDialogShowing = true;
                        VariousUtilities.showTimeDialog(edtUntil,
                                self.getActivity(),
                                ()->{
                                    isTimePickerDialogShowing = false;
                                });
                    }
                }
            }
        });
        //endregion txvUntil mostrar TimePicker y esconder teclado virtual
        //endregion EVENTOS
    }
}
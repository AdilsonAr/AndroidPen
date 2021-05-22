package com.example.pen.utility;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * version: 1.0.1
 * ------------------
 * 1.0.1
 * -annadidos formatos de fecha y hora para acceder desde
 * cualquier clase
 * -annadido metodo para esconder el teclado virtual
 * -annadido metodo para mostrar un timepickerdialog
 * -annadida funcion para sumar horas a un tiempo
 */
public class VariousUtilities {
    /**
     * version 1.0
     * Permite acceder el formato de fecha salvadorenna globalmente
     */
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    /**
     * version 1.0
     * Permite acceder el fomato de hora con am y pm
     */
    private static SimpleDateFormat hourFormat = new SimpleDateFormat("hh:mm aa");

    //region PROPIEDADES

    public static SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public static void setDateFormat(SimpleDateFormat dateFormat) {
        VariousUtilities.dateFormat = dateFormat;
    }

    public static SimpleDateFormat getHourFormat() {
        return hourFormat;
    }

    public static void setHourFormat(SimpleDateFormat hourFormat) {
        VariousUtilities.hourFormat = hourFormat;
    }

    //

    //region FUNCIONES

    /**
     * version 1.0
     * esconde el teclado virtual
     * @param activity
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //detectar la vista con focus
        View view = activity.getCurrentFocus();
        //determinar su exitencio o crear nueva
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * version 1.0
     * Muestra un dialogo para seleccionar la hora y luego ejecuta onFinishSettingTime
     * sin embargo, lo ejecuta tanto para cuando la fecha es seleccionada como para cuendo
     * el usuario decide cancelar la accion
     * @param time
     * @param context
     * @param onFinishSettingTime accion a realizar al seleccionar o cancelar la seleccion
     */
    public static void showTimeDialog(EditText time, Context context, Runnable onFinishSettingTime) {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog tpd;

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(calendar.MINUTE, minute);

                time.setText(getHourFormat().format(calendar.getTime()));

                if(onFinishSettingTime != null){
                    onFinishSettingTime.run();
                }
            }
        };

        tpd = new TimePickerDialog(context, timeSetListener
                , calendar.get(Calendar.HOUR_OF_DAY)
                , calendar.get(Calendar.MINUTE)
                , false);

        tpd.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if(onFinishSettingTime != null){
                    onFinishSettingTime.run();
                }
            }
        });

        tpd.show();
    }

    /**
     * version: 1.0
     * @param time
     * @param housToAdd
     * @return
     */
    private static Date addHoursToDate(Date time, int housToAdd){
        return new Date(time.getTime() + (housToAdd * 60 * 1000));
    }
    //endregion

}

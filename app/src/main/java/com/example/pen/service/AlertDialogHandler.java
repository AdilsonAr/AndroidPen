package com.example.pen.service;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.example.pen.activity.SearchList;

import java.util.function.Function;

public class AlertDialogHandler {
    private AlertDialog alertDialog;
    AlertDialog.Builder builder;

    public AlertDialogHandler(Context context,String message,String okWord,String cancelWord
            , Runnable ok, Runnable cancel) {
        builder=new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setPositiveButton(okWord, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ok.run();
            }
        });
        builder.setNegativeButton(cancelWord, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancel.run();
            }
        });
    }

    public void run(){
        alertDialog=builder.create();
        alertDialog.show();
    }
}

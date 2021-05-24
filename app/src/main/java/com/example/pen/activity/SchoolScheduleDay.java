package com.example.pen.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pen.R;
import com.example.pen.dao.AppDb;
import com.example.pen.dao.utilities.PenDb;
import com.example.pen.fragments.ModalSchoolSubjectEditForm;
import com.example.pen.fragments.ModalSchoolSubjectEditOptions;
import com.example.pen.fragments.NoteOption;
import com.example.pen.model.SchoolSubject;
import com.example.pen.service.AlertDialogHandler;
import com.example.pen.service.SchoolSubjectAdapter;
import com.example.pen.service.SchoolSubjectAdapterPlus;
import com.example.pen.utility.AsyncTaskCallBack;
import com.example.pen.utility.IActionOnViewAtPossition;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SchoolScheduleDay extends AppCompatActivity {
    //region VARIABLES
    SchoolScheduleDay self = this;
    //end region VARIABLES

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_schedule_day);

        //variables
        TextView txtSchlSchdlDy;
        RecyclerView rcvSchlSchdlAssignments;
        FloatingActionButton btnAdd;
        Bundle receivedDataBundle;
        ImageButton btnReturn;

        //inicializacion
        receivedDataBundle = getIntent().getExtras();
        txtSchlSchdlDy = findViewById(R.id.txtSchlSchdlDyDay);
        rcvSchlSchdlAssignments = findViewById(R.id.rcvSchlSchdlDyAssingments);
        btnAdd = findViewById(R.id.btnSchlSchdlDyAdd);
        btnReturn = findViewById(R.id.btnSchlSchdlDyReturn);

        //establecer valores de los elementos
        txtSchlSchdlDy.setText(receivedDataBundle.getString("WeekDay"));

        //instanciar la base de datos
        AppDb befDb;

        befDb = Room.databaseBuilder(this, AppDb.class, "db_pen")
                .allowMainThreadQueries().build();

        //poblar reciclerview
        SchoolSubjectAdapterPlus ssa;

        ssa = new SchoolSubjectAdapterPlus(befDb.schoolSubjectDao()
                .getByDay(SchoolSubject.WeekDay
                        .valueOf(receivedDataBundle.getString("WeekDay"))));
        //region EVENTOS
        //btnOptions de cada asignatura listada
        ssa.setBtnOptionsOnClickListener(new IActionOnViewAtPossition() {
            @Override
            public void action(View v, int possition) {
                //llamar a la modal que mostrara editar y eliminar
                FragmentManager fm = getSupportFragmentManager();
                ModalSchoolSubjectEditOptions modalSseOptions
                        = ModalSchoolSubjectEditOptions.newInstance();

                //region asignar las acciones para para los botones de la modal modalSseOptions

                //Editar
                modalSseOptions.setTxvEditOnClickListener(()->{
                    //ocultar el modal de editar y eliminar
                    modalSseOptions.dismiss();

                    //llamar a la modal que mostrara el formulario de
                    //insercion y modificacion
                    ModalSchoolSubjectEditForm modalSseForm
                            = ModalSchoolSubjectEditForm.newInstance();

                    //region assignar las acciones  los botones de la modal modalSseForm
                    modalSseForm.setWeekDay(
                            ssa.getSubjectList().get(possition).getWeekDay()
                    );
                    modalSseForm.setCurrentSS(ssa.getSubjectList().get(possition));
                    //confirmar la edicion de la materia
                    modalSseForm.setTxvSaveOnClickListener(()->{
                        new AlertDialogHandler(
                                self,
                                "Esta seguro de que quiere modificar" +
                                        "la asignatura?",
                                "Si",
                                "no",
                                //accion confirmada
                                () ->{
                                    //eliminar de la base
                                    AsyncTaskCallBack asc;
                                    AppDb db = PenDb.getInstance(self);

                                    asc = AsyncTaskCallBack.getInstance();
                                    asc.execute(
                                            //actualizar el registro
                                            () ->{
                                                SchoolSubject ssTmp;

                                                //armar el SchoolSubject con los datos del formulario
                                                ssTmp = modalSseForm.getCurrentSS();

                                                db.schoolSubjectDao().update(ssTmp);
                                            }//fin eliminar de la base
                                            //actualizar el recyclerview
                                            , ()->{
                                                //traer la lista actualizada desde base
                                                List<SchoolSubject> newSsList;
                                                newSsList = db.schoolSubjectDao().getByDay(
                                                        SchoolSubject.WeekDay.valueOf(
                                                                receivedDataBundle.getString("WeekDay"))
                                                );

                                                //actualizar los datos en el adapter y el recycler
                                                runOnUiThread(()->{
                                                    ssa.getSubjectList().clear();
                                                    ssa.setSubjectList(newSsList);
                                                    ssa.notifyDataSetChanged();

                                                    modalSseForm.dismiss();

                                                    Toast.makeText(
                                                            self,
                                                            "asignatura modificacda con exito",
                                                            Toast.LENGTH_SHORT).show();
                                                });
                                                //cerrar db
                                                db.close();
                                            });//fin de actualizar el recyclerview
                                },
                                //accion cancelada
                                ()->{
                                    modalSseOptions.dismiss();
                                    Toast.makeText(
                                            self,
                                            "Ha cancelado la modificacion",
                                            Toast.LENGTH_SHORT).show();
                                }//fin accion cancelada
                        ).run();
                    });
                    //cancelar la edicion de la materia
                    modalSseForm.setTxvCancelOnClickListener(()->{
                        modalSseForm.dismiss();
                        Toast.makeText(
                                self,
                                "Ha cancelado el ingreso de los datos",
                                Toast.LENGTH_SHORT).show();
                    });

                    modalSseForm.show(fm, "Ingresar datos");
                    //endregion assignar las acciones  los botones de la modal modalSseForm
                });

                //Eliminar
                modalSseOptions.setTxvDeleteOnClickListener(()->{
                    new AlertDialogHandler(
                            self,
                            "Esta seguro de que quiere eliminar" +
                                    "la asignatura?",
                            "Si",
                            "no",
                            //accion confirmada
                            () ->{
                                //eliminar de la base
                                AsyncTaskCallBack asc;
                                AppDb db = PenDb.getInstance(self);

                                asc = AsyncTaskCallBack.getInstance();
                                asc.execute(
                                    //eliminar el registro
                                    () ->{
                                        db.schoolSubjectDao().delete(
                                        ssa.getSubjectList().get(possition)
                                    );
                                }//fin eliminar de la base
                                //actualizar el recyclerview
                                , ()->{
                                    //traer la lista actualizada desde base
                                    List<SchoolSubject> newSsList;
                                    newSsList = db.schoolSubjectDao().getByDay(
                                            SchoolSubject.WeekDay.valueOf(
                                                    receivedDataBundle.getString("WeekDay"))
                                    );

                                    //actualizar los datos en el adapter y el recycler
                                    runOnUiThread(()->{
                                        ssa.getSubjectList().clear();
                                        ssa.setSubjectList(newSsList);
                                        ssa.notifyDataSetChanged();

                                        modalSseOptions.dismiss();

                                        Toast.makeText(
                                                self,
                                                "asignatura eliminada con exito",
                                                Toast.LENGTH_SHORT).show();
                                    });
                                    //cerrar db
                                    db.close();
                                });//fin de actualizar el recyclerview
                            },
                            //accion cancelada
                            ()->{
                                modalSseOptions.dismiss();
                                Toast.makeText(
                                        self,
                                        "Ha cancelado la eliminacion",
                                        Toast.LENGTH_SHORT).show();
                            }//fin accion cancelada
                    ).run();
                });

                //Cancelar
                modalSseOptions.setTxvCancelOnClickListener(()->{
                    modalSseOptions.dismiss();
                });
                //endregion asignar las acciones para para los botones de la modal modalSseOptions

                //mostrar modal
                modalSseOptions.show(fm, "Acción sobre la asignatura!");
            }
        });

        //btnAdd para annadir asignatura
        btnAdd.setOnClickListener((view)->{
            //llamar a la modal que mostrara el formulario de
            //insercion y modificacion
            FragmentManager fm = getSupportFragmentManager();
            SchoolSubject ssTmp = new SchoolSubject();

            ModalSchoolSubjectEditForm modalSseForm
                    = ModalSchoolSubjectEditForm.newInstance();

            //establecer datos poro defecto
            modalSseForm.setWeekDay(
                    SchoolSubject.WeekDay
                            .valueOf(receivedDataBundle.getString("WeekDay"))
            );

            //establecer una hora aproximada
            ssTmp.setName("");
            ssTmp.setWeekDay(SchoolSubject.WeekDay
                    .valueOf(receivedDataBundle.getString("WeekDay")));
            ssTmp.setFromTime(Calendar.getInstance().getTime());
            ssTmp.setUntilTime(
                    new Date(Calendar.getInstance().
                            getTime().getTime() + (60*60*1000))
            );
            //establecer el obejeto al formulario modal
            modalSseForm.setCurrentSS(ssTmp);
            //confirmar la insercion de la materia
            modalSseForm.setTxvSaveOnClickListener(()->{
                new AlertDialogHandler(
                        self,
                        "Esta seguro de que quiere añadir" +
                                "la asignatura?",
                        "Si",
                        "no",
                        //accion confirmada
                        () ->{
                            //eliminar de la base
                            AsyncTaskCallBack asc;
                            AppDb db = PenDb.getInstance(self);

                            asc = AsyncTaskCallBack.getInstance();
                            asc.execute(
                                    //actualizar el registro
                                    () ->{
                                        SchoolSubject ss;

                                        //armar el SchoolSubject con los datos del formulario
                                        ss = modalSseForm.getCurrentSS();

                                        db.schoolSubjectDao().insert(ss);
                                    }//fin eliminar de la base
                                    //actualizar el recyclerview
                                    , ()->{
                                        //traer la lista actualizada desde base
                                        List<SchoolSubject> newSsList;
                                        newSsList = db.schoolSubjectDao().getByDay(
                                                SchoolSubject.WeekDay.valueOf(
                                                        receivedDataBundle.getString("WeekDay"))
                                        );

                                        //actualizar los datos en el adapter y el recycler
                                        runOnUiThread(()->{
                                            ssa.getSubjectList().clear();
                                            ssa.setSubjectList(newSsList);
                                            ssa.notifyDataSetChanged();

                                            modalSseForm.dismiss();

                                            Toast.makeText(
                                                    self,
                                                    "asignatura añdida con exito",
                                                    Toast.LENGTH_SHORT).show();
                                        });
                                        //cerrar db
                                        db.close();
                                    });//fin de actualizar el recyclerview
                        },
                        //accion cancelada
                        ()->{
                            modalSseForm.dismiss();
                            Toast.makeText(
                                    self,
                                    "Ha cancelado la modificacion",
                                    Toast.LENGTH_SHORT).show();
                        }//fin accion cancelada
                ).run();
            });
            //cancelar la edicion de la materia
            modalSseForm.setTxvCancelOnClickListener(()->{
                modalSseForm.dismiss();
                Toast.makeText(
                        self,
                        "Ha cancelado el ingreso de los datos",
                        Toast.LENGTH_SHORT).show();
            });

            modalSseForm.show(fm, "Ingresar datos");
            //endregion assignar las acciones  los botones de la modal modalSseForm
        });

        //btnReturn
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //endregion EVENTOS

        rcvSchlSchdlAssignments.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        ));
        rcvSchlSchdlAssignments.setAdapter(ssa);


        //cerrar base
        befDb.close();
    }


}
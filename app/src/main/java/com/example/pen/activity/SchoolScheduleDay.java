package com.example.pen.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.view.View;
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
        Bundle receivedDataBundle;

        //inicializacion
        receivedDataBundle = getIntent().getExtras();
        txtSchlSchdlDy = findViewById(R.id.txtSchlSchdlDyDay);
        rcvSchlSchdlAssignments = findViewById(R.id.rcvSchlSchdlDyAssingments);

        //establecer valores de los elementos
        txtSchlSchdlDy.setText(receivedDataBundle.getString("WeekDay"));

        //instanciar la base de datos
        AppDb db;

        db = Room.databaseBuilder(this, AppDb.class, "db_pen")
                .allowMainThreadQueries().build();

        //poblar reciclerview
        SchoolSubjectAdapterPlus ssa;

        ssa = new SchoolSubjectAdapterPlus(db.schoolSubjectDao()
                .getByDay(SchoolSubject.WeekDay
                        .valueOf(receivedDataBundle.getString("WeekDay"))));
        ssa.setBtnOptionsOnClickListener(new IActionOnViewAtPossition() {
            @Override
            public void action(View v, int possition) {
                //llamar a la modal que mostrara editar y eliminar
                FragmentManager fm = getSupportFragmentManager();
                ModalSchoolSubjectEditOptions modalSseOptions
                        = ModalSchoolSubjectEditOptions.newInstance();

                //asignar las acciones para para los botones de la modal modalSseOptions
                modalSseOptions.setTxvEditOnClickListener(()->{
                    //ocultar el modal de editar y eliminar
                    modalSseOptions.dismiss();

                    //llamar a la modal que mostrara el formulario de
                    //insercion y modificacion
                    ModalSchoolSubjectEditForm modalSseForm
                            = ModalSchoolSubjectEditForm.newInstance();

                    //assignar las acciones  los botones de la modal modalSseForm
                    modalSseForm.setWeekDay(
                            ssa.getSubjectList().get(possition).getWeekDay()
                    );
                    modalSseForm.setTxvSaveOnClickListener(()->{

                    });
                    modalSseForm.setTxvCancelOnClickListener(()->{

                    });

                    modalSseForm.show(fm, "Ingresar datos");
                });
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
                //asignar las acciones para para los botones de la modal modalSseOptions

                //mostrar modal
                modalSseOptions.show(fm, "Acción sobre la asignatura!");
            }
        });

        rcvSchlSchdlAssignments.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        ));
        rcvSchlSchdlAssignments.setAdapter(ssa);

        db.close();
    }


}
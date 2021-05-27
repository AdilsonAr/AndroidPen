package com.example.pen.activity.fragments;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pen.R;
import com.example.pen.model.ActividadesAgenda;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.transition.MaterialSharedAxis;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgregarActividadesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgregarActividadesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    FloatingActionButton btnfecha_agregar,btnhorainicio_agregar,btnhorafin_agregar;
    ExtendedFloatingActionButton btnagregar_agregar;
    TextView txtfecha_agregar,txthorainicio_agregar,txthorafin_agregar;
    TextInputEditText txtactividad_agregar,txtdescripcion_agregar;

    int t1Hour,t1Minute,t2Hour,t2Minute;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AgregarActividadesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgregarActividadesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgregarActividadesFragment newInstance(String param1, String param2) {
        AgregarActividadesFragment fragment = new AgregarActividadesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                AgendaFragment agendaFragment = new AgendaFragment();
                FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out);
                fragmentTransaction.replace(R.id.container, agendaFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agregar_actividades, container, false);
        showToolbar("Agregar Actividad",true,view);

        btnfecha_agregar = view.findViewById(R.id.btnfecha_agregar);
        btnhorainicio_agregar = view.findViewById(R.id.btnhorainicio_agregar);
        btnhorafin_agregar = view.findViewById(R.id.btnhorafin_agregar);
        btnagregar_agregar = view.findViewById(R.id.btnagregar_agregar);

        txtactividad_agregar = view.findViewById(R.id.txtactividad_agregar);
        txtfecha_agregar = view.findViewById(R.id.txtfecha_agregar);
        txthorainicio_agregar = view.findViewById(R.id.txthorainicio_agregar);
        txthorafin_agregar = view.findViewById(R.id.txthorafin_agregar);
        txtdescripcion_agregar = view.findViewById(R.id.txtdescripcion_agregar);

        long today = MaterialDatePicker.todayInUtcMilliseconds();

        CalendarConstraints.Builder constraint = new CalendarConstraints.Builder();
        constraint.setValidator(DateValidatorPointForward.now());

        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Seleccionar fecha");
        builder.setSelection(today);
        builder.setCalendarConstraints(constraint.build());
        final MaterialDatePicker materialDatePicker = builder.build();

        //show the calendar
        btnfecha_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), "DATE_PICKER");
            }
        });

        //capture the date selected
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                txtfecha_agregar.setText(materialDatePicker.getHeaderText());
            }
        });

        btnhorainicio_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(
                            getActivity(),R.style.TimePickerTheme, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            t1Hour = hourOfDay;
                            t1Minute = minute;

                            Calendar calendar = Calendar.getInstance();
                            calendar.set(0,0,0,t1Hour,t1Minute);
                            txthorainicio_agregar.setText(DateFormat.format("hh:mm aa",calendar));
                        }
                    },12,0,false
                    );
                    timePickerDialog.updateTime(t1Hour,t1Minute);
                    timePickerDialog.show();
            }
        });

        btnhorafin_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getActivity(),R.style.TimePickerTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        t2Hour = hourOfDay;
                        t2Minute = minute;

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(0,0,0,t2Hour,t2Minute);
                        txthorafin_agregar.setText(DateFormat.format("hh:mm aa",calendar));
                    }
                },12,0,false
                );
                timePickerDialog.updateTime(t2Hour,t2Minute);
                timePickerDialog.show();
            }
        });

        btnagregar_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validar()){
                    ActividadesAgenda actividadesAgenda = new ActividadesAgenda();
                    actividadesAgenda.setActividad(
                            Objects.requireNonNull(txtactividad_agregar.getText()).toString()
                                    .substring(0,1).toUpperCase()+txtactividad_agregar.getText().toString().substring(1));
                    actividadesAgenda.setFecha(Objects.requireNonNull(txtfecha_agregar.getText()).toString());
                    actividadesAgenda.setHorainicio(Objects.requireNonNull(txthorainicio_agregar.getText()).toString());
                    actividadesAgenda.setHorafin(Objects.requireNonNull(txthorafin_agregar.getText()).toString());
                    actividadesAgenda.setDescripcion(
                            Objects.requireNonNull(txtdescripcion_agregar.getText()).toString()
                                    .substring(0,1).toUpperCase()+txtdescripcion_agregar.getText().toString().substring(1));
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference reference = database.getReference("ACTIVIDADES");
                    reference.push().setValue(actividadesAgenda);

                    actividadGuardada();
                }else{
                    Snackbar snackbar = Snackbar.make(v, "Complete todos los campos.", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

        return view;
    }

    public void showToolbar(String title, boolean upButton, View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle(title);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(upButton);

        toolbar.setNavigationOnClickListener(v -> {
            AgendaFragment agendaFragment = new AgendaFragment();
            FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out);
            fragmentTransaction.replace(R.id.container, agendaFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
    }

    public boolean validar(){
        if(Objects.requireNonNull(txtactividad_agregar.getText()).toString().trim().isEmpty()){
            txtactividad_agregar.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_error, 0);
            return false;
        }else{
            txtactividad_agregar.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_checkmark, 0);
        }
        if(Objects.requireNonNull(txtfecha_agregar.getText()).toString().trim().isEmpty()){
            txtfecha_agregar.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_error, 0);
            return false;
        }else{
            txtfecha_agregar.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_checkmark, 0);
        }
        if(Objects.requireNonNull(txthorainicio_agregar.getText()).toString().trim().isEmpty()){
            txthorainicio_agregar.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_error, 0);
            return false;
        }else{
            txthorainicio_agregar.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_checkmark, 0);
        }
        if(Objects.requireNonNull(txthorafin_agregar.getText()).toString().trim().isEmpty()){
            txthorafin_agregar.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_error, 0);
            return false;
        }else{
            txthorafin_agregar.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_checkmark, 0);
        }
        if(Objects.requireNonNull(txtdescripcion_agregar.getText()).toString().trim().isEmpty()){
            txtdescripcion_agregar.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_error, 0);
            return false;
        }else{
            txtdescripcion_agregar.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_checkmark, 0);
        }

        return true;
    }

    public void actividadGuardada(){
        AgendaFragment agendaFragment = new AgendaFragment();
        FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out);
        fragmentTransaction.replace(R.id.container, agendaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        Toast toast = Toast.makeText(getContext(),"Actividad Guardada",Toast.LENGTH_SHORT);
        toast.show();
    }
}
package com.example.pen.activity.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pen.R;
import com.example.pen.activity.MainMenu;
import com.example.pen.dao.AgendaAdaptador;
import com.example.pen.model.ActividadesAgenda;
import com.example.pen.model.ActividadesServicios;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.transition.MaterialSharedAxis;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgendaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgendaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FloatingActionButton btnagregar;
    ProgressBar progressBar;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView picturesRecycler;
    public AgendaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgendaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgendaFragment newInstance(String param1, String param2) {
        AgendaFragment fragment = new AgendaFragment();
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
                Intent intent = new Intent(getActivity(), MainMenu.class);
                Objects.requireNonNull(getActivity()).startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agenda, container, false);
        showToolbar("Agenda", true, view);
        picturesRecycler = view.findViewById(R.id.pictureRecycler);
        progressBar = view.findViewById(R.id.progressBar);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        picturesRecycler.setLayoutManager(linearLayoutManager);

        AgendaAdaptador picturesRecyclerview = new AgendaAdaptador(
                ActividadesServicios.activida, R.layout.cardview_picture, getActivity());
        picturesRecycler.setAdapter(picturesRecyclerview);

        cargaActividades();

        btnagregar = view.findViewById(R.id.btnagregar);
        btnagregar.setOnClickListener(v -> {
            AgregarActividadesFragment agregarActividad = new AgregarActividadesFragment();
            FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out);
            fragmentTransaction.replace(R.id.container, agregarActividad);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        return view;
    }

    public void showToolbar(String title, boolean upButton, View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle(title);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(upButton);

        toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MainMenu.class);
            Objects.requireNonNull(getActivity()).startActivity(intent);
            getActivity().overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        });

    }

    public void cargaActividades(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("ACTIVIDADES");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    reference.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            ActividadesAgenda actividad = snapshot.getValue(ActividadesAgenda.class);
                            assert actividad != null;
                            actividad.setId(snapshot.getKey());

                            if(!ActividadesServicios.activida.contains(actividad)){
                                ActividadesServicios.agregarActividad(actividad);
                            }
                            Objects.requireNonNull(picturesRecycler.getAdapter()).notifyDataSetChanged();
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable  String previousChildName) {
                            ActividadesAgenda actividad = snapshot.getValue(ActividadesAgenda.class);
                            assert actividad != null;
                            actividad.setId(snapshot.getKey());
                            if(ActividadesServicios.activida.contains(actividad)){
                                ActividadesServicios.actualizar(actividad);
                            }
                            Objects.requireNonNull(picturesRecycler.getAdapter()).notifyDataSetChanged();
                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                            ActividadesAgenda actividad = snapshot.getValue(ActividadesAgenda.class);
                            assert actividad != null;
                            actividad.setId(snapshot.getKey());
                            if(ActividadesServicios.activida.contains(actividad)){
                                ActividadesServicios.eliminar(actividad);
                            }
                            Objects.requireNonNull(picturesRecycler.getAdapter()).notifyDataSetChanged();
                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {

                        }
                    });
                } else {
                    picturesRecycler.setBackgroundResource(R.drawable.add);
                    picturesRecycler.clearOnChildAttachStateChangeListeners();
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
            }
        });

    }

}
package com.example.pen.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pen.R;
import com.example.pen.activity.Apuntes;
import com.example.pen.dao.AppDb;
import com.example.pen.model.ApunteKeyValue;
import com.example.pen.model.ApunteSimple;
import com.example.pen.model.KeyValue;
import com.example.pen.model.UpsertFragment;
import com.example.pen.service.DateFormats;
import com.example.pen.service.KeyValueAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FragmentApunteKeyValueUpsert extends Fragment implements UpsertFragment {
    private String task;
    private ApunteKeyValue apunte =new ApunteKeyValue();
    private SimpleDateFormat format= DateFormats.DATE_TIME.getFormat();
    private List<KeyValue> keyValues= new ArrayList<>();
    private KeyValueAdapter adapter=new KeyValueAdapter();
    private ImageButton newItem;
    private EditText nombre;
    private RecyclerView rv;
    private View v;

    public void setKeyValues(List<KeyValue> keyValues) {
        this.keyValues = keyValues;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public ApunteKeyValue getApunte() {
        return apunte;
    }

    public List<KeyValue> getKeyValues() {
        return keyValues;
    }

    public void setApunte(ApunteKeyValue apunte) {
        this.apunte = apunte;
    }

    public FragmentApunteKeyValueUpsert() {
        // Required empty public constructor
    }

    public static FragmentApunteKeyValueUpsert newInstance(String task, ApunteKeyValue a, List<KeyValue> list) {
        FragmentApunteKeyValueUpsert fragment = new FragmentApunteKeyValueUpsert();
        if(a!=null){
            fragment.getApunte().setNombre(a.getNombre());
            fragment.getApunte().setTipo(a.getTipo());
            fragment.getApunte().setFecha(a.getFecha());
            fragment.getApunte().setId(a.getId());
            list.forEach(x->fragment.getKeyValues().add(x));
        }
        fragment.setTask(task);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_apunte_key_value_upsert, container, false);
        newItem =v.findViewById(R.id.newItem6754);
        nombre=v.findViewById(R.id.nombre6754);
        rv=v.findViewById(R.id.rv6754);

        if(task.equals("create")){
            apunte.setFecha(format.format(new Date()));
            apunte.setNombre("sin titulo");
            apunte.setTipo("preguntas");
            KeyValue keyValue=new KeyValue(-1,"","");
            keyValues.add(keyValue);
        }else{
            nombre.setText(apunte.getNombre());
        }
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setList(keyValues);
        adapter.setListener(new KeyValueAdapter.KeyValueAdapterListener() {
            @Override
            public void deleteOnClick(View v, int position) {
                nombre.requestFocus();
                keyValues.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        newItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyValue keyValue=new KeyValue(-1,"","");
                keyValues.add(keyValue);
                adapter.notifyDataSetChanged();
            }
        });
        rv.setAdapter(adapter);
        return v;
    }

    @Override
    public void upsert() {
        v.clearFocus();
        AppDb dbKeyValue= Room.databaseBuilder(getActivity(),AppDb.class,"keyValue").allowMainThreadQueries().build();
        AppDb dbApunteKeyValue= Room.databaseBuilder(getActivity(),AppDb.class,"apunteKeyValue").allowMainThreadQueries().build();
        if(task.equals("update")){
            List<KeyValue> l=dbKeyValue.keyValueDAO().findByIdApunte(apunte.getId());
            l.forEach(x->dbKeyValue.keyValueDAO().delete(x));
            //ApunteKeyValue apk=dbApunteKeyValue.apunteKeyValueDAO().findById(apunte.getId());
            dbApunteKeyValue.apunteKeyValueDAO().delete(apunte);
            keyValues.forEach(x->x.setId(0));
            apunte.setId(0);
        }

        if(nombre.getText().toString().isEmpty()){
            apunte.setNombre("sin titulo");
        }else{
            apunte.setNombre(nombre.getText().toString());
        }

        long id=dbApunteKeyValue.apunteKeyValueDAO().insert(apunte);

        keyValues.forEach(x->{
            x.setIdApunte(id);
            dbKeyValue.keyValueDAO().insert(x);
        });

        Intent in=new Intent(getActivity(), Apuntes.class);
        startActivity(in);
    }
}
package com.example.pen.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pen.R;
import com.example.pen.activity.ApunteView;
import com.example.pen.activity.Apuntes;
import com.example.pen.activity.Upsert;
import com.example.pen.dao.AppDb;
import com.example.pen.model.ApunteFace;
import com.example.pen.model.ApunteKeyValue;
import com.example.pen.model.ApunteSimple;
import com.example.pen.model.KeyValue;
import com.example.pen.model.ListWrapper;
import com.example.pen.service.AlertDialogHandler;
import com.example.pen.service.ApunteAdapter;
import com.example.pen.service.ApunteService;
import com.example.pen.service.DateFormats;
import com.example.pen.service.RecyclerItemClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ApuntesFragment extends Fragment {
    private List<ApunteFace> facesEffectivelyFinal=new ArrayList<>();
    private List<ApunteSimple> simpleEffectivelyFinal=new ArrayList<>();
    private List<ApunteKeyValue> keyValuesEffectivelyFinal=new ArrayList<>();
    private AppDb dbSimple;
    private AppDb dbKeyValue;
    private RecyclerView rv;
    private ApunteAdapter adapter=new ApunteAdapter();
    private Apuntes.Counter counter;
    private ItemOptions opt = new ItemOptions();

    public void setCounter(Apuntes.Counter counter) {
        this.counter = counter;
    }

    public ApuntesFragment() {
        // Required empty public constructor
    }

    public static ApuntesFragment newInstance(Apuntes.Counter counter) {
        ApuntesFragment fragment = new ApuntesFragment();
        fragment.setCounter(counter);
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
        View view= inflater.inflate(R.layout.fragment_apuntes, container, false);
        rv=view.findViewById(R.id.rv343);
        ApunteService service=new ApunteService();
        List<ApunteFace> faces=new ArrayList<>();

        dbKeyValue= Room.databaseBuilder(getActivity(), AppDb.class,"apunteKeyValue").allowMainThreadQueries().build();
        dbSimple= Room.databaseBuilder(getActivity(), AppDb.class,"apunteSimple").allowMainThreadQueries().build();

        List<ApunteSimple> simple=dbSimple.apunteSimpleDAO().findAll();
        List<ApunteKeyValue> keyValue=dbKeyValue.apunteKeyValueDAO().findAll();

        try {
            faces=service.getFaces(keyValue,simple);
        } catch (Exception e) {
            e.printStackTrace();
        }
        keyValue.forEach(x->keyValuesEffectivelyFinal.add(x));
        simple.forEach(x->simpleEffectivelyFinal.add(x));
        faces.forEach(x->facesEffectivelyFinal.add(x));
        //recycler view...
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setLista(facesEffectivelyFinal);
        adapter.setOnClickListener(new ApunteAdapter.ApunteAdapterListener() {
            @Override
            public void opcionesOnClick(View v, int position) {
                FragmentManager fm = getFragmentManager();
                opt.setP(position);
                opt.setOp(new ApuntesOp());
                opt.show(fm, "fragment_item_options");
            }

            @Override
            public void itemOnClick(View v, int position) {
                Intent in=new Intent(getActivity(),ApunteView.class);
                Optional<?> optional=findAnyApunte.apply(position);
                if(optional.get() instanceof ApunteSimple){
                    in.putExtra("fragment","simple");
                    in.putExtra("apunte",(ApunteSimple)optional.get());
                }else if(optional.get() instanceof ApunteKeyValue){
                    ApunteKeyValue akv=(ApunteKeyValue)optional.get();
                    in.putExtra("fragment","keyValue");
                    in.putExtra("apunte",akv);
                    AppDb dbItems =Room.databaseBuilder(getActivity(),AppDb.class,"keyValue").allowMainThreadQueries().build();
                    List<KeyValue> list=dbItems.keyValueDAO().findByIdApunte(akv.getId());
                    ListWrapper<KeyValue> wrapper=new ListWrapper<>();
                    wrapper.setList(list);
                    in.putExtra("list",wrapper);
                }
                startActivity(in);
            }
        });
        rv.setAdapter(adapter);
        return view;
    }
    Function<Integer,Optional<?>> findAnyApunte=x->{
        ApunteFace apunteFace=facesEffectivelyFinal.get(x);
        SimpleDateFormat format= DateFormats.DATE_TIME.getFormat();
        Optional<ApunteSimple> apunteSimple=simpleEffectivelyFinal.stream().filter(y->y.getFecha()
                .equals(format.format(apunteFace.getFecha())) && y.getNombre().equals(apunteFace.getNombre()))
                .findAny();

        if(apunteSimple.isPresent()){
            return apunteSimple;
        }
        else{
            Optional<ApunteKeyValue> apunteKeyValue=keyValuesEffectivelyFinal.stream().filter(y->y.getFecha()
                    .equals(format.format(apunteFace.getFecha())) && y.getNombre().equals(apunteFace.getNombre()))
                    .findAny();
            if(apunteKeyValue.isPresent()){
                return apunteKeyValue;
            }
        }
        return null;
    };

    public class ApuntesOp{
        AppDb dbItems =Room.databaseBuilder(getActivity(),AppDb.class,"keyValue").allowMainThreadQueries().build();

        public void editApunte(int position){
            Intent in=new Intent(getActivity(), Upsert.class);
            Optional<?> optional=findAnyApunte.apply(position);
            if(optional.get() instanceof ApunteSimple){
                in.putExtra("task","ApunteSimple");
                in.putExtra("entity",(ApunteSimple)optional.get());
            }else if(optional.get() instanceof ApunteKeyValue){
                ApunteKeyValue akv=(ApunteKeyValue)optional.get();
                List<KeyValue> list=dbItems.keyValueDAO().findByIdApunte(akv.getId());
                ListWrapper<KeyValue> wrapper=new ListWrapper<>();
                wrapper.setList(list);
                in.putExtra("task","ApunteKeyValue");
                in.putExtra("list",wrapper);
                in.putExtra("entity",akv);
            }
            startActivity(in);
        }

        public void deleteApunte(int position){
            opt.dismiss();
            AlertDialogHandler alertDialogHandler=new AlertDialogHandler(getActivity(),
                        "Se eliminará el apunte de manera permanente"
                        ,"Aceptar","Cancelar",()->
                {
                    Optional<?> optional=findAnyApunte.apply(position);
                    if(optional.get() instanceof ApunteSimple){
                        ApunteSimple a=(ApunteSimple)optional.get();
                        dbSimple.apunteSimpleDAO().delete(a);
                    }else if(optional.get() instanceof ApunteKeyValue){
                        ApunteKeyValue a=(ApunteKeyValue)optional.get();
                        List<KeyValue> l=dbItems.keyValueDAO().findByIdApunte(a.getId());
                        l.forEach(x->dbItems.keyValueDAO().delete(x));
                        dbKeyValue.apunteKeyValueDAO().delete(a);
                    }
                    facesEffectivelyFinal.remove(position);
                    if(adapter.getItemCount()>0){
                        adapter.notifyDataSetChanged();
                    }else{
                        counter.count(adapter.getItemCount());
                    }
                },
                        ()->{});
                alertDialogHandler.run();

        }
    }
}
package com.example.pen.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.pen.R;
import com.example.pen.activity.Search;
import com.example.pen.dao.AppDb;
import com.example.pen.model.Url;
import com.example.pen.service.AlertDialogHandler;
import com.example.pen.service.DateFormats;
import com.example.pen.service.UrlAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class SearchListFragment extends Fragment {
    View view;
    public SearchListFragment() {
        // Required empty public constructor
    }

    public static SearchListFragment newInstance(String param1, String param2) {
        SearchListFragment fragment = new SearchListFragment();
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
        view=inflater.inflate(R.layout.fragment_search_list, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.recycler);
        ImageButton menu=view.findViewById(R.id.menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        AppDb db= Room.databaseBuilder(getActivity(), AppDb.class,"url").allowMainThreadQueries().build();
        List<Url> listFromDb = db.urlDAO().getAll();
        sort(listFromDb);
        List<Url> listToShow=new ArrayList<>();

        for(Url current:listFromDb){
            String urlShort=current.getUrl();

            if(urlShort.length()>8 && urlShort.substring(0,8).equalsIgnoreCase("https://")){
                urlShort=urlShort.substring(8);
            }

            if(urlShort.length()>55){
                urlShort=urlShort.substring(0,55);
                urlShort+="...";
            }
            listToShow.add(new Url(current.getTime(),urlShort));
        }
        UrlAdapter urlAdapter=new UrlAdapter();
        urlAdapter.setLista(listToShow);
        urlAdapter.setOnClickListener(new UrlAdapter.UrlAdapterListener() {
            @Override
            public void deleteOnClick(View v, int position) {
                Url deleting=listFromDb.get(position);
                AppDb db= Room.databaseBuilder(getActivity(), AppDb.class,"url").allowMainThreadQueries().build();

                AlertDialogHandler alertDialogHandler=new AlertDialogHandler(getActivity(),"Se eliminará "
                        +deleting.getUrl(),"Aceptar","Cancelar",()->
                {
                    db.urlDAO().delete(deleting);
                    listFromDb.remove(position);
                    listToShow.remove(position);
                    urlAdapter.notifyDataSetChanged();
                },
                        ()->{});
                alertDialogHandler.run();
            }

            @Override
            public void searchAgainOnClick(View v, int position) {
                Url searching=listFromDb.get(position);
                Intent act=new Intent(getActivity(), Search.class);
                act.putExtra("url",searching.getUrl());
                startActivity(act);
            }
        });

        recyclerView.setAdapter(urlAdapter);
        return view;
    }
    public void sort(List<Url> list){
        SimpleDateFormat format= DateFormats.DATE_TIME.getFormat();
        Collections.sort(list,(x, y)-> {
            try {
                return -(format.parse(x.getTime()).compareTo(format.parse(y.getTime())));
            } catch (ParseException e) {
                //nothing
            }
            ;
            return 0;
        });
    }
}
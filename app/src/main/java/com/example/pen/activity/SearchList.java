package com.example.pen.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pen.R;
import com.example.pen.dao.AppDb;
import com.example.pen.dao.UrlDAO;
import com.example.pen.model.Url;
import com.example.pen.service.AlertDialogHandler;
import com.example.pen.service.UrlAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchList extends AppCompatActivity {
    private UrlAdapter urlAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        RecyclerView recyclerView=findViewById(R.id.recycler);
        ImageButton menu=findViewById(R.id.menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AppDb db= Room.databaseBuilder(SearchList.this, AppDb.class,"url").allowMainThreadQueries().build();
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
                AppDb db= Room.databaseBuilder(SearchList.this, AppDb.class,"url").allowMainThreadQueries().build();

                AlertDialogHandler alertDialogHandler=new AlertDialogHandler(SearchList.this,"Se eliminará "
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
                Intent act=new Intent(SearchList.this,Search.class);
                act.putExtra("url",searching.getUrl());
                startActivity(act);
            }
        });

        recyclerView.setAdapter(urlAdapter);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(SearchList.this,MainMenu.class);
                startActivity(in);
            }
        });
    }

    public void sort(List<Url> list){
        SimpleDateFormat format= Url.SIMPLE_DATE_FORMAT;

        Collections.sort(list,(x,y)-> {
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
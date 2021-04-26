package com.example.pen.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.pen.R;
import com.example.pen.dao.AppDb;
import com.example.pen.model.Url;
import com.example.pen.service.UrlAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

public class SearchList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        RecyclerView recyclerView=findViewById(R.id.recycler);
        ImageButton menu=findViewById(R.id.menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AppDb db= Room.databaseBuilder(SearchList.this, AppDb.class,"url").allowMainThreadQueries().build();
        List<Url> list = db.urlDAO().getAll();
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Collections.sort(list,(x,y)-> {
            try {
                return -(format.parse(x.getTime()).compareTo(format.parse(y.getTime())));
            } catch (ParseException e) {
                //nothing
            }
            ;
            return 0;
        });

        UrlAdapter urlAdapter=new UrlAdapter(list);
        recyclerView.setAdapter(urlAdapter);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(SearchList.this,MainMenu.class);
                startActivity(in);
            }
        });
    }
}
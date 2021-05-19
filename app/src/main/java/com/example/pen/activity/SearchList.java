package com.example.pen.activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.example.pen.R;
import com.example.pen.dao.AppDb;
import com.example.pen.fragments.EmptyFragment;
import com.example.pen.fragments.SearchListFragment;
import com.example.pen.model.Url;
import com.example.pen.service.UrlAdapter;
import java.util.List;

public class SearchList extends AppCompatActivity {
    private UrlAdapter urlAdapter;
    private Counter counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);
        AppDb dbCount= Room.databaseBuilder(SearchList.this, AppDb.class,"url").allowMainThreadQueries().build();
        List<Url> lista = dbCount.urlDAO().getAll();
        ImageButton menu=findViewById(R.id.menu);
        counter=new Counter();
        counter.count(lista.size());
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(SearchList.this,MainMenu.class);
                startActivity(in);
            }
        });
    }

    public class Counter{
        public void count(int n){
            if(n>0){
                getSupportFragmentManager().beginTransaction().add(R.id.frame,SearchListFragment.newInstance(counter)).commit();
            }else{
                getSupportFragmentManager().beginTransaction().add(R.id.frame,new EmptyFragment()).commit();
            }
        }
    }
}
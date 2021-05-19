package com.example.pen.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.pen.R;
import com.example.pen.fragments.FragmentApunteSimpleView;
import com.example.pen.model.ApunteKeyValue;
import com.example.pen.model.ApunteSimple;

public class ApunteView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apunte_view);
        ImageButton btn=findViewById(R.id.back8778);
        Bundle b=getIntent().getExtras();
        String fragment=b.getString("fragment");
        if(fragment.equals("simple")){
            ApunteSimple apunte=(ApunteSimple)b.get("apunte");
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame8778, FragmentApunteSimpleView.newInstance(apunte)).commit();
            //llamar el fragment
        }else{
            ApunteKeyValue apunte=(ApunteKeyValue)b.get("apunte");
            //llamar el fragment
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }
}
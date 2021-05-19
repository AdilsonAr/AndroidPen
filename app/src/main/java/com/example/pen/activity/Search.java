package com.example.pen.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pen.R;
import com.example.pen.dao.AppDb;
import com.example.pen.model.Url;
import com.example.pen.service.DateFormats;
import com.example.pen.service.WebViewHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ImageButton back=findViewById(R.id.back8778);
        ImageButton menu =findViewById(R.id.menu);
        ImageButton go = findViewById(R.id.go);
        WebView wv=findViewById(R.id.wv);

        WebViewHandler handler=new WebViewHandler(wv);
        Bundle bundle=getIntent().getExtras();
        handler.getWebView().setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                handler.loadDefault();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                AppDb db= Room.databaseBuilder(Search.this, AppDb.class,"url").allowMainThreadQueries().build();
                if(!url.equals("file:///android_asset/cannotload.html")){
                    Url urlSaved=db.urlDAO().findByUrl(url);
                    SimpleDateFormat format= DateFormats.DATE_TIME.getFormat();
                    if(urlSaved==null){
                        Url urlModel =new Url(format.format(new Date()),url);
                        db.urlDAO().insert(urlModel);
                    }else{
                        urlSaved.setTime(format.format(new Date()));
                        db.urlDAO().update(urlSaved);
                    }
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wv.canGoBack()){
                    wv.goBack();
                }else{
                    Toast.makeText(getApplicationContext(),"No se encontraron más sitios",Toast.LENGTH_SHORT).show();
                }
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act=new Intent(Search.this,MainMenu.class);
                startActivity(act);
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wv.canGoForward()){
                    wv.goForward();
                }else{
                    Toast.makeText(getApplicationContext(),"No se encontraron más sitios",Toast.LENGTH_SHORT).show();
                }
            }
        });

        if(bundle !=null && bundle.getString("url")!=null){
            handler.loadUrl(bundle.getString("url"));
        }else{
            handler.loadUrl("https://www.google.com/");
        }
    }
}
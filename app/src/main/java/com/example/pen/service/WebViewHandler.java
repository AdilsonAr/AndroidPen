package com.example.pen.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.room.Room;

import com.example.pen.activity.MainMenu;
import com.example.pen.dao.AppDb;

public class WebViewHandler {
    private WebView webView;

    public WebView getWebView() {
        return webView;
    }

    public void setWebView(WebView webView) {
        this.webView = webView;
    }

    public WebViewHandler(WebView webView) {
        this.webView = webView;
        configure(webView);
    }

    public void loadDefault(){
        loadUrl("file:///android_asset/cannotload.html");
    }

    public void load(String text) throws Exception{
        this.webView.loadData(text,"text/html", "utf-8");
    }

    public void loadUrl(String url){
        this.webView.loadUrl(url);
    }

    private void configure(WebView wv){
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        wv.setWebChromeClient(new WebChromeClient());
        wv.setWebViewClient(new WebViewClient());
    }
}

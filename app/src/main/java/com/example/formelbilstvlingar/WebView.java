package com.example.formelbilstvlingar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class WebView extends AppCompatActivity {

    private android.webkit.WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        myWebView = findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);

        // Visar lokal html fil
        myWebView.loadUrl("file:///android_asset/about.html");
    }
}
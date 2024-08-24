package com.example.itsadeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebView_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient()); // Ensure links open within the WebView
        webView.getSettings().setJavaScriptEnabled(true); // Enable JavaScript if needed

        String url = getIntent().getStringExtra("url");
        webView.loadUrl(url);
    }
}
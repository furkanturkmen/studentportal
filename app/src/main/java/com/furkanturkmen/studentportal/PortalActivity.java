package com.furkanturkmen.studentportal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PortalActivity extends AppCompatActivity {

    private WebView portalView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal);

        portalView = findViewById(R.id.webView);
        Portal updatedPortal = getIntent().getParcelableExtra(MainActivity.EXTRA_PORTAL);

        portalView.setWebViewClient(new WebViewClient());
        WebSettings settings = portalView.getSettings();
        settings.setJavaScriptEnabled(true);
        portalView.loadUrl(updatedPortal.getUrl());
    }
}

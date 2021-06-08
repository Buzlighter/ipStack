package com.example.ipstack.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ipstack.R;
import com.example.ipstack.data.util.ApiAccess;
import com.example.ipstack.data.util.CustomWebClient;

public class WebViewActivity extends AppCompatActivity {
    private WebView webView;
    private CustomWebClient customWebClient;
    public static final String PREF_TAG = "CacheStorage";
    private SharedPreferences prefUrlStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = (WebView) findViewById(R.id.webview);

        prefUrlStorage = getSharedPreferences(PREF_TAG, Context.MODE_PRIVATE);

        customWebClient = new CustomWebClient(prefUrlStorage);

        configureWebView();

        openWebView();

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void configureWebView() {
        webView.setWebViewClient(customWebClient);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }

    public void openWebView() {
        String savedUrl = prefUrlStorage.getString(PREF_TAG,  null);
        if (savedUrl == null) {
            webView.loadUrl(ApiAccess.YANDEX_URL);
        }
        else {
            webView.loadUrl(savedUrl);
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
package com.example.ipstack.data.util;

import android.content.SharedPreferences;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ipstack.ui.WebViewActivity;

public class CustomWebClient extends WebViewClient {
    private SharedPreferences sharedPreferences;

    public CustomWebClient(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        view.loadUrl(request.getUrl().toString());
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        sharedPreferences.edit().putString(WebViewActivity.PREF_TAG, url).apply();
    }
}

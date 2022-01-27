package com.microfinance.hsmicrofinance.UI.billing;

import android.app.Activity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Controller extends WebViewClient {

    private Activity activity = null;

    public Controller(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        webView.loadUrl(url);
        return true;
    }

}

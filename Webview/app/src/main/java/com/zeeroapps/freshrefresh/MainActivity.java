package com.zeeroapps.freshrefresh;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import im.delight.android.webview.AdvancedWebView;

public class MainActivity extends Activity{

    private WebView mWebView;
    ProgressBar progressBar;

    String loadUrl;
    String email;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor spEdit;
    String SPTAG = "EMAIL";
    private boolean backFlag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(SPTAG, MODE_PRIVATE);
        spEdit = sharedPreferences.edit();

        loadUrl="http://fresh2refresh.com/";

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);

        try{
            mWebView.loadUrl(loadUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
        this.mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                handleURL(url);
                return true;
            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                handleURL(request.getUrl().toString());
                return true;
            }
        });

//        mWebView.getSettings().setSupportMultipleWindows(true);
//        mWebView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, android.os.Message resultMsg)
//            {
//                WebView.HitTestResult result = view.getHitTestResult();
//                String data = result.getExtra();
//                Context context = view.getContext();
//                Intent browserIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(data));
//                startActivity(browserIntent);
//                return false;
//            }
//        });
    }

    public void handleURL(String url){
        if (url.startsWith("tel:") || url.startsWith("geo:") || url.startsWith("mailto:")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return ;
        }else
            mWebView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
//            if (backFlag) {
//                finish();
//                return;
//            }
//            backFlag = true;
//            Snackbar.make(mWebView, "Tap Back again to Exit", Snackbar.LENGTH_LONG).setAction("Exit", new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    finish();
//                }
//            }).show();
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    backFlag = false;
//                }
//            }, 2000);
        }
    }
}

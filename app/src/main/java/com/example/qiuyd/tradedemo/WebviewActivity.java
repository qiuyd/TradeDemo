package com.example.qiuyd.tradedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewActivity extends AppCompatActivity {

    private WebView webView;
    private WebSettings settings;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        Intent intent = getIntent();
        url = intent.getExtras().getString("url");

        webView = (WebView)findViewById(R.id.webview);

        settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_BACK){
                    if(webView.canGoBack()) {
                        webView.goBack();
                        return true;
                    }
                }
                return false;
            }
        });

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
//        webView.loadUrl("file:///android_asset/html/LDJ.html");
//        webView.loadUrl("http://wxadvideo.grandes.com.cn:8089/wuxitong/n111.mp4");
    }


}

package com.example.qiuyd.tradedemo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.qiuyd.tradedemo.R;

public class WuxiPage extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wuxi_page);
        webView = (WebView)findViewById(R.id.wuxi);
    }
}

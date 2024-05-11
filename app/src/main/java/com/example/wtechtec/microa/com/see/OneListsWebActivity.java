package com.example.wtechtec.microa.com.see;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.wtechtec.microa.R;


/**
 * Created by xuetao on 2017/9/13.
 */

public class OneListsWebActivity extends AppCompatActivity {
    private WebView webView;
    private ImageView imageViewback;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.oneweb_layout);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        Intent intent=getIntent();
        webView= (WebView) findViewById(R.id.webview_onelist);
           imageViewback= (ImageView) findViewById(R.id.oneimgback);
        imageViewback.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case  MotionEvent.ACTION_DOWN:

                        imageViewback.setImageResource(R.drawable.backt);
                        break;
                    case MotionEvent.ACTION_UP:
                        imageViewback.setImageResource(R.drawable.back);
                        finish();
                        break;

                }

                return true;
            }
        });
        final String url1=intent.getStringExtra("URL");

       webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub

                return false;
            }
        });
        webView.loadUrl(url1);
    }
}

package com.example.bei_i_bei.everywheretrip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bei_i_bei.everywheretrip.util.AndroidJs;
import com.jaeger.library.StatusBarUtil;

public class HomeWebActivity extends AppCompatActivity {


    //  private AgentWeb magentWeb;
    private LinearLayout mLl;
    /**
     * 详情
     */
    private TextView mShowToolbar;
    private WebView mWeb;
    private Toolbar mToolbarBanmishow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_web);
        initView();
    }

    private void initView() {
        mWeb = (WebView) findViewById(R.id.web);
        StatusBarUtil.setLightMode(this);
        String url = getIntent().getStringExtra("url") + "?os=android";
        final String type = getIntent().getStringExtra("type");
        mLl = (LinearLayout) findViewById(R.id.ll);
        mShowToolbar = (TextView) findViewById(R.id.show_toolbar);
        mToolbarBanmishow = (Toolbar) findViewById(R.id.toolbar_banmishow);

        mToolbarBanmishow.setTitle("");
        mShowToolbar.setText(type);



        mWeb.getSettings().setJavaScriptEnabled(true);
        mWeb.setWebViewClient(new WebViewClient());
        mWeb.loadUrl(url);

        AndroidJs androidJs = new AndroidJs(this);
        mWeb.addJavascriptInterface(androidJs,"android");
    }


}

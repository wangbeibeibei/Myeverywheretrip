package com.example.bei_i_bei.everywheretrip.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bei_i_bei.everywheretrip.R;
import com.example.bei_i_bei.everywheretrip.base.BaseActivity;
import com.example.bei_i_bei.everywheretrip.presenter.EmptyPresenter;
import com.example.bei_i_bei.everywheretrip.view.EmptyView;
import com.jaeger.library.StatusBarUtil;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {

    @BindView(R.id.txt_toolbar)
    TextView txtToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.webview)
    WebView webview;
    private AgentWeb magentWeb;


    public static void startAct(Context context) {
        Intent intent = new Intent(context, WebViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initView() {
        //亮色模式 会将状态栏文字改为黑色
        StatusBarUtil.setLightMode(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!magentWeb.back()) {
                    finish();
                }
            }
        });
        magentWeb = AgentWeb.with(this)
                .setAgentWebParent(ll, new LinearLayout.LayoutParams(-1, -1))
                .closeIndicator()
                .createAgentWeb()
                .ready()
                .go("https://api.banmi.com/app2017/agreement.html");

        /*获取网页标题*/
        magentWeb.getWebCreator().getWebView().setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (!TextUtils.isEmpty(title)) {
                    txtToolbar.setText(title);
                }
                super.onReceivedTitle(view, title);
            }
        });
        String contentURL = getIntent().getStringExtra("contentURL");
        webview.loadUrl(contentURL);
    }

    @Override
    protected void onPause() {
        magentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        magentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        magentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }


}



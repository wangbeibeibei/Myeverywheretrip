package com.example.bei_i_bei.everywheretrip.wxapi;

import android.os.Bundle;


import com.example.bei_i_bei.everywheretrip.R;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

public class WXEntryActivity extends WXCallbackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
    }
}

package com.example.bei_i_bei.everywheretrip;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.example.bei_i_bei.everywheretrip.base.Constants;
import com.example.bei_i_bei.everywheretrip.ui.main.activity.MainActivity;
import com.example.bei_i_bei.everywheretrip.util.SpUtil;
import com.jaeger.library.StatusBarUtil;

public class WeComeActivity extends AppCompatActivity {
    //是否是第一次使用
    private boolean isFirstUse;

    private void guide() {

        //是否是第一次登陆
        SharedPreferences sharedPreferences = this.getSharedPreferences("share", MODE_PRIVATE);
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (isFirstRun) {
            Log.e("debug", "第一次运行");
            editor.putBoolean("isFirstRun", false);
            editor.commit();
            Intent intent = new Intent();
            intent.setClass(this, GuidepageActivity.class);
            startActivity(intent);
        } else {
            Log.e("debug", "不是第一次运行");

            String token = (String) SpUtil.getParam(Constants.TOKEN, "");
            if (TextUtils.isEmpty(token)) {
                Intent intent = new Intent();
                intent.setClass(this, LoginActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent();
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
            }
        }
    }
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_come);
        setTheme(R.style.NoAppTheme);
            SystemClock.sleep(2000);
        initTime();
        initView();

    }

    private void initView() {


    }

    private void initTime() {
        StatusBarUtil.setLightMode(this);
        new Thread(){
            @Override
            public void run() {
                super.run();
                guide();
                finish();
                /*int a=2;
                while (a>=0){
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (a==0){
                        guide();
                        finish();
                    }
                    a--;
                }*/

            }
        }.start();
    }
}

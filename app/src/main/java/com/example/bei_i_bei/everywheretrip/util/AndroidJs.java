package com.example.bei_i_bei.everywheretrip.util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.example.bei_i_bei.everywheretrip.HomeShowActivity;
import com.example.bei_i_bei.everywheretrip.To_viewActivity;
import com.example.bei_i_bei.everywheretrip.ui.main.activity.MainActivity;

import static com.just.agentweb.BaseJsAccessEntrace.TAG;


/**
 * @author xts
 * Created by asus on 2019/5/13.
 * Js调用Android的桥梁类,
 */

public class AndroidJs extends Object {
    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    private  Context context;
    @JavascriptInterface
    public void callAndroid(String msg , int id) {
        if (msg.equals("route_details")){
            Log.i(TAG, "callAndroid: "+id);
            Intent intent = new Intent(context, HomeShowActivity.class);
            intent.putExtra("page",id);
            context.startActivity(intent);
        }

        System.out.println(msg);
    }
    @JavascriptInterface
    public void callAndroid(String msg) {
        if (msg.equals("subject_list")){
            Log.i(TAG, "callAndroid: ");
            Intent intent = new Intent(context, To_viewActivity.class);
            context.startActivity(intent);
        }

        System.out.println(msg);
    }
    public AndroidJs(Context context) {
        this.context = context;
    }
}

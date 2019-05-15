package com.example.bei_i_bei.everywheretrip.view;

import android.app.Activity;

import com.example.bei_i_bei.everywheretrip.base.BaseMvpView;

public interface LoginOrBindView extends BaseMvpView {

    Activity gatAct();
    void go2MainActivity();
    void toastShort(String msg);
    void setData(String code);
}

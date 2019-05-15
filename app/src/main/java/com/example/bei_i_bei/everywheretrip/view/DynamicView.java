package com.example.bei_i_bei.everywheretrip.view;

import com.example.bei_i_bei.everywheretrip.base.BaseMvpView;
import com.example.bei_i_bei.everywheretrip.bean.DynamicBean;

public interface DynamicView extends BaseMvpView {
    void setData(DynamicBean bean);
}

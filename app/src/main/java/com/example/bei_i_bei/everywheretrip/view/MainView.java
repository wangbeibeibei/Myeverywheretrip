package com.example.bei_i_bei.everywheretrip.view;

import com.example.bei_i_bei.everywheretrip.base.BaseMvpView;
import com.example.bei_i_bei.everywheretrip.bean.BalanceBean;
import com.example.bei_i_bei.everywheretrip.bean.InformationBean;

public interface MainView extends BaseMvpView {

    void  setBalance(BalanceBean balance);

    void setInformation(InformationBean information);
}

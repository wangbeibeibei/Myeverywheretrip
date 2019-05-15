package com.example.bei_i_bei.everywheretrip.view;

import com.example.bei_i_bei.everywheretrip.base.BaseMvpView;
import com.example.bei_i_bei.everywheretrip.bean.Cancel_collectionBean;
import com.example.bei_i_bei.everywheretrip.bean.HomeShowBean;

public interface HomeShowView extends BaseMvpView{

    void  setHomeShow(HomeShowBean homeShow);

    void setIsCollected(String s);

    void  setCancel_Collection(Cancel_collectionBean c);
}

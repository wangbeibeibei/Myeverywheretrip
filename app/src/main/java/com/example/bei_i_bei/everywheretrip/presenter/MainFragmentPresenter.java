package com.example.bei_i_bei.everywheretrip.presenter;

import com.example.bei_i_bei.everywheretrip.base.BasePresenter;
import com.example.bei_i_bei.everywheretrip.bean.MainBean;
import com.example.bei_i_bei.everywheretrip.model.MainModel;
import com.example.bei_i_bei.everywheretrip.net.ResultCallBack;
import com.example.bei_i_bei.everywheretrip.view.MainFragmentView;

public class MainFragmentPresenter extends BasePresenter<MainFragmentView> {

    private MainModel mainModel;

    @Override
    protected void initModel() {

        mainModel = new MainModel();
    }

    public void getData(int page) {
        mainModel.getData(page,new ResultCallBack<MainBean>() {
            @Override
            public void onSuccess(MainBean bean) {
                if (bean != null) {
                    if (mMvpView != null) {
                        mMvpView.setData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}

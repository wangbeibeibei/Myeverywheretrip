package com.example.bei_i_bei.everywheretrip.presenter;

import com.example.bei_i_bei.everywheretrip.base.BasePresenter;
import com.example.bei_i_bei.everywheretrip.bean.BalanceBean;
import com.example.bei_i_bei.everywheretrip.bean.InformationBean;
import com.example.bei_i_bei.everywheretrip.model.MainModel;
import com.example.bei_i_bei.everywheretrip.net.ResultCallBack;
import com.example.bei_i_bei.everywheretrip.view.MainView;

public class MainPresenter extends BasePresenter<MainView> {

    private MainModel mainModel;

    @Override
    protected void initModel() {

        mainModel = new MainModel();
        mModels.add(mainModel);
    }

    public void getBalanceBean() {
        mainModel.getBalance(new ResultCallBack<BalanceBean>() {
            @Override
            public void onSuccess(BalanceBean bean) {

                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setBalance(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {


            }
        });

    }

    public void getInformation() {

        mainModel.getgetInformation(new ResultCallBack<InformationBean>() {
            @Override
            public void onSuccess(InformationBean bean) {

                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setInformation(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}

package com.example.bei_i_bei.everywheretrip.presenter;

import android.widget.ImageView;

import com.example.bei_i_bei.everywheretrip.base.BasePresenter;
import com.example.bei_i_bei.everywheretrip.bean.BanMiShowBean;
import com.example.bei_i_bei.everywheretrip.model.BanMiShowModel;
import com.example.bei_i_bei.everywheretrip.net.ResultCallBack;
import com.example.bei_i_bei.everywheretrip.view.BanMiShowView;

public class BanMiShowPresenter extends BasePresenter<BanMiShowView> {

    private BanMiShowModel banMiShowModel;

    @Override
    protected void initModel() {

        banMiShowModel = new BanMiShowModel();
        mModels.add(banMiShowModel);
    }


    public void getBanMiShow(int banmiId) {
        banMiShowModel.getBanMiShow(banmiId,new ResultCallBack<BanMiShowBean>() {
            @Override
            public void onSuccess(BanMiShowBean bean) {
                if (bean!=null){
                    if (mMvpView!=null){
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

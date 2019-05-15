package com.example.bei_i_bei.everywheretrip.presenter;

import com.example.bei_i_bei.everywheretrip.base.BasePresenter;
import com.example.bei_i_bei.everywheretrip.bean.BanMiBean;
import com.example.bei_i_bei.everywheretrip.model.BanMiFragmentModel;
import com.example.bei_i_bei.everywheretrip.net.ResultCallBack;
import com.example.bei_i_bei.everywheretrip.view.BanMiFragmentView;

public class BanMiFragmentPresenter extends BasePresenter<BanMiFragmentView> {

    private BanMiFragmentModel banMiFragmentModel;

    @Override
    protected void initModel() {

        banMiFragmentModel = new BanMiFragmentModel();
        mModels.add(banMiFragmentModel);
    }

    public void getData(int page) {

        banMiFragmentModel.getData(page, new ResultCallBack<BanMiBean>() {
            @Override
            public void onSuccess(BanMiBean  bean) {

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

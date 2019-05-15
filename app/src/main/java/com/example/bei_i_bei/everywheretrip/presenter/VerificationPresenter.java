package com.example.bei_i_bei.everywheretrip.presenter;

import android.widget.Toast;

import com.example.bei_i_bei.everywheretrip.base.BasePresenter;
import com.example.bei_i_bei.everywheretrip.bean.VerifyCodeBean;
import com.example.bei_i_bei.everywheretrip.model.VerificationModel;
import com.example.bei_i_bei.everywheretrip.net.ApiService;
import com.example.bei_i_bei.everywheretrip.net.ResultCallBack;
import com.example.bei_i_bei.everywheretrip.view.VerificationView;

public class VerificationPresenter extends BasePresenter<VerificationView> {

    private VerificationModel verificationModel;

    @Override
    protected void initModel() {

        verificationModel = new VerificationModel();
        mModels.add(verificationModel);
    }

    public void getVerifyCode() {

        verificationModel.getVerifyCode(new ResultCallBack<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean bean) {

                if (bean!=null && bean.getCode() == ApiService.SUCCESS_CODE){
                    if (mMvpView!=null){
                        mMvpView.setData(bean.getData());
                    }
                }else {
                    if (mMvpView!=null){


                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}

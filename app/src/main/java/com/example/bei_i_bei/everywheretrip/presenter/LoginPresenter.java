package com.example.bei_i_bei.everywheretrip.presenter;

import com.example.bei_i_bei.everywheretrip.base.BasePresenter;
import com.example.bei_i_bei.everywheretrip.bean.LoginBean;
import com.example.bei_i_bei.everywheretrip.bean.VerifyCodeBean;
import com.example.bei_i_bei.everywheretrip.model.LoginModel;
import com.example.bei_i_bei.everywheretrip.net.ResultCallBack;
import com.example.bei_i_bei.everywheretrip.view.LoginView;

public class LoginPresenter extends BasePresenter<LoginView> {

    private LoginModel loginModel;

    @Override
    protected void initModel() {

        loginModel = new LoginModel();
        mModels.add(loginModel);
    }

    public void getVerifyCode() {
     loginModel.getVerifyCode(new ResultCallBack<VerifyCodeBean>() {
         @Override
         public void onSuccess(VerifyCodeBean bean) {

         }

         @Override
         public void onFail(String msg) {

         }
     });
    }
}

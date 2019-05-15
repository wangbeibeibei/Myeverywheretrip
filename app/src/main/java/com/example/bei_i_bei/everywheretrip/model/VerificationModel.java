package com.example.bei_i_bei.everywheretrip.model;

import com.example.bei_i_bei.everywheretrip.base.BaseModel;
import com.example.bei_i_bei.everywheretrip.bean.LoginBean;
import com.example.bei_i_bei.everywheretrip.bean.VerifyCodeBean;
import com.example.bei_i_bei.everywheretrip.net.ApiService;
import com.example.bei_i_bei.everywheretrip.net.BaseObserver;
import com.example.bei_i_bei.everywheretrip.net.HttpUtils;
import com.example.bei_i_bei.everywheretrip.net.ResultCallBack;
import com.example.bei_i_bei.everywheretrip.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class VerificationModel extends BaseModel {
    public void getVerifyCode(final ResultCallBack<VerifyCodeBean> resultCallBack) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.sBaseUrl, ApiService.class);
        Observable<VerifyCodeBean> verifyCode = apiserver.getVerifyCode();
        verifyCode.compose(RxUtils.<VerifyCodeBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<VerifyCodeBean>() {
                    @Override
                    public void onNext(VerifyCodeBean verifyCodeBean) {
                        resultCallBack.onSuccess(verifyCodeBean);
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });

    }
}

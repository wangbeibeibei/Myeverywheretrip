package com.example.bei_i_bei.everywheretrip.model;

import com.example.bei_i_bei.everywheretrip.base.BaseModel;
import com.example.bei_i_bei.everywheretrip.bean.BanMiBean;
import com.example.bei_i_bei.everywheretrip.bean.BanMiShowBean;
import com.example.bei_i_bei.everywheretrip.net.ApiService;
import com.example.bei_i_bei.everywheretrip.net.BaseObserver;
import com.example.bei_i_bei.everywheretrip.net.HttpUtils;
import com.example.bei_i_bei.everywheretrip.net.ResultCallBack;
import com.example.bei_i_bei.everywheretrip.net.RxUtils;

import io.reactivex.disposables.Disposable;

public class BanMiShowModel extends BaseModel {
    public void getBanMiShow(int banmiId, final ResultCallBack<BanMiShowBean> resultCallBack) {

        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.BanMi, ApiService.class);
        apiserver.getBanMishow(banmiId).compose(RxUtils.<BanMiShowBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<BanMiShowBean>() {
                    @Override
                    public void onNext(BanMiShowBean banMiShowBean) {

                        if (banMiShowBean!=null){
                            resultCallBack.onSuccess(banMiShowBean);
                        }else {
                            resultCallBack.onFail("错误");
                        }
                    }

                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {

                        addDisposable(d);
                    }
                });

    }
}

package com.example.bei_i_bei.everywheretrip.model;

import com.example.bei_i_bei.everywheretrip.base.BaseModel;
import com.example.bei_i_bei.everywheretrip.bean.DynamicBean;
import com.example.bei_i_bei.everywheretrip.net.ApiService;
import com.example.bei_i_bei.everywheretrip.net.BaseObserver;
import com.example.bei_i_bei.everywheretrip.net.HttpUtils;
import com.example.bei_i_bei.everywheretrip.net.ResultCallBack;
import com.example.bei_i_bei.everywheretrip.net.RxUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DynamicModel extends BaseModel {

    public void getData(int banmiId, int page, final ResultCallBack<DynamicBean> resultCallBack) {

        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.BanMi, ApiService.class);
        apiserver.getDynamic(banmiId,page).compose(RxUtils.<DynamicBean>rxObserableSchedulerHelper())
                .subscribe(new Observer<DynamicBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(DynamicBean dynamicBean) {

                        if (dynamicBean!=null){
                            resultCallBack.onSuccess(dynamicBean);
                        }else {
                            resultCallBack.onFail("错误");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        resultCallBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

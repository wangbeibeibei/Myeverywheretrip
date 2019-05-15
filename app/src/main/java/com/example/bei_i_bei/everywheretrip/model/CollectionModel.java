package com.example.bei_i_bei.everywheretrip.model;

import com.example.bei_i_bei.everywheretrip.base.BaseModel;
import com.example.bei_i_bei.everywheretrip.bean.CollectionBean;
import com.example.bei_i_bei.everywheretrip.net.ApiService;
import com.example.bei_i_bei.everywheretrip.net.BaseObserver;
import com.example.bei_i_bei.everywheretrip.net.HttpUtils;
import com.example.bei_i_bei.everywheretrip.net.ResultCallBack;
import com.example.bei_i_bei.everywheretrip.net.RxUtils;

import io.reactivex.disposables.Disposable;

public class CollectionModel extends BaseModel {
    public void getCollection(final ResultCallBack<CollectionBean> resultCallBack) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.MainUtl, ApiService.class);
        apiserver.getCollection().compose(RxUtils.<CollectionBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CollectionBean>() {
                    @Override
                    public void onNext(CollectionBean collectionBean) {

                        if (collectionBean != null) {
                            resultCallBack.onSuccess(collectionBean);
                        } else {
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
                })
        ;
    }
}

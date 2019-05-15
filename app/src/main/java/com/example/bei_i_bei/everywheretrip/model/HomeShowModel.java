package com.example.bei_i_bei.everywheretrip.model;

import com.example.bei_i_bei.everywheretrip.base.BaseModel;
import com.example.bei_i_bei.everywheretrip.bean.Cancel_collectionBean;
import com.example.bei_i_bei.everywheretrip.bean.HomeShowBean;
import com.example.bei_i_bei.everywheretrip.net.ApiService;
import com.example.bei_i_bei.everywheretrip.net.BaseObserver;
import com.example.bei_i_bei.everywheretrip.net.HttpUtils;
import com.example.bei_i_bei.everywheretrip.net.ResultCallBack;
import com.example.bei_i_bei.everywheretrip.net.RxUtils;
import com.example.bei_i_bei.everywheretrip.util.ToastUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class HomeShowModel extends BaseModel {
    public void getHomeShow(int page, final ResultCallBack<HomeShowBean> resultCallBack) {


        ApiService service = HttpUtils.getInstance().getApiserver(ApiService.MainUtl, ApiService.class);

        Observable<HomeShowBean> homeShow = service.getHomeShow(page);
        homeShow.compose(RxUtils.<HomeShowBean>rxObserableSchedulerHelper())
                .subscribe(new Observer<HomeShowBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(HomeShowBean homeShowBean) {

                        if (homeShowBean != null) {
                            resultCallBack.onSuccess(homeShowBean);
                        } else {
                            resultCallBack.onFail("错误");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    public void getCollected(int page, String token, final ResultCallBack<ResponseBody> resultCallBack) {

        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.MainUtl, ApiService.class);
        apiserver.getIsCollected(page, token).compose(RxUtils.<ResponseBody>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {

                        if (responseBody != null) {
                            resultCallBack.onSuccess(responseBody);
                        } else {
                            resultCallBack.onFail("错误");
                        }
                    }

                    @Override
                    public void error(String msg) {
                        ToastUtil.showShort(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });

    }

    public void getCancle_Collection(int page, final ResultCallBack<Cancel_collectionBean> resultCallBack) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.MainUtl, ApiService.class);
        apiserver.getCancel_colleaction(page).compose(RxUtils.<Cancel_collectionBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<Cancel_collectionBean>() {
                    @Override
                    public void onNext(Cancel_collectionBean cancel_collectionBean) {

                        if (cancel_collectionBean!=null){
                            resultCallBack.onSuccess(cancel_collectionBean);
                        }else {
                            resultCallBack.onFail("错误");
                        }
                    }

                    @Override
                    public void error(String msg) {
                      ToastUtil.showShort(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });


    }
}


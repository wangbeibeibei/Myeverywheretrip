package com.example.bei_i_bei.everywheretrip.model;

import com.example.bei_i_bei.everywheretrip.base.BaseModel;
import com.example.bei_i_bei.everywheretrip.bean.RouteBean;
import com.example.bei_i_bei.everywheretrip.net.ApiService;
import com.example.bei_i_bei.everywheretrip.net.BaseObserver;
import com.example.bei_i_bei.everywheretrip.net.HttpUtils;
import com.example.bei_i_bei.everywheretrip.net.ResultCallBack;
import com.example.bei_i_bei.everywheretrip.net.RxUtils;

import io.reactivex.disposables.Disposable;

public class RouteModel extends BaseModel {
    public void getRoute(int page, final ResultCallBack<RouteBean> resultCallBack) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.BanMi, ApiService.class);
        apiserver.getRoute(page).compose(RxUtils.<RouteBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<RouteBean>() {
                    @Override
                    public void onNext(RouteBean routeBean) {

                        if (routeBean!=null){
                            resultCallBack.onSuccess(routeBean);
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

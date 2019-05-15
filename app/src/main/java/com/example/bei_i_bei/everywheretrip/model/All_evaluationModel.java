package com.example.bei_i_bei.everywheretrip.model;

import com.example.bei_i_bei.everywheretrip.base.BaseModel;
import com.example.bei_i_bei.everywheretrip.bean.All_evaluationBean;
import com.example.bei_i_bei.everywheretrip.net.ApiService;
import com.example.bei_i_bei.everywheretrip.net.BaseObserver;
import com.example.bei_i_bei.everywheretrip.net.HttpUtils;
import com.example.bei_i_bei.everywheretrip.net.ResultCallBack;
import com.example.bei_i_bei.everywheretrip.net.RxUtils;

import io.reactivex.disposables.Disposable;

public class All_evaluationModel extends BaseModel {
    public void getData(int routeId, int cid, final ResultCallBack<All_evaluationBean> resultCallBack) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.MainUtl, ApiService.class);
        apiserver.getAll_evaluation(routeId, cid).compose(RxUtils.<All_evaluationBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<All_evaluationBean>() {
                    @Override
                    public void onNext(All_evaluationBean all_evaluationBean) {

                        if (all_evaluationBean != null) {
                            resultCallBack.onSuccess(all_evaluationBean);
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
                });
    }
}

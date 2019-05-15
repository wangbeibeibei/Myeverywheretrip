package com.example.bei_i_bei.everywheretrip.model;

import com.example.bei_i_bei.everywheretrip.base.BaseModel;
import com.example.bei_i_bei.everywheretrip.bean.BalanceBean;
import com.example.bei_i_bei.everywheretrip.bean.InformationBean;
import com.example.bei_i_bei.everywheretrip.bean.MainBean;
import com.example.bei_i_bei.everywheretrip.net.ApiService;
import com.example.bei_i_bei.everywheretrip.net.HttpUtils;
import com.example.bei_i_bei.everywheretrip.net.ResultCallBack;
import com.example.bei_i_bei.everywheretrip.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainModel extends BaseModel {
    public void getData(int page, final ResultCallBack<MainBean> resultCallBack) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.MainUtl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Observable<MainBean> mainData = apiService.getMainData(page);
        mainData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MainBean mainBean) {

                        resultCallBack.onSuccess(mainBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getBalance(final ResultCallBack<BalanceBean> resultCallBack) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.BanMi, ApiService.class);
        apiserver.getBalance()
                .compose(RxUtils.<BalanceBean>rxObserableSchedulerHelper())
                .subscribe(new Observer<BalanceBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(BalanceBean balanceBean) {

                        if (balanceBean!=null){
                            resultCallBack.onSuccess(balanceBean);
                        }else {
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

    public void getgetInformation(final ResultCallBack<InformationBean> resultCallBack) {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.MainUtl)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<InformationBean> info = apiService.getInfo();
        info.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InformationBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(InformationBean informationBean) {

                        if (informationBean!=null){
                            resultCallBack.onSuccess(informationBean);
                        }else {
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
}

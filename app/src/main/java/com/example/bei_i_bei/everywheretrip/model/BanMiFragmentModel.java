package com.example.bei_i_bei.everywheretrip.model;

import com.example.bei_i_bei.everywheretrip.base.BaseModel;
import com.example.bei_i_bei.everywheretrip.bean.BanMiBean;
import com.example.bei_i_bei.everywheretrip.net.ApiService;
import com.example.bei_i_bei.everywheretrip.net.ResultCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BanMiFragmentModel extends BaseModel {

    public void getData(int page, final ResultCallBack<BanMiBean> resultCallBack) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BanMi)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<BanMiBean> banmi = apiService.getBanmi(page);
        banmi.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BanMiBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BanMiBean banMiBean) {

                        resultCallBack.onSuccess(banMiBean);
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

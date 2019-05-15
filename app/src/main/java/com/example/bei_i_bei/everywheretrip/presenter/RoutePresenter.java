package com.example.bei_i_bei.everywheretrip.presenter;

import com.example.bei_i_bei.everywheretrip.base.BasePresenter;
import com.example.bei_i_bei.everywheretrip.bean.RouteBean;
import com.example.bei_i_bei.everywheretrip.model.RouteModel;
import com.example.bei_i_bei.everywheretrip.net.ResultCallBack;
import com.example.bei_i_bei.everywheretrip.util.ToastUtil;
import com.example.bei_i_bei.everywheretrip.view.RouteView;

public class RoutePresenter extends BasePresenter<RouteView> {

    private RouteModel routeModel;

    @Override
    protected void initModel() {

        routeModel = new RouteModel();
        mModels.add(routeModel);
    }

    public void getRoute(int page) {
        routeModel.getRoute( page, new ResultCallBack<RouteBean>() {
            @Override
            public void onSuccess(RouteBean bean) {

                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

                ToastUtil.showShort(msg);
            }
        });
    }
}

package com.example.bei_i_bei.everywheretrip.presenter;

import com.example.bei_i_bei.everywheretrip.base.BasePresenter;
import com.example.bei_i_bei.everywheretrip.bean.All_evaluationBean;
import com.example.bei_i_bei.everywheretrip.model.All_evaluationModel;
import com.example.bei_i_bei.everywheretrip.net.ResultCallBack;
import com.example.bei_i_bei.everywheretrip.util.ToastUtil;
import com.example.bei_i_bei.everywheretrip.view.All_evaluationView;

public class All_evaluationPresenter extends BasePresenter<All_evaluationView> {

    private All_evaluationModel all_evaluationModel;

    @Override
    protected void initModel() {

        all_evaluationModel = new All_evaluationModel();
        mModels.add(all_evaluationModel);
    }

    public void getData(int routeId, int cid) {
        all_evaluationModel.getData(routeId,cid, new ResultCallBack<All_evaluationBean>() {
            @Override
            public void onSuccess(All_evaluationBean bean) {

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

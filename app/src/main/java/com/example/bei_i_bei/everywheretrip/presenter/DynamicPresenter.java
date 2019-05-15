package com.example.bei_i_bei.everywheretrip.presenter;

import com.example.bei_i_bei.everywheretrip.base.BasePresenter;
import com.example.bei_i_bei.everywheretrip.bean.DynamicBean;
import com.example.bei_i_bei.everywheretrip.model.DynamicModel;
import com.example.bei_i_bei.everywheretrip.net.ResultCallBack;
import com.example.bei_i_bei.everywheretrip.util.ToastUtil;
import com.example.bei_i_bei.everywheretrip.view.DynamicView;

public class DynamicPresenter extends BasePresenter<DynamicView> {

    private DynamicModel dynamicModel;

    @Override
    protected void initModel() {

        dynamicModel = new DynamicModel();
        mModels.add(dynamicModel);
    }

    public void getData(int banmiId,int page ) {
        dynamicModel.getData(banmiId ,page, new ResultCallBack<DynamicBean>() {
            @Override
            public void onSuccess(DynamicBean bean) {
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

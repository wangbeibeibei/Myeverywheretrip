package com.example.bei_i_bei.everywheretrip.presenter;

import com.example.bei_i_bei.everywheretrip.base.BasePresenter;
import com.example.bei_i_bei.everywheretrip.bean.CollectionBean;
import com.example.bei_i_bei.everywheretrip.model.CollectionModel;
import com.example.bei_i_bei.everywheretrip.net.ResultCallBack;
import com.example.bei_i_bei.everywheretrip.view.CollectionView;

public class CollectionPresenter extends BasePresenter<CollectionView> {

    private CollectionModel collectionModel;

    @Override
    protected void initModel() {

        collectionModel = new CollectionModel();
        mModels.add(collectionModel);
    }

    public void getCollection() {

        collectionModel.getCollection(new ResultCallBack<CollectionBean>() {
            @Override
            public void onSuccess(CollectionBean bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}

package com.example.bei_i_bei.everywheretrip.presenter;

import com.example.bei_i_bei.everywheretrip.base.BasePresenter;
import com.example.bei_i_bei.everywheretrip.bean.Cancel_collectionBean;
import com.example.bei_i_bei.everywheretrip.bean.HomeShowBean;
import com.example.bei_i_bei.everywheretrip.model.HomeShowModel;
import com.example.bei_i_bei.everywheretrip.net.ResultCallBack;
import com.example.bei_i_bei.everywheretrip.util.ToastUtil;
import com.example.bei_i_bei.everywheretrip.view.HomeShowView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;

public class HomeShowPresenter extends BasePresenter<HomeShowView> {

    private HomeShowModel homeShowModel;

    @Override
    protected void initModel() {

        homeShowModel = new HomeShowModel();
        mModels.add(homeShowModel);
    }

    public void getHomeShow(int page) {
        homeShowModel.getHomeShow(page, new ResultCallBack<HomeShowBean>() {
            @Override
            public void onSuccess(HomeShowBean bean) {
                if (bean != null) {
                    if (mMvpView != null) {
                        mMvpView.setHomeShow(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                ToastUtil.showShort(msg);
            }
        });
    }

    public void getIsCollected(int page, String token) {
        homeShowModel.getCollected(page, token, new ResultCallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody bean) {


                    try {
                        String string = bean.string();
                        JSONObject jsonObject = new JSONObject(string);
                        String s = jsonObject.getString("desc");
                        if (mMvpView != null) {
                            mMvpView.setIsCollected(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


            }

            @Override
            public void onFail(String msg) {

            }
        });

    }

    public void getCancel_Collection(int page) {
        homeShowModel.getCancle_Collection(page,new ResultCallBack<Cancel_collectionBean>() {
            @Override
            public void onSuccess(Cancel_collectionBean bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setCancel_Collection(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}

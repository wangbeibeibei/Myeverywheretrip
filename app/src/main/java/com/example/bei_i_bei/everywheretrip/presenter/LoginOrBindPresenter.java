package com.example.bei_i_bei.everywheretrip.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.bei_i_bei.everywheretrip.R;
import com.example.bei_i_bei.everywheretrip.base.BaseApp;
import com.example.bei_i_bei.everywheretrip.base.BasePresenter;
import com.example.bei_i_bei.everywheretrip.base.Constants;
import com.example.bei_i_bei.everywheretrip.bean.LoginInfo;
import com.example.bei_i_bei.everywheretrip.bean.VerifyCodeBean;
import com.example.bei_i_bei.everywheretrip.model.LoginOrBindMolde;
import com.example.bei_i_bei.everywheretrip.net.ApiService;
import com.example.bei_i_bei.everywheretrip.net.ResultCallBack;
import com.example.bei_i_bei.everywheretrip.util.SpUtil;
import com.example.bei_i_bei.everywheretrip.view.LoginOrBindView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class LoginOrBindPresenter extends BasePresenter<LoginOrBindView> {

    private LoginOrBindMolde loginOrBindMolde;

    @Override
    protected void initModel() {

        loginOrBindMolde = new LoginOrBindMolde();
        mModels.add(loginOrBindMolde);
        loginOrBindMolde=new LoginOrBindMolde();
        mModels.add(loginOrBindMolde);
    }

    public void oauthLogin(SHARE_MEDIA type) {

        UMShareAPI umShareAPI = UMShareAPI.get(mMvpView.gatAct());
        umShareAPI.deleteOauth(mMvpView.gatAct(),SHARE_MEDIA.SINA,umAuthListener);
        umShareAPI.getPlatformInfo(mMvpView.gatAct(), type, umAuthListener);
    }

    UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            if (map != null){
                logMap(map);
                if (share_media == SHARE_MEDIA.SINA) {
                    loginSina(map.get("uid"));
                }
            }
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {

        }
    };

    private void loginSina(String uid) {
        loginOrBindMolde.loginSina(uid, new ResultCallBack<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo bean) {
                /*
                 * 登陆成功跳转主页面，保存用户信息
                 * */
                if (bean.getResult() != null) {
                    saveUserInfo(bean.getResult());
                    if (mMvpView != null) {
                        mMvpView.toastShort(BaseApp.getRes().getString(R.string.login_success));
                        mMvpView.go2MainActivity();
                    }
                } else {
                    if (mMvpView != null) {
                        mMvpView.toastShort(BaseApp.getRes().getString(R.string.login_fail));
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                if (mMvpView != null) {
                    mMvpView.toastShort(msg);
                }
            }
        });
    }

    private void saveUserInfo(LoginInfo.ResultBean result) {
        SpUtil.setParam(Constants.TOKEN, result.getToken());
        SpUtil.setParam(Constants.DESC, result.getDescription());
        SpUtil.setParam(Constants.USERNAME, result.getUserName());
        SpUtil.setParam(Constants.GENDER, result.getGender());
        SpUtil.setParam(Constants.EMAIL, result.getEmail());
        SpUtil.setParam(Constants.PHOTO, result.getPhoto());
        SpUtil.setParam(Constants.PHONE, result.getPhone());

    }

    private void logMap(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Log.i("logMap","logMap"+entry.getKey() + "," + entry.getValue());
        }
    }

    public void getVerifyCode() {
        loginOrBindMolde.getVerifyCode(new ResultCallBack<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean bean) {

                if (bean != null && bean.getCode() == ApiService.SUCCESS_CODE){
                    if (mMvpView != null){
                        mMvpView.setData(bean.getData());
                    }
                }else {
                    if (mMvpView != null){
                        mMvpView.toastShort(BaseApp.getRes().getString(R.string.get_verify_fail));
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}

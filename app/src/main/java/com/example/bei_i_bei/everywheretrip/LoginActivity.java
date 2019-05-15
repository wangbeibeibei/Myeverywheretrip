package com.example.bei_i_bei.everywheretrip;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;

import com.example.bei_i_bei.everywheretrip.base.BaseActivity;
import com.example.bei_i_bei.everywheretrip.base.Constants;
import com.example.bei_i_bei.everywheretrip.bean.LoginBean;
import com.example.bei_i_bei.everywheretrip.bean.VerifyCodeBean;
import com.example.bei_i_bei.everywheretrip.presenter.LoginPresenter;
import com.example.bei_i_bei.everywheretrip.ui.main.fragment.LoginOrBindFragment;
import com.example.bei_i_bei.everywheretrip.ui.main.fragment.VerificationFragment;
import com.example.bei_i_bei.everywheretrip.util.Tools;
import com.example.bei_i_bei.everywheretrip.view.LoginView;
import com.umeng.socialize.UMShareAPI;


public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {


    public static final int TYPE_LOGIN = 0;
    public static final int TYPE_BIND = 1;
    public static String TAG="LoginActivity";
    private int mType;

    public static void startAct(Context context, int type) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(Constants.TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void showLoading() {

        mPresenter.getVerifyCode();
    }

    @Override
    protected void initView() {
        Tools.addShortcut(this, R.drawable.umeng_socialize_qq, this);
        getIntentData();
        FragmentManager manager = getSupportFragmentManager();
        LoginOrBindFragment fragment = LoginOrBindFragment.newIntance(mType);
        manager.beginTransaction().add(R.id.frame, fragment,LoginActivity.TAG).commit();

    }



    @Override
    public void hideLoading() {
        showLoading();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }



    public void getIntentData() {
        mType = getIntent().getIntExtra(Constants.TYPE, TYPE_LOGIN);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //内存泄漏解决方案
        UMShareAPI.get(this).release();
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        new AlertDialog.Builder(this)
                .setMessage("是否退出应用")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("否", null)
                .show();
    }
}

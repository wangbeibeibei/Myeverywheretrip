package com.example.bei_i_bei.everywheretrip.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bei_i_bei.everywheretrip.LoginActivity;
import com.example.bei_i_bei.everywheretrip.R;
import com.example.bei_i_bei.everywheretrip.base.BaseApp;
import com.example.bei_i_bei.everywheretrip.base.BaseFragment;
import com.example.bei_i_bei.everywheretrip.base.Constants;
import com.example.bei_i_bei.everywheretrip.presenter.VerificationPresenter;
import com.example.bei_i_bei.everywheretrip.ui.main.activity.MainActivity;
import com.example.bei_i_bei.everywheretrip.util.Logger;
import com.example.bei_i_bei.everywheretrip.view.VerificationView;
import com.example.bei_i_bei.everywheretrip.widget.IdentifyingCodeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class VerificationFragment extends BaseFragment<VerificationView, VerificationPresenter> implements VerificationView {


    @BindView(R.id.finlish_img)
    ImageView finlishImg;
    @BindView(R.id.to_resend)
    TextView toResend;
    @BindView(R.id.verification_code)
    TextView verificationCode;
    @BindView(R.id.icv)
    IdentifyingCodeView icv;
    @BindView(R.id.tv_wait)
    TextView tvWait;
    private int mTime;


    public  static  VerificationFragment newIntance(String code){
        VerificationFragment verificationFragment = new VerificationFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.VERIFY_CODE,code);
        verificationFragment.setArguments(bundle);
        return verificationFragment;
    }

    @Override
    protected VerificationPresenter initPresenter() {
        return new VerificationPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_verification;
    }

    @OnClick({R.id.finlish_img, R.id.to_resend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finlish_img:
                pop();
                break;
            case R.id.to_resend:
                if (mTime == 0) {
                    mPresenter.getVerifyCode();
                    //重新发送倒计时
                    LoginOrBindFragment fragment = (LoginOrBindFragment) getActivity().getSupportFragmentManager().findFragmentByTag(LoginActivity.TAG);
                    fragment.countDown();
                }
                break;
        }
    }

    private void pop() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        //获取回退栈中碎片的数量

        //弹栈
        manager.popBackStack();

    }

    @Override
    protected void initData() {
        mPresenter.getVerifyCode();
    }

    @Override
    public void setData(String data) {

        if (!TextUtils.isEmpty(data)) {
            tvWait.setText(BaseApp.getRes().getString(R.string.verify_code) + data);
        }
    }

    @Override
    protected void initListener() {
        icv.setOnEditorActionListener(new IdentifyingCodeView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }

            @Override
            public void onTextChanged(String s) {
                autoLogin();
            }
        });
    }

    private void autoLogin() {
//        Logger.logD(icv.getTextContent("tag",icv.getTextContent()));
        if (icv.getTextContent().length() >= 4) {
            //自动登录
            Toast.makeText(getContext(), "自动登录", Toast.LENGTH_SHORT).show();
            icv.setBackgroundEnter(false);
            tvWait.setText(BaseApp.getRes().getString(R.string.wait_please));
            showLoading();
            startActivity(new Intent(getContext(), MainActivity.class));
        }
    }

    public void setCountDownTime(int time) {
        mTime = time;
        if (toResend != null) {
            if (time != 0) {
                String format = String.format(getResources().getString(R.string.send_again) + "(%ss)", time);
                toResend.setText(format);
                toResend.setTextColor(getResources().getColor(R.color.c_999999));
            } else {
                toResend.setText(getResources().getString(R.string.send_again));
                toResend.setTextColor(getResources().getColor(R.color.c_fa6a13));
            }
        }
    }

    @Override
    protected void initView() {
        String code = getArguments().getString(Constants.VERIFY_CODE);
        setData(code);
    }
}

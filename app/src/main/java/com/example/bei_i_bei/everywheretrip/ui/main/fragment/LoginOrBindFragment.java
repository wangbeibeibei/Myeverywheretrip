package com.example.bei_i_bei.everywheretrip.ui.main.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bei_i_bei.everywheretrip.LoginActivity;
import com.example.bei_i_bei.everywheretrip.R;
import com.example.bei_i_bei.everywheretrip.base.BaseFragment;
import com.example.bei_i_bei.everywheretrip.base.Constants;
import com.example.bei_i_bei.everywheretrip.presenter.LoginOrBindPresenter;
import com.example.bei_i_bei.everywheretrip.ui.main.activity.MainActivity;
import com.example.bei_i_bei.everywheretrip.ui.main.activity.WebViewActivity;
import com.example.bei_i_bei.everywheretrip.util.Tools;
import com.example.bei_i_bei.everywheretrip.view.LoginOrBindView;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginOrBindFragment extends BaseFragment<LoginOrBindView, LoginOrBindPresenter> implements LoginOrBindView {


    @BindView(R.id.phone_sum)
    TextView phoneSum;
    @BindView(R.id.select_img)
    ImageView selectImg;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.btn_yz)
    Button btnYz;
    @BindView(R.id.wechat_img)
    ImageView wechatImg;
    @BindView(R.id.weibo)
    ImageView weibo;
    @BindView(R.id.qq)
    ImageView qq;
    @BindView(R.id.login_regist)
    TextView loginRegist;
    @BindView(R.id.img_tofinish)
    ImageView imgTofinish;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.ll_or)
    LinearLayout llOr;
    @BindView(R.id.share_ll)
    LinearLayout shareLl;
    @BindView(R.id.wechat_login)
    TextView wechatLogin;
 /*   @BindView(R.id.security_account)
    TextView securityAccount;*/
 /*   @BindView(R.id.binding)
    TextView binding;*/

    //验证码
    private String mVerifyCode = "";
    private static int COUNT_DOWN_TIME = 10;
    private int mTime = COUNT_DOWN_TIME;
    private Handler mHandler;
    private int mType;
    private VerificationFragment verificationFragment;

    public static LoginOrBindFragment newIntance(int type){
        LoginOrBindFragment fragment = new LoginOrBindFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.TYPE,type);
        fragment.setArguments(bundle);
        return fragment;
    }
    private InputMethodManager imm;

    private void show_User_Reply(String hint) {
        etPhone.setHint(hint);
        etPhone.setFocusable(true);//获取焦点
        etPhone.requestFocus();
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
    }
    public LoginOrBindFragment() {
        // Required empty public constructor
    }

    @Override
    protected LoginOrBindPresenter initPresenter() {
        return new LoginOrBindPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }


    @OnClick({R.id.select_img, R.id.et_phone, R.id.btn_yz, R.id.wechat_img, R.id.weibo, R.id.qq,R.id.img_tofinish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_tofinish:
                getActivity().finish();
                break;
            case R.id.select_img:
                break;
            case R.id.et_phone:

                break;
            case R.id.btn_yz:
                Toast.makeText(getContext(), "请输入手机号", Toast.LENGTH_SHORT).show();
                getVerifyCode();
                addVerifyFragment();
                time();
                break;
            case R.id.wechat_img:
                mPresenter.oauthLogin(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.weibo:
               /* Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);*/
                mPresenter.oauthLogin(SHARE_MEDIA.SINA);
                break;
            case R.id.qq:
                mPresenter.oauthLogin(SHARE_MEDIA.QQ);
                break;
        }
    }

    private void time() {
        //避免多次执行倒计时
        if (mTime>0 && mTime<COUNT_DOWN_TIME){
            return;
        }
        countDown();

    }

    /*倒计时，如果执行中，不要再调用*/
    public void countDown() {
        if (mHandler==null) {
         mHandler=new Handler();
        }
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    /*为了避免倒计时负数*/
                    if (mTime<=0){
                        mTime=COUNT_DOWN_TIME;
                        return;
                    }
                    mTime--;
                    if (verificationFragment!=null){
                        verificationFragment.setCountDownTime(mTime);
                    }
                    countDown();
                }
            },1000);
    }

    private void getVerifyCode() {
        if (mTime>0 && mTime<COUNT_DOWN_TIME-1){
            return;
        }
        //给验证码设置空值
        mVerifyCode="";
        mPresenter.getVerifyCode();
    }

    private void addVerifyFragment() {
        if (TextUtils.isEmpty(etPhone.getText().toString().trim())) {
            return;
        }
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
       //添加到回退栈
        fragmentTransaction.addToBackStack(null);
        verificationFragment=VerificationFragment.newIntance(mVerifyCode);
        fragmentTransaction.add(R.id.frame, verificationFragment).commit();
    //关闭软键盘
        Tools.closeKeyBoard(getActivity());
    }


    @Override
    protected void initListener() {
        etPhone.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    /*隐藏软键盘*/
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    etPhone.setHint("请输入手机号");
                    return true;
                }
                return false;
            }
        });
        //文本发生改变监听
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                switchBtnState(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 根据输入框中是否有内容,切换发送验证码的背景
     *
     * @param s
     */
    private void switchBtnState(CharSequence s) {
        if (TextUtils.isEmpty(s)) {
            btnYz.setBackgroundResource(R.drawable.bg_btn_ea_r15);
        } else {
            btnYz.setBackgroundResource(R.drawable.bg_btn_fa6a13_r15);
        }
    }


    @Override
    protected void initView() {
        getArgumentsData();
        setProtocol();
        showOrHideView();
    }


    private void showOrHideView() {
        if (mType == LoginActivity.TYPE_LOGIN) {
            imgTofinish.setVisibility(View.INVISIBLE);
            shareLl.setVisibility(View.VISIBLE);
            llOr.setVisibility(View.VISIBLE);
         /*   securityAccount.setVisibility(View.VISIBLE);
            binding.setVisibility(View.VISIBLE);*/
        }else {
            imgTofinish.setVisibility(View.VISIBLE);
            shareLl.setVisibility(View.GONE);
            llOr.setVisibility(View.GONE);
         /*   securityAccount.setVisibility(View.GONE);
            binding.setVisibility(View.GONE);*/

        }

    }
    private void getArgumentsData() {
        Bundle arguments = getArguments();
        mType = arguments.getInt(Constants.TYPE);
    }

    private void setProtocol() {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(getResources().getString(R.string.agree_protocol));
        /*点击事件*/
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(getContext(), "~~", Toast.LENGTH_SHORT).show();
                WebViewActivity.startAct(getActivity());
            }
        };
        stringBuilder.setSpan(clickableSpan, 12, 16, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //下划线
        UnderlineSpan underlineSpan = new UnderlineSpan();
        stringBuilder.setSpan(underlineSpan, 12, 16, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //前景色
        ForegroundColorSpan what = new ForegroundColorSpan(getResources().getColor(R.color.c_fa6a13));
        stringBuilder.setSpan(what, 12, 16, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        /*必须设置这个clickableSpan 才会有效*/
        loginRegist.setMovementMethod(LinkMovementMethod.getInstance());
        loginRegist.setText(stringBuilder);
    }

    @Override
    public Activity gatAct() {
        return getActivity();
    }

    @Override
    public void go2MainActivity() {
        MainActivity.startAct(getContext());
    }

    @Override
    public void toastShort(String msg) {

    }

    @Override
    public void setData(String code) {

        this.mVerifyCode=code;
        if (verificationFragment!=null){
            verificationFragment.setData(code);
        }

    }

}

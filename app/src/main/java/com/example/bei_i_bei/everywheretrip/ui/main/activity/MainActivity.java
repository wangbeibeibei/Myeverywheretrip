package com.example.bei_i_bei.everywheretrip.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bei_i_bei.everywheretrip.CollectionActivity;
import com.example.bei_i_bei.everywheretrip.MyFocusActivity;
import com.example.bei_i_bei.everywheretrip.PersonalActivity;
import com.example.bei_i_bei.everywheretrip.R;
import com.example.bei_i_bei.everywheretrip.base.BaseActivity;
import com.example.bei_i_bei.everywheretrip.base.Constants;
import com.example.bei_i_bei.everywheretrip.bean.BalanceBean;
import com.example.bei_i_bei.everywheretrip.bean.InformationBean;
import com.example.bei_i_bei.everywheretrip.presenter.MainPresenter;
import com.example.bei_i_bei.everywheretrip.ui.main.adapter.VpMainAdapter;
import com.example.bei_i_bei.everywheretrip.ui.main.fragment.BanMiFragment;
import com.example.bei_i_bei.everywheretrip.ui.main.fragment.MainFragment;
import com.example.bei_i_bei.everywheretrip.util.SpUtil;
import com.example.bei_i_bei.everywheretrip.util.ToastUtil;
import com.example.bei_i_bei.everywheretrip.view.MainView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView, View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.toolbar_img)
    ImageView toolbarImg;
    @BindView(R.id.nv)
    NavigationView nv;
    @BindView(R.id.dl)
    DrawerLayout dl;

    @BindView(R.id.my_purse_money)
    TextView myPurseMoney;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.idiographic)
    TextView idiographic;
    @BindView(R.id.feedback_img)
    ImageView feedbackImg;
    @BindView(R.id.feedback)
    TextView feedback;
    @BindView(R.id.icon_me_banben)
    ImageView iconMeBanben;
    @BindView(R.id.head_portrait)
    ImageView headPortrait;
    @BindView(R.id.rl_collection)
    RelativeLayout rlCollection;
    @BindView(R.id.rl_nv)
    RelativeLayout rlNv;


    private VpMainAdapter vpMainAdapter;
    private RelativeLayout mRlMyFoucus;
    private InformationBean.ResultBean result;
    private RequestOptions requestOptions;

    public static void startAct(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);

    }


    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;

    }

    @Override
    protected void initData() {
        //获取余额
        mPresenter.getBalanceBean();
        //获取个人信息
        mPresenter.getInformation();
    }

    @Override
    protected void initView() {
        //亮色模式 会将状态栏文字改为黑色
        StatusBarUtil.setLightMode(this);
        toolbar.setTitle("");
        // toolbar.setNavigationIcon(R.drawable.abc);
        requestOptions = new RequestOptions().circleCrop();
        Glide.with(this).load(SpUtil.getParam(Constants.HEADIMG,"")).apply(requestOptions).into(toolbarImg);
        toolbarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl.openDrawer(Gravity.LEFT);
            }
        });
        nv.setItemIconTintList(null);
        setSupportActionBar(toolbar);

        ArrayList<String> tablist = new ArrayList<>();
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new MainFragment());
        fragments.add(new BanMiFragment());

        tab.addTab(tab.newTab().setText("首页").setIcon(R.drawable.maintab_select));
        tab.addTab(tab.newTab().setText("伴米").setIcon(R.drawable.banmitab_select));

        vpMainAdapter = new VpMainAdapter(getSupportFragmentManager(), fragments, tablist);
        vp.setAdapter(vpMainAdapter);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));


        mRlMyFoucus = (RelativeLayout) findViewById(R.id.rl_my_foucus);
        mRlMyFoucus.setOnClickListener(this);



        Glide.with(this).load( SpUtil.getParam(Constants.HEADIMG,"")).apply(requestOptions).into(headPortrait);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rl_my_foucus:
                startActivity(new Intent(this, MyFocusActivity.class));
                break;
        }
    }

    @Override
    public void setBalance(BalanceBean balance) {

        BalanceBean.ResultBean result = balance.getResult();
        String balance1 = result.getBalance();
        //  myPurseMoney.setText(balance1+"");

        Log.i("balance1", "balance1余额：" + balance1 + ",余额：" + myPurseMoney + "---" + result.toString());
    }

    @Override
    public void setInformation(InformationBean information) {

        result = information.getResult();
        //零钱
        String balance = result.getBalance();
        myPurseMoney.setText(balance);

        //用户昵称
        String userName = result.getUserName();
        name.setText(userName);
      //  name.setText(Constants.USERNAME);
        //个性签名
        String description = result.getDescription();
        idiographic.setText(description);

        Log.i("info", "name：" + userName + ",balance：" + balance + ",result：" + result.toString());


    }


    @OnClick({R.id.head_portrait, R.id.rl_collection})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_portrait:
                Intent intent = new Intent(MainActivity.this, PersonalActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_collection:
               startActivity(new Intent(this,CollectionActivity.class));
                break;
        }
    }

    @OnClick(R.id.rl_nv)
    public void onViewClicked() {
        Intent intent = new Intent(MainActivity.this, PersonalActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //用户昵称
        String userName = result.getUserName();
        name.setText((String)SpUtil.getParam(Constants.NAME,""));

        //个性签名
        String description = result.getDescription();
        idiographic.setText((String)SpUtil.getParam(Constants.SIGNATURE,""));

        Glide.with(this).load( SpUtil.getParam(Constants.HEADIMG,"")).apply(requestOptions).into(headPortrait);

    }
}

package com.example.bei_i_bei.everywheretrip;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.bei_i_bei.everywheretrip.base.BaseActivity;
import com.example.bei_i_bei.everywheretrip.bean.BanMiShowBean;
import com.example.bei_i_bei.everywheretrip.presenter.BanMiShowPresenter;
import com.example.bei_i_bei.everywheretrip.ui.main.adapter.VpBanMiShowAdapter;
import com.example.bei_i_bei.everywheretrip.ui.main.fragment.DynamicFragment;
import com.example.bei_i_bei.everywheretrip.ui.main.fragment.RouteFragment;
import com.example.bei_i_bei.everywheretrip.util.Tools;
import com.example.bei_i_bei.everywheretrip.view.BanMiShowView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BanMiShowActivity extends BaseActivity<BanMiShowView, BanMiShowPresenter> implements BanMiShowView {

    @BindView(R.id.back_finish)
    ImageView backFinish;
    @BindView(R.id.share)
    ImageView share;
    @BindView(R.id.toolbar_banmishow)
    Toolbar toolbarBanmishow;
    @BindView(R.id.cardURL)
    ImageView cardURL;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.follow)
    TextView follow;
    @BindView(R.id.follow_img)
    ImageView followImg;
    @BindView(R.id.follow_text)
    TextView followText;
    @BindView(R.id.location_img)
    ImageView locationImg;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.author_img)
    ImageView authorImg;
    @BindView(R.id.author)
    TextView author;
    @BindView(R.id.cardview)
    CardView cardview;
    @BindView(R.id.tab)
    TabLayout tab;
    /*  @BindView(R.id.vp)
      ViewPager vp;*/
    @BindView(R.id.introduction)
    TextView introduction;
    private int banmiId;
    private BanMiShowBean.ResultBean.BanmiBean banmi;
    private DynamicFragment dynamicFragment;
    private RouteFragment routeFragment;

    @Override
    protected BanMiShowPresenter initPresenter() {
        return new BanMiShowPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ban_mi_show;
    }

    @Override
    protected void initView() {

        banmiId = getIntent().getIntExtra("banmiId", 0);
        StatusBarUtil.setLightMode(this);
/*
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new DynamicFragment(banmiId));
        fragments.add(new RouteFragment(banmiId));

        ArrayList<String> tablist = new ArrayList<>();
        tablist.add("动态");
        tablist.add("线路");

        VpBanMiShowAdapter vpBanMiShowAdapter = new VpBanMiShowAdapter(getSupportFragmentManager(), fragments, tablist);

        vp.setAdapter(vpBanMiShowAdapter);
        tab.setupWithViewPager(vp);*/
        tab.addTab(tab.newTab().setText("动态"));
        tab.addTab(tab.newTab().setText("线路"));
        dynamicFragment = new DynamicFragment(banmiId);
        routeFragment = new RouteFragment(banmiId);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame, dynamicFragment);
        transaction.add(R.id.frame, routeFragment);
        transaction.show(dynamicFragment).hide(routeFragment).commit();

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:

                        getSupportFragmentManager().beginTransaction().show(dynamicFragment).hide(routeFragment).commit();
                        break;

                    case 1:
                        getSupportFragmentManager().beginTransaction().show(routeFragment).hide(dynamicFragment).commit();

                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @OnClick({R.id.back_finish, R.id.share, R.id.follow_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_finish:
                finish();
                break;

            case R.id.share:

                break;

            case R.id.follow_img:
                break;
        }
    }

    @Override
    protected void initData() {
        mPresenter.getBanMiShow(banmiId);
    }

    @Override
    public void setData(BanMiShowBean bean) {
        banmi = bean.getResult().getBanmi();
        String mname = banmi.getName();
        name.setText(mname);

        String mlocation = banmi.getLocation();
        location.setText(mlocation);

        String moccupation = banmi.getOccupation();
        author.setText(moccupation);

        String mintroduction = banmi.getIntroduction();
        introduction.setText(mintroduction);

        String photo = banmi.getPhoto();
        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(10);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
        Glide.with(this).load(photo).apply(options).into(cardURL);

        int following = banmi.getFollowing();
        follow.setText(following + "关注");
    }


}

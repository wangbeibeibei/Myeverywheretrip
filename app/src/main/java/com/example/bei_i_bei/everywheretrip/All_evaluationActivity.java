package com.example.bei_i_bei.everywheretrip;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.bei_i_bei.everywheretrip.base.BaseActivity;
import com.example.bei_i_bei.everywheretrip.bean.All_evaluationBean;
import com.example.bei_i_bei.everywheretrip.presenter.All_evaluationPresenter;
import com.example.bei_i_bei.everywheretrip.ui.main.adapter.RvAll_evaluationAdapter;
import com.example.bei_i_bei.everywheretrip.view.All_evaluationView;
import com.jaeger.library.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class All_evaluationActivity extends BaseActivity<All_evaluationView, All_evaluationPresenter> implements All_evaluationView {

    @BindView(R.id.back_finish)
    ImageView mBackFinish;
    @BindView(R.id.toolbar_banmishow)
    Toolbar mToolbarBanmishow;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.smart_layout)
    SmartRefreshLayout smartLayout;
    private int routeId;
    private int cid;
    private ArrayList<All_evaluationBean.ResultBean.ReviewsBean> mlist;
    private RvAll_evaluationAdapter rvAll_evaluationAdapter;
    private List<All_evaluationBean.ResultBean.ReviewsBean> reviews;

    @Override
    protected All_evaluationPresenter initPresenter() {
        return new All_evaluationPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_all_evaluation;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        routeId = getIntent().getIntExtra("routeId", 0);
        mToolbarBanmishow.setTitle("");
        setSupportActionBar(mToolbarBanmishow);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(linearLayoutManager);
        mlist = new ArrayList<>();
        rvAll_evaluationAdapter = new RvAll_evaluationAdapter(this, mlist);
        mRv.setAdapter(rvAll_evaluationAdapter);
        /*初始化*/
        smartLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                cid = 1;
                mlist.clear();
                initData();
                smartLayout.finishRefresh();
            }
        });
        /*加载更多*/
        smartLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                cid++;
                initData();
                smartLayout.finishLoadmore();
            }
        });

        // 开始下拉
        smartLayout.setEnableRefresh(true);//启用刷新
        smartLayout.setEnableLoadmore(true);//启用加载
        //关闭下拉
        smartLayout.finishRefresh();
        smartLayout.finishLoadmore();
    }

    @Override
    protected void initData() {
        mPresenter.getData(routeId, cid);
    }

    @Override
    public void setData(All_evaluationBean all_evaluationBean) {

        reviews = all_evaluationBean.getResult().getReviews();
        mlist.addAll(reviews);
        rvAll_evaluationAdapter.notifyDataSetChanged();
    }


    @OnClick(R.id.back_finish)
    public void onViewClicked() {
        finish();
    }


}

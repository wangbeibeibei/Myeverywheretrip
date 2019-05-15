package com.example.bei_i_bei.everywheretrip.ui.main.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bei_i_bei.everywheretrip.R;
import com.example.bei_i_bei.everywheretrip.base.BaseFragment;
import com.example.bei_i_bei.everywheretrip.bean.DynamicBean;
import com.example.bei_i_bei.everywheretrip.presenter.DynamicPresenter;
import com.example.bei_i_bei.everywheretrip.ui.main.adapter.DynamicAdapter;
import com.example.bei_i_bei.everywheretrip.view.DynamicView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DynamicFragment extends BaseFragment<DynamicView, DynamicPresenter> implements DynamicView {
    @BindView(R.id.rv)
    RecyclerView mRv;
    Unbinder unbinder;


    int banmiId;
    int page=1;
    private ArrayList<DynamicBean.ResultBean.ActivitiesBean> mlist;
    private DynamicAdapter dynamicAdapter;

    @SuppressLint("ValidFragment")
    public DynamicFragment(int banmiId) {
        this.banmiId = banmiId;
    }

    public DynamicFragment() {
        // Required empty public constructor
    }

    @Override
    protected DynamicPresenter initPresenter() {
        return new DynamicPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dynamic;
    }

    @Override
    protected void initData() {
        mPresenter.getData(banmiId,page);
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRv.setLayoutManager(linearLayoutManager);
        mlist = new ArrayList<>();
        dynamicAdapter = new DynamicAdapter(getContext(), mlist);
        mRv.setAdapter(dynamicAdapter);
    }


    @Override
    public void setData(DynamicBean bean) {
        List<DynamicBean.ResultBean.ActivitiesBean> activities = bean.getResult().getActivities();
        mlist.addAll(activities);
        dynamicAdapter.notifyDataSetChanged();
    }
}

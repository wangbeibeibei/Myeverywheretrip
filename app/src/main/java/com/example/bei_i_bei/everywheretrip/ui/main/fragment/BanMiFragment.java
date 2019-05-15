package com.example.bei_i_bei.everywheretrip.ui.main.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bei_i_bei.everywheretrip.BanMiShowActivity;
import com.example.bei_i_bei.everywheretrip.R;
import com.example.bei_i_bei.everywheretrip.base.BaseFragment;
import com.example.bei_i_bei.everywheretrip.bean.BanMiBean;
import com.example.bei_i_bei.everywheretrip.bean.FocusBean;
import com.example.bei_i_bei.everywheretrip.presenter.BanMiFragmentPresenter;
import com.example.bei_i_bei.everywheretrip.ui.main.adapter.RvBanMiAdapter;
import com.example.bei_i_bei.everywheretrip.util.DButls;
import com.example.bei_i_bei.everywheretrip.view.BanMiFragmentView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class BanMiFragment extends BaseFragment<BanMiFragmentView, BanMiFragmentPresenter> implements BanMiFragmentView {


    @BindView(R.id.rv_banmi)
    RecyclerView rvBanmi;
    Unbinder unbinder;
    private int page = 1;
    private ArrayList<BanMiBean.ResultBean.BanmiBean> mlist;
    private RvBanMiAdapter rvBanMiAdapter;

    public BanMiFragment() {
        // Required empty public constructor
    }

    @Override
    protected BanMiFragmentPresenter initPresenter() {
        return new BanMiFragmentPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ban_mi;
    }

    @Override
    protected void initData() {
        mPresenter.getData(page);
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvBanmi.setLayoutManager(linearLayoutManager);
        mlist = new ArrayList<>();
        rvBanMiAdapter = new RvBanMiAdapter(getContext(), mlist);
        rvBanmi.setAdapter(rvBanMiAdapter);
        rvBanMiAdapter.setMyOnItemClick(new RvBanMiAdapter.MyOnItemClick() {
            @Override
            public void myOnItemClick(int position) {
                Intent intent = new Intent(getContext(), BanMiShowActivity.class);
                intent.putExtra("banmiId",mlist.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void setData(BanMiBean banmiBean) {

        List<BanMiBean.ResultBean.BanmiBean> banmi = banmiBean.getResult().getBanmi();
        mlist.addAll(banmi);
        rvBanMiAdapter.notifyDataSetChanged();
    }



}

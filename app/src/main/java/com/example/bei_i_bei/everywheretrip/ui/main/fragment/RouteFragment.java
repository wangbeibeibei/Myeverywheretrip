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
import com.example.bei_i_bei.everywheretrip.bean.RouteBean;
import com.example.bei_i_bei.everywheretrip.presenter.RoutePresenter;
import com.example.bei_i_bei.everywheretrip.ui.main.adapter.RvRouteAdapter;
import com.example.bei_i_bei.everywheretrip.view.RouteView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RouteFragment extends BaseFragment<RouteView, RoutePresenter> implements RouteView {
    @BindView(R.id.route_rv)
    RecyclerView routeRv;
    Unbinder unbinder;
    private ArrayList<RouteBean.ResultBean.RoutesBean> mlist;
    private RvRouteAdapter rvRouteAdapter;

    public RouteFragment() {
        // Required empty public constructor
    }

    int page;

    @SuppressLint("ValidFragment")
    public RouteFragment(int page) {
        this.page = page;
    }

    @Override
    protected RoutePresenter initPresenter() {
        return new RoutePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_route;
    }

    @Override
    protected void initData() {
        mPresenter.getRoute(page);
    }

    @Override
    protected void initView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        routeRv.setLayoutManager(linearLayoutManager);
        mlist = new ArrayList<>();
        rvRouteAdapter = new RvRouteAdapter(getContext(), mlist);
        routeRv.setAdapter(rvRouteAdapter);
    }

    @Override
    public void setData(RouteBean routeBean) {
        List<RouteBean.ResultBean.RoutesBean> routes = routeBean.getResult().getRoutes();
        mlist.addAll(routes);
        rvRouteAdapter.notifyDataSetChanged();
    }





}

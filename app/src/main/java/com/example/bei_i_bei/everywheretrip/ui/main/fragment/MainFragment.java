package com.example.bei_i_bei.everywheretrip.ui.main.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bei_i_bei.everywheretrip.HomeShowActivity;
import com.example.bei_i_bei.everywheretrip.R;
import com.example.bei_i_bei.everywheretrip.base.BaseFragment;
import com.example.bei_i_bei.everywheretrip.bean.MainBean;
import com.example.bei_i_bei.everywheretrip.presenter.MainFragmentPresenter;
import com.example.bei_i_bei.everywheretrip.ui.main.adapter.RvMainAdapter;
import com.example.bei_i_bei.everywheretrip.view.MainFragmentView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment<MainFragmentView, MainFragmentPresenter> implements MainFragmentView {


    @BindView(R.id.rv)
    RecyclerView rv;
    int page=1;
    private RvMainAdapter rvMainAdapter;
    private List<MainBean.ResultBean.RoutesBean> routes;
    private ArrayList<MainBean.ResultBean.RoutesBean> mlist;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    protected MainFragmentPresenter initPresenter() {
        return new MainFragmentPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(linearLayoutManager);

    }

    @Override
    protected void initData() {
        mPresenter.getData(page);
    }

    @Override
    public void setData(MainBean mainBean) {
        List<MainBean.ResultBean.BannersBean> banners = mainBean.getResult().getBanners();
        List<MainBean.ResultBean.RoutesBean> routes = mainBean.getResult().getRoutes();
        ArrayList<MainBean.ResultBean.BannersBean> bannerlist = new ArrayList<>();
        mlist = new ArrayList<>();
        bannerlist.addAll(banners);
        mlist.addAll(routes);
        rvMainAdapter = new RvMainAdapter(getContext(), mlist, bannerlist);
        rv.setAdapter(rvMainAdapter);
        rvMainAdapter.notifyDataSetChanged();
        rvMainAdapter.setMyOnItemClick(new RvMainAdapter.MyOnItemClick() {
            @Override
            public void myOnItemClick(int position) {
                Intent intent = new Intent(getContext(), HomeShowActivity.class);
                intent.putExtra("page",mlist.get(position).getId());
                startActivity(intent);
            }
        });


    }


}

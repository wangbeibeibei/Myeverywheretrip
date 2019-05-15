package com.example.bei_i_bei.everywheretrip;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.bei_i_bei.everywheretrip.base.BaseActivity;
import com.example.bei_i_bei.everywheretrip.bean.CollectionBean;
import com.example.bei_i_bei.everywheretrip.presenter.CollectionPresenter;
import com.example.bei_i_bei.everywheretrip.ui.main.adapter.CollectionAdapter;
import com.example.bei_i_bei.everywheretrip.view.CollectionView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CollectionActivity extends BaseActivity<CollectionView, CollectionPresenter> implements CollectionView {

    @BindView(R.id.rv_collection)
    RecyclerView rvCollection;
    @BindView(R.id.back_finish)
    ImageView backFinish;
    private CollectionAdapter collectionAdapter;
    private ArrayList<CollectionBean.ResultBean.CollectedRoutesBean> mlist;

    @Override
    protected CollectionPresenter initPresenter() {
        return new CollectionPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvCollection.setLayoutManager(linearLayoutManager);

        mlist = new ArrayList<>();
        collectionAdapter = new CollectionAdapter(this, mlist);
        rvCollection.setAdapter(collectionAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.getCollection();
    }


    @Override
    public void setData(CollectionBean collectionBean) {
        List<CollectionBean.ResultBean.CollectedRoutesBean> collectedRoutes = collectionBean.getResult().getCollectedRoutes();
        mlist.addAll(collectedRoutes);
        collectionAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.back_finish)
    public void onViewClicked() {
        finish();
    }
}

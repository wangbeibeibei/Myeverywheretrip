package com.example.bei_i_bei.everywheretrip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bei_i_bei.everywheretrip.bean.To_ViewBean;
import com.example.bei_i_bei.everywheretrip.net.ApiService;
import com.example.bei_i_bei.everywheretrip.ui.main.adapter.RvTo_ViewAdapter;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class To_viewActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRv;
    private ArrayList<To_ViewBean.ResultBean.BundlesBean> mlist;
    private RvTo_ViewAdapter rvTo_viewAdapter;
    private ImageView mBackFinish;
    private TextView mShowToolbar;
    private Toolbar mToolbarBanmishow;
    private List<To_ViewBean.ResultBean.BundlesBean> bundles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_view);
        initView();
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.MainUtl)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<To_ViewBean> viewBean = apiService.getViewBean();
        viewBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<To_ViewBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(To_ViewBean to_viewBean) {
                        bundles = to_viewBean.getResult().getBundles();
                        mlist.addAll(bundles);
                        rvTo_viewAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        StatusBarUtil.setLightMode(this);
        mRv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(linearLayoutManager);
        mBackFinish = (ImageView) findViewById(R.id.back_finish);
        mBackFinish.setOnClickListener(this);
        mShowToolbar = (TextView) findViewById(R.id.show_toolbar);

        mToolbarBanmishow = (Toolbar) findViewById(R.id.toolbar_banmishow);
        mToolbarBanmishow.setTitle("");

        mlist = new ArrayList<>();
        rvTo_viewAdapter = new RvTo_ViewAdapter(this, mlist);
        mRv.setAdapter(rvTo_viewAdapter);

        rvTo_viewAdapter.setMyImgClick(new RvTo_ViewAdapter.MyImgClick() {
            @Override
            public void setMyImgClick(int position) {
                mShowToolbar.setText(bundles.get(position).getTitle());

                finish();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back_finish:
                finish();
                break;
        }
    }
}

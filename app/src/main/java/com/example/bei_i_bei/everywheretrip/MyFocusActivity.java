package com.example.bei_i_bei.everywheretrip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bei_i_bei.everywheretrip.bean.FocusBean;
import com.example.bei_i_bei.everywheretrip.ui.main.adapter.RvFocusAdapter;
import com.example.bei_i_bei.everywheretrip.util.DButls;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class MyFocusActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRv;
    private ArrayList<FocusBean> mlist;
    private RvFocusAdapter rvFocusAdapter;
    private ImageView mBackFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_focus);
        initView();
        initData();

    }

    private void initData() {
        List<FocusBean> focusBeans = DButls.getdButls().queryAll();
        Log.i("focusBeans", "focusBeans" + focusBeans.toString());
        Toast.makeText(this, focusBeans.toString(), Toast.LENGTH_SHORT).show();
        mlist.addAll(focusBeans);
        rvFocusAdapter.notifyDataSetChanged();
    }

    private void initView() {
        StatusBarUtil.setLightMode(this);
        mRv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(linearLayoutManager);

        mlist = new ArrayList<>();
        rvFocusAdapter = new RvFocusAdapter(this, mlist);
        mRv.setAdapter(rvFocusAdapter);
        mBackFinish = (ImageView) findViewById(R.id.back_finish);
        mBackFinish.setOnClickListener(this);
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

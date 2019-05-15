package com.example.bei_i_bei.everywheretrip;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.bei_i_bei.everywheretrip.ui.main.adapter.GuidePageAdapter;
import com.example.bei_i_bei.everywheretrip.widget.PreviewIndicator;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

public class GuidepageActivity extends AppCompatActivity implements View.OnClickListener {


    private ViewPager vp;
    private PreviewIndicator mPl;
    private ArrayList<View> viewlist;
    /**
     * 立即体验
     */
    private Button mBtnTiyan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidepage);
        initView();
        initSp();


    }

    private void initSp() {

    }

    private void initView() {
        StatusBarUtil.setLightMode(this);
        vp = (ViewPager) findViewById(R.id.vp);
        mPl = (PreviewIndicator) findViewById(R.id.pl);
        mPl.setOnClickListener(this);


        viewlist = new ArrayList<>();
        View guidepage_one = LayoutInflater.from(this).inflate(R.layout.layout_guidepage_one, null);
        View guidepage_two = LayoutInflater.from(this).inflate(R.layout.layout_guidepage_two, null);
        View guidepage_three = LayoutInflater.from(this).inflate(R.layout.layout_guidepage_three, null);
        viewlist.add(guidepage_one);
        viewlist.add(guidepage_two);
        viewlist.add(guidepage_three);
        GuidePageAdapter guidePageAdapter = new GuidePageAdapter(this, viewlist);
        vp.setAdapter(guidePageAdapter);



        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                mPl.setSelected(i);
                if (i == 2) {
                    mPl.setVisibility(View.GONE);
                    mBtnTiyan.setVisibility(View.VISIBLE);

                } else {
                    mPl.setVisibility(View.VISIBLE);
                    mBtnTiyan.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        mBtnTiyan = (Button) findViewById(R.id.btn_tiyan);
        mBtnTiyan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.pl:
                mPl.setNumbers(3);
                mPl.initSize(80, 32, 6);
                break;
            case R.id.btn_tiyan:
                startActivity(new Intent(GuidepageActivity.this, LoginActivity.class));
                break;
        }

    }
}


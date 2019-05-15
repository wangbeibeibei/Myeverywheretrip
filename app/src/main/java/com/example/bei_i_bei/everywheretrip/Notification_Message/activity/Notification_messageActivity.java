package com.example.bei_i_bei.everywheretrip.Notification_Message.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.bei_i_bei.everywheretrip.Notification_Message.fragment.MessageFragment;
import com.example.bei_i_bei.everywheretrip.Notification_Message.fragment.NotificationFragment;
import com.example.bei_i_bei.everywheretrip.R;
import com.example.bei_i_bei.everywheretrip.base.BaseActivity;
import com.example.bei_i_bei.everywheretrip.presenter.Notification_messagePresenter;
import com.example.bei_i_bei.everywheretrip.Notification_Message.adapter.VpNotification_messageAdapter;
import com.example.bei_i_bei.everywheretrip.view.Notification_messageView;

import java.util.ArrayList;

import butterknife.BindView;

public class Notification_messageActivity extends BaseActivity<Notification_messageView, Notification_messagePresenter> implements Notification_messageView {


    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;

    @Override
    protected Notification_messagePresenter initPresenter() {
        return new Notification_messagePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notification_message;
    }

    @Override
    protected void initView() {

        ArrayList<String> tablist = new ArrayList<>();
        ArrayList<Fragment> fragments = new ArrayList<>();
        tablist.add("通知");
        tablist.add("消息");
        fragments.add(new NotificationFragment());
        fragments.add(new MessageFragment());
        VpNotification_messageAdapter vpNotification_messageAdapter = new VpNotification_messageAdapter(getSupportFragmentManager(), fragments, tablist);
        vp.setAdapter(vpNotification_messageAdapter);
        tab.setupWithViewPager(vp);
    }

}

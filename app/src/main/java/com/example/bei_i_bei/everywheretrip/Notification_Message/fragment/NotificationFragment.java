package com.example.bei_i_bei.everywheretrip.Notification_Message.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.Toast;

import com.example.bei_i_bei.everywheretrip.Notification_Message.adapter.RvNotificationAdapter;
import com.example.bei_i_bei.everywheretrip.Notification_Message.bean.NotificationBean;
import com.example.bei_i_bei.everywheretrip.R;
import com.example.bei_i_bei.everywheretrip.base.BaseFragment;
import com.example.bei_i_bei.everywheretrip.presenter.NotificationPresenter;
import com.example.bei_i_bei.everywheretrip.view.NotificationView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends BaseFragment<NotificationView, NotificationPresenter> implements NotificationView {


    Unbinder unbinder;
    @BindView(R.id.notification_rv)
    SwipeMenuRecyclerView notificationRv;
    Unbinder unbinder1;
    private ArrayList<NotificationBean> mlist;
    private RvNotificationAdapter rvNotificationAdapter;



    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    protected NotificationPresenter initPresenter() {
        return new NotificationPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notification;
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        notificationRv.setLayoutManager(linearLayoutManager);
        notificationRv.addItemDecoration(new DefaultItemDecoration(Color.GRAY));
        notificationRv.setSwipeMenuCreator(swipeMenuCreator);
        notificationRv.setSwipeMenuItemClickListener(swipeMenuItemClickListener);
        mlist = new ArrayList<>();
        rvNotificationAdapter = new RvNotificationAdapter(getContext(), mlist);
        notificationRv.setAdapter(rvNotificationAdapter);





    }
    SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            SwipeMenuItem deleteItem = new SwipeMenuItem(getContext())
                    .setBackground(R.color.fa6a13)
                    .setImage(R.mipmap.back_black)
                    .setTextColor(Color.WHITE)
                    .setText(R.string.DeleteText)
                    .setWidth(width)
                    .setHeight(height);
            swipeRightMenu.addMenuItem(deleteItem);
        }
    };
    // 菜单点击监听。
    // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
//左边还是右边菜单
//    ecyclerView的Item的position。
// 菜单在RecyclerView的Item中的Position。
//删除item
    SwipeMenuItemClickListener  swipeMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection();//左边还是右边菜单
            int adapterPosition = menuBridge.getAdapterPosition();//    ecyclerView的Item的position。
            int position = menuBridge.getPosition();// 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                mlist.remove(adapterPosition);//删除item
                rvNotificationAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "list第" + adapterPosition + "; 右侧菜单第" + position, Toast.LENGTH_SHORT).show();
            }

        }
    };
    @Override
    protected void initData() {
        ArrayList<NotificationBean> arrayList = new ArrayList<>();
        arrayList.add(new NotificationBean("系统通知", "2017/10/21", "用户注册成功"));
        arrayList.add(new NotificationBean("系统通知", "2017/10/21", "通过分享获得两米粒"));
        arrayList.add(new NotificationBean("系统通知", "2017/10/21", "通过分享获得两米粒"));
        mlist.addAll(arrayList);
        rvNotificationAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }
}

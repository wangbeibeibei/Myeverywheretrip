package com.example.bei_i_bei.everywheretrip.Notification_Message.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bei_i_bei.everywheretrip.Notification_Message.activity.MessageShowActivity;
import com.example.bei_i_bei.everywheretrip.Notification_Message.adapter.RvMessageAdapter;
import com.example.bei_i_bei.everywheretrip.Notification_Message.bean.MessageBean;
import com.example.bei_i_bei.everywheretrip.R;
import com.example.bei_i_bei.everywheretrip.base.BaseFragment;
import com.example.bei_i_bei.everywheretrip.presenter.MessagePresenter;
import com.example.bei_i_bei.everywheretrip.view.MessageView;
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
public class MessageFragment extends BaseFragment<MessageView, MessagePresenter> implements MessageView {



    @BindView(R.id.rv_message)
    SwipeMenuRecyclerView rvMessage;
    Unbinder unbinder1;
    private ArrayList<MessageBean> mlist;
    private RvMessageAdapter rvMessageAdapter;

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    protected MessagePresenter initPresenter() {
        return new MessagePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvMessage.setLayoutManager(linearLayoutManager);
        rvMessage.addItemDecoration(new DefaultItemDecoration(Color.GRAY));
        rvMessage.setSwipeMenuCreator(swipeMenuCreator);
        rvMessage.setSwipeMenuItemClickListener(swipeMenuItemClickListener);
        mlist = new ArrayList<>();
        rvMessageAdapter = new RvMessageAdapter(getContext(), mlist);
        rvMessage.setAdapter(rvMessageAdapter);
       rvMessageAdapter.setMyItemOnClickListener(new RvMessageAdapter.MyItemOnClickListener() {
           @Override
           public void myItemOnClickListener(int position) {
               Intent intent = new Intent(getContext(), MessageShowActivity.class);
               startActivity(intent);
           }
       });
    }

    // 设置菜单监听器。
    // 创建菜单：
    SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        // 创建菜单：
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            SwipeMenuItem deleteItem = new SwipeMenuItem(getContext())
                    .setBackground(R.color.c_f11818)
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
    SwipeMenuItemClickListener swipeMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection();//左边还是右边菜单
            int adapterPosition = menuBridge.getAdapterPosition();//    ecyclerView的Item的position。
            int position = menuBridge.getPosition();// 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                mlist.remove(adapterPosition);//删除item
                rvMessageAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "list第" + adapterPosition + "; 右侧菜单第" + position, Toast.LENGTH_SHORT).show();
            }

        }
    };
    @Override
    protected void initData() {
        ArrayList<MessageBean> messageBeans = new ArrayList<>();
        messageBeans.add(new MessageBean("消息回复", "2017/10/21", "库索回复了你的留言"));
        messageBeans.add(new MessageBean("系统通知", "2017/10/21", "竹林回复了你的留言"));
        messageBeans.add(new MessageBean("消息回复", "2017/10/21", "马云回复了你的留言"));
        mlist.addAll(messageBeans);
        rvMessageAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }




}

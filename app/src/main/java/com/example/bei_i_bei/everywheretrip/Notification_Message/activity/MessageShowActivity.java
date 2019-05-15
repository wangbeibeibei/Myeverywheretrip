package com.example.bei_i_bei.everywheretrip.Notification_Message.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bei_i_bei.everywheretrip.Notification_Message.adapter.MessageShowAdapter;
import com.example.bei_i_bei.everywheretrip.Notification_Message.bean.MessageShowBean;
import com.example.bei_i_bei.everywheretrip.R;

import java.util.ArrayList;

public class MessageShowActivity extends AppCompatActivity implements View.OnClickListener {


    /**
     * 消息详情
     */
    private TextView txt;
    private Toolbar toolbar;
    private RecyclerView rv_messageshow;
    private ArrayList<MessageShowBean> mlist;
    private MessageShowAdapter messageShowAdapter;
    private ImageView img_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_show);
        initView();
        initData();

    }

    private void initData() {
        ArrayList<MessageShowBean> messageShowBeans = new ArrayList<>();
        messageShowBeans.add(new MessageShowBean(1, "2017/10/21", "请问日本的西瓜卡怎么办理？"));
        messageShowBeans.add(new MessageShowBean(2, "2017/10/21", "在个地铁口都可以买到， 有一个西瓜卡的自动售卖机。"));
        mlist.addAll(messageShowBeans);
        messageShowAdapter.notifyDataSetChanged();
    }

    private void initView() {

        txt = (TextView) findViewById(R.id.txt);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rv_messageshow = (RecyclerView) findViewById(R.id.rv_messageshow);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_messageshow.setLayoutManager(linearLayoutManager);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        mlist = new ArrayList<>();
        messageShowAdapter = new MessageShowAdapter(this, mlist);
        rv_messageshow.setAdapter(messageShowAdapter);
        img_finish = (ImageView) findViewById(R.id.img_finish);
        img_finish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.img_finish:
                finish();
                break;
        }
    }
}

package com.example.bei_i_bei.everywheretrip.Notification_Message.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bei_i_bei.everywheretrip.Notification_Message.bean.NotificationBean;
import com.example.bei_i_bei.everywheretrip.R;

import java.util.ArrayList;

public class RvNotificationAdapter extends RecyclerView.Adapter<RvNotificationAdapter.MyHolder> {

    private Context context;
    private ArrayList<NotificationBean>list;

    public RvNotificationAdapter(Context context, ArrayList<NotificationBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.notification_item, null);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        myHolder.message.setText(list.get(i).getMessage());
        myHolder.systematic_notification.setText(list.get(i).getSystematic_notification());
        myHolder.time.setText(list.get(i).getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        private TextView message,time,systematic_notification;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            systematic_notification=itemView.findViewById(R.id.systematic_notification);
            message=itemView.findViewById(R.id.message);
            time=itemView.findViewById(R.id.time);
        }
    }
}

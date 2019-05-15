package com.example.bei_i_bei.everywheretrip.Notification_Message.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bei_i_bei.everywheretrip.Notification_Message.bean.MessageBean;
import com.example.bei_i_bei.everywheretrip.R;

import java.util.ArrayList;

public class RvMessageAdapter extends RecyclerView.Adapter<RvMessageAdapter.MyHolder> {
    private Context context;
    private ArrayList<MessageBean>list;

    public RvMessageAdapter(Context context, ArrayList<MessageBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.message_item, null);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        myHolder.reply.setText(list.get(i).getReply());
        myHolder.systematic_notification.setText(list.get(i).getSystematic_notification());
        myHolder.time.setText(list.get(i).getTime());

        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myItemOnClickListener!=null){
                    myItemOnClickListener.myItemOnClickListener(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends  RecyclerView.ViewHolder{
        private TextView reply,time,systematic_notification;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            systematic_notification=itemView.findViewById(R.id.systematic_notification);
            reply=itemView.findViewById(R.id.reply);
            time=itemView.findViewById(R.id.time);
        }
    }
    public  interface  MyItemOnClickListener{
        void myItemOnClickListener(int position);
    }
    private MyItemOnClickListener myItemOnClickListener;

    public void setMyItemOnClickListener(MyItemOnClickListener myItemOnClickListener) {
        this.myItemOnClickListener = myItemOnClickListener;
    }
}

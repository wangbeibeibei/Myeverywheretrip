package com.example.bei_i_bei.everywheretrip.Notification_Message.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bei_i_bei.everywheretrip.Notification_Message.bean.MessageShowBean;
import com.example.bei_i_bei.everywheretrip.R;

import java.util.ArrayList;

public class MessageShowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<MessageShowBean> list;

    public MessageShowAdapter(Context context, ArrayList<MessageShowBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder holder = null;
        if (i == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_messageshow_left, null);
            holder = new MyItemLeft(inflate);
        } else if (i == 2) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_messageshow_right, null);
            holder = new MyItemRight(inflate);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int itemViewType = getItemViewType(i);
        if (itemViewType==1){
            MyItemLeft myItemLeft = (MyItemLeft) viewHolder;

            myItemLeft.message_left.setText(list.get(i).getMessage());
            myItemLeft.time_left.setText(list.get(i).getTime());
        }else if (itemViewType==2){
            MyItemRight myItemRight = (MyItemRight) viewHolder;
            myItemRight.message_right.setText(list.get(i).getMessage());
            myItemRight.time_right.setText(list.get(i).getTime());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getType() == 1) {
            return 1;
        } else {
            return 2;
        }
    }

    public class MyItemLeft extends RecyclerView.ViewHolder {
        private TextView time_left, message_left;

        public MyItemLeft(@NonNull View itemView) {
            super(itemView);
            time_left = itemView.findViewById(R.id.time_left);
            message_left = itemView.findViewById(R.id.message_left);

        }
    }

    public class MyItemRight extends RecyclerView.ViewHolder {
        private TextView time_right, message_right;

        public MyItemRight(@NonNull View itemView) {
            super(itemView);
            message_right = itemView.findViewById(R.id.message_right);
            time_right = itemView.findViewById(R.id.time_right);
        }
    }
}

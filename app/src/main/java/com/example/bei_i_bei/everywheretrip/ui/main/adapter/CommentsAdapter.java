package com.example.bei_i_bei.everywheretrip.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bei_i_bei.everywheretrip.R;
import com.example.bei_i_bei.everywheretrip.bean.HomeShowBean;

import java.util.ArrayList;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.MyHolder> {

    private Context context;
    private ArrayList<HomeShowBean.ResultBean.ReviewsBean>list;

    public CommentsAdapter(Context context, ArrayList<HomeShowBean.ResultBean.ReviewsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_comments_item, null);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        myHolder.createdAt.setText(list.get(i).getCreatedAt());
        myHolder.content.setText(list.get(i).getContent());
        myHolder.userName.setText(list.get(i).getUserName());
         RequestOptions requestOptions = new RequestOptions().circleCrop();
        Glide.with(context).load(list.get(i).getUserPhoto()).apply(requestOptions).into(myHolder.userPhoto);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends  RecyclerView.ViewHolder{
        private ImageView userPhoto;
        private TextView userName,createdAt,content;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            content=itemView.findViewById(R.id.content);
            userPhoto=itemView.findViewById(R.id.userPhoto);
            userName=itemView.findViewById(R.id.userName);
            createdAt=itemView.findViewById(R.id.createdAt);
        }
    }
}

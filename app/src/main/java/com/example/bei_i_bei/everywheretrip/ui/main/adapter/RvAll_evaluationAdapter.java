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
import com.example.bei_i_bei.everywheretrip.bean.All_evaluationBean;

import java.util.ArrayList;

public class RvAll_evaluationAdapter extends RecyclerView.Adapter<RvAll_evaluationAdapter.MyHolder> {

    private Context context;
    private ArrayList<All_evaluationBean.ResultBean.ReviewsBean>list;

    public RvAll_evaluationAdapter(Context context, ArrayList<All_evaluationBean.ResultBean.ReviewsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_allevalution_item, null);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        myHolder.username.setText(list.get(i).getUserName());
        myHolder.content.setText(list.get(i).getContent());
        myHolder.createdAt.setText(list.get(i).getCreatedAt());
         RequestOptions requestOptions = new RequestOptions().circleCrop();
        Glide.with(context).load(list.get(i).getUserPhoto()).apply(requestOptions).into(myHolder.userPhoto);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends  RecyclerView.ViewHolder{
        private ImageView userPhoto;
        private TextView username,createdAt,content;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.username);
            userPhoto=itemView.findViewById(R.id.userPhoto);
            createdAt=itemView.findViewById(R.id.createdAt);
            content=itemView.findViewById(R.id.content);
        }
    }
}

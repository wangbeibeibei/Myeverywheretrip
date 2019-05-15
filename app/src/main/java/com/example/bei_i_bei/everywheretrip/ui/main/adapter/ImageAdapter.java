package com.example.bei_i_bei.everywheretrip.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.bei_i_bei.everywheretrip.R;
import com.example.bei_i_bei.everywheretrip.bean.DynamicBean;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyHolder> {

    private Context context;
    private ArrayList<DynamicBean.ResultBean.ActivitiesBean> list;

    public ImageAdapter(Context context, ArrayList<DynamicBean.ResultBean.ActivitiesBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.img_item, null);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        if (list.get(i).getImages().size()>0){
            Glide.with(context).load(list.get(i).getImages().get(0)).into(myHolder.img_item);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        private ImageView img_item;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            img_item=itemView.findViewById(R.id.img_item);
        }
    }
}

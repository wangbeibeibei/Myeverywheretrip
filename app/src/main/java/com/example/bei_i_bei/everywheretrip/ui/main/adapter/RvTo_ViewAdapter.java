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
import com.example.bei_i_bei.everywheretrip.bean.To_ViewBean;

import java.util.ArrayList;

public class RvTo_ViewAdapter extends RecyclerView.Adapter<RvTo_ViewAdapter.MyHolder> {

    private Context context;
    private ArrayList<To_ViewBean.ResultBean.BundlesBean>list;

    public RvTo_ViewAdapter(Context context, ArrayList<To_ViewBean.ResultBean.BundlesBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_to_view, null);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {

        Glide.with(context).load(list.get(i).getCardURL()).into(myHolder.img);

        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myImgClick!=null){
                    myImgClick.setMyImgClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends  RecyclerView.ViewHolder{
     private ImageView img;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
        }
    }

    public  interface  MyImgClick{
        void setMyImgClick(int position);
    }
    private MyImgClick myImgClick;

    public void  setMyImgClick(MyImgClick myImgClick) {
        this.myImgClick = myImgClick;
    }
}

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
import com.example.bei_i_bei.everywheretrip.R;
import com.example.bei_i_bei.everywheretrip.bean.CollectionBean;

import java.util.ArrayList;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.MyHolder> {

    private Context context;
    private ArrayList<CollectionBean.ResultBean.CollectedRoutesBean>list;

    public CollectionAdapter(Context context, ArrayList<CollectionBean.ResultBean.CollectedRoutesBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_collection_item, null);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        myHolder.title.setText(list.get(i).getTitle());
        myHolder.intro.setText(list.get(i).getIntro());
        Glide.with(context).load(list.get(i).getCardURL()).into(myHolder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        private TextView title,intro;
        private ImageView img;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title_collection);
            intro=itemView.findViewById(R.id.intro);
            img=itemView.findViewById(R.id.img);
        }
    }
}

package com.example.bei_i_bei.everywheretrip.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bei_i_bei.everywheretrip.R;
import com.example.bei_i_bei.everywheretrip.bean.FocusBean;

import java.util.ArrayList;

public class RvFocusAdapter extends RecyclerView.Adapter<RvFocusAdapter.MyHolder> {
    private Context context;
    private ArrayList<FocusBean>list;

    public RvFocusAdapter(Context context, ArrayList<FocusBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_focus_item, null);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        myHolder.name.setText(list.get(i).getName());
        myHolder.author.setText(list.get(i).getOccupation());
        myHolder.location.setText(list.get(i).getLocation());
        myHolder.focus.setText(list.get(i).getFollowing()+"人关注");
        Glide.with(context).load(list.get(i).getPhoto()).into(myHolder.img);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        private TextView name,focus,location,author;
        private ImageView img,location_img,author_img;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            focus=itemView.findViewById(R.id.focus);
            location=itemView.findViewById(R.id.location);
            author=itemView.findViewById(R.id.author);
            img=itemView.findViewById(R.id.img);
            location_img=itemView.findViewById(R.id.location_img);
            author_img=itemView.findViewById(R.id.author_img);
        }
    }
}

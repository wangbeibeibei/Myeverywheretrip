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
import com.example.bei_i_bei.everywheretrip.bean.RouteBean;

import java.util.ArrayList;

import okhttp3.Route;

public class RvRouteAdapter extends RecyclerView.Adapter<RvRouteAdapter.MyHolder> {
    private Context context;
    private ArrayList<RouteBean.ResultBean.RoutesBean>list;

    public RvRouteAdapter(Context context, ArrayList<RouteBean.ResultBean.RoutesBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_main_item, null);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.attractions.setText(list.get(i).getTitle());
        myHolder.place.setText(list.get(i).getCity());
        myHolder.crazy_shopping_buy.setText(list.get(i).getIntro());
        Glide.with(context).load(list.get(i).getCardURL()).into(myHolder.cardURL);
        myHolder.button_buy.setText(list.get(i).getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends  RecyclerView.ViewHolder{
        private ImageView cardURL,attractions_img;
        private TextView attractions,place,button_buy,crazy_shopping_buy,people_buy;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            cardURL=itemView.findViewById(R.id.cardURL);
            attractions_img=itemView.findViewById(R.id.attractions_img);
            attractions=itemView.findViewById(R.id.attractions);
            place=itemView.findViewById(R.id.place);
            button_buy=itemView.findViewById(R.id.button_buy);
            crazy_shopping_buy=itemView.findViewById(R.id.crazy_shopping_buy);
            people_buy=itemView.findViewById(R.id.people_buy);
        }
    }
}

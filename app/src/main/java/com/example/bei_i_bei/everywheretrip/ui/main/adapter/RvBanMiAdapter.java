package com.example.bei_i_bei.everywheretrip.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bei_i_bei.everywheretrip.R;
import com.example.bei_i_bei.everywheretrip.bean.BanMiBean;
import com.example.bei_i_bei.everywheretrip.bean.FocusBean;
import com.example.bei_i_bei.everywheretrip.util.DButls;

import java.util.ArrayList;
import java.util.List;

public class RvBanMiAdapter extends RecyclerView.Adapter<RvBanMiAdapter.MyHolder> {
    boolean flass = false;
    private Context context;
    private ArrayList<BanMiBean.ResultBean.BanmiBean> list;

    public RvBanMiAdapter(Context context, ArrayList<BanMiBean.ResultBean.BanmiBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_banmi_item, null);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, final int i) {

        myHolder.name.setText(list.get(i).getName());
        myHolder.author.setText(list.get(i).getOccupation());
        myHolder.location.setText(list.get(i).getLocation());
        myHolder.focus.setText(list.get(i).getFollowing() + "人关注");
        Glide.with(context).load(list.get(i).getPhoto()).into(myHolder.img);

        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myOnItemClick != null) {
                    myOnItemClick.myOnItemClick(i);
                }
            }
        });

        myHolder.love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flass == false ) {
                    myHolder.love.setImageResource(R.mipmap.follow);
                    flass = true;
                    BanMiBean.ResultBean.BanmiBean banmiBean = list.get(i);
                    FocusBean focusBean = new FocusBean();
                    focusBean.setFollowing(banmiBean.getFollowing());
                    focusBean.setLocation(banmiBean.getLocation());
                    focusBean.setName(banmiBean.getName());
                    focusBean.setPhoto(banmiBean.getPhoto());
                    DButls.getdButls().insertAll(focusBean);
                    List<FocusBean> focusBeans = DButls.getdButls().queryAll();
                    Toast.makeText(context, "关注了", Toast.LENGTH_SHORT).show();
                } else {
                    myHolder.love.setImageResource(R.mipmap.follow_unselected);
                    Toast.makeText(context, "取消关注了", Toast.LENGTH_SHORT).show();
                    flass = false;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView name, focus, location, author;
        private ImageView img, love, location_img, author_img;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            focus = itemView.findViewById(R.id.focus);
            location = itemView.findViewById(R.id.location);
            author = itemView.findViewById(R.id.author);
            img = itemView.findViewById(R.id.img);
            love = itemView.findViewById(R.id.love);
            location_img = itemView.findViewById(R.id.location_img);
            author_img = itemView.findViewById(R.id.author_img);
        }
    }

    public interface MyOnItemClick {
        void myOnItemClick(int position);
    }

    private MyOnItemClick myOnItemClick;

    public void setMyOnItemClick(MyOnItemClick myOnItemClick) {
        this.myOnItemClick = myOnItemClick;
    }

}

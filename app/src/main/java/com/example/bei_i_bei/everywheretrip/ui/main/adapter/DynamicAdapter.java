package com.example.bei_i_bei.everywheretrip.ui.main.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.example.bei_i_bei.everywheretrip.R;
import com.example.bei_i_bei.everywheretrip.bean.DynamicBean;

import java.util.ArrayList;
import java.util.List;

import static android.widget.PopupWindow.*;

public class DynamicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<DynamicBean.ResultBean.ActivitiesBean> list;

    public DynamicAdapter(Context context, ArrayList<DynamicBean.ResultBean.ActivitiesBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder holder = null;
        if (i == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_voice_item, null);
            holder = new MyVoice(inflate);
        } else if (i == 2) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_topic_item, null);
            holder = new Mytopic(inflate);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

        int itemViewType = getItemViewType(i);
        if (itemViewType == 1) {
            MyVoice myVoice = (MyVoice) viewHolder;
            myVoice.chatAt.setText(list.get(i).getDate());

            myVoice.play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(context, Uri.parse(list.get(i).getAudioURL()));
                    mediaPlayer.start();
                }
            });
        } else if (itemViewType == 2) {
            Mytopic mytopic = (Mytopic) viewHolder;
            if (list.get(i).getImages().size()>0){
                Glide.with(context).load(list.get(i).getImages().get(0)).into(mytopic.imges);
            }
            mytopic.data.setText(list.get(i).getDate());
            mytopic.content.setText(list.get(i).getContent());
            mytopic.imges.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initPop(i);
                }
            });

/*
            ArrayList<DynamicBean.ResultBean.ActivitiesBean> mlist = new ArrayList<>();
            ImageAdapter imageAdapter = new ImageAdapter(context, mlist);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            mytopic.rv.setLayoutManager(linearLayoutManager);
           mlist.addAll(list);
           Glide.with(context).load(mlist.get(1).getImages()).into(mytopic.imges);
           mytopic.rv.setAdapter(imageAdapter);
           imageAdapter.notifyDataSetChanged();*/
        }

    }

    private void initPop(int i) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_imgpopu, null);
        PhotoView img_popu = inflate.findViewById(R.id.img_popu);
        img_popu.enable();
        Glide.with(context).load(list.get(i).getImages().get(0)).into(img_popu);

        final PopupWindow popupWindow = new PopupWindow(inflate, Gallery.LayoutParams.MATCH_PARENT, Gallery.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.c_60000000)));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(inflate, Gravity.CENTER,0,0);
        img_popu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }


        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getAudioURL().length()>0) {
            return 1;
        } else {
            return 2;
        }
    }

    public class MyVoice extends RecyclerView.ViewHolder {
        private TextView chatAt, info, give_a_like_sum, comment_sum;
        private ImageView comment, give_a_like, play;

        public MyVoice(@NonNull View itemView) {
            super(itemView);
            chatAt=itemView.findViewById(R.id.chatAt);
            info=itemView.findViewById(R.id.info);
            give_a_like_sum=itemView.findViewById(R.id.give_a_like_sum);
            comment_sum=itemView.findViewById(R.id.comment_sum);
            comment=itemView.findViewById(R.id.comment);
            give_a_like=itemView.findViewById(R.id.give_a_like);
            play=itemView.findViewById(R.id.play);
        }
    }

    public class Mytopic extends RecyclerView.ViewHolder {
        private ImageView praise_unselected,comment;
        private RecyclerView rv;
        private TextView data,up,content,praise_unselected_sum,comment_sum;
        private PhotoView imges;
        public Mytopic(@NonNull View itemView) {
            super(itemView);
            imges = itemView.findViewById(R.id.imges);
            praise_unselected = itemView.findViewById(R.id.praise_unselected);
            comment = itemView.findViewById(R.id.comment);
            data = itemView.findViewById(R.id.data);
            up = itemView.findViewById(R.id.up);

            content = itemView.findViewById(R.id.content);
            praise_unselected_sum = itemView.findViewById(R.id.praise_unselected_sum);
            comment_sum = itemView.findViewById(R.id.comment_sum);
            rv = itemView.findViewById(R.id.rv);
        }
    }
}

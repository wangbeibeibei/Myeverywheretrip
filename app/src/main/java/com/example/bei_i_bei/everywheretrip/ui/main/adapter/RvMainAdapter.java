package com.example.bei_i_bei.everywheretrip.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bei_i_bei.everywheretrip.HomeWebActivity;
import com.example.bei_i_bei.everywheretrip.R;
import com.example.bei_i_bei.everywheretrip.bean.MainBean;
import com.example.bei_i_bei.everywheretrip.ui.main.activity.WebViewActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class RvMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<MainBean.ResultBean.RoutesBean> mlist;
    private ArrayList<MainBean.ResultBean.BannersBean> bannerlist;

    public RvMainAdapter(Context context, ArrayList<MainBean.ResultBean.RoutesBean> mlist, ArrayList<MainBean.ResultBean.BannersBean> bannerlist) {
        this.context = context;
        this.mlist = mlist;
        this.bannerlist = bannerlist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder holder = null;
        if (i == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_banner_item, null);
            holder = new MyBanner(inflate);
        } else if (i == 2) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_main_item2, null);
            holder = new MyImg(inflate);
        } else if (i == 3) {

            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_main_item, null);
            holder = new MyHolder(inflate);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

        int itemViewType = getItemViewType(i);
        if (itemViewType == 1) {
            final MyBanner myBanner = (MyBanner) viewHolder;
            myBanner.banner.setImages(bannerlist);
            myBanner.banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
            myBanner.banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    MainBean.ResultBean.BannersBean bannersBean = (MainBean.ResultBean.BannersBean) path;
                    Glide.with(context).load(bannersBean.getImageURL()).into(imageView);
                }
            });
            myBanner.banner.start();

        } else if (itemViewType == 2) {
            final MyImg myImg = (MyImg) viewHolder;
            int mposition = i;
            if (mlist.size() > 0) {
                mposition = i - 1;
            }

            Glide.with(context).load(mlist.get(mposition).getCardURL()).into(myImg.main2_img);

            final int finalMposition = mposition;
            myImg.main2_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    context.startActivity(new Intent(context,WebViewActivity.class));
                    Intent intent = new Intent(context, HomeWebActivity.class);
                    intent.putExtra("url",mlist.get(finalMposition).getContentURL());
                    intent.putExtra("type",mlist.get(finalMposition).getTitle());
                    context.startActivity(intent);
                }
            });


        } else if (itemViewType == 3) {
            MyHolder myHolder = (MyHolder) viewHolder;
            int mposition = i;
            if (mlist.size() > 0) {
                mposition = i - 1;
            }
            myHolder.attractions.setText(mlist.get(mposition).getTitle());
            myHolder.place.setText(mlist.get(mposition).getCity());
            myHolder.crazy_shopping_buy.setText(mlist.get(mposition).getIntro());
            Glide.with(context).load(mlist.get(mposition).getCardURL()).into(myHolder.cardURL);
            myHolder.button_buy.setText(mlist.get(mposition).getPrice());

            myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myOnItemClick != null) {
                        myOnItemClick.myOnItemClick(i - 1);
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
       /* if (bannerlist.size() > 0) {
            return mlist.size() - 1;
        }*/
        return mlist.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else if (mlist.get(position-1).getType().equals("bundle") ) {
            return 2;
        } else {
            return 3;
        }
    }

    public class MyBanner extends RecyclerView.ViewHolder {
        private Banner banner;

        public MyBanner(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner_item);
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private ImageView cardURL, attractions_img;
        private TextView attractions, place, button_buy, crazy_shopping_buy, people_buy;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            cardURL = itemView.findViewById(R.id.cardURL);
            attractions_img = itemView.findViewById(R.id.attractions_img);
            attractions = itemView.findViewById(R.id.attractions);
            place = itemView.findViewById(R.id.place);
            button_buy = itemView.findViewById(R.id.button_buy);
            crazy_shopping_buy = itemView.findViewById(R.id.crazy_shopping_buy);
            people_buy = itemView.findViewById(R.id.people_buy);
        }


    }

    public class MyImg extends RecyclerView.ViewHolder {
        private ImageView main2_img;

        public MyImg(@NonNull View itemView) {
            super(itemView);
            main2_img = itemView.findViewById(R.id.main2_img);
        }
    }


    private MyOnItemClick myOnItemClick;


    public interface MyOnItemClick {
        void myOnItemClick(int position);
    }

    public void setMyOnItemClick(MyOnItemClick myOnItemClick) {
        this.myOnItemClick = myOnItemClick;
    }


}

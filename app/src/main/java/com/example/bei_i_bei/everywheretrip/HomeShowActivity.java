package com.example.bei_i_bei.everywheretrip;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bei_i_bei.everywheretrip.base.BaseActivity;
import com.example.bei_i_bei.everywheretrip.base.Constants;
import com.example.bei_i_bei.everywheretrip.bean.Cancel_collectionBean;
import com.example.bei_i_bei.everywheretrip.bean.HomeShowBean;
import com.example.bei_i_bei.everywheretrip.presenter.HomeShowPresenter;
import com.example.bei_i_bei.everywheretrip.ui.main.activity.MainActivity;
import com.example.bei_i_bei.everywheretrip.util.SpUtil;
import com.example.bei_i_bei.everywheretrip.util.ToastUtil;
import com.example.bei_i_bei.everywheretrip.view.HomeShowView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.List;


public class HomeShowActivity extends BaseActivity<HomeShowView,HomeShowPresenter> implements HomeShowView ,View.OnClickListener{


    /**
     * 东京
     */
    private TextView mCity;
    /**
     * 银座
     */
    private TextView mTitle;
    /**
     * 6小时疯狂买买买攻略！
     */
    private TextView mIntro;
    private ImageView mCarousel;
    private ImageView mPhoto;
    /**
     * 伴米名字
     */
    private TextView mName;
    /**
     * 伴米的职业
     */
    private TextView mOccupation;
    /**
     * 北京市
     */
    private TextView mLocation;
    /**
     * 跑完步。。。
     */
    private TextView mIntroduction;
    private ImageView mUserPhoto;
    /**
     * 柳吱吱
     */
    private TextView mUserName;
    /**
     * 2017年3月11日
     */
    private TextView mCreatedAt;
    /**
     * contentcontentcontentcontentcontent
     */
    private TextView mContent;
    private int page;
    private RecyclerView mRv;
    private ArrayList<HomeShowBean.ResultBean.ReviewsBean> mlist;
    private ImageView mUserPhoto2;
    /**
     * 柳吱吱
     */
    private TextView mUserName2;
    /**
     * 2017年3月11日
     */
    private TextView mCreatedAt2;
    /**
     * contentcontentcontentcontentcontent
     */
    private TextView mContent2;
    private ImageView mUserPhoto3;
    /**
     * 柳吱吱
     */
    private TextView mUserName3;
    /**
     * 2017年3月11日
     */
    private TextView mCreatedAt3;
    /**
     * contentcontentcontentcontentcontent
     */
    private TextView mContent3;
    /**
     * 查看全部评价
     */
    private TextView mLookAtAll;
    private LinearLayout mLlShare;
    private ImageView mCollectionImg;
    /**
     * 收藏
     */
    private TextView mCollection;
    private LinearLayout mLlCollection;
    private boolean collected;
    private HomeShowBean.ResultBean.BanmiBean banmi;
    private List<HomeShowBean.ResultBean.ReviewsBean> reviews;
    private HomeShowBean.ResultBean.RouteBean route;
    private int id;


    @Override
    protected HomeShowPresenter initPresenter() {
        return new HomeShowPresenter();
    }

    @Override
    protected void initData() {
        mPresenter.getHomeShow(page);
    }

    @Override
    protected void initView() {
        page = getIntent().getIntExtra("page", 0);
       // id = getIntent().getIntExtra("id", 0);
        mCity = (TextView) findViewById(R.id.city);
        mTitle = (TextView) findViewById(R.id.title);
        mIntro = (TextView) findViewById(R.id.intro);
        mCarousel = (ImageView) findViewById(R.id.carousel);
        mPhoto = (ImageView) findViewById(R.id.photo);
        mName = (TextView) findViewById(R.id.name);
        mOccupation = (TextView) findViewById(R.id.occupation);
        mLocation = (TextView) findViewById(R.id.location);
        mIntroduction = (TextView) findViewById(R.id.introduction);
        mUserPhoto = (ImageView) findViewById(R.id.userPhoto);
        mUserName = (TextView) findViewById(R.id.userName);
        mCreatedAt = (TextView) findViewById(R.id.createdAt);
        mContent = (TextView) findViewById(R.id.content);

        mUserPhoto2 = (ImageView) findViewById(R.id.userPhoto2);
        mUserName2 = (TextView) findViewById(R.id.userName2);
        mCreatedAt2 = (TextView) findViewById(R.id.createdAt2);
        mContent2 = (TextView) findViewById(R.id.content2);

        mUserPhoto3 = (ImageView) findViewById(R.id.userPhoto3);
        mUserName3 = (TextView) findViewById(R.id.userName3);
        mCreatedAt3 = (TextView) findViewById(R.id.createdAt3);
        mContent3 = (TextView) findViewById(R.id.content3);
        mLookAtAll = (TextView) findViewById(R.id.look_at_all);
        mLookAtAll.setOnClickListener(this);
        mLlShare = (LinearLayout) findViewById(R.id.ll_share);
        mLlShare.setOnClickListener(this);
        mCollectionImg = (ImageView) findViewById(R.id.collection_img);
        mCollection = (TextView) findViewById(R.id.collection);
        mLlCollection = (LinearLayout) findViewById(R.id.ll_collection);
        mLlCollection.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_show;
    }

    @Override
    public void setHomeShow(HomeShowBean homeShow) {

        route = homeShow.getResult().getRoute();
        RequestOptions requestOptions = new RequestOptions().circleCrop();

        String title = route.getTitle();
        mTitle.setText(title);
        String city = route.getCity();
        mCity.setText(city);
        String intro = route.getIntro();
        mIntro.setText(intro);
        String cardURL = route.getCardURL();
        Glide.with(HomeShowActivity.this).load(cardURL).into(mCarousel);

        banmi = homeShow.getResult().getBanmi();
        String name = banmi.getName();
        mName.setText(name);
        String location = banmi.getLocation();
        mLocation.setText(location);
        String photo = banmi.getPhoto();
        Glide.with(HomeShowActivity.this).load(photo).apply(requestOptions).into(mPhoto);
        String occupation = banmi.getOccupation();
        mOccupation.setText(occupation);
        String introduction = banmi.getIntroduction();
        mIntroduction.setText(introduction);

        reviews = homeShow.getResult().getReviews();
        HomeShowBean.ResultBean.ReviewsBean reviewsBean = reviews.get(0);
        String userName = reviewsBean.getUserName();
        mUserName.setText(userName);

        String content = reviewsBean.getContent();
        mContent.setText(content);

        String createdAt = reviewsBean.getCreatedAt();
        mCreatedAt.setText(createdAt);

        String userPhoto = reviewsBean.getUserPhoto();
        Glide.with(HomeShowActivity.this).load(userPhoto).apply(requestOptions).into(mUserPhoto);


        HomeShowBean.ResultBean.ReviewsBean r2 = reviews.get(1);
        String userName2 = r2.getUserName();
        mUserName2.setText(userName2);

        String content2 = r2.getContent();
        mContent2.setText(content2);

        String createdAt2 = r2.getCreatedAt();
        mCreatedAt2.setText(createdAt2);

        String userPhoto2 = r2.getUserPhoto();
        Glide.with(HomeShowActivity.this).load(userPhoto2).apply(requestOptions).into(mUserPhoto2);

        HomeShowBean.ResultBean.ReviewsBean r3 = reviews.get(2);
        String userName3 = r3.getUserName();
        mUserName3.setText(userName3);

        String content3 = r3.getContent();
        mContent3.setText(content3);

        String createdAt3 = r3.getCreatedAt();
        mCreatedAt3.setText(createdAt3);

        String userPhoto3 = r3.getUserPhoto();
        Glide.with(HomeShowActivity.this).load(userPhoto3).apply(requestOptions).into(mUserPhoto3);


        collected = route.getisIsCollected();

        if (collected){
            mCollectionImg.setImageResource(R.mipmap.collect_highlight);
            mCollection.setText("已收藏");
        }else {
            mCollectionImg.setImageResource(R.mipmap.collect_default);
            mCollection.setText("未收藏");

        }
    }

    @Override
    public void setIsCollected(String s) {
        ToastUtil.showShort(s);
    }

    @Override
    public void setCancel_Collection(Cancel_collectionBean c) {
        String desc = c.getDesc();
        ToastUtil.showShort(desc);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;

                //查看全部
            case R.id.look_at_all:

                Intent intent = new Intent(HomeShowActivity.this, All_evaluationActivity.class);
                intent.putExtra("routeId",page);
                startActivity(intent);
                break;

                //分享
            case R.id.ll_share:
                    initshare(SHARE_MEDIA.SINA);
                break;

                //收藏
            case R.id.ll_collection:

                if (collected){
                    mCollectionImg.setImageResource(R.mipmap.collect_default);
                    mCollection.setText("未收藏");
                    collected =false;
//                    mPresenter.getIsCollected(page, (String) SpUtil.getParam(Constants.TOKEN,""));
                    mPresenter.getCancel_Collection(page);
                }else {
                    mCollectionImg.setImageResource(R.mipmap.collect_highlight);
                    mCollection.setText("已收藏");
                    collected =true;
                    mPresenter.getIsCollected(page, (String) SpUtil.getParam(Constants.TOKEN,""));

                }
                break;
        }
    }

    private void initshare(SHARE_MEDIA type) {

        UMImage umImage = new UMImage(this, route.getShareImageWechat());
                umImage.compressStyle  = UMImage.CompressStyle.SCALE;
                new ShareAction(HomeShowActivity.this).withText(route.getShareTitle())
                        .withMedia(umImage)
                        .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                        .setCallback(umShareListener).open();
    }
    UMShareListener umShareListener=new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            ToastUtil.showShort("开始分享");
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            ToastUtil.showShort("重新分享");

        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            ToastUtil.showShort("失败");

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            ToastUtil.showShort("取消");

        }
    };
}

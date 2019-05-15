package com.example.bei_i_bei.everywheretrip.net;


import com.example.bei_i_bei.everywheretrip.bean.All_evaluationBean;
import com.example.bei_i_bei.everywheretrip.bean.BalanceBean;
import com.example.bei_i_bei.everywheretrip.bean.BanMiBean;
import com.example.bei_i_bei.everywheretrip.bean.BanMiShowBean;
import com.example.bei_i_bei.everywheretrip.bean.Cancel_collectionBean;
import com.example.bei_i_bei.everywheretrip.bean.CollectionBean;
import com.example.bei_i_bei.everywheretrip.bean.DynamicBean;
import com.example.bei_i_bei.everywheretrip.bean.FollowBanMiBean;
import com.example.bei_i_bei.everywheretrip.bean.HomeShowBean;
import com.example.bei_i_bei.everywheretrip.bean.InformationBean;
import com.example.bei_i_bei.everywheretrip.bean.MainBean;
import com.example.bei_i_bei.everywheretrip.bean.RouteBean;
import com.example.bei_i_bei.everywheretrip.bean.To_ViewBean;
import com.example.bei_i_bei.everywheretrip.bean.UpDateUsreBean;
import com.example.bei_i_bei.everywheretrip.bean.UpLoadPictureBean;
import com.example.bei_i_bei.everywheretrip.bean.VerifyCodeBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author xts
 * Created by asus on 2019/4/2.
 */

public interface ApiService {
    String sBaseUrl = "http://yun918.cn/study/public/index.php/";
    int SUCCESS_CODE = 200;

    /**
     * 获取验证码
     *
     * @return
     */
    @GET("verify")
    Observable<VerifyCodeBean> getVerifyCode();


    /**
     * 首页数据 api.banmi.com/api/3.0/content/routesbundles?page=1
     */
    String MainUtl = "https://api.banmi.com/api/3.0/";

    @GET("content/routesbundles?")
    @Headers("banmi-app-token:UbUWXq7IAUBzJaVwSvUwVjsJbFpEtI2tRSgPUDvcFrvtin4OIpQsRsPF7vV63OWTwHhTLRck5aAyixw6b619f7HXQhti89trDntpsQptaR6Uk234TKZXN17qg1idjyX59VFg")
    Observable<MainBean> getMainData(@Query("page") int page);


    String BanMi = "https://api.banmi.com/api/3.0/";

    /**
     * 伴米页面数据
     */
    @GET("banmi?")
    @Headers("banmi-app-token:UbUWXq7IAUBzJaVwSvUwVjsJbFpEtI2tRSgPUDvcFrvtin4OIpQsRsPF7vV63OWTwHhTLRck5aAyixw6b619f7HXQhti89trDntpsQptaR6Uk234TKZXN17qg1idjyX59VFg")
    Observable<BanMiBean> getBanmi(@Query("page") int page);


    /**
     * 上传头像 http://yun918.cn/study/public/index.php/uploadheader
     */
    String UpLoadPrctureUtl = "https://yun918.cn/study/public/index.php/";

    @GET("uploadheader")
    Observable<UpLoadPictureBean> getUpLoadPrcture();

    /**
     * 修改用户信息  https://api.banmi.com/api/3.0/account/updateInfo
     */
    @POST("account/updateInfo")
    @Headers("banmi-app-token:UbUWXq7IAUBzJaVwSvUwVjsJbFpEtI2tRSgPUDvcFrvtin4OIpQsRsPF7vV63OWTwHhTLRck5aAyixw6b619f7HXQhti89trDntpsQptaR6Uk234TKZXN17qg1idjyX59VFg")
    @FormUrlEncoded
    Observable<UpDateUsreBean> getUpdate(@Field("userName") String userName,
                                         @Field("description") String description,
                                         @Field("gender") String gender,
                                         @Field("photo") String photo
    );

    /**
     * 首页线路详情 https://api.banmi.com/api/3.0/content/routes/196
     */
    @GET("content/routes/{page}")
    @Headers("banmi-app-token:UbUWXq7IAUBzJaVwSvUwVjsJbFpEtI2tRSgPUDvcFrvtin4OIpQsRsPF7vV63OWTwHhTLRck5aAyixw6b619f7HXQhti89trDntpsQptaR6Uk234TKZXN17qg1idjyX59VFg")
    Observable<HomeShowBean> getHomeShow(@Path("page") int page);

    /*
     * 余额 https://api.banmi.com/api/3.0/account/balance
     * */

    @GET("account/balance")
    @Headers("banmi-app-token:UbUWXq7IAUBzJaVwSvUwVjsJbFpEtI2tRSgPUDvcFrvtin4OIpQsRsPF7vV63OWTwHhTLRck5aAyixw6b619f7HXQhti89trDntpsQptaR6Uk234TKZXN17qg1idjyX59VFg")
    Observable<BalanceBean> getBalance();

    /*
     * 获取个人信息数据 https://api.banmi.com/api/3.0/account/info
     * */
    @GET("account/info")
    @Headers("banmi-app-token:UbUWXq7IAUBzJaVwSvUwVjsJbFpEtI2tRSgPUDvcFrvtin4OIpQsRsPF7vV63OWTwHhTLRck5aAyixw6b619f7HXQhti89trDntpsQptaR6Uk234TKZXN17qg1idjyX59VFg")
    Observable<InformationBean> getInfo();


    /*
     * 收藏 https://api.banmi.com/api/3.0/content/routes/49/like
     * */
    @POST("content/routes/{page}/like")
    Observable<ResponseBody> getIsCollected(@Path("page") int page, @Header("banmi-app-token") String token);


    /*
     * 取消收藏 https://api.banmi.com/api/3.0/content/routes/199/dislike
     * */

    @POST("content/routes/{page}/dislike")
    @Headers("banmi-app-token:UbUWXq7IAUBzJaVwSvUwVjsJbFpEtI2tRSgPUDvcFrvtin4OIpQsRsPF7vV63OWTwHhTLRck5aAyixw6b619f7HXQhti89trDntpsQptaR6Uk234TKZXN17qg1idjyX59VFg")
    Observable<Cancel_collectionBean> getCancel_colleaction(@Path("page") int page);

    /*
     * 收藏的内容 https://api.banmi.com/api/3.0/account/collectedRoutes
     * */

    @GET("account/collectedRoutes")
    @Headers("banmi-app-token:UbUWXq7IAUBzJaVwSvUwVjsJbFpEtI2tRSgPUDvcFrvtin4OIpQsRsPF7vV63OWTwHhTLRck5aAyixw6b619f7HXQhti89trDntpsQptaR6Uk234TKZXN17qg1idjyX59VFg")
    Observable<CollectionBean> getCollection();


    /*
     * 关注半米 https://api.banmi.com/api/3.0/banmi/57/follow
     * */

    @POST("banmi/{page}/follow")
    @Headers("banmi-app-token:UbUWXq7IAUBzJaVwSvUwVjsJbFpEtI2tRSgPUDvcFrvtin4OIpQsRsPF7vV63OWTwHhTLRck5aAyixw6b619f7HXQhti89trDntpsQptaR6Uk234TKZXN17qg1idjyX59VFg")
    Observable<FollowBanMiBean> getFollowBanMi(@Path("page") int page);


    /*
     * 伴米详情页面--伴米动态 https://api.banmi.com/api/3.0/banmi/57?page=1
     * */
    @GET("banmi/{banmiId}?page=1")
    @Headers("banmi-app-token:UbUWXq7IAUBzJaVwSvUwVjsJbFpEtI2tRSgPUDvcFrvtin4OIpQsRsPF7vV63OWTwHhTLRck5aAyixw6b619f7HXQhti89trDntpsQptaR6Uk234TKZXN17qg1idjyX59VFg")
    Observable<BanMiShowBean> getBanMishow(@Path("banmiId") int banmiId);


    /*
     * 线路 https://api.banmi.com/api/3.0/banmi/57/routes
     * */
    @GET("banmi/{page}/routes")
    @Headers("banmi-app-token:UbUWXq7IAUBzJaVwSvUwVjsJbFpEtI2tRSgPUDvcFrvtin4OIpQsRsPF7vV63OWTwHhTLRck5aAyixw6b619f7HXQhti89trDntpsQptaR6Uk234TKZXN17qg1idjyX59VFg")
    Observable<RouteBean> getRoute(@Path("page") int page);


    /*
     * 查看更多评论 https://api.banmi.com/api/3.0/content/routes/57/reviews?page=1
     * */
    @GET("content/routes/{routeId}/reviews?")
    @Headers("banmi-app-token:UbUWXq7IAUBzJaVwSvUwVjsJbFpEtI2tRSgPUDvcFrvtin4OIpQsRsPF7vV63OWTwHhTLRck5aAyixw6b619f7HXQhti89trDntpsQptaR6Uk234TKZXN17qg1idjyX59VFg")
    Observable<All_evaluationBean> getAll_evaluation(@Path("routeId") int routeId, @Query("page") int page);



    /*查看 https://api.banmi.com/api/3.0/content/bundles
    * */
    @GET("content/bundles")
    @Headers("banmi-app-token:UbUWXq7IAUBzJaVwSvUwVjsJbFpEtI2tRSgPUDvcFrvtin4OIpQsRsPF7vV63OWTwHhTLRck5aAyixw6b619f7HXQhti89trDntpsQptaR6Uk234TKZXN17qg1idjyX59VFg")
    Observable<To_ViewBean> getViewBean();


    /*动态 https://api.banmi.com/api/3.0/banmi/25?page=1
    * */
    @GET("banmi/{banmiId}?")
    @Headers("banmi-app-token:UbUWXq7IAUBzJaVwSvUwVjsJbFpEtI2tRSgPUDvcFrvtin4OIpQsRsPF7vV63OWTwHhTLRck5aAyixw6b619f7HXQhti89trDntpsQptaR6Uk234TKZXN17qg1idjyX59VFg")
    Observable<DynamicBean> getDynamic(@Path("banmiId")int banmiId,@Query("page")int page);

}



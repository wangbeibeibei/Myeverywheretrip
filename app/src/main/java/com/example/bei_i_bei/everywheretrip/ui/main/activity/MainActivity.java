package com.example.bei_i_bei.everywheretrip.ui.main.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bei_i_bei.everywheretrip.CollectionActivity;
import com.example.bei_i_bei.everywheretrip.MyFocusActivity;
import com.example.bei_i_bei.everywheretrip.PersonalActivity;
import com.example.bei_i_bei.everywheretrip.R;
import com.example.bei_i_bei.everywheretrip.base.BaseActivity;
import com.example.bei_i_bei.everywheretrip.base.Constants;
import com.example.bei_i_bei.everywheretrip.bean.BalanceBean;
import com.example.bei_i_bei.everywheretrip.bean.GetVersionInfoBean;
import com.example.bei_i_bei.everywheretrip.bean.InformationBean;
import com.example.bei_i_bei.everywheretrip.presenter.MainPresenter;
import com.example.bei_i_bei.everywheretrip.ui.main.adapter.VpMainAdapter;
import com.example.bei_i_bei.everywheretrip.ui.main.fragment.BanMiFragment;
import com.example.bei_i_bei.everywheretrip.ui.main.fragment.MainFragment;
import com.example.bei_i_bei.everywheretrip.util.InstallUtil;
import com.example.bei_i_bei.everywheretrip.util.SpUtil;
import com.example.bei_i_bei.everywheretrip.util.ToastUtil;
import com.example.bei_i_bei.everywheretrip.util.Tools;
import com.example.bei_i_bei.everywheretrip.view.MainView;
import com.jaeger.library.StatusBarUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView, View.OnClickListener {
    private static final String TAG = "MainActivity";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.toolbar_img)
    ImageView toolbarImg;
    @BindView(R.id.nv)
    NavigationView nv;
    @BindView(R.id.dl)
    DrawerLayout dl;

    @BindView(R.id.my_purse_money)
    TextView myPurseMoney;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.idiographic)
    TextView idiographic;
    @BindView(R.id.feedback_img)
    ImageView feedbackImg;
    @BindView(R.id.feedback)
    TextView feedback;
    @BindView(R.id.icon_me_banben)
    ImageView iconMeBanben;
    @BindView(R.id.head_portrait)
    ImageView headPortrait;
    @BindView(R.id.rl_collection)
    RelativeLayout rlCollection;
    @BindView(R.id.banben)
    TextView banben;
    @BindView(R.id.rl_nv)
    RelativeLayout rlNv;


    private VpMainAdapter vpMainAdapter;
    private RelativeLayout mRlMyFoucus;
    private InformationBean.ResultBean result;
    private RequestOptions requestOptions;
    private String version;
    private File sd;
    //定义notification实用的ID
     private static final int NO_3 =0x3;
    private NotificationCompat.Builder builder;
    private long count;

    public static void startAct(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);

    }


    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;

    }

    @Override
    protected void initData() {
        //获取余额
        mPresenter.getBalanceBean();
        //获取个人信息
        mPresenter.getInformation();

        //获取版本信息
        mPresenter.getVersionlnfo();
    }

    @Override
    protected void initView() {
        initSD();
        GetVersionInfo();
        //亮色模式 会将状态栏文字改为黑色
        StatusBarUtil.setLightMode(this);
        toolbar.setTitle("");
        // toolbar.setNavigationIcon(R.drawable.abc);
        requestOptions = new RequestOptions().circleCrop();
        Glide.with(this).load(SpUtil.getParam(Constants.HEADIMG, "")).apply(requestOptions).into(toolbarImg);
        toolbarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl.openDrawer(Gravity.LEFT);
            }
        });
        nv.setItemIconTintList(null);
        setSupportActionBar(toolbar);

        ArrayList<String> tablist = new ArrayList<>();
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new MainFragment());
        fragments.add(new BanMiFragment());

        tab.addTab(tab.newTab().setText("首页").setIcon(R.drawable.maintab_select));
        tab.addTab(tab.newTab().setText("伴米").setIcon(R.drawable.banmitab_select));

        vpMainAdapter = new VpMainAdapter(getSupportFragmentManager(), fragments, tablist);
        vp.setAdapter(vpMainAdapter);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));


        mRlMyFoucus = (RelativeLayout) findViewById(R.id.rl_my_foucus);
        mRlMyFoucus.setOnClickListener(this);
        Glide.with(this).load(SpUtil.getParam(Constants.HEADIMG, "")).apply(requestOptions).into(headPortrait);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rl_my_foucus:
                startActivity(new Intent(this, MyFocusActivity.class));
                break;
        }
    }

    @Override
    public void setBalance(BalanceBean balance) {

        BalanceBean.ResultBean result = balance.getResult();
        String balance1 = result.getBalance();
        //  myPurseMoney.setText(balance1+"");

        Log.i("balance1", "balance1余额：" + balance1 + ",余额：" + myPurseMoney + "---" + result.toString());
    }

    @Override
    public void setInformation(InformationBean information) {

        result = information.getResult();
        //零钱
        String balance = result.getBalance();
        myPurseMoney.setText(balance);

        //用户昵称
        String userName = result.getUserName();
        name.setText(userName);
        //  name.setText(Constants.USERNAME);
        //个性签名
        String description = result.getDescription();
        idiographic.setText(description);

        Log.i("info", "name：" + userName + ",balance：" + balance + ",result：" + result.toString());


    }

    @Override
    public void setVersionlnfo(GetVersionInfoBean bean) {

        version = bean.getResult().getInfo().getVersion();
    }


    @OnClick({R.id.head_portrait, R.id.rl_collection, R.id.banben})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_portrait:
                Intent intent = new Intent(MainActivity.this, PersonalActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_collection:
                startActivity(new Intent(this, CollectionActivity.class));
                break;

            case R.id.banben:
                GetVersionInfo();
                break;


        }
    }

    private void GetVersionInfo() {

        String versionName = Tools.getVersionName();
        if (!versionName.equals(version)) {
            final AlertDialog show = new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("发现最新版本" + " " + version)//+"--"+versionName
                    .setNegativeButton("取消", null)
                    .setPositiveButton("下载", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            initNotifition();
                            ToastUtil.showShort("正在下载，请稍后···");
                            ok(sd + "/" + "abc.apk");
                        }
                    })
                    .show();
        } else {
            ToastUtil.showShort("已经是最新版本了！");
        }

    }


    private void initSD() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            readSD();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
                    .WRITE_EXTERNAL_STORAGE}, 100);
        }
    }

    private void readSD() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            sd = Environment.getExternalStorageDirectory();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                if (grantResults.length > 0 && grantResults[0] == PackageManager
                        .PERMISSION_GRANTED) {
                    readSD();
                }
                break;
        }
    }

    private void ok(final String path) {
             String url="http://cdn.banmi.com/banmiapp/apk/banmi_330.apk";
        //  String url="http://ww1.sinaimg.cn/large/0065oQSqly1g2pquqlp0nj30n00yiq8u.jpg";

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                InputStream inputStream = body.byteStream();
                long max = body.contentLength();

                //文件下载保存
                saveFile(inputStream, path, max);
            }
        });
    }

    private void saveFile(InputStream inputStream, final String path, long max) {

        try {
            FileOutputStream fos = new FileOutputStream(new File(path));

            count = 0;

            int length = -1;
            byte[] bys = new byte[1024 * 10];

            while ((length = inputStream.read(bys)) != -1) {
                fos.write(bys, 0, length);

                count += length;

                Log.e(TAG, "count: " + count + ", max:" + max);
                double v = (double) count / (double) max;
                final int v1 = (int) (v * 100);


                //进度条和视频播放SurfaceView可以直接在子线程中刷新
               /* pb.setMax((int) max);
                pb.setProgress((int) count);*/
                //下载进度提示
             /*   builder.setContentText("下载" + count + "%");
                builder.setProgress(((int) max), ((int) count), true);*/
                initNotifition(v1,max);
            }


            inputStream.close();
            fos.close();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "下载完毕", Toast.LENGTH_SHORT).show();

                    //apk安装处理
                    mPath = path;
                    InstallUtil.installApk(MainActivity.this, path);
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 8.0安装处理
     */
    private String mPath = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == InstallUtil.UNKNOWN_CODE) {
            InstallUtil.installApk(MainActivity.this, mPath);//再次执行安装流程，包含权限判等
        }
    }

    private void initNotifition(int v1, long max) {
        builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.zhifubao);
        builder.setContentTitle("下载");
        builder.setContentText("正在下载");

        final NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NO_3, builder.build());

        builder.setProgress(((int)max), v1, false);
        builder.setContentText("下载" + v1 + "%");
        if (count ==max){
            builder.setProgress(((int)max), ((int) this.count), true);
            //下载完成后更改标题以及提示信息
            builder.setContentTitle("开始安装");
            builder.setContentText("安装中...");
//            manager.cancel(NO_3);
        }
        //下载完成后更改标题以及提示信息
        //builder.setContentTitle("开始安装");
       // builder.setContentText("安装中...");
        //设置进度为不确定，用于模拟安装
     //   builder.setProgress(0, ((int)count), true);
        manager.notify(NO_3, builder.build());

//        manager.cancel(NO_3);


        //下载以及安装线程模拟
       /* new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    builder.setProgress(100, i, false);
                    manager.notify(NO_3, builder.build());
                    //下载进度提示
                    builder.setContentText("下载" + count + "%");

                    try {
                        Thread.sleep(50);//演示休眠50毫秒

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                builder.setContentText("下载" + count + "%");
                //下载完成后更改标题以及提示信息
                builder.setContentTitle("开始安装");
                builder.setContentText("安装中...");
                //设置进度为不确定，用于模拟安装
                builder.setProgress(0, ((int)count), true);
                manager.notify(NO_3, builder.build());
                // manager.cancel(NO_3);//设置关闭通知栏
            }
        }).start();*/
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //用户昵称
        String userName = result.getUserName();
        name.setText((String) SpUtil.getParam(Constants.NAME, ""));

        //个性签名
        String description = result.getDescription();
        idiographic.setText((String) SpUtil.getParam(Constants.SIGNATURE, ""));

        Glide.with(this).load(SpUtil.getParam(Constants.HEADIMG, "")).apply(requestOptions).into(headPortrait);

    }


}

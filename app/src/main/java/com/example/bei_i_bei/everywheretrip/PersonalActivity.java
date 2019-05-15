package com.example.bei_i_bei.everywheretrip;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bei_i_bei.everywheretrip.base.Constants;
import com.example.bei_i_bei.everywheretrip.bean.UpDateUsreBean;
import com.example.bei_i_bei.everywheretrip.bean.UpLoadPictureBean;
import com.example.bei_i_bei.everywheretrip.net.ApiService;
import com.example.bei_i_bei.everywheretrip.ui.main.activity.MainActivity;
import com.example.bei_i_bei.everywheretrip.util.SpUtil;
import com.example.bei_i_bei.everywheretrip.util.ToastUtil;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;

import java.io.File;
import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PersonalActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PersonalActivity";
    private ImageView mToolbarImg;
    private static final int ALBUM_CODE = 200;
    private static final int CAMERA_CODE = 100;
    /**
     * 个人信息
     */
    private TextView mToolbarText;
    private Toolbar mPersonalToolbar;
    private ImageView mHeadPortrait;

    /**
     * 伴小米
     */
    private TextView mName;
    /**
     * 保密
     */
    private TextView mGender;
    /**
     * 四肢不全五体不勤
     */
    private TextView mPersonalizedSignature;
    /**
     * 修改密码
     */
    private TextView mChangePassword;
    /**
     * 绑定手机
     */
    private TextView mBindPhone;
    /**
     * 退出登录
     */
    private Button mLogOut;
    private RelativeLayout mHeadPortraitLayout;
    private File mFile;
    private Uri mImageUri;
    private LinearLayout mLl;
    private RelativeLayout mRlUsername;
    private RelativeLayout mRlPersonalizedSignature;
    private RequestOptions requestOptions;

    /**
     * 个人信息
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        initView();
    }

    private void initView() {
        StatusBarUtil.setLightMode(this);
        /*ToolBar设置
         * */
        requestOptions = new RequestOptions().circleCrop();

        mToolbarImg = (ImageView) findViewById(R.id.toolbar_img);
        mToolbarImg.setOnClickListener(this);
        mToolbarText = (TextView) findViewById(R.id.toolbar_text);
        mToolbarText.setOnClickListener(this);
        mPersonalToolbar = (Toolbar) findViewById(R.id.personal_toolbar);

        mHeadPortrait = (ImageView) findViewById(R.id.head_portrait);
        mHeadPortrait.setOnClickListener(this);
        mName = (TextView) findViewById(R.id.name);
        mName.setOnClickListener(this);
        mGender = (TextView) findViewById(R.id.gender);
        mGender.setOnClickListener(this);
        mPersonalizedSignature = (TextView) findViewById(R.id.personalized_signature);
        mPersonalizedSignature.setOnClickListener(this);
        mChangePassword = (TextView) findViewById(R.id.change_password);
        mChangePassword.setOnClickListener(this);
        mBindPhone = (TextView) findViewById(R.id.bind_phone);
        mBindPhone.setOnClickListener(this);
        mLogOut = (Button) findViewById(R.id.log_out);
        mLogOut.setOnClickListener(this);
        mHeadPortraitLayout = (RelativeLayout) findViewById(R.id.head_portrait_layout);
        mHeadPortraitLayout.setOnClickListener(this);
        mLl = (LinearLayout) findViewById(R.id.ll);
        mRlUsername = (RelativeLayout) findViewById(R.id.rl_username);
        mRlUsername.setOnClickListener(this);
        mRlPersonalizedSignature = (RelativeLayout) findViewById(R.id.rl_personalized_signature);
        mRlPersonalizedSignature.setOnClickListener(this);

        mPersonalToolbar.setTitle("");
        setSupportActionBar(mPersonalToolbar);

        mName.setText((String)SpUtil.getParam(Constants.NAME,""));
        mPersonalizedSignature.setText((String)SpUtil.getParam(Constants.SIGNATURE,""));
        Glide.with(this).load(SpUtil.getParam(Constants.HEADIMG,"")).apply(requestOptions).into(mHeadPortrait);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;

            /*返回键图标*/
            case R.id.toolbar_img:
                finish();
                break;

            /*ToolBar文字*/
            case R.id.toolbar_text:
                break;

            /*头像*/
            case R.id.head_portrait:
                break;

            /*姓名*/
            case R.id.name:
                break;

            /*性别*/
            case R.id.gender:
                break;

            /*个性签名*/
            case R.id.personalized_signature:
                break;

            /*修改密码*/
            case R.id.change_password:
                break;

            /*绑定手机*/
            case R.id.bind_phone:
                break;

            /*退出登录*/
            case R.id.log_out:
                startActivity(new Intent(PersonalActivity.this, LoginActivity.class));
                break;


            /*头像条目*/
            case R.id.head_portrait_layout:
                /*修改头像*/
                //  modify_the_picture();
                openpopuwindow();
                break;
            case R.id.rl_username:
                Intent intent = new Intent(this, UpPersonalActivity.class);
                String name = mName.getText().toString();
                intent.putExtra("name", name);
                startActivityForResult(intent, 1);
                break;

            /*个性签名*/
            case R.id.rl_personalized_signature:
                Intent intent2 = new Intent(this, Individuality_signatureActivity.class);
                String persignature = mPersonalizedSignature.getText().toString();
                intent2.putExtra("persignature", persignature);
                startActivityForResult(intent2, 3);
                break;
        }
    }

    private void openpopuwindow() {

        View inflate = LayoutInflater.from(this).inflate(R.layout.layout_popu, null);

        final PopupWindow popupWindow = new PopupWindow(inflate, Gallery.LayoutParams.MATCH_PARENT, Gallery.LayoutParams.WRAP_CONTENT);

        popupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        // popupWindow.setBackgroundDrawable(new PaintDrawable());
        //点击popupwindow以外的地方和返回键 可以消失
        popupWindow.setOutsideTouchable(true);
        //popupwindow放在界面的下面
        popupWindow.showAtLocation(mLl, Gravity.BOTTOM, 0, 0);

        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        inflate.findViewById(R.id.quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

                // popupWindow回收后，界面的灰色消失
                WindowManager.LayoutParams lp = PersonalActivity.this.getWindow().getAttributes();
                lp.alpha = 1f;
                PersonalActivity.this.getWindow().setAttributes(lp);
            }
        });
        inflate.findViewById(R.id.open_album).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData2();
            }
        });
        inflate.findViewById(R.id.open_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
    }

    private void modify_the_picture() {
        Toast.makeText(this, "被点击了", Toast.LENGTH_SHORT).show();
        new AlertDialog.Builder(PersonalActivity.this)
                .setNegativeButton("打开相机", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //相机
                        initData();
                    }
                })
                .setPositiveButton("打开相册", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //相册
                        initData2();

                    }
                })
                .show();
    }

    /*打开相册*/
    private void initData2() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            //相册
            openAlbum();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2000);
        }
    }

    private void openAlbum() {

        //启动相册
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, ALBUM_CODE);
    }

    /*打开相机*/
    private void initData() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            //相机
            openCamera();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1000);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    private void openCamera() {
        //创建文件用于保存图片getExternalCacheDir
        mFile = new File(getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
        if (!mFile.exists()) {
            try {
                mFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //适配7.0,  等到对应的mImageUri路径地址
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mImageUri = Uri.fromFile(mFile);
        } else {
            //第二个参数要和清单文件中的配置保持一致
            mImageUri = FileProvider.getUriForFile(this, "com.baidu.upload.provider", mFile);
        }
        //启动相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);//将拍照图片存入mImageUri
        startActivityForResult(intent, CAMERA_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {//判断回调成功
            if (requestCode == CAMERA_CODE) {//拍照
                //拍照后的图片上传
                uploadFile(mFile);
            } else if (requestCode == ALBUM_CODE) {//相册
                //获取到相册选中后的图片URI路径
                Uri imageUri = data.getData();

                //文件上传，将Uri路径转换为File对象
                //处理uri,7.0以后的fileProvider 把URI 以content provider 方式 对外提供的解析方法
                File file = getFileFromUri(imageUri, this);
                if (file.exists()) {
                    uploadFile(file);
                }
            }
        }
        if (requestCode == 1 && resultCode == 2) {
            String name = data.getStringExtra("name");
            SpUtil.setParam(Constants.NAME,name);
            mName.setText(name);
            initUpData();
        }
        if (requestCode == 3 && resultCode == 4) {
            String mUpdateSignature = data.getStringExtra("mUpdateSignature");
            mPersonalizedSignature.setText(mUpdateSignature);
            SpUtil.setParam(Constants.SIGNATURE,mUpdateSignature);
            initUpData();
        }
    }

    public void initUpData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.MainUtl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        //head_portrait:name,gender,personalized_signature

        final String s = mName.getText().toString();
        final String s1 = mPersonalizedSignature.getText().toString();
        final String s2 = mGender.getText().toString();

        Observable<UpDateUsreBean> update = apiService.getUpdate(s, s1, s2, "");
        update.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpDateUsreBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpDateUsreBean upDateUsreBean) {

                        ToastUtil.showShort("请求成功");

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShort(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private File getFileFromUri(Uri imageUri, PersonalActivity personalActivity) {
        if (imageUri == null) {
            return null;
        }
        switch (imageUri.getScheme()) {
            case "content":
                return getFileFromContentUri(imageUri, personalActivity);
            case "file":
                return new File(imageUri.getPath());
            default:
                return null;
        }

    }

    private File getFileFromContentUri(Uri imageUri, PersonalActivity personalActivity) {

        if (imageUri == null) {
            return null;
        }
        File file = null;
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};
        ContentResolver contentResolver = personalActivity.getContentResolver();
        Cursor cursor = contentResolver.query(imageUri, filePathColumn, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            filePath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
            cursor.close();

            if (!TextUtils.isEmpty(filePath)) {
                file = new File(filePath);
            }
        }
        return file;
    }

    private void uploadFile(File mFile) {
        String url = "http://yun918.cn/study/public/file_upload.php";

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        //  file-->RequestBody
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), mFile);

        // 创建多媒体 请求对象
        MultipartBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("key", "H1808C")
                .addFormDataPart("file", mFile.getName(), requestBody)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                final UpLoadPictureBean upLoadBean = gson.fromJson(string, UpLoadPictureBean.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (upLoadBean != null) {
                            if (upLoadBean.getCode() == 200) {
                                Toast.makeText(PersonalActivity.this, upLoadBean.getRes(), Toast.LENGTH_SHORT).show();
                                SpUtil.setParam(Constants.HEADIMG,upLoadBean.getData().getUrl());
                                Glide.with(PersonalActivity.this).load(upLoadBean.getData().getUrl()).apply(requestOptions).into(mHeadPortrait);
                                Log.e(TAG, "run: " + upLoadBean.getData().getUrl());
                            } else {
                                Toast.makeText(PersonalActivity.this, upLoadBean.getRes(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
    }
}

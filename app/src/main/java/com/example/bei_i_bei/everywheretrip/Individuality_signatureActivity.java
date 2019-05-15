package com.example.bei_i_bei.everywheretrip;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bei_i_bei.everywheretrip.bean.UpLoadPictureBean;
import com.example.bei_i_bei.everywheretrip.net.ApiService;
import com.jaeger.library.StatusBarUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Individuality_signatureActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mToolbarImg;
    /**
     * 完成
     */
    private TextView mComplete;
    private Toolbar mToolbar;
    private View mViewV;
    private EditText mUpdateSignature;
    private RelativeLayout mRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individuality_signature);
        initView();
        initData();
    }

    private void initData() {
        String persignature = getIntent().getStringExtra("persignature");
        mUpdateSignature.setText(persignature);
    }

    private void initView() {
        StatusBarUtil.setLightMode(this);
        mToolbarImg = (ImageView) findViewById(R.id.toolbar_img);
        mToolbarImg.setOnClickListener(this);
        mComplete = (TextView) findViewById(R.id.complete);
        mComplete.setOnClickListener(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mViewV = (View) findViewById(R.id.view_v);
        mUpdateSignature = (EditText) findViewById(R.id.update_signature);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
//      mUpdateSignature.setSelection(mUpdateSignature.length());//将光标移至文字末尾


        mRl = (RelativeLayout) findViewById(R.id.rl);
        mRl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.toolbar_img:
                finish();
                break;
            case R.id.complete:

                Intent intent = new Intent();
                intent.putExtra("mUpdateSignature", mUpdateSignature.getText().toString());
                setResult(4, intent);

                finish();
                break;
            case R.id.rl:
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                mRl.requestFocus();

                break;
        }
    }


}

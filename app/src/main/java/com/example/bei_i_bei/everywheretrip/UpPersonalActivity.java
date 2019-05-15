package com.example.bei_i_bei.everywheretrip;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bei_i_bei.everywheretrip.base.Constants;
import com.example.bei_i_bei.everywheretrip.util.SpUtil;
import com.jaeger.library.StatusBarUtil;

public class UpPersonalActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mToolbarImg;
    private Toolbar mToolbar;
    private EditText mUpdateName;
    /**
     * 完成
     */
    private TextView mComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_personal);
        initView();
        initData();
    }

    private void initData() {

        String name = getIntent().getStringExtra("name");
        mUpdateName.setText(name);
    }

    private void initView() {
        StatusBarUtil.setLightMode(this);
        mToolbarImg = (ImageView) findViewById(R.id.toolbar_img);
        mToolbarImg.setOnClickListener(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mUpdateName = (EditText) findViewById(R.id.update_name);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mComplete = (TextView) findViewById(R.id.complete);
        mComplete.setOnClickListener(this);
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
                String up_name = mUpdateName.getText().toString();
                intent.putExtra("name",up_name);
//                SpUtil.setParam(Constants.NAME,up_name);
                setResult(2,intent);
                finish();
                break;
        }
    }

}

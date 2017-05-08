package com.xmnode.demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.xmnode.demo.R;

/**
 * Created by user on 2016/7/5.
 */
public class BaseActivity extends FragmentActivity {

    public TextView mTitleTv;

    //TODO 一些通用设置
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initBar(String title) {
        mTitleTv = (TextView) findViewById(R.id.tvTitle);
        mTitleTv.setText(title);
        View backIv = findViewById(R.id.ivBack);
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

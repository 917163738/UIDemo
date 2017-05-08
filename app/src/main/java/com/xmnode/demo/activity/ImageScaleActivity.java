package com.xmnode.demo.activity;

import android.os.Bundle;

import com.xmnode.demo.R;
import com.xmnode.demo.base.BaseActivity;
import com.xmnode.demo.fragment.ImageScaleFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageScaleActivity extends BaseActivity implements ImageScaleFragment.OnTapListener{
    private static final String TAG = ImageScaleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_scale);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick(R.id.iv_image1)
    void showImage1() {
        ImageScaleFragment fragment = new ImageScaleFragment();
        fragment.setOnTapListener(this);
        fragment.setImageId(R.drawable.test1);
        getSupportFragmentManager().beginTransaction().replace(R.id.rl_scale_main, fragment).commit();
    }

    @OnClick(R.id.iv_image2)
    void showImage2() {
        ImageScaleFragment fragment = new ImageScaleFragment();
        fragment.setOnTapListener(this);
        fragment.setImageId(R.drawable.test2);
        getSupportFragmentManager().beginTransaction().replace(R.id.rl_scale_main, fragment).commit();
    }


    @OnClick(R.id.ivBack)
    void back() {
        finish();
    }

    @Override
    public void onTap(ImageScaleFragment fragment) {
        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }
}

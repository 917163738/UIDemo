package com.xmnode.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xmnode.demo.R;
import com.xmnode.demo.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.tvSendcode)
    TextView mTvSendcode;
    @Bind(R.id.tv_ripper)
    TextView mTvRipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    @Bind(R.id.ivBack)
    ImageView ivBack;

    @Bind(R.id.tvTitle)
    TextView tvTitle;

    @Bind(R.id.tvMultiDisplay)
    TextView tvMultiDisplay;

    @Bind(R.id.tvRefresh)
    TextView tvRefresh;

    @Bind(R.id.tvRoundDisplay)
    TextView tvRoundDisplay;
    @Bind(R.id.tvWebview)
    TextView tvWebview;

    @OnClick(R.id.tvRoundDisplay)
    void launchRound() {
        Intent intent = new Intent();
        intent.setClass(this, RoundAcitivty.class);
        startActivity(intent);
    }

    @OnClick(R.id.tvMultiDisplay)
    void launchMulti() {
        Intent intent = new Intent();
        intent.setClass(this, MasonryAcitivty.class);
        startActivity(intent);
    }

    @OnClick(R.id.tvRefresh)
    void launchRefresh() {
        Intent intent = new Intent();
        intent.setClass(this, RefreshListAcitivty.class);
        startActivity(intent);
    }


    @OnClick(R.id.tvImageCrop)
    void launchImageCrop() {
        Intent intent = new Intent(this, CropImageActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tvImageGallery)
    void launchImageGallery() {
        Intent intent = new Intent(MainActivity.this, ViewPagerActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tvImageScale)
    void launchImageScale() {
        Intent intent = new Intent();
        intent.setClass(this, ImageScaleActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tvQrcode)
    void launchQrcode() {
        Intent intent = new Intent();
        intent.setClass(this, QrcodeActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tvSlidingMenu)
    void launchSlidingMenu() {
        Intent intent = new Intent(MainActivity.this, DrawerLayoutActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tvSlidingTab)
    void launchSlidingTab() {
        Intent intent = new Intent(this, SlidingTabActivity.class);
        startActivity(intent);

    }
    @OnClick(R.id.tvGuaguaka)
    void launchGuaguaka() {
        Intent intent = new Intent(this, GuaguakaActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.tvWebview)
    void launchWebview() {
        startActivity(new Intent(this, WebViewActivity.class));
    }
    @OnClick(R.id.tvToolbar)
    void launchToolbar() {
        startActivity(new Intent(this, ToolbarActivity.class));
    }
    @OnClick(R.id.tvTagView)
    void launchTagActivity() {
        startActivity(new Intent(this, TagActivity.class));
    }
    @OnClick(R.id.tvVolume)
    void launchVolume() {
        startActivity(new Intent(this, VolumeActivity.class));
    }


    private void init() {
        ivBack.setVisibility(View.GONE);
        tvTitle.setText("Demo");
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @OnClick(R.id.tvSendcode)
    public void onClick() {
        startActivity(new Intent(this, SendCodeActivity.class));
    }
    @OnClick(R.id.tv_ripper)
    public void tv_ripper() {
        startActivity(new Intent(this, RippleActivity.class));
    }
    @OnClick(R.id.tvGif)
    public void tvGif() {
        startActivity(new Intent(this, GifActivity.class));
    }
    @OnClick(R.id.tvScroll)
    public void tvScroll() {
        startActivity(new Intent(this, GoodsDetailActivity.class));
    }
    @OnClick(R.id.tvAddCart)
    public void tvAddCart() {
        startActivity(new Intent(this, AddCartActivity.class));
    }
    @OnClick(R.id.tvToolbarDemo)
    public void tvToolbarDemo() {
        startActivity(new Intent(this, AvatarToolbarSample.class));
    }
    @OnClick(R.id.tvIntent)
    public void tvIntent() {
        startActivity(new Intent(this, Main2Activity.class));
    }
}

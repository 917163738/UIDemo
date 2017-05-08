package com.xmnode.demo.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.xmnode.demo.R;
import com.xmnode.demo.adapter.SimplePagerAdapter;
import com.xmnode.demo.base.BaseActivity;
import com.xmnode.demo.views.CircleIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ViewPagerActivity extends BaseActivity {
    ViewPager mViewPager;
    CircleIndicator mIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        initWidgets();
        initialData();
    }



    private void initWidgets() {
        ImageView ivBack= (ImageView) findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mIndicator = (CircleIndicator) findViewById(R.id.indicator);

    }

    private void initialData() {
      List<View> viewList = new ArrayList<View>();
        Random random = new Random();
        for(int i=0;i<5;i++){
            View view = new View(this);
            view.setBackgroundColor(0xff000000| random.nextInt(0x00ffffff));
            viewList.add(view);
        }
        mViewPager.setAdapter(new SimplePagerAdapter(viewList));
        //要先确保ViewPager已经设置了Adapter才能调用CircleIndicator.setViewPager
        mIndicator.setViewPager(mViewPager);
        //viewpager可设置切换动画
        mViewPager.setPageTransformer(true,new DepthPageTransformer());
    }
    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
}

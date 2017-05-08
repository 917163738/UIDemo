package com.xmnode.demo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import com.xmnode.demo.R;
import com.xmnode.demo.views.MyScrollView;

/**
 * 防商品详情页下滑阻尼效果
 */
public class GoodsDetailActivity extends AppCompatActivity {

    private MyScrollView mScrollView;
    private View tv_float;

    private int mTitleTopAndHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);

        tv_float = findViewById(R.id.tv_float);

        final View tv_title = findViewById(R.id.tv_title);

        int measuredHeightAndState = tv_title.getMeasuredHeightAndState();
        int measuredWidthAndState = tv_title.getMeasuredWidthAndState();
        tv_title.measure(measuredWidthAndState, measuredHeightAndState);

        mTitleTopAndHeight = tv_title.getTop() + tv_title.getHeight();

        tv_title.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout()
            {
                mTitleTopAndHeight = tv_title.getTop();
                Log.d("top", mTitleTopAndHeight + "");
                tv_title.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

//        tv_title.post(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                mTitleTopAndHeight = tv_title.getTop();
//            }
//        });

        tv_float.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                mScrollView.onTouchEvent(event);
                return false;
            }
        });

        mScrollView = (MyScrollView) findViewById(R.id.sv_main);


        //方式一:
//        mScrollView.setOnScrollListener(new MyScrollView.OnScrollListener()
//        {
//            @Override
//            public void onScroll(int scrollY, int state)
//            {
//                Log.d("onScroll: ", scrollY + "" + "----------- state:" + state);
//
//                if (scrollY <= mTitleTopAndHeight)
//                {
//                    tv_float.setVisibility(View.INVISIBLE);
//                } else
//                {
//                    tv_float.setVisibility(View.VISIBLE);
//                }
//            }
//        });

        //方式二：
        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener()
        {
            @Override
            public void onScrollChanged()
            {
                int scrollY = mScrollView.getScrollY();
                if (scrollY <= mTitleTopAndHeight)
                {
                    tv_float.setVisibility(View.INVISIBLE);
                } else
                {
                    tv_float.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}

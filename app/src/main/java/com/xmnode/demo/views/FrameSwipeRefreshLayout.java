package com.xmnode.demo.views;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;

/**
 *
 * 下拉刷新
 */
public class FrameSwipeRefreshLayout extends SwipeRefreshLayout {

    public FrameSwipeRefreshLayout(Context context) {
        super(context);
        setColorSchemeResources();
    }

    public FrameSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setColorSchemeResources();
    }

    private void setColorSchemeResources(){
        setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
//        setColorSchemeResources(android.R.color.black);
    }

    /**
     * 只针对AbsListView这些需要加入emptyview(不然SwipeRefreshLayout中AbsListView和emptyview显示会有异常)
     */
    private AbsListView absListView;
    public void setAbsListView(AbsListView view){
        absListView = view;
    }

    @Override
    public boolean canChildScrollUp() {
        if (absListView != null) {
            View child = absListView.getChildAt(0);
            return child != null && child.getTop() != 0;
        }
        return super.canChildScrollUp();
    }
}
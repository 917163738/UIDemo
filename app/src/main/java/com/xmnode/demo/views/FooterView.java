package com.xmnode.demo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xmnode.demo.R;


/**
 *
 */
public class FooterView extends RelativeLayout {


    public FooterView(Context context) {
        this(context, null);
    }


    public FooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.footer, this, true);
        if (isInEditMode()) {
            return;
        }
        findViews();
    }

    /**
     * 设置背景色
     * @param bg_color_res
     */
    public void setBackground(int bg_color_res) {
        rlFooterBg.setBackgroundResource(bg_color_res);
    }

    private TextView tvFooter;
    private ImageView ivFooter;
    private RelativeLayout rlFooterBg;

    private void findViews() {
        tvFooter = (TextView) findViewById(R.id.tvFooter);
        ivFooter = (ImageView) findViewById(R.id.ivFooter);
        rlFooterBg = (RelativeLayout) findViewById(R.id.rlFooterBg);
    }

    public void hasMore() {
        tvFooter.setVisibility(VISIBLE);
        ivFooter.setVisibility(GONE);
    }

    public void noMore() {
        tvFooter.setVisibility(GONE);
        ivFooter.setVisibility(VISIBLE);
    }
}

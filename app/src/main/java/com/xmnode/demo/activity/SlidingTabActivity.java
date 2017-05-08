package com.xmnode.demo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;

import com.xmnode.demo.R;
import com.xmnode.demo.base.BaseActivity;
import com.xmnode.demo.fragment.TabAFragment;
import com.xmnode.demo.fragment.TabBFragment;
import com.xmnode.demo.fragment.TabCFragment;
import com.xmnode.demo.views.PagerSlidingTabStrip;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SlidingTabActivity extends BaseActivity {
    private static final String TAG = SlidingTabActivity.class.getSimpleName();

    DisplayMetrics mDisplayMetrics;

    TabAFragment mTabAFragment;
    TabBFragment mTabBFragment;
    TabCFragment mTabCFragment;
    String[] titles = { "AA", "BB", "CC" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_tab);
        ButterKnife.bind(this);
        initView();
    }

    @Bind(R.id.vp_pager)
    ViewPager mViewPager;

    @Bind(R.id.sliding_tab)
    PagerSlidingTabStrip mPagerSlidingTabStrip;

    @OnClick(R.id.ivBack)
    void back() {
        finish();
    }

    private void initView(){
        mDisplayMetrics = getResources().getDisplayMetrics();
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(),titles));
        mPagerSlidingTabStrip.setViewPager(mViewPager);
    }

    public class MyAdapter extends FragmentPagerAdapter {
        String[] _titles;
        public MyAdapter(FragmentManager fm, String[] titles) {
            super(fm);
            _titles=titles;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return _titles[position];
        }

        @Override
        public int getCount() {
            return _titles.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (mTabAFragment == null) {
                        mTabAFragment = new TabAFragment();
                    }
                    return mTabAFragment;
                case 1:
                    if (mTabBFragment == null) {
                        mTabBFragment = new TabBFragment();
                    }
                    return mTabBFragment;
                case 2:
                    if (mTabCFragment == null) {
                        mTabCFragment = new TabCFragment();
                    }
                    return mTabCFragment;
                default:
                    return null;
            }
        }
    }
}

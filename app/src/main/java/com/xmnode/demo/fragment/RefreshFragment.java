package com.xmnode.demo.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;


import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xmnode.demo.R;
import com.xmnode.demo.model.ListItem4Test;
import com.xmnode.demo.views.BaseAdapterHelper;
import com.xmnode.demo.views.FooterView;
import com.xmnode.demo.views.FrameSwipeRefreshLayout;
import com.xmnode.demo.views.QuickAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 *
 * 下拉刷新
 *
 */
public class RefreshFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_refresh_list, null);
        ButterKnife.bind(this,view);
        init();
        return view;
    }

    @Bind(R.id.lvRefresh)
    ListView lvRefresh;

    @Bind(R.id.ptr_layout)
    FrameSwipeRefreshLayout ptr_layout;


    /**
     * 初始化
     */
    private void init() {
        initCustom();
        initUI();
        setListeners();
        initData(true);
    }

    /**
     * 数据加载
     */
    public void initData(boolean firstPage) {
        if(firstPage){
            mRefreshList.clear();
        }

        for(int i=1;i<15;i++){
            int random = new Random().nextInt()*i;
            ListItem4Test item = new ListItem4Test();
            item.setContent("content(for test)----"+ random);
            item.setTitle("title--"+(random*random));
            mRefreshList.add(item);
        }

        mAdapter.notifyDataSetChanged();

    }

    private boolean mGettingData = false;
    private boolean mHasNextPage = true;

    /**
     * 设置监听
     */
    private void setListeners() {
        lvRefresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO click
            }
        });
        ptr_layout.setOnRefreshListener(this);

        lvRefresh.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 判断滚动到底部
                        if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
                            if (!mGettingData && mHasNextPage) {
                                onRefresh();
                            }
                        }
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private FooterView vFooter;
    /**
     * 判断是否有下一页
     *
     */
    private void hasNextPage() {
        if ( mRefreshList.size() < 100 ) {
            mHasNextPage = true;
            vFooter.hasMore();
        } else {
            vFooter.noMore();
            mHasNextPage = false;
        }
    }

    /**
     * 界面UI初始化
     */
    private void initUI() {
        vFooter = new FooterView(getActivity());
        lvRefresh.addFooterView(vFooter);
        vFooter.hasMore();
        initAdapter();
    }

    /**
     * 通用数据初始化
     */
    private void initCustom() {
    }


    private List<ListItem4Test> mRefreshList = new ArrayList<ListItem4Test>();
    private QuickAdapter<ListItem4Test> mAdapter;

    private void initAdapter() {

        if (mAdapter == null) {
            mAdapter = new QuickAdapter<ListItem4Test>(getActivity(), R.layout.listitem_refresh, mRefreshList) {
                @Override
                protected void convert(BaseAdapterHelper helper, ListItem4Test item, int position) {
                    helper.setText(R.id.tvRefreshTitle, item.getTitle());
                    helper.setText(R.id.tvRefreshContent, item.getContent());
                    setAvatar(helper,item);
                }

                /**
                 * 设置头像
                 * @param helper
                 * @param item
                 */
                private void setAvatar(BaseAdapterHelper helper, ListItem4Test item){
                    //暂时用默认图
                    SimpleDraweeView sdvAvater = helper.getView(R.id.sdvAvatar);
                    sdvAvater.setImageURI(Uri.parse("res://com.xmnode.demo/"+R.drawable.ic_avatar_default));
                }
            };
        }
        lvRefresh.setAdapter(mAdapter);
    }

    /**
     * refresh complete
     */
    private void setRefreshComplete(){
        if(ptr_layout != null && ptr_layout.isRefreshing()){
            ptr_layout.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        //TODO get data
        ptr_layout.postDelayed(new Runnable() {
            @Override
            public void run() {
                setRefreshComplete();
                initData(false);
                hasNextPage();
            }
        },3000L);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
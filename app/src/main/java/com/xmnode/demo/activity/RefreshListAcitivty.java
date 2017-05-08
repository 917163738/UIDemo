package com.xmnode.demo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.xmnode.demo.R;
import com.xmnode.demo.fragment.RefreshFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 *
 * 下拉刷新
 * 
 */
public class RefreshListAcitivty extends FragmentActivity implements OnClickListener {

	private static final String TAG = RefreshListAcitivty.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_refresh_list);
		ButterKnife.bind(this);
		init();
	}

	private void init(){
		initUi();
		initData();
		setListeners();
	}

	private void setListeners(){

	}

	@Bind(R.id.tvTitle)
	TextView tvTitle;

	@OnClick(R.id.ivBack)
	void back(){
		finish();
	}

	private void initUi(){
		tvTitle.setText("下拉刷新");
	}

	private RefreshFragment fmRefresh;

	private void initData() {
		fmRefresh = new RefreshFragment();
		getSupportFragmentManager().beginTransaction().replace(R.id.fmRefresh,
				fmRefresh, "fmRefresh").commit();
	}

	@Override
	public void onClick(View v) {
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}

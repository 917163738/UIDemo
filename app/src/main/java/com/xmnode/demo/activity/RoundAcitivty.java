package com.xmnode.demo.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xmnode.demo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 *
 * 圆角
 * 
 */
public class RoundAcitivty extends FragmentActivity {

	private static final String TAG = RoundAcitivty.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_round);
		ButterKnife.bind(this);
		init();
	}

	@Bind(R.id.tvTitle)
	TextView tvTitle;

	@OnClick(R.id.ivBack)
	void back(){
		finish();
	}

	private void initUi(){
		tvTitle.setText("圆角控件");
	}

	@Bind(R.id.sdvRound1)
	SimpleDraweeView sdvRound1;

	@Bind(R.id.sdvRound2)
	SimpleDraweeView sdvRound2;

	private void init(){
		initUi();
		sdvRound1.setImageURI(Uri.parse("res://com.xmnode.demo/"+R.drawable.test1));
		sdvRound2.setImageURI(Uri.parse("res://com.xmnode.demo/"+R.drawable.test2));
	}


}

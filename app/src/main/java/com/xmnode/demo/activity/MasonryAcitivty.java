package com.xmnode.demo.activity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;

import com.xmnode.demo.R;
import com.xmnode.demo.adapter.MasonryAdapter;
import com.xmnode.demo.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 *
 * 瀑布流
 * 
 */
public class MasonryAcitivty extends FragmentActivity {

	private static final String TAG = MasonryAcitivty.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_masonry);
		ButterKnife.bind(this);
		init();
	}

	@Bind(R.id.tvTitle)
	TextView tvTitle;

	@OnClick(R.id.ivBack)
	void back(){
		finish();
	}

	private StaggeredGridLayoutManager mLayoutManager;
	private MasonryAdapter mAdapter;
	private List<String> mImageUrls = new ArrayList<>();

	private void initUi(){
		tvTitle.setText("图片");

		mAdapter = new MasonryAdapter(mImageUrls);
		rcvMasonry.setAdapter(mAdapter);

		SpacesItemDecoration decoration = new SpacesItemDecoration(8);
		rcvMasonry.addItemDecoration(decoration);
	}

	@Bind(R.id.rcvMasonry)
	RecyclerView rcvMasonry;


	private void init(){
		initCustom();
		initUi();
		initData();
		setListeners();
	}

	private void initCustom(){
		mLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
		mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
		rcvMasonry.setLayoutManager(mLayoutManager);
		//rcvMasonry.setPadding(0, 0, 0, 0);
	}

	private void setListeners(){
		rcvMasonry.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);
				mLayoutManager.invalidateSpanAssignments();
			}

			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
			}
		});
	}

	private void initData(){
		Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null );
		if ( cursor != null ) {
			if (cursor.moveToFirst()) {
				do {
					long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID ) );
					Uri imageUri = Uri.parse( MediaStore.Images.Media.EXTERNAL_CONTENT_URI + "/" + id );
					mImageUrls.add(imageUri.toString());
				} while (cursor.moveToNext());
			}
			cursor.close();
			cursor = null;
		}
		mAdapter.notifyDataSetChanged();
	}
}

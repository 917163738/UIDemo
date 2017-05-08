package com.xmnode.demo.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xmnode.demo.R;
import com.xmnode.demo.base.BaseActivity;

public class RippleActivity extends BaseActivity {

    String[] data = {"item1", "item2", "item3", "item4", "item5", "item6", "item7", "item8"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple);
        initBar(getResources().getString(R.string.main_ripple));

        ListView listView = (ListView) findViewById(R.id.listView);
        assert listView != null;
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 20;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View inflate = View.inflate(RippleActivity.this, R.layout.item_ripple, null);
                TextView titleTv = (TextView) inflate.findViewById(R.id.textView);
                titleTv.setText("item");
                return inflate;
            }
        });
    }

}

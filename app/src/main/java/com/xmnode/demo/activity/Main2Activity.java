package com.xmnode.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xmnode.demo.R;
import com.xmnode.demo.model.Apple;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private List<Apple> mAppleList = new ArrayList<>();
    private MyListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ListView listView = (ListView) findViewById(R.id.myList);
        adapter= new MyListAdapter(this);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onNewIntent(Intent intent) {
//        String action="";
//        action=intent.getAction();
//        if(action.equals("action_swc")){
            mAppleList= (List<Apple>) intent.getSerializableExtra("key_swc");
            adapter.notifyDataSetChanged();
//        }
    }

    public void testClick(View v){
        Intent it =new Intent();
        it.setClass(this,TestActivity.class);
        startActivity(it);
    }
    private class MyListAdapter extends BaseAdapter {
        private LayoutInflater mInflater;


        public MyListAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mAppleList.size();
        }

        @Override
        public Object getItem(int position) {
            return mAppleList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = new ViewHolder();
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.test_item, null);
                holder.nameTV = (TextView) convertView.findViewById(R.id.name);
                holder.imageTV = (ImageView) convertView.findViewById(R.id.image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Apple item = (Apple) getItem(position);
            holder.nameTV.setText(item.name);
            holder.imageTV.setVisibility(item.isSelect ? View.VISIBLE : View.INVISIBLE);
            return convertView;
        }

        public class ViewHolder {
            public TextView nameTV;
            public ImageView imageTV;
        }
    }
}

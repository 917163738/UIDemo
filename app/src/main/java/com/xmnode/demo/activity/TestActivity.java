package com.xmnode.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xmnode.demo.R;
import com.xmnode.demo.model.Apple;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    private List<Apple> mAppleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        for (int i = 0; i < 30; i++) {
            Apple apple = new Apple();
            apple.name = "swc" + i;
            mAppleList.add(apple);
        }
        ListView listView = (ListView) findViewById(R.id.myList);
        final MyListAdapter adapter = new MyListAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAppleList.get(position).isSelect = !mAppleList.get(position).isSelect;
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void testClick(View v) {
        Intent it=new Intent();
        List<Apple> list=new ArrayList<>();
        for (int i=0;i<mAppleList.size();i++){
            if(mAppleList.get(i).isSelect){
                list.add(mAppleList.get(i));
            }
        }
        it.putExtra("key_swc", (Serializable) list);
//        it.setAction("action_swc");
        it.setClass(this,Main2Activity.class);
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

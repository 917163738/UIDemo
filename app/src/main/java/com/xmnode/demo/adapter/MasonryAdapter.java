package com.xmnode.demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xmnode.demo.R;
import com.xmnode.demo.utils.ImgUtils;

import java.util.List;


public class MasonryAdapter extends RecyclerView.Adapter<MasonryAdapter.MasonryViewHolder>{
    private List<String> mImageUrls;


    public MasonryAdapter(List<String> list) {
        mImageUrls = list;
    }

    @Override
    public MasonryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_masonry, viewGroup, false);
        return new MasonryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MasonryViewHolder holder, int position) {
        holder.ivPhoto.setImageResource(R.drawable.img_default);//占位图
        ImgUtils.setImageIconAnsyCachePre(mImageUrls.get(position),holder.ivPhoto,R.drawable.img_default);
    }


    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    //viewholder
    public static class MasonryViewHolder extends  RecyclerView.ViewHolder{

        private ImageView ivPhoto;

        public MasonryViewHolder(View itemView){
            super(itemView);
            ivPhoto = (ImageView) itemView.findViewById(R.id.ivPhoto);
        }
    }


}

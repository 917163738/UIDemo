package com.xmnode.demo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xmnode.demo.R;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageScaleFragment extends Fragment {

    private OnTapListener mOnTapListener;
    private int mImageId;


    public ImageScaleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_image_scale, container, false);
        PhotoView photoView = (PhotoView)view.findViewById(R.id.pv_image);
        photoView.setImageResource(mImageId);
        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                if (mOnTapListener != null) {
                    mOnTapListener.onTap(ImageScaleFragment.this);
                }
            }
        });
        return view;
    }

    public void setImageId(int id) {
        mImageId = id;
    }

    public void setOnTapListener(OnTapListener l) {
        mOnTapListener = l;
    }

    public interface OnTapListener {
        public void onTap (ImageScaleFragment fragment);
    }

}

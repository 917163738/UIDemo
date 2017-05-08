package com.xmnode.demo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xmnode.demo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabCFragment extends Fragment {


    public TabCFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        View v=new View(getActivity());
        v.setLayoutParams(params);
        v.setBackgroundResource(android.R.color.holo_purple);
        return v;
    }

}

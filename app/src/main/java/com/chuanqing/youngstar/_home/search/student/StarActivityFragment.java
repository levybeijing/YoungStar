package com.chuanqing.youngstar._home.search.student;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chuanqing.youngstar.R;

/**
 * 首页搜索 星活动
 * A simple {@link Fragment} subclass.
 */
public class StarActivityFragment extends Fragment {


    public StarActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_star_activity, container, false);
    }

}

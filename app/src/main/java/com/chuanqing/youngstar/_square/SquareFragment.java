package com.chuanqing.youngstar._square;


import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar._square.follow.FollowFragment;
import com.chuanqing.youngstar._square.starshow.StarShowFragment;
import com.chuanqing.youngstar.myadapter.TablayoutAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 广场页面
 * A simple {@link Fragment} subclass.
 */
public class SquareFragment extends Fragment {
    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @BindView(R.id.tab_layout1)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager1)
    ViewPager mViewpager;
    private ArrayList<android.support.v4.app.Fragment> mFragments = new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_square, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showinfo();
        setTtitle();
    }

    /**
     * 展示信息
     */
    private void showinfo() {
        StarShowFragment starShowFragment = new StarShowFragment();
        FollowFragment followFragment = new FollowFragment();
        mFragments.add(starShowFragment);
        mFragments.add(followFragment);
        list.add("星秀");
        list.add("关注");
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewpager);
        mViewpager.setAdapter(new TablayoutAdapter(getFragmentManager(),mFragments,list));

    }

    /**
     * 写入title名字
     */
    @BindView(R.id.common_left_img)
    ImageView left_img;
    @BindView(R.id.common_center_title)
    TextView tv_title;
    @BindView(R.id.common_rigth_img)
    ImageView right_img;
    private void setTtitle(){
        tv_title.setText("广场");
        left_img.setVisibility(View.GONE);
        right_img.setVisibility(View.VISIBLE);
    }


}

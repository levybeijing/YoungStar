package com.chuanqing.youngstar._active;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar._home.searchstudent.StudentShowActivity;
import com.chuanqing.youngstar._square.follow.FollowFragment;
import com.chuanqing.youngstar._square.starshow.StarShowFragment;
import com.chuanqing.youngstar.myadapter.TablayoutAdapter;
import com.chuanqing.youngstar.tools.SharedPFUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.chuanqing.youngstar.MainActivity.identity;

/**
 * 星活动页面
 * A simple {@link Fragment} subclass.
 */
public class StarActivityFragment extends Fragment {

    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_satr_activity, container, false);
        ButterKnife.bind(this,view);
        return view;
    }
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTtitle();
        showinfo();
    }
    /**
     * 展示信息
     */
    private void showinfo() {
        StarleitaiFragment starShowFragment = new StarleitaiFragment();
        StarbangFragment starbangFragment = new StarbangFragment();
        ZhaopinFragment zhaopinFragment = new ZhaopinFragment();

        if ((int)SharedPFUtils.getParam(getContext(),"identity",4) ==1){
            mFragments.add(zhaopinFragment);
            list.add("星职场");
        }
        mFragments.add(starShowFragment);
        mFragments.add(starbangFragment);

        list.add("星擂台");
        list.add("星榜");
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
        tv_title.setText("风云榜");
        left_img.setVisibility(View.GONE);
        right_img.setVisibility(View.VISIBLE);
        right_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,StudentShowActivity.class);
                startActivity(intent);
            }
        });
    }
}

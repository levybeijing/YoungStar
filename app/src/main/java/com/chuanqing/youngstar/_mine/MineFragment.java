package com.chuanqing.youngstar._mine;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar._mine.student.FragmentStatus;
import com.chuanqing.youngstar._mine.student.FragmentWorks;
import com.chuanqing.youngstar.myadapter.AdapterMineVP;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的页面
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment implements View.OnClickListener {

    private List<Fragment> list=new ArrayList<>();

    private ViewPager vp;

    public MineFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine_stu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        vp = view.findViewById(R.id.vp_mines);
        TabLayout tab = view.findViewById(R.id.tab_mines);

        RadioButton rb1 = view.findViewById(R.id.rb1_mines);
        RadioButton rb2 = view.findViewById(R.id.rb2_mines);
        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);


        list.add(new FragmentStatus());
        list.add(new FragmentWorks());
        vp.setAdapter(new AdapterMineVP(getChildFragmentManager(),list));

        tab.setTabMode(TabLayout.MODE_FIXED);
        tab.setupWithViewPager(vp);

        TabLayout.Tab tab1 = tab.getTabAt(0);
        tab1.setText("动态");
        TabLayout.Tab tab2 = tab.getTabAt(1);
        tab2.setText("作品集");

//        //设置tab1
//        TabLayout.Tab tab1 = tab.getTabAt(0);
////        tab1.setCustomView(R.layout.item_tab_mall);//给每一个tab设置view
////        tab1.getCustomView().findViewById(R.id.iv_item_tab_mall).setSelected(true);//第一个tab被选中
////        ImageView iv1 = tab1.getCustomView().findViewById(R.id.iv_item_tab_mall);
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
////            iv1.setBackground(getResources().getDrawable(R.mipmap.tab_reme));//设置tab上的文字
////        }
//        //设置tab2
//        TabLayout.Tab tab2 = tab.getTabAt(1);
////        tab2.setCustomView(R.layout.item_tab_mall);//给每一个tab设置view
////        ImageView iv2 = tab2.getCustomView().findViewById(R.id.iv_item_tab_mall);
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
////            iv2.setBackground(getResources().getDrawable(R.mipmap.tab_gift));//设置tab上的文字
////        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rb1_mines:
                vp.setCurrentItem(0);
                break;
            case R.id.rb2_mines:
                vp.setCurrentItem(1);
                break;
        }
    }
}

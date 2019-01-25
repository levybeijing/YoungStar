package com.chuanqing.youngstar._mine.student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.myadapter.AdapterMineVP;

import java.util.ArrayList;
import java.util.List;

public class InterestActivity extends BaseActivity {

    private ViewPager vp;
    private List<Fragment> list=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);

        initView();
    }

    private void initView() {

        findViewById(R.id.iv_back_interests).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TabLayout tab = findViewById(R.id.tab_interests);
        vp = findViewById(R.id.vp_interests);

        list.add(new FragmentCareS());
        list.add(new FragmentCareC());

        AdapterMineVP adapter=new AdapterMineVP(getSupportFragmentManager(),list);
        vp.setAdapter(adapter);

        tab.setTabMode(TabLayout.MODE_FIXED);
        tab.setupWithViewPager(vp);
        TabLayout.Tab tab1 = tab.getTabAt(0);
        tab1.setText("星秀");
        TabLayout.Tab tab2 = tab.getTabAt(1);
        tab2.setText("公司");

    }
}

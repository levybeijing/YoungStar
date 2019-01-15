package com.chuanqing.youngstar._mine.student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class InterestActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);

        initView();
    }

    private void initView() {
        TabLayout tab = findViewById(R.id.tab_interests);
        ViewPager vp = findViewById(R.id.vp_interests);
    }
}

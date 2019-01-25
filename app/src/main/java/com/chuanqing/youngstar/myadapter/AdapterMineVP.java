package com.chuanqing.youngstar.myadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class AdapterMineVP extends FragmentPagerAdapter {

    private List<Fragment> mfragmentList;

    public AdapterMineVP(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        mfragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mfragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mfragmentList.size();
    }
}

package com.chuanqing.youngstar.myadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.mybean.HomeFenleiVPBean;
import com.chuanqing.youngstar.tools.Api;

import java.util.ArrayList;

public class HomeViewpager2 extends PagerAdapter {
    ArrayList<HomeFenleiVPBean> arrayList;
    Context context;
    int a = 0;
    public HomeViewpager2(Context context, ArrayList<HomeFenleiVPBean> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }
    public void setTag(int position){
        a=position;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
//        if (arrayList.size()>2){
//            return arrayList.size()*10;
//        }else {
//            return arrayList.size();
//        }

        return arrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    /**
     * 返回要显示的view，即要显示的视图
     * @param container
     * @param postion
     * @return
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int postion) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.viewpager_title_items,null);
        TextView tv_title = view.findViewById(R.id.tv_title);
        if (a==postion){
            tv_title.setTextColor(context.getResources().getColor(R.color.white));
        }else {
            tv_title.setTextColor(context.getResources().getColor(R.color.mainColor));
        }
        tv_title.setText(arrayList.get(postion).getData().get(postion).getName());
        container.addView(view); //添加到父控件

        return  view;
    }

    //销毁条目
    @Override
    public void destroyItem(@NonNull ViewGroup container, int i, @NonNull Object object) {
        container.removeView((View) object);
    }
}

package com.chuanqing.youngstar.myadapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar._home.searchstudent.SearchStatusActivity;
import com.chuanqing.youngstar.mybean.HomeFenleiVPBean;

import java.util.ArrayList;

import static com.chuanqing.youngstar._home.HomeFragment.zhi;

public class HomeViewpager2 extends PagerAdapter {
    ArrayList<HomeFenleiVPBean> arrayList;
    Context context;
    public HomeViewpager2(Context context, ArrayList<HomeFenleiVPBean> arrayList){
        this.context = context;
        this.arrayList = arrayList;
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
        if (zhi==postion){
            tv_title.setTextColor(context.getResources().getColor(R.color.white));
        }else {
            tv_title.setTextColor(context.getResources().getColor(R.color.mainColor));
        }
        tv_title.setText(arrayList.get(postion).getData().get(postion).getName());
        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SearchStatusActivity.class);
                context.startActivity(intent);
            }
        });

        container.addView(view); //添加到父控件

        return  view;
    }

    //销毁条目
    @Override
    public void destroyItem(@NonNull ViewGroup container, int i, @NonNull Object object) {
        container.removeView((View) object);
    }

    private int mChildCount = 0;

    @Override public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    @Override public int getItemPosition(Object object) {
        if (mChildCount > 0) {
            mChildCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

}

package com.chuanqing.youngstar.myadapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar._home.searchstudent.SearchStatusActivity;
import com.chuanqing.youngstar.login._student.LableActivity;
import com.chuanqing.youngstar.mybean.HomeFenleiVPBean;
import com.chuanqing.youngstar.tools.Api;

import java.util.ArrayList;

public class HomeViewpager1 extends PagerAdapter {
    ArrayList<HomeFenleiVPBean> arrayList;
    Context context;
    public HomeViewpager1(Context context,ArrayList<HomeFenleiVPBean> arrayList){
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
        int i = postion%arrayList.size();
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.home_viewpager1,null);
        GridLayout gridLayout = view.findViewById(R.id.gridlayout);
        ImageView img1 = view.findViewById(R.id.home_img1);
        ImageView img2 = view.findViewById(R.id.home_img2);
        ImageView img3 = view.findViewById(R.id.home_img3);
        ImageView img4 = view.findViewById(R.id.home_img4);
        ImageView img5 = view.findViewById(R.id.home_img5);
        ImageView img6 = view.findViewById(R.id.home_img6);
        TextView tv_1 = view.findViewById(R.id.home_text1);
        TextView tv_2 = view.findViewById(R.id.home_text2);
        TextView tv_3 = view.findViewById(R.id.home_text3);
        TextView tv_4 = view.findViewById(R.id.home_text4);
        TextView tv_5 = view.findViewById(R.id.home_text5);
        TextView tv_6 = view.findViewById(R.id.home_text6);

        Glide.with(context)
                .load(Api.ossurl+arrayList.get(i).getData().get(i).getDeatil().get(0).getImg())
                .error(R.mipmap.my11)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(img1);
        tv_1.setText(arrayList.get(i).getData().get(i).getDeatil().get(0).getUser_code());

        Glide.with(context)
                .load(Api.ossurl+arrayList.get(i).getData().get(i).getDeatil().get(1).getImg())
                .error(R.mipmap.my11)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(img2);
        tv_2.setText(arrayList.get(i).getData().get(i).getDeatil().get(1).getUser_code());

        Glide.with(context)
                .load(Api.ossurl+arrayList.get(i).getData().get(i).getDeatil().get(2).getImg())
                .error(R.mipmap.my11)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(img3);
        tv_3.setText(arrayList.get(i).getData().get(i).getDeatil().get(2).getUser_code());

        Glide.with(context)
                .load(Api.ossurl+arrayList.get(i).getData().get(i).getDeatil().get(3).getImg())
                .error(R.mipmap.my11)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(img4);
        tv_4.setText(arrayList.get(i).getData().get(i).getDeatil().get(3).getUser_code());

        Glide.with(context)
                .load(Api.ossurl+arrayList.get(i).getData().get(i).getDeatil().get(4).getImg())
                .error(R.mipmap.my11)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(img5);
        tv_5.setText(arrayList.get(i).getData().get(i).getDeatil().get(4).getUser_code());

        Glide.with(context)
                .load(Api.ossurl+arrayList.get(i).getData().get(i).getDeatil().get(5).getImg())
                .error(R.mipmap.my11)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(img6);
        tv_6.setText(arrayList.get(i).getData().get(i).getDeatil().get(5).getUser_code());

        container.addView(view); //添加到父控件
        gridLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SearchStatusActivity.class);
                context.startActivity(intent);
            }
        });
        return  view;
    }

    //销毁条目
    @Override
    public void destroyItem(@NonNull ViewGroup container, int i, @NonNull Object object) {
        container.removeView((View) object);
    }
}

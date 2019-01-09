package com.chuanqing.youngstar.myadapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.tools.Api;


public class MyAdapter extends PagerAdapter {
    private String[] mData;
    private Context mContext;

    public MyAdapter(Context ctx, String[] data) {
        this.mContext = ctx;
        this.mData = data;
    }

    @Override
    public int getCount() {
        if (mData.length==1){
            return mData.length;// 返回数据的个数
        }else {
            return mData.length*10;// 返回数据的个数
        }

    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {//子View显示
        final int i = position%mData.length;
        View view = View.inflate(container.getContext(), R.layout.item, null);
        ImageView imageView = view.findViewById(R.id.iv_icon);
        Glide.with(mContext)
                .load(Api.ossurl+mData[i])
                .placeholder(R.mipmap.my11)
                .error(R.mipmap.my11)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(mContext, "当前条目：" + i, Toast.LENGTH_SHORT).show();
            }
        });

        container.addView(view);//添加到父控件
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;// 过滤和缓存的作用
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);//从viewpager中移除掉
    }

}

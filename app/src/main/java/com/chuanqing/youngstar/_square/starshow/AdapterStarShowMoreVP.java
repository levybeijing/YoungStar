package com.chuanqing.youngstar._square.starshow;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.tools.Api;
import java.util.List;

public class AdapterStarShowMoreVP extends PagerAdapter {

    Context context;
    List<String> list;

    public AdapterStarShowMoreVP(Context context, List<String> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        if (list==null){
            return 0;
        }
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vp_starshowmore, null);
        ImageView iv = view.findViewById(R.id.iv_vp_starshowmore);
        Glide.with(context).load(Api.ossurl+list.get(position)).into(iv);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

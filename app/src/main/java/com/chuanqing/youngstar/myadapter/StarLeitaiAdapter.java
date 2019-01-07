package com.chuanqing.youngstar.myadapter;

import android.content.Context;
import android.os.Build;
import android.sax.TextElementListener;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.mybean.StarLeitaiBean;
import com.chuanqing.youngstar.tools.Api;

import java.util.ArrayList;

public class StarLeitaiAdapter extends BaseAdapter {
    Context context;
    ArrayList<StarLeitaiBean> arrayList;
    public StarLeitaiAdapter(Context context, ArrayList<StarLeitaiBean> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.star_show_leitai,parent,false);
            view.setTag(new ViewHolder(view));
        }
        viewHolder = (ViewHolder) view.getTag();
        StarLeitaiBean starLeitaiBean = arrayList.get(position);


        Glide.with(context).load(Api.ossurl+starLeitaiBean.getData().getPageInfo().getList().get(position).getList_img())
                .placeholder(R.mipmap.my166)
                .error(R.mipmap.my166)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(new ViewTarget<View, GlideDrawable>(viewHolder.layout) {
                    //括号里为需要加载的控件
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResourceReady(GlideDrawable resource,
                                                GlideAnimation<? super GlideDrawable> glideAnimation) {
                        this.view.setBackground(resource.getCurrent());
                    }
                });
        viewHolder.tv_name.setText(starLeitaiBean.getData().getPageInfo().getList().get(position).getActivity_name());
        viewHolder.tv_time.setText(starLeitaiBean.getData().getPageInfo().getList().get(position).getCurrentTime());
        viewHolder.tv_number.setText(starLeitaiBean.getData().getPageInfo().getList().get(position).getNum());
        return view;
    }

    private static class ViewHolder{
        LinearLayout layout;
        TextView tv_name;
        TextView tv_number;
        TextView tv_time;
        private ViewHolder(View view){
            layout = view.findViewById(R.id.starleitai_layout);
            tv_name = view.findViewById(R.id.starleitai_name);
            tv_number = view.findViewById(R.id.starleitai_number);
            tv_time = view.findViewById(R.id.starleitai_time);
        }
    }
}

package com.chuanqing.youngstar.myadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.mybean.HomeYanyiBean;
import com.chuanqing.youngstar.mybean.StarZhaopinBean;
import com.chuanqing.youngstar.tools.Api;
import com.chuanqing.youngstar.tools.CircleImageView;

import java.util.ArrayList;

public class StarZhaopinAdapter extends BaseAdapter {
    Context context;
    ArrayList<StarZhaopinBean> arrayList;
    public StarZhaopinAdapter(Context context,ArrayList<StarZhaopinBean> arrayList){
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
    String yanyiimg;
    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.home_yanyi_items,parent,false);
            view.setTag(new ViewHolder(view));
        }
        viewHolder = (ViewHolder) view.getTag();
        StarZhaopinBean zhaopinBean = arrayList.get(i);

        if (zhaopinBean.getData().getPageInfo().getList().get(i).getImg().contains(",")){
            yanyiimg = Api.ossurl+zhaopinBean.getData().getPageInfo().getList().get(i).getImg().split(",")[0];
        }else {
            yanyiimg = Api.ossurl+zhaopinBean.getData().getPageInfo().getList().get(i).getImg();
        }
        Glide.with(context)
                .load(yanyiimg)
                .placeholder(R.mipmap.my166)
                .error(R.mipmap.my166)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(viewHolder.img);
        String headimg = Api.ossurl+zhaopinBean.getData().getPageInfo().getList().get(i).getUser_img();
        Log.e("演绎图片",headimg);
        Glide.with(context)
                .load(headimg)
                .error(R.mipmap.my11)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(viewHolder.img_head);
        viewHolder.tv_name.setText(zhaopinBean.getData().getPageInfo().getList().get(i).getTitle());
        viewHolder.tv_people.setText(zhaopinBean.getData().getPageInfo().getList().get(i).getAttend_num()+"");
        viewHolder.tv_time.setText(zhaopinBean.getData().getPageInfo().getList().get(i).getUser_code());

        return view;
    }
    private static class ViewHolder{
        ImageView img;
        TextView tv_name;
        TextView tv_time;
        TextView tv_people;
        ImageView img_head;
        private ViewHolder(View v_item){
            img = v_item.findViewById(R.id.home_activity_1_img);
            tv_name = v_item.findViewById(R.id.home_activity_1_name);
            tv_time = v_item.findViewById(R.id.home_activity_1_time);
            tv_people = v_item.findViewById(R.id.home_activity_1_people);
            img_head = v_item.findViewById(R.id.yanyi_headimg);

        }
    }
}

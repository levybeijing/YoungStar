package com.chuanqing.youngstar.myadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.mybean.HomeActivityBean;
import com.chuanqing.youngstar.tools.Api;

import java.util.ArrayList;

public class HomeActivityAdapter extends BaseAdapter {
    Context context;
    ArrayList<HomeActivityBean> arrayList  = new ArrayList<>();
    public HomeActivityAdapter(Context context,ArrayList<HomeActivityBean> arrayList){
        this.context  = context;
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
            view = LayoutInflater.from(context).inflate(R.layout.home_activity_items,parent,false);
            view.setTag(new ViewHolder(view));
        }
        viewHolder  = (ViewHolder) view.getTag();
        HomeActivityBean homeActivityBean = arrayList.get(position);
        Glide.with(context)
                .load(Api.ossurl+homeActivityBean.getData().get(position).getList_img())
                .placeholder(R.mipmap.my166)
                .error(R.mipmap.my166)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(viewHolder.img);
        viewHolder.tv_name.setText(homeActivityBean.getData().get(position).getActivity_name());
        viewHolder.tv_people.setText(homeActivityBean.getData().get(position).getAttendCount());
        viewHolder.tv_time.setText(homeActivityBean.getData().get(position).getCurrentTime());

        return view;
    }
    private static class ViewHolder{
        ImageView img;
        TextView tv_name;
        TextView tv_time;
        TextView tv_people;
        public ViewHolder(View view){
            img = view.findViewById(R.id.home_activity_1_img);
            tv_name = view.findViewById(R.id.home_activity_1_name);
            tv_time = view.findViewById(R.id.home_activity_1_time);
            tv_people = view.findViewById(R.id.home_activity_1_people);
        }
    }
}

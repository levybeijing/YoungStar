package com.chuanqing.youngstar.myadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.mybean.PartakeBean;
import com.chuanqing.youngstar.tools.Api;

import java.util.ArrayList;

public class PartakeAdapter extends BaseAdapter {
    Context context;
    ArrayList<PartakeBean> arrayList;
    public PartakeAdapter(Context context,ArrayList<PartakeBean> arrayList){
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
            view = LayoutInflater.from(context).inflate(R.layout.partake_item,parent,false);
            view.setTag(new ViewHolder(view));
        }
        viewHolder = (ViewHolder) view.getTag();
        PartakeBean partakeBean = arrayList.get(position);
        String headimg= Api.ossurl+partakeBean.getData().getPageInfo().getList().get(position).getUser_img();
        Log.e("头像",headimg);
        Glide.with(context)
                .load(headimg)
//                .placeholder(R.mipmap.my11)//图片加载出来前，显示的图片
                .error(R.mipmap.my11)//图片加载失败后，显示的图片
                .into(viewHolder.img_head);
        viewHolder.tv_id.setText("SID:"+partakeBean.getData().getPageInfo().getList().get(position).getUser_code());
        viewHolder.tv_type.setText(partakeBean.getData().getPageInfo().getList().get(position).getLabel());
        viewHolder.tv_redu.setText(partakeBean.getData().getPageInfo().getList().get(position).getUserRecommend()+"");
        viewHolder.tv_num.setText(""+partakeBean.getData().getPageInfo().getList().get(position).getRecommend());
        return view;
    }
    private static class ViewHolder{
        ImageView img_head;
        TextView tv_id;
        TextView tv_type;
        TextView tv_redu;
        Button btn_zuopin;
        TextView tv_num;
        public ViewHolder(View view){
            img_head = view.findViewById(R.id.search_student_headimg);
            tv_id = view.findViewById(R.id.search_student_id);
            tv_type = view.findViewById(R.id.search_student_type);
            tv_redu = view.findViewById(R.id.search_student_redu);
            btn_zuopin = view.findViewById(R.id.search_guanzhu);
            tv_num = view.findViewById(R.id.partake_num);
        }
    }
}

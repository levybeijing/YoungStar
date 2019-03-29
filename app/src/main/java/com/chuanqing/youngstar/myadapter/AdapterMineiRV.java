package com.chuanqing.youngstar.myadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.mybean.FragJobBean;
import com.chuanqing.youngstar.mybean.MineWorksBean;

import java.util.List;

public class AdapterMineiRV extends RecyclerView.Adapter<AdapterMineiRV.MyViewHolder>{
    private List<MineWorksBean.DataBean.PageInfoBean.ListBean> list;
    private Context context;

    public AdapterMineiRV(Context context_) {
        context=context_;
    }

    public void setData(List<MineWorksBean.DataBean.PageInfoBean.ListBean> list_){
        list=list_;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_minei, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.tv_number.setText(list.get(i).getId()+"");
        Glide.with(context).load(Urls.IMAGEURL+list.get(i).getBlog_img()).into(holder.iv_cover);
        String media_url = list.get(i).getMedia_url();
        int media_type = list.get(i).getMedia_type();

    }

    @Override
    public int getItemCount() {
        if (list==null){
            return 0;
        }
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_number;
        ImageView iv_cover;
        public MyViewHolder(View view)
        {
            super(view);
            tv_number = view.findViewById(R.id.tv_rv_minei);
            iv_cover = view.findViewById(R.id.iv_rv_minei);
        }
    }
}

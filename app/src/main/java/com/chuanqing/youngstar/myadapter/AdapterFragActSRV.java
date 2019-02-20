package com.chuanqing.youngstar.myadapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.mybean.FragStarActSBean;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterFragActSRV extends RecyclerView.Adapter<AdapterFragActSRV.MyViewHolder> {
    private List<FragStarActSBean.DataBean.PageInfoBean.ListBean> list;
    private Context context;

    public AdapterFragActSRV(Context context_) {
        context=context_;
    }

    public void setData(List<FragStarActSBean.DataBean.PageInfoBean.ListBean> list_){
        list=list_;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_fragstaracts, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.tv_title.setText(list.get(i).getActivity_name());
        holder.tv_date.setText(list.get(i).getTime());
        holder.tv_number.setText(list.get(i).getRanking()+"");
        String end_time = list.get(i).getEnd_time();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = null;
        try {
            dt = sdf.parse(end_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dt.getTime()>System.currentTimeMillis()){
            holder.tv_status.setText("进行中");
            holder.tv_status.setTextColor(Color.parseColor("#F56250"));
        }else{
            holder.tv_status.setText("已结束");
            holder.tv_status.setTextColor(Color.parseColor("#999999"));
        }
        Glide.with(context).load(Urls.IMAGEURL+list.get(i).getList_img()).into(holder.iv_cover);
//        Glide.with(context).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550660072455&di=545b6b2ae1277aeb02ea9f50d244b9b9&imgtype=0&src=http%3A%2F%2Fs10.sinaimg.cn%2Fmw690%2F001LVQHHty6Pf9F3xvba9%26690").into(holder.iv_cover);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                Toast.makeText(context, "123", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
//        return 1;
        if (list==null){
            return 0;
        }
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_title,tv_date,tv_status,tv_number;
        ImageView iv_cover;
        public MyViewHolder(View view)
        {
            super(view);
            tv_title = view.findViewById(R.id.tv_title_item_fragacts);
            tv_date = view.findViewById(R.id.tv_date_item_fragacts);
            tv_status = view.findViewById(R.id.tv_status_item_fragacts);
            tv_number = view.findViewById(R.id.tv_number_item_fragacts);
            iv_cover = view.findViewById(R.id.iv_cover_item_fragacts);
        }
    }
}

package com.chuanqing.youngstar.myadapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar._mine.OfficalMsgDetailActivity;
import com.chuanqing.youngstar.mybean.MatchBean;
import com.chuanqing.youngstar.mybean.OfficeMsgBean;

import java.util.List;

public class AdapterMatchRV extends RecyclerView.Adapter<AdapterMatchRV.MyViewHolder> {
    private List<MatchBean.DataBean.PageInfoBean.ListBean> list;
    private Activity context;

    public AdapterMatchRV(Activity context_) {
        context=context_;
    }

    public void setData(List<MatchBean.DataBean.PageInfoBean.ListBean> list_){
        list=list_;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_match, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

        if ("已结束".equals(list.get(i).getCondition())){
            holder.rb_time.setChecked(false);
            holder.rb_num.setChecked(false);
            holder.tv_status.setTextColor(Color.parseColor("#666666"));
            holder.tv_time.setTextColor(Color.parseColor("#666666"));
            holder.tv_num.setTextColor(Color.parseColor("#666666"));
        }
        holder.tv_title.setText(list.get(i).getActivity_name());
        holder.tv_rank.setText((int) list.get(i).getRownum()+"");
        holder.tv_status.setText(list.get(i).getCondition());
        holder.tv_time.setText(list.get(i).getEnd_time());
        holder.tv_num.setText(list.get(i).getNum()+"");

        Glide.with(context).load(Urls.IMAGEURL+list.get(i).getList_img()).into(holder.iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(context, OfficalMsgDetailActivity.class);
//
//                context.startActivity(intent);
            }
        });
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
        ImageView iv;
        TextView tv_title,tv_status,tv_time,tv_rank,tv_num;
        RadioButton rb_time,rb_num;
        public MyViewHolder(View view)
        {
            super(view);
            tv_title = view.findViewById(R.id.tv_title_rv_match);
            tv_time = view.findViewById(R.id.tv_time_rv_match);
            tv_status = view.findViewById(R.id.tv_status_rv_match);
            tv_rank = view.findViewById(R.id.tv_rank_rv_match);
            tv_num = view.findViewById(R.id.tv_number_rv_match);

            rb_time = view.findViewById(R.id.rb_time_rv_match);
            rb_num = view.findViewById(R.id.rb_num_rv_match);
            iv = view.findViewById(R.id.iv_rv_match);

        }
    }
}

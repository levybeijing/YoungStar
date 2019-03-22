package com.chuanqing.youngstar.myadapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar._mine.OfficalMsgDetailActivity;
import com.chuanqing.youngstar.mybean.FragStatusBean;
import com.chuanqing.youngstar.mybean.OfficeMsgBean;

import java.util.List;

public class AdapterOfficeMsgRV extends RecyclerView.Adapter<AdapterOfficeMsgRV.MyViewHolder> {
    private List<OfficeMsgBean.DataBean.PageInfoBean.ListBean> list;
    private Activity context;

    public AdapterOfficeMsgRV(Activity context_) {
        context=context_;
    }

    public void setData(List<OfficeMsgBean.DataBean.PageInfoBean.ListBean> list_){
        list=list_;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_officemsg, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.tv_title.setText(list.get(i).getTitle());
        holder.tv_time.setText(list.get(i).getTime());
        holder.tv_sub.setText(list.get(i).getContent());
        if (list.get(i).getStatus()==2){
            holder.iv.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, OfficalMsgDetailActivity.class);
                intent.putExtra("infoid",list.get(i).getId());
                intent.putExtra("toptitle",list.get(i).getTitle());
                context.startActivity(intent);
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
        TextView tv_title,tv_sub,tv_time;
        ImageView iv;
        public MyViewHolder(View view)
        {
            super(view);
            tv_title = view.findViewById(R.id.tv_title_rv_officemsg);
            tv_time = view.findViewById(R.id.tv_time_rv_officemsg);
            tv_sub = view.findViewById(R.id.tv_sub_rv_officemsg);
            iv=view.findViewById(R.id.iv_dot_officemsg);
        }
    }
}

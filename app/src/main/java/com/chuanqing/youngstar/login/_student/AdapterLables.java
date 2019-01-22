package com.chuanqing.youngstar.login._student;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chuanqing.youngstar.R;

import java.util.ArrayList;
import java.util.List;


public class AdapterLables extends RecyclerView.Adapter<AdapterLables.MyViewHolder> {
    private Context context;
    private ClickPositon  itemClickListener;

    private List<String> list;
    private int[] flag=new int[]{-1,-1};


    public AdapterLables(Context context_) {
        context=context_;
    }

    public void setOnItemClickListener(ClickPositon itemClickListene_){
        itemClickListener=itemClickListene_;
    }

    public interface ClickPositon{
        void sendInfo(int p,String lable);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_lables, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv.setText(list.get(position));
        if (position!=flag[0]&&position!=flag[1]){
            holder.iv.setVisibility(View.INVISIBLE);
        }else{
            holder.iv.setVisibility(View.VISIBLE);
        }
        //监听事件
        View itemView =holder.itemView;
        if (itemClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.sendInfo(position,list.get(position));
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        if (list==null) {
            return 0;
        }
        return list.size();
    }

    public void setFlag(int[] flag_) {
        flag=flag_;
        notifyDataSetChanged();
    }

    public void setData(List<String> list_) {
        list=list_;
        notifyDataSetChanged();
    }

    class MyViewHolder extends ViewHolder
    {
        ImageView iv;
        TextView tv;
        public MyViewHolder(View view)
        {
            super(view);
            tv= view.findViewById(R.id.tv_rv_lables);
            iv= view.findViewById(R.id.iv_rv_lables);
        }
    }

}

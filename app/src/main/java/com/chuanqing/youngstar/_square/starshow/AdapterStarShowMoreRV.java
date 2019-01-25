package com.chuanqing.youngstar._square.starshow;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.tools.Api;

import java.util.List;

public class AdapterStarShowMoreRV extends RecyclerView.Adapter<AdapterStarShowMoreRV.MyViewHolder> {
    private Context context;
    private OnItemClickListenerPosition itemClickListener;
    private List<String> list;
    private int pos=0;

    public AdapterStarShowMoreRV(Context context_) {
        context=context_;
    }

    public void setOnItemClickListener(OnItemClickListenerPosition itemClickListene_){
        itemClickListener=itemClickListene_;
    }

    public void setData(List<String> list_){
        list=list_;
        notifyDataSetChanged();
    }

    public void setTag(int position){
        pos=position;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.starshow_img_more_items, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        Glide.with(context).load(Api.ossurl+list.get(position)).into(holder.iv1);
        if (position==pos){
            holder.iv2.setVisibility(View.VISIBLE);
        }else{
            holder.iv2.setVisibility(View.INVISIBLE);
        }
        View itemView = holder.itemView;

        if (itemClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(position);
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
        if (list==null){
            return 0;
        }
        return list.size();
    }

    class MyViewHolder extends ViewHolder
    {
        ImageView iv1,iv2;
        public MyViewHolder(View view)
        {
            super(view);
            iv1 = view.findViewById(R.id.showimg1);
            iv2 = view.findViewById(R.id.showimg2);
        }
    }

    public interface OnItemClickListenerPosition{
        void onItemClick(int position);
    }
}

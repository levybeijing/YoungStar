package com.chuanqing.youngstar.myadapter;

import android.content.Context;
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
import com.chuanqing.youngstar.mybean.FragStatusBean;
import com.chuanqing.youngstar.mybean.FragWorksBean;

import java.util.List;

public class AdapterFragWorksRV extends RecyclerView.Adapter<AdapterFragWorksRV.MyViewHolder> implements View.OnClickListener {
    private List<FragWorksBean.DataBean.PageInfoBean.ListBean> list;
    private Context context;

    public AdapterFragWorksRV(Context context_) {
        context=context_;
    }

    public void setData(List<FragWorksBean.DataBean.PageInfoBean.ListBean> list_){
        list=list_;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_fragworks, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.tv_title.setText(list.get(i).getTitle());
        holder.tv_time.setText(list.get(i).getTime());
        holder.tv_praise.setText(list.get(i).getRecommend()+"");

        Glide.with(context).load(Urls.IMAGEURL+list.get(i).getFirst_img()).into(holder.iv_cover);

        holder.iv_delete.setOnClickListener(this);
        holder.iv_share.setOnClickListener(this);
        holder.rb_praise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.rb_praise.isChecked()){

                }else{

                }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_delete_item_fragstatus:

                break;
            case R.id.iv_share_item_fragstatus:

                break;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_title,tv_time,tv_praise;
        ImageView iv_cover,iv_delete,iv_share;
        RadioButton rb_praise;
        public MyViewHolder(View view)
        {
            super(view);
            tv_title = view.findViewById(R.id.tv_title_item_fragworks);
            tv_time = view.findViewById(R.id.tv_time_item_fragworks);
            tv_praise = view.findViewById(R.id.tv_praise_item_fragworks);
            iv_cover = view.findViewById(R.id.iv_cover_item_fragworks);
            iv_delete = view.findViewById(R.id.iv_delete_item_fragworks);
            iv_share = view.findViewById(R.id.iv_share_item_fragworks);
            rb_praise = view.findViewById(R.id.rb_praise_item_fragworks);
        }
    }
}

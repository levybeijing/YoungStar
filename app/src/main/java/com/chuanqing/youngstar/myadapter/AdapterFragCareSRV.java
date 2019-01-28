package com.chuanqing.youngstar.myadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.mybean.FragCareSBean;
import com.chuanqing.youngstar.mybean.FragStatusBean;

import java.util.List;

public class AdapterFragCareSRV extends RecyclerView.Adapter<AdapterFragCareSRV.MyViewHolder> {
    private List<FragCareSBean.DataBean.PageInfoBean.ListBean> list;
    private Context context;

    public AdapterFragCareSRV(Context context_) {
        context=context_;
    }

    public void setData(List<FragCareSBean.DataBean.PageInfoBean.ListBean> list_){
        list=list_;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_fragcares, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.tv_id.setText(list.get(i).getConcern_code());
        holder.tv_type.setText(list.get(i).getStatus()+"");
        holder.tv_hot.setText(list.get(i).getStatus()+"");
        Glide.with(context).load(Urls.IMAGEURL+list.get(i).getUser_img()).into(holder.iv_photo);
//        holder.btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
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
        TextView tv_id,tv_type,tv_hot;
        ImageView iv_photo;
        TextView btn;
        public MyViewHolder(View view)
        {
            super(view);
            tv_id = view.findViewById(R.id.tv_id_fragcares);
            tv_type = view.findViewById(R.id.tv_type_fragcares);
            tv_hot = view.findViewById(R.id.tv_hot_fragcares);

            iv_photo = view.findViewById(R.id.iv_photo_fragcares);
            btn = view.findViewById(R.id.btn_care_fragcares);
        }
    }
}

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
import com.chuanqing.youngstar.mybean.FragJobBean;
import com.chuanqing.youngstar.mybean.FragStatusBean;

import java.util.List;

public class AdapterFragJobRV extends RecyclerView.Adapter<AdapterFragJobRV.MyViewHolder> implements View.OnClickListener {
    private List<FragJobBean.DataBean.PageInfoBean.ListBean> list;
    private Context context;

    public AdapterFragJobRV(Context context_) {
        context=context_;
    }

    public void setData(List<FragJobBean.DataBean.PageInfoBean.ListBean> list_){
        list=list_;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_fragjob, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.tv_title.setText(list.get(i).getTitle());
        switch (list.get(i).getStatus()){
            case 1:                holder.tv_status.setText("未审核");
                break;
            case 2:                holder.tv_status.setText("通过");
                break;
            case 3:                holder.tv_status.setText("拒绝");
                break;

        }
        holder.tv_num.setText(list.get(i).getAttend_num()+"");

        Glide.with(context).load(Urls.IMAGEURL+list.get(i).getImg()).into(holder.iv_cover);

        holder.iv_delete.setOnClickListener(this);
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
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_title,tv_status,tv_num;
        ImageView iv_cover,iv_delete;
        public MyViewHolder(View view)
        {
            super(view);
            tv_title = view.findViewById(R.id.tv_title_item_fragjob);
            tv_status = view.findViewById(R.id.tv_status_item_fragjob);
            tv_num = view.findViewById(R.id.tv_num_item_fragjob);
            iv_cover = view.findViewById(R.id.iv_cover_item_fragjob);
            iv_delete = view.findViewById(R.id.iv_delete_item_fragjob);
        }
    }
}

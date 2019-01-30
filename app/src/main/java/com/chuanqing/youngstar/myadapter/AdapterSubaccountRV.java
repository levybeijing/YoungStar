package com.chuanqing.youngstar.myadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.login.privacy.P;
import com.chuanqing.youngstar.mybean.FragJobBean;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class AdapterSubaccountRV extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<FragJobBean.DataBean.PageInfoBean.ListBean> list;
    private Context context;
    private static final int TYPE_1=1111;
    private static final int TYPE_2=2222;
    private static final int TYPE_3=3333;

    public AdapterSubaccountRV(Context context_) {
        context=context_;
    }

    public void setData(List<FragJobBean.DataBean.PageInfoBean.ListBean> list_){
        list=list_;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        switch (viewType){
            case TYPE_1:
                view = LayoutInflater.from(context).inflate(R.layout.item_forbid_subacount, parent, false);
                return  new MyViewHolder1(view);
            case TYPE_2:
                view = LayoutInflater.from(context).inflate(R.layout.item_enable_subacount, parent, false);
                return  new MyViewHolder2(view);
            case TYPE_3:
                view = LayoutInflater.from(context).inflate(R.layout.item_check_subacount, parent, false);
                return  new MyViewHolder3(view);
        }
//        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_fragjob, parent, false);
//        return  new MyViewHolder1(view);/
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if (holder instanceof MyViewHolder1){
//            ((MyViewHolder1)holder).tv_name.setText();
//            ((MyViewHolder1)holder).tv_phone.setText();
            ((MyViewHolder1)holder).tv_forbid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    request(4);

                }
            });
        }else if (holder instanceof MyViewHolder2){
//            ((MyViewHolder2)holder).tv_name.setText();
//            ((MyViewHolder2)holder).tv_phone.setText();
            ((MyViewHolder2)holder).tv_enable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    request(2);

                }
            });

        }else if (holder instanceof MyViewHolder3){
//            ((MyViewHolder1)holder).tv_name.setText();
//            ((MyViewHolder1)holder).tv_phone.setText();
            ((MyViewHolder3)holder).tv_pass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    request(2);
                }
            });
            ((MyViewHolder3)holder).tv_refuse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    request(4);

                }
            });

        }

    }

    @Override
    public int getItemViewType(int position) {
//        switch (){
//            case :
//                return TYPE_1;
//            case :
//                return TYPE_2;
//            case :
//                return TYPE_3;
//        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        if (list==null){
            return 0;
        }
        return list.size();
    }

    private void request(int code) {
        OkGo.post(Urls.updateCompanySecondary)//
                .tag(this)//
                .params("userCode", (String) SharedPFUtils.getParam(context,"usercode",""))//文件名
                .params("status", code)//文件名
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("=============", "updateCompanySecondary"+s);
                    }
                });
    }
    class MyViewHolder1 extends RecyclerView.ViewHolder
    {
        TextView tv_name,tv_phone;
        TextView tv_forbid;
        public MyViewHolder1(View view)
        {
            super(view);
            tv_name = view.findViewById(R.id.tv_name_item_subaccount);
            tv_phone = view.findViewById(R.id.tv_phone_item_subaccount);
            tv_forbid = view.findViewById(R.id.tv_forbid_subaccount);
        }
    }
    class MyViewHolder2 extends RecyclerView.ViewHolder
    {
        TextView tv_name,tv_phone;
        TextView tv_enable;
        public MyViewHolder2(View view)
        {
            super(view);
            tv_name = view.findViewById(R.id.tv_name_item_subaccount);
            tv_phone = view.findViewById(R.id.tv_phone_item_subaccount);
            tv_enable = view.findViewById(R.id.tv_enable_subaccount);
        }
    }
    class MyViewHolder3 extends RecyclerView.ViewHolder
    {
        TextView tv_name,tv_phone;
        TextView tv_pass;
        TextView tv_refuse;
        public MyViewHolder3(View view)
        {
            super(view);
            tv_name = view.findViewById(R.id.tv_name_item_subaccount);
            tv_phone = view.findViewById(R.id.tv_phone_item_subaccount);
            tv_pass = view.findViewById(R.id.tv_pass_subaccount);
            tv_refuse = view.findViewById(R.id.tv_refuse_subaccount);
        }
    }
}

package com.chuanqing.youngstar.myadapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar._active.starbang.StarbangMoreActivity;
import com.chuanqing.youngstar.mybean.StarbangBean;
import com.chuanqing.youngstar.tools.Api;

import java.util.ArrayList;

public class StarbangAdapter extends BaseAdapter {
    Context context;
    ArrayList<StarbangBean> arrayList;
    public StarbangAdapter(Context context,ArrayList<StarbangBean> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.star_show_xingbang,parent,false);
            view.setTag(new ViewHolder(view));
        }
        viewHolder = (ViewHolder) view.getTag();
        final StarbangBean starbangBean = arrayList.get(position);

        Glide.with(context).load(Api.ossurl+starbangBean.getData().getPageInfo().getList().get(position).getList_img())
                .placeholder(R.mipmap.my166)
                .error(R.mipmap.my166)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(new ViewTarget<View, GlideDrawable>(viewHolder.layout) {
                    //括号里为需要加载的控件
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResourceReady(GlideDrawable resource,
                                                GlideAnimation<? super GlideDrawable> glideAnimation) {
                        this.view.setBackground(resource.getCurrent());
                    }
                });
        viewHolder.tv_name.setText(starbangBean.getData().getPageInfo().getList().get(position).getActivity_name());
       //先都隐藏，然后在去判断哪个展示哪个不展示
        viewHolder.guanjun_img.setVisibility(View.GONE);
        viewHolder.yajun_img.setVisibility(View.GONE);
        viewHolder.jijun_img.setVisibility(View.GONE);
        viewHolder.tv_guanju.setVisibility(View.GONE);
        viewHolder.tv_yajun.setVisibility(View.GONE);
        viewHolder.tv_jijun.setVisibility(View.GONE);
        viewHolder.img1.setVisibility(View.GONE);
        viewHolder.img2.setVisibility(View.GONE);
        viewHolder.img3.setVisibility(View.GONE);
        //开始判断
        if (starbangBean.getData().getPageInfo().getList().get(position).getStudent().size()>0){
            viewHolder.guanjun_img.setVisibility(View.VISIBLE);
            viewHolder.yajun_img.setVisibility(View.GONE);
            viewHolder.jijun_img.setVisibility(View.GONE);
            viewHolder.tv_guanju.setVisibility(View.VISIBLE);
            viewHolder.tv_yajun.setVisibility(View.GONE);
            viewHolder.tv_jijun.setVisibility(View.GONE);
            viewHolder.img1.setVisibility(View.VISIBLE);
            viewHolder.img2.setVisibility(View.GONE);
            viewHolder.img3.setVisibility(View.GONE);
            Glide.with(context)
                    .load(Api.ossurl+starbangBean.getData().getPageInfo().getList().get(position).getStudent().get(0).getUser_img())
                    .error(R.mipmap.my11)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(viewHolder.guanjun_img);
            viewHolder.tv_guanju.setText("ID:"+starbangBean.getData().getPageInfo().getList().get(position).getStudent().get(0).getUser_code());
        }
        if (starbangBean.getData().getPageInfo().getList().get(position).getStudent().size()>1){
            viewHolder.guanjun_img.setVisibility(View.VISIBLE);
            viewHolder.yajun_img.setVisibility(View.VISIBLE);
            viewHolder.jijun_img.setVisibility(View.GONE);
            viewHolder.tv_guanju.setVisibility(View.VISIBLE);
            viewHolder.tv_yajun.setVisibility(View.VISIBLE);
            viewHolder.tv_jijun.setVisibility(View.GONE);
            viewHolder.img1.setVisibility(View.VISIBLE);
            viewHolder.img2.setVisibility(View.VISIBLE);
            viewHolder.img3.setVisibility(View.GONE);
            Glide.with(context)
                    .load(Api.ossurl+starbangBean.getData().getPageInfo().getList().get(position).getStudent().get(1).getUser_img())
                    .error(R.mipmap.my11)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(viewHolder.yajun_img);
            viewHolder.tv_yajun.setText("ID:"+starbangBean.getData().getPageInfo().getList().get(position).getStudent().get(1).getUser_code());
        }
        if (starbangBean.getData().getPageInfo().getList().get(position).getStudent().size()>2){
            viewHolder.guanjun_img.setVisibility(View.VISIBLE);
            viewHolder.yajun_img.setVisibility(View.VISIBLE);
            viewHolder.jijun_img.setVisibility(View.VISIBLE);
            viewHolder.tv_guanju.setVisibility(View.VISIBLE);
            viewHolder.tv_yajun.setVisibility(View.VISIBLE);
            viewHolder.tv_jijun.setVisibility(View.VISIBLE);
            viewHolder.img1.setVisibility(View.VISIBLE);
            viewHolder.img2.setVisibility(View.VISIBLE);
            viewHolder.img3.setVisibility(View.VISIBLE);
            StarbangBean.DataBean.PageInfoBean.ListBean.StudentBean studentBean = starbangBean.getData().getPageInfo().getList().get(position).getStudent().get(2);
            if (studentBean!=null){
                Glide.with(context)
                        .load(Api.ossurl+ studentBean.getUser_img())
                        .error(R.mipmap.my11)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(viewHolder.jijun_img);
                viewHolder.tv_jijun.setText("ID:"+ studentBean.getUser_code());
            }
        }

        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,StarbangMoreActivity.class);
                intent.putExtra("activityCode",starbangBean.getData().getPageInfo().getList().get(position).getActivity_code()+"");
                context.startActivity(intent);
            }
        });


        return view;
    }

    private static class ViewHolder{
        RelativeLayout layout;
        TextView tv_name;
        ImageView guanjun_img;
        ImageView yajun_img;
        ImageView jijun_img;
        TextView tv_guanju;
        TextView tv_yajun;
        TextView tv_jijun;
        ImageView img1;
        ImageView img2;
        ImageView img3;
        private ViewHolder(View view){
            layout = view.findViewById(R.id.starbang_relativelayout);
            tv_name = view.findViewById(R.id.star_show_name);
            guanjun_img = view.findViewById(R.id.star_show_xingbang_guanjun);
            yajun_img = view.findViewById(R.id.star_show_xingbang_yajun);
            jijun_img = view.findViewById(R.id.star_show_xingbang_jijun);
            tv_guanju = view.findViewById(R.id.starbang_tv1);
            tv_yajun = view.findViewById(R.id.starbang_tv2);
            tv_jijun = view.findViewById(R.id.starbang_tv3);
            img1 = view.findViewById(R.id.starbangimg1);
            img2 = view.findViewById(R.id.starbangimg2);
            img3 = view.findViewById(R.id.starbangimg3);
        }
    }
}

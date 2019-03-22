package com.chuanqing.youngstar.myadapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar._square.starshow.StarShowMoreActivity;
import com.chuanqing.youngstar.mybean.SquareStarShowBean;
import com.chuanqing.youngstar.tools.Api;

import java.util.ArrayList;
import java.util.List;

public class SquareStarShowAdaper extends RecyclerView.Adapter<SquareStarShowAdaper.ViewHolder> {
    Context context;
    List<SquareStarShowBean.DataBean.PageInfoBean.ListBean> arrayList;
    public SquareStarShowAdaper(Context context, List<SquareStarShowBean.DataBean.PageInfoBean.ListBean> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.square_starshow_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
//        final SquareStarShowBean squareStarShowBean = arrayList.get(position);
        String tuUrl = Api.ossurl+arrayList.get(position).getBlog_img();
//        Log.e("图片显示地址",tuUrl);
        viewHolder.tv_number.setText(arrayList.get(position).getNum()+"");
        //类型1（图片）2（视频）3（音频）
        if (arrayList.get(position).getMedia_type()==1){
            viewHolder.img_video.setVisibility(View.GONE);
            viewHolder.linearLayout_img.setVisibility(View.VISIBLE);
            if (arrayList.get(position).getBlog_img().contains(",")){
                Glide.with(context)
                        .load(Api.ossurl+arrayList.get(position).getBlog_img().split(",")[0])
                        .placeholder(R.mipmap.my11)
                        .error(R.mipmap.my11)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(viewHolder.img);
            }else {
                Glide.with(context)
                        .load(Api.ossurl+arrayList.get(position).getBlog_img())
                        .placeholder(R.mipmap.my11)
                        .error(R.mipmap.my11)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(viewHolder.img);
            }

        }else if (arrayList.get(position).getMedia_type()==2){
            viewHolder.img_video.setVisibility(View.VISIBLE);
            viewHolder.linearLayout_img.setVisibility(View.GONE);
            Glide.with(context)
                    .load(tuUrl)
                    .placeholder(R.mipmap.my11)
                    .error(R.mipmap.my11)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(viewHolder.img);
        }else if (arrayList.get(position).getMedia_type()==3){
            viewHolder.img_video.setVisibility(View.GONE);
            viewHolder.linearLayout_img.setVisibility(View.GONE);
            viewHolder.img.setImageDrawable(context.getResources().getDrawable(R.mipmap.square_yinpin));
        }

        //点击跳转到详情
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,StarShowMoreActivity.class);
                intent.putExtra("userBlogId",arrayList.get(position).getId()+"");
                intent.putExtra("type",arrayList.get(position).getMedia_type()+"");
                intent.putExtra("hot",arrayList.get(position).getNum()+"");
                context.startActivity(intent);
            }
        });


    }

    //② 创建ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        ImageView img_video;
        LinearLayout linearLayout_img;
        TextView tv_number;
        RelativeLayout relativeLayout;
        public ViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.starshow_imgs);
            img_video = view.findViewById(R.id.starshow_img_video);
            linearLayout_img = view.findViewById(R.id.starshow_img_img);
            tv_number = view.findViewById(R.id.starshow_img_img_number);
            relativeLayout = view.findViewById(R.id.starshow_body);
        }
    }



}

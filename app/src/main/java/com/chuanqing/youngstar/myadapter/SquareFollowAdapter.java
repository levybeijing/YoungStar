package com.chuanqing.youngstar.myadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.mybean.SquareFollowBean;
import com.chuanqing.youngstar.tools.Api;
import com.chuanqing.youngstar.tools.CircleImageView;

import java.util.ArrayList;

public class SquareFollowAdapter extends BaseAdapter
{
    Context context;
    ArrayList<SquareFollowBean> arrayList ;
    public SquareFollowAdapter(Context context,ArrayList<SquareFollowBean> arrayList){
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
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.follow_item,parent,false);
            view.setTag(new ViewHolder(view));
        }
        viewHolder = (ViewHolder) view.getTag();
        SquareFollowBean squareFollowBean = arrayList.get(position);
        String tuUrl = Api.ossurl + squareFollowBean.getData().getPageInfo().getList().get(position).getBlog_img();
        // 类型1（图片）2（视频）3（音频）
        if (squareFollowBean.getData().getPageInfo().getList().get(position).getMedia_type().equals("1")){
            viewHolder.linearLayout.setVisibility(View.VISIBLE);
            viewHolder.img_video.setVisibility(View.GONE);
            Glide.with(context)
                    .load(tuUrl)
                    .placeholder(R.mipmap.my166)
                    .error(R.mipmap.my166)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(viewHolder.imgbig);
        }else if (squareFollowBean.getData().getPageInfo().getList().get(position).getMedia_type().equals("2")){
            viewHolder.linearLayout.setVisibility(View.GONE);
            viewHolder.img_video.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(tuUrl)
                    .placeholder(R.mipmap.my166)
                    .error(R.mipmap.my166)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(viewHolder.imgbig);
        }else if (squareFollowBean.getData().getPageInfo().getList().get(position).getMedia_type().equals("3")){
            viewHolder.linearLayout.setVisibility(View.GONE);
            viewHolder.img_video.setVisibility(View.GONE);
            viewHolder.imgbig.setImageDrawable(context.getResources().getDrawable(R.mipmap.square_yinpin_zuopinji));
        }

        if (squareFollowBean.getData().getPageInfo().getList().get(position).getTitle().length()>15){
            viewHolder.tv_name.setText(squareFollowBean.getData().getPageInfo().getList().get(position).getTitle()+"..");
        }else  if (squareFollowBean.getData().getPageInfo().getList().get(position).getTitle().length()>15){
            viewHolder.tv_name.setText(squareFollowBean.getData().getPageInfo().getList().get(position).getTitle());
        }
        viewHolder.tv_time.setText(squareFollowBean.getData().getPageInfo().getList().get(position).getCreate_time());
        viewHolder.textView_renshu.setText(squareFollowBean.getData().getPageInfo().getList().get(position).getNum());
        viewHolder.tv_info.setText(squareFollowBean.getData().getPageInfo().getList().get(position).getBlog_detail());
        String headimg = Api.ossurl+squareFollowBean.getData().getPageInfo().getList().get(position).getUser_img();
        Log.e("头像地址",headimg);
        Glide.with(context)
                .load(headimg)
                .error(R.mipmap.my11)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(viewHolder.circleImageView);
        viewHolder.tv_id.setText("SID："+squareFollowBean.getData().getPageInfo().getList().get(position).getId());
        viewHolder.tv_type.setText(squareFollowBean.getData().getPageInfo().getList().get(position).getType()+"");
        viewHolder.tv_dianzan.setText(squareFollowBean.getData().getPageInfo().getList().get(position).getRecommend()+"");
        return view;
    }
    private static class ViewHolder{
        TextView tv_name;
        TextView tv_time;
        ImageView imgbig;
        ImageView img_video;
        LinearLayout linearLayout;
        TextView textView_renshu;
        TextView tv_info;
        ImageView circleImageView;
        TextView tv_id;
        TextView tv_type;
        TextView tv_dianzan;
        public ViewHolder(View view){
            tv_name = view.findViewById(R.id.squarefollow_name);
            tv_time = view.findViewById(R.id.squarefollow_time);
            imgbig = view.findViewById(R.id.squarefollow_img);
            img_video = view.findViewById(R.id.squarefollow_video);
            linearLayout = view.findViewById(R.id.squarefollow_img_img);
            textView_renshu = view.findViewById(R.id.squarefollow_img_img_number);
            tv_info = view.findViewById(R.id.squarefollow_info);
            circleImageView = (ImageView) view.findViewById(R.id.squarefollow_headimg);
            tv_id =view.findViewById(R.id.squarefollow_userid);
            tv_type = view.findViewById(R.id.squarefollow_type);
            tv_dianzan =view.findViewById(R.id.squarefollow_dianzannumber);
        }
    }
}

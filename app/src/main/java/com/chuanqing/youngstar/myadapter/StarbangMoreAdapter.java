package com.chuanqing.youngstar.myadapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar._home.UserDetailActivity;
import com.chuanqing.youngstar.mybean.StarBangMoreBean;
import com.chuanqing.youngstar.tools.Api;
import com.chuanqing.youngstar.tools.ToastUtils;

import java.util.ArrayList;

public class StarbangMoreAdapter extends BaseAdapter {
    Context context;
    ArrayList<StarBangMoreBean> arrayList;
    public StarbangMoreAdapter(Context context,ArrayList<StarBangMoreBean> arrayList){
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
            view = LayoutInflater.from(context).inflate(R.layout.starbang_more_items,parent,false);
            view.setTag(new ViewHolder(view));
        }
        viewHolder = (ViewHolder) view.getTag();
        StarBangMoreBean starBangMoreBean = arrayList.get(position);
        Glide.with(context)
                .load(Api.ossurl+starBangMoreBean.getData().getStudent().get(position).getUser_img())
                .placeholder(R.mipmap.my11)
                .error(R.mipmap.my11)
                .into(viewHolder.img_head);
        String user_code = starBangMoreBean.getData().getStudent().get(position).getUser_code();
        viewHolder.tv_id.setText("ID:"+ user_code);
        viewHolder.tv_redu.setText(""+starBangMoreBean.getData().getStudent().get(position).getRecommend());
        viewHolder.tv_mingci.setText(starBangMoreBean.getData().getActivity_name()+"大赛中"+"第"+(position+1)+"名");
        //点击事件
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, UserDetailActivity.class);
                intent.putExtra("usercode",user_code);
                context.startActivity(intent);
            }
        });
        return view;
    }
   private static class ViewHolder{
        LinearLayout linearLayout;
        ImageView img_head;
        TextView tv_id;
        TextView tv_redu;
        TextView tv_mingci;
        private ViewHolder(View view){
            linearLayout = view.findViewById(R.id.starbang_items_linearlayout);
            img_head = view.findViewById(R.id.starbang_items_head);
            tv_id = view.findViewById(R.id.starbang_items_id);
            tv_redu = view.findViewById(R.id.starbang_items_redu);
            tv_mingci = view.findViewById(R.id.starbang_items_mingci);

        }
    }
}

package com.chuanqing.youngstar.myadapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar._home.UserDetailActivity;
import com.chuanqing.youngstar.mybean.SearchStudentBean;
import com.chuanqing.youngstar.mybean.StatusBean;
import com.chuanqing.youngstar.mybean.StatusListBean;
import com.chuanqing.youngstar.tools.Api;

import java.util.ArrayList;

public class StatusListAdapter extends BaseAdapter {
    Context context;
    ArrayList<StatusListBean> arrayList;
    public StatusListAdapter(Context context,ArrayList<StatusListBean> arrayList){
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
            view = LayoutInflater.from(context).inflate(R.layout.student_show_item,parent,false);
            view.setTag(new ViewHolder(view));
        }
        viewHolder = (ViewHolder) view.getTag();
        StatusListBean searchStudentBean = arrayList.get(position);
        String headimg= Api.ossurl+searchStudentBean.getData().getPageInfo().getList().get(position).getUser_img();
        Glide.with(context)
                .load(headimg)
                .error(R.mipmap.my11)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(viewHolder.img_head);
        viewHolder.tv_id.setText("SID:"+searchStudentBean.getData().getPageInfo().getList().get(position).getUser_code());
        viewHolder.tv_type.setText(searchStudentBean.getData().getPageInfo().getList().get(position).getLabel());
        int hot = searchStudentBean.getData().getPageInfo().getList().get(position).getRecommendTotal();
        viewHolder.tv_redu.setText(hot +"");
        viewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, UserDetailActivity.class);
                intent.putExtra("hot",hot+"");
                intent.putExtra("usercode",searchStudentBean.getData().getPageInfo().getList().get(position).getUser_code());
                context.startActivity(intent);
            }
        });
        return view;
    }


    public static class ViewHolder{
        ImageView img_head;
        TextView tv_id;
        TextView tv_type;
        TextView tv_redu;
        Button btn_guanzhu;
        LinearLayout ll;
        private ViewHolder(View view){
            ll = view.findViewById(R.id.ll_statuslist);
            img_head = view.findViewById(R.id.search_student_headimg);
            tv_id = view.findViewById(R.id.search_student_id);
            tv_type = view.findViewById(R.id.search_student_type);
            tv_redu = view.findViewById(R.id.search_student_redu);
            btn_guanzhu = view.findViewById(R.id.search_guanzhu);

        }
    }
}

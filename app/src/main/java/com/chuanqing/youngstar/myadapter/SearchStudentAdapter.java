package com.chuanqing.youngstar.myadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.mybean.SearchStudentBean;
import com.chuanqing.youngstar.tools.Api;

import java.util.ArrayList;

public class SearchStudentAdapter extends BaseAdapter {
    Context context;
    ArrayList<SearchStudentBean> arrayList;
    public SearchStudentAdapter(Context context,ArrayList<SearchStudentBean> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    };
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
        SearchStudentBean searchStudentBean = arrayList.get(position);
        String headimg= Api.ossurl+searchStudentBean.getData().getPageInfo().getList().get(position).getUser_img();
        Glide.with(context)
                .load(headimg)
                .error(R.mipmap.my11)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(viewHolder.img_head);
        viewHolder.tv_id.setText("SID:"+searchStudentBean.getData().getPageInfo().getList().get(position).getUser_code());
        viewHolder.tv_type.setText(searchStudentBean.getData().getPageInfo().getList().get(position).getLabel());
        viewHolder.tv_redu.setText(searchStudentBean.getData().getPageInfo().getList().get(position).getRecommendTotal()+"");
        if (searchStudentBean.getData().getPageInfo().getList().get(position).getFlag()==1){
            viewHolder.btn_guanzhu.setBackground(context.getResources().getDrawable(R.mipmap.yiguanzhu));
            viewHolder.btn_guanzhu.setText("已关注");
            viewHolder.btn_guanzhu.setTextColor(context.getResources().getColor(R.color.home_gray));
        }else {
            viewHolder.btn_guanzhu.setBackground(context.getResources().getDrawable(R.mipmap.leitai_baoming));
            viewHolder.btn_guanzhu.setText("关注");
            viewHolder.btn_guanzhu.setTextColor(context.getResources().getColor(R.color.mainColor));
        }
        return view;
    }
    static class ViewHolder{
        ImageView img_head;
        TextView tv_id;
        TextView tv_type;
        TextView tv_redu;
        Button btn_guanzhu;
        private ViewHolder(View view){
            img_head = view.findViewById(R.id.search_student_headimg);
            tv_id = view.findViewById(R.id.search_student_id);
            tv_type = view.findViewById(R.id.search_student_type);
            tv_redu = view.findViewById(R.id.search_student_redu);
            btn_guanzhu = view.findViewById(R.id.search_guanzhu);

        }
    }
}

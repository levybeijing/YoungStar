package com.chuanqing.youngstar.myadapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.mybean.WorksBean;
import com.chuanqing.youngstar.tools.Api;

import java.util.ArrayList;

public class PublishWorksAdapter extends RecyclerView.Adapter<PublishWorksAdapter.ViewHolder>{
    ArrayList<WorksBean.DataBean.MediaTimeBean.MediaBean> arrayList;
    Context context;
    CallBack cb;
    public PublishWorksAdapter(Context context, ArrayList<WorksBean.DataBean.MediaTimeBean.MediaBean> arrayList, CallBack callBack){
        this.context = context;
        this.arrayList = arrayList;
        this.cb = callBack;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.publishworks_img_items,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

//        Uri uri = Uri.parse(arrayList.get(i));
//        viewHolder.imageView.setImageURI(uri);
        String path = Api.ossurl+arrayList.get(i).getMedia_url();
        Glide.with(context)
                .load(path)
                .into(viewHolder.imageView);
        viewHolder.imageView_no.setVisibility(View.VISIBLE);
        viewHolder.imageView_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb.clalback(arrayList.get(i).getId());
                arrayList.remove(i);
                notifyDataSetChanged();
            }
        });

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageView imageView_no;
        public ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.publishs_img_items_img);
            imageView_no = view.findViewById(R.id.publishs_img_items_img_no);
        }
    }

    public interface CallBack{
        void clalback(int id);
    }
}

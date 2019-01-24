package com.chuanqing.youngstar.myadapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.tools.ToastUtils;

import java.util.ArrayList;

public class PublishAdapter extends RecyclerView.Adapter<PublishAdapter.ViewHolder>{
    ArrayList<String> arrayList;
    Context context;
    CallBack cb;
    public PublishAdapter(Context context,ArrayList<String> arrayList,CallBack callBack){
        this.context = context;
        this.arrayList = arrayList;
        this.cb = callBack;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.publish_items,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        if (i==arrayList.size()){
            viewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.mipmap.up_tupian));
            if (i==8){
                viewHolder.imageView.setVisibility(View.GONE);
                viewHolder.imageView_no.setVisibility(View.GONE);
            }else {
                viewHolder.imageView_no.setVisibility(View.GONE);
                viewHolder.imageView.setVisibility(View.VISIBLE);
            }
            //点击事件
            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        cb.clalback();
                }
            });
        }else {
            Uri uri = Uri.parse(arrayList.get(i));
            viewHolder.imageView.setImageURI(uri);
            viewHolder.imageView_no.setVisibility(View.VISIBLE);
            viewHolder.imageView_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    arrayList.remove(i);
                    notifyDataSetChanged();
                }
            });
        }
//        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtils.shortToast(i+"");
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return arrayList.size()+1;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageView imageView_no;
        public ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.publishs_items_img);
            imageView_no = view.findViewById(R.id.publishs_items_img_no);
        }
    }

    public interface CallBack{
        void clalback();
    }
}

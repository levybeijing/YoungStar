package com.chuanqing.youngstar._home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar._home.search.student.StudentShowActivity;
import com.chuanqing.youngstar.myadapter.HomeActivityAdapter;
import com.chuanqing.youngstar.mybean.HomeActivityBean;
import com.chuanqing.youngstar.mybean.HomeLunboBean;
import com.chuanqing.youngstar.mybean.HomeYanyiBean;
import com.chuanqing.youngstar.tools.Api;
import com.chuanqing.youngstar.tools.CircleImageView;
import com.chuanqing.youngstar.tools.ToastUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 首页
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements OnBannerListener {

    private Context context;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTtitle();
        initView();
        showactivity();
        showyanyi();
    }

    /**
     * 展示活动信息
     */
    @BindView(R.id.home_activity_1_body)
    LinearLayout home_body;
    ArrayList<HomeActivityBean> arrayList = new ArrayList<>();
    HomeActivityAdapter adapter ;
    private void showactivity() {
        OkGo.post(Api.home_activity)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, okhttp3.Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, okhttp3.Response response) {
                        Log.e("首页活动",s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        final HomeActivityBean activityBean = gson.fromJson(s,HomeActivityBean.class);
                        if (activityBean.getState()==1){
                            if (activityBean.getData()!=null){
                                for (int i = 0; i <activityBean.getData().size() ; i++) {
                                    final View v_item=LayoutInflater.from(context).inflate(R.layout.home_activity_items,null);
                                    ImageView img;
                                    TextView tv_name;
                                    TextView tv_time;
                                    TextView tv_people;
                                    img = v_item.findViewById(R.id.home_activity_1_img);
                                    tv_name = v_item.findViewById(R.id.home_activity_1_name);
                                    tv_time = v_item.findViewById(R.id.home_activity_1_time);
                                    tv_people = v_item.findViewById(R.id.home_activity_1_people);
                                    Glide.with(context)
                                            .load(Api.ossurl+activityBean.getData().get(i).getList_img())
                                            .placeholder(R.mipmap.my166)
                                            .error(R.mipmap.my166)
                                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                                            .into(img);
                                    tv_name.setText(activityBean.getData().get(i).getActivity_name());
                                    tv_people.setText(activityBean.getData().get(i).getAttendCount());
                                    tv_time.setText(activityBean.getData().get(i).getCurrentTime());
                                    home_body.addView(v_item);
                                    final int finalI = i;
                                    v_item.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            ToastUtils.shortToast("活动名字"+activityBean.getData().get(finalI).getActivity_code());
                                        }
                                    });
                                }
                            }
                        }else {
                            ToastUtils.shortToast(activityBean.getMessage());
                        }
                    }
                });
    }

    /**
     * 演绎专区
     */
    @BindView(R.id.home_activity_2_body)
    LinearLayout linearLayout_2_boty;
    String yanyiimg;
    private void showyanyi(){
        OkGo.post(Api.home_zhaopin)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        final HomeYanyiBean yanyiBean = gson.fromJson(s,HomeYanyiBean.class);
                        if (yanyiBean.getState()==1){
                            if (yanyiBean.getData().size()>0){
                                for (int i = 0; i <yanyiBean.getData().size() ; i++) {
                                    final View v_item=LayoutInflater.from(context).inflate(R.layout.home_yanyi_items,null);
                                    ImageView img;
                                    TextView tv_name;
                                    TextView tv_time;
                                    TextView tv_people;
                                    img = v_item.findViewById(R.id.home_activity_1_img);
                                    tv_name = v_item.findViewById(R.id.home_activity_1_name);
                                    tv_time = v_item.findViewById(R.id.home_activity_1_time);
                                    tv_people = v_item.findViewById(R.id.home_activity_1_people);
                                    CircleImageView img_head = v_item.findViewById(R.id.yanyi_headimg);
                                    if (yanyiBean.getData().get(i).getImg().contains(",")){
                                        yanyiimg = Api.ossurl+yanyiBean.getData().get(i).getImg().split(",")[0];
                                    }else {
                                        yanyiimg = Api.ossurl+yanyiBean.getData().get(i).getImg();
                                    }
                                    Glide.with(context)
                                            .load(yanyiimg)
                                            .placeholder(R.mipmap.my166)
                                            .error(R.mipmap.my166)
                                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                                            .into(img);
                                    String headimg = Api.ossurl+yanyiBean.getData().get(i).getUser_img();
                                    Log.e("演绎图片",headimg);
                                    Glide.with(context)
                                            .load(headimg)
                                            .placeholder(R.mipmap.my11)
                                            .error(R.mipmap.my11)
                                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                                            .into(img_head);
                                    tv_name.setText(yanyiBean.getData().get(i).getTitle());
                                    tv_people.setText(yanyiBean.getData().get(i).getAttendCount()+"");
                                    tv_time.setText(yanyiBean.getData().get(i).getUser_code());
                                    linearLayout_2_boty.addView(v_item);
                                    final int finalI = i;
                                    v_item.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            ToastUtils.shortToast("演绎"+yanyiBean.getData().get(finalI).getAttendCount());
                                        }
                                    });
                                }
                            }else {

                            }
                        }else {
                            ToastUtils.shortToast(yanyiBean.getMessage());
                        }
                    }
                });
    }
    /**
     * 写入title名字
     */
    @BindView(R.id.common_left_img)
    ImageView left_img;
    @BindView(R.id.common_center_title)
    TextView tv_title;
    @BindView(R.id.common_rigth_img)
    ImageView right_img;
    private void setTtitle(){
        tv_title.setText("首页");
        left_img.setVisibility(View.GONE);
        right_img.setVisibility(View.VISIBLE);
        right_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,StudentShowActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 展示轮播
     */
    @BindView(R.id.home_banner)
    Banner banner;
    private ArrayList<String> list_path;
    private ArrayList<String> list_title;
    String[] img_url ;
    private void initView() {
        //放图片地址的集合
        list_path = new ArrayList<>();
        OkGo.post(Api.home_lunbo)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, okhttp3.Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, okhttp3.Response response) {
//                        Log.e("首页轮播",s);
                        Gson gson = new Gson();
                        HomeLunboBean homeLunboBean = gson.fromJson(s,HomeLunboBean.class);
                        if (homeLunboBean.getState()==1){
                            if (homeLunboBean.getData()!=null){
                                img_url = new String[ homeLunboBean.getData().size()];
                                for (int i = 0; i < homeLunboBean.getData().size(); i++) {
                                    list_path.add(Api.ossurl+homeLunboBean.getData().get(i).getImg());
                                    img_url[i] = homeLunboBean.getData().get(i).getImg();
                                }
                                onshow();
                            }
                        }
                    }
                });


    }
    private void onshow(){
        //放标题的集合
        list_title = new ArrayList<>();
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();
    }
    //轮播图的监听方法
    @Override
    public void OnBannerClick(int position) {
        Intent intent = new Intent(context,WebViewActivity.class);
        intent.putExtra("detailUrl",img_url[position]);
        startActivity(intent);
//        ToastUtils.shortToast("你点了第"+position+"张轮播图");
    }
    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context)
                    .load((String) path)
                    .placeholder(R.mipmap.my32)
                    .error(R.mipmap.my32)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(imageView);
        }
    }

}

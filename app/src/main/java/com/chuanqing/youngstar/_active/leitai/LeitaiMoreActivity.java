package com.chuanqing.youngstar._active.leitai;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.myadapter.MyAdapter;
import com.chuanqing.youngstar.mybean.LeitaiMoreBean;
import com.chuanqing.youngstar.tools.Api;
import com.chuanqing.youngstar.tools.ToastUtils;
import com.chuanqing.youngstar.tools.ZoomOutPageTransformer;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 擂台活动详情
 */
public class LeitaiMoreActivity extends BaseActivity {

    private ViewPager mViewPager ;
//    private String[] mPics = new String[]{};
    private LinearLayout ll_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square_more);
        ButterKnife.bind(this);
        showinfo();
        setTtitle();
    }
    private void initView(String[] jihe) {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        ll_layout = (LinearLayout) findViewById(R.id.ll_layout);

        //设置适配器
        mViewPager.setAdapter(new MyAdapter(this, jihe));
        mViewPager.setPageMargin(20);
        mViewPager.setOffscreenPageLimit(jihe.length);
        mViewPager.setCurrentItem(jihe.length*1000);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());//3D画廊模式

        //左右都有图
        mViewPager.setCurrentItem(1);

        //viewPager左右两边滑动无效的处理
        ll_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return mViewPager.dispatchTouchEvent(motionEvent);
            }
        });
    }

    /**
     * 展示信息
     */
    @BindView(R.id.leitai_more_img)
    ImageView img_info;
    String[] imgurl;
    @BindView(R.id.leitai_cansai)
    Button btn_cansai;
    @BindView(R.id.leitai_baoming)
    Button btn_baoming;
    @BindView(R.id.leitai_jieshu)
    Button btn_jieshu;
    private void showinfo() {
        OkGo.post(Api.activity_more)
                .tag(this)
                .params("activityCode",getIntent().getStringExtra("activitycode").toString())
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("擂台详情",s);
                        Gson gson = new Gson();
                        LeitaiMoreBean leitaiMoreBean = gson.fromJson(s,LeitaiMoreBean.class);
                        if (leitaiMoreBean.getState()==1){
                            //含，号
                            if (leitaiMoreBean.getData().getList_img().contains(",")){
                                imgurl = leitaiMoreBean.getData().getList_img().split(",");
                                initView(imgurl);
                            }else {
                                imgurl = new String[1];
                                imgurl[0] = leitaiMoreBean.getData().getList_img();
                                initView(imgurl);
                            }
                            Glide.with(LeitaiMoreActivity.this)
                                    .load(Api.ossurl+leitaiMoreBean.getData().getDetail_img())
                                    .placeholder(R.mipmap.my169)
                                    .error(R.mipmap.my169)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .into(img_info);
                            //0：不能报名，1可以报名2活动结束
                            if (leitaiMoreBean.getData().getFlag().equals("0")){
                                btn_cansai.setVisibility(View.VISIBLE);
                                btn_baoming.setVisibility(View.GONE);
                                btn_jieshu.setVisibility(View.GONE);
                            }else if (leitaiMoreBean.getData().getFlag().equals("1")){
                                //0:未报名，1已报名
                                if (leitaiMoreBean.getData().getUserFlag().equals("0")){
                                    btn_cansai.setVisibility(View.VISIBLE);
                                    btn_baoming.setVisibility(View.VISIBLE);
                                    btn_jieshu.setVisibility(View.GONE);
                                }else{
                                    btn_cansai.setVisibility(View.VISIBLE);
                                    btn_baoming.setVisibility(View.VISIBLE);
                                    btn_baoming.setText("更换作品集");
                                    btn_jieshu.setVisibility(View.GONE);
                                }
                            }else if (leitaiMoreBean.getData().getFlag().equals("2")){
                                btn_cansai.setVisibility(View.GONE);
                                btn_baoming.setVisibility(View.GONE);
                                btn_jieshu.setVisibility(View.VISIBLE);
                            }
                        }else {
                            ToastUtils.shortToast(leitaiMoreBean.getMessage());
                        }
                    }
                });
        btn_cansai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeitaiMoreActivity.this,PartakeActivity.class);
                intent.putExtra("activityCode",getIntent().getStringExtra("activitycode").toString());
                startActivity(intent);
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
        tv_title.setText("活动详情");
        left_img.setVisibility(View.VISIBLE);
        right_img.setVisibility(View.VISIBLE);
        //返回
        left_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LeitaiMoreActivity.this.finish();
            }
        });
        right_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //暂无分享功能
            }
        });
    }
}

package com.chuanqing.youngstar._active.zhichang;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar._active.leitai.LeitaiMoreActivity;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.myadapter.MyAdapter;
import com.chuanqing.youngstar.mybean.ZhaopingMoreBean;
import com.chuanqing.youngstar.tools.Api;
import com.chuanqing.youngstar.tools.BamAutoLineList;
import com.chuanqing.youngstar.tools.ToastUtils;
import com.chuanqing.youngstar.tools.ZoomOutPageTransformer;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 招聘详情
 */
public class ZhichangMoreActivity extends BaseActivity {
    private ViewPager mViewPager;
    private LinearLayout ll_layout;
    String[] imglist;
    String[] fulilist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhichang_more);
        ButterKnife.bind(this);
        showinfo();
        setTtitle();
        Log.e("信息值",getIntent().getStringExtra("employCode").toString());
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
        left_img.setVisibility(View.VISIBLE);
        right_img.setVisibility(View.VISIBLE);
        //返回
        left_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZhichangMoreActivity.this.finish();
            }
        });
        right_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //暂无分享功能
            }
        });
    }

    /**
     * 展示信息
     */
    @BindView(R.id.zhaopin_more_head)
    ImageView img_head;
    @BindView(R.id.zhaopin_more_id)
    TextView tv_id;
    @BindView(R.id.zhaopin_more_info)
    TextView tv_info;
    @BindView(R.id.zhaopin_more_yaoqiu)
    TextView tv_yaoqiu;
    @BindView(R.id.zhaopin_more_renshu)
    TextView tv_renshu;
    @BindView(R.id.zhaopin_more_xinzi)
    TextView tv_xinzi;
    @BindView(R.id.zhaopin_more_zhuanye)
    TextView tv_zhuanye;
    @BindView(R.id.zhaopin_more_jingyan)
    TextView tv_jingyan;
    @BindView(R.id.zhaopin_more_address)
    TextView tv_address;
    private void showinfo() {
        OkGo.post(Api.zhaoping_more)
                .tag(this)
                .params("employCode",getIntent().getStringExtra("employCode").toString())
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("招聘详情",s);
                        Gson gson = new Gson();
                        ZhaopingMoreBean zhaopingMoreBean = gson.fromJson(s,ZhaopingMoreBean.class);
                        if (zhaopingMoreBean.getState()==1){
                            if (zhaopingMoreBean.getData()!=null){
                                if (zhaopingMoreBean.getData().getImg().contains(",")){
                                    imglist = zhaopingMoreBean.getData().getImg().split(",");
                                    initView(imglist);
                                }else {
                                    imglist = new String[1];
                                    imglist[0] = zhaopingMoreBean.getData().getImg();
                                    initView(imglist);
                                }
                                Glide.with(ZhichangMoreActivity.this)
                                        .load(Api.ossurl+zhaopingMoreBean.getData().getUser_img())
                                        .error(R.mipmap.my11)
                                        .into(img_head);
                                tv_id.setText("SID:"+zhaopingMoreBean.getData().getUser_code());
                                tv_info.setText(zhaopingMoreBean.getData().getRequirements());
                                tv_yaoqiu.setText(zhaopingMoreBean.getData().getPosition());
                                tv_renshu.setText(zhaopingMoreBean.getData().getNum()+"");
                                tv_xinzi.setText(zhaopingMoreBean.getData().getSalary());
                                tv_zhuanye.setText(zhaopingMoreBean.getData().getMajor());
                                tv_jingyan.setText(zhaopingMoreBean.getData().getExperience());
                                tv_address.setText(zhaopingMoreBean.getData().getLocation());
                                //标题
                                if (zhaopingMoreBean.getData().getTitle().length()>8){
                                    tv_title.setText(zhaopingMoreBean.getData().getTitle().substring(0,8)+"..");
                                }else {
                                    tv_title.setText(zhaopingMoreBean.getData().getTitle());
                                }
                                if (zhaopingMoreBean.getData().getBenefits().contains(",")){
                                    fulilist = zhaopingMoreBean.getData().getBenefits().split(",");
                                    createView(fulilist);
                                }else {
                                    fulilist = new String[1];
                                    fulilist[0] = zhaopingMoreBean.getData().getBenefits();
                                    createView(fulilist);
                                }
                            }else {
                                ToastUtils.shortToast("没有找到详情页面");
                            }

                        }else {
                            ToastUtils.shortToast(zhaopingMoreBean.getMessage());
                        }
                    }
                });
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
    @BindView(R.id.fulilinear)
    BamAutoLineList fuli_linear;
    /**
     * 初始化一个布局
     *
     * @return
     */
    private void createView(String[] name) {
        for (int i = 0; i < name.length; i++) {
            // 实例化一个View，以放到List里
            View item = LayoutInflater.from(ZhichangMoreActivity.this).inflate(R.layout.fuli_items, null);
            // 设置View里的文本值
            TextView textView = item.findViewById(R.id.item_linear);
            textView.setText(name[i]);
            fuli_linear.addView(item);
        }
    }
}

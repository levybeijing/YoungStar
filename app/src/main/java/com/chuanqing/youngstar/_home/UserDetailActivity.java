package com.chuanqing.youngstar._home;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar._mine.student.FragmentStatus;
import com.chuanqing.youngstar._mine.student.FragmentWorks;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.login.bean.CommenBean;
import com.chuanqing.youngstar.myadapter.AdapterMineVP;
import com.chuanqing.youngstar.mybean.FragMinesBean;
import com.chuanqing.youngstar.mybean.MineWorksBean;
import com.chuanqing.youngstar.mybean.StudentDetailBean;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class UserDetailActivity extends BaseActivity implements View.OnClickListener {
    private List<Fragment> list=new ArrayList<>();
    private ImageView iv_bg;
    private ImageView iv_photo;
    private TextView tv_id;
    private TextView tv_lable;
    private ImageView iv_sex;
    private ViewPager vp;
    private RadioGroup rg;
    private RadioButton rb;
    private TextView tvcare;
    private String usercode;
    private TextView tvhot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetail);

        usercode = getIntent().getStringExtra("usercode");

        list.add(new FragmentStatus());
        list.add(new FragmentWorks());
        list.add(new FragmentActS());
        initView();
    }

    private void initView() {
        iv_bg = findViewById(R.id.iv_bg_photo_usedetail);
        iv_photo = findViewById(R.id.iv_photo_userdetail);
        tv_id = findViewById(R.id.tv_id_userdetail);
        tv_lable = findViewById(R.id.tv_lable_userdetail);
        iv_sex = findViewById(R.id.iv_sex_userdetail);

        rg = findViewById(R.id.rg_userdetail);
        vp = findViewById(R.id.vp_userdetail);
        AdapterMineVP adapter=new AdapterMineVP(getSupportFragmentManager(),list);
        vp.setAdapter(adapter);
//关注
        rb = findViewById(R.id.rb_care_userdetail);
        rb.setOnClickListener(this);
        tvcare = findViewById(R.id.tv_care_userdetail);

        tvhot = findViewById(R.id.tv_hot_userdetail);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb1_userdetail:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.rb2_userdetail:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.rb3_userdetail:
                        vp.setCurrentItem(2);
                        break;
                }

            }
        });
//        TabLayout tab = findViewById(R.id.tab_userdetail);

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        ((RadioButton)findViewById(R.id.rb1_userdetail)).setChecked(true);
                        break;
                    case 1:
                        ((RadioButton)findViewById(R.id.rb2_userdetail)).setChecked(true);
                        break;
                    case 2:
                        ((RadioButton)findViewById(R.id.rb3_userdetail)).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
//顶部渐变
        AppBarLayout appbars = findViewById(R.id.appbar_userdetail);
        Toolbar toolbars = findViewById(R.id.toolbar_userdetail);
        appbars.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                toolbars.setBackgroundColor(changeAlpha(getResources().getColor(R.color.mainColor),Math.abs(i*1.0f)/appbars.getTotalScrollRange()));
//                        tab.setBackgroundColor(changeAlpha(getResources().getColor(R.color.mainColor),1-Math.abs(i*1.0f)/appbars.getTotalScrollRange()));
            }
        });

        findViewById(R.id.ll_care_userdetail).setOnClickListener(this);
        findViewById(R.id.ll_hot_userdetail).setOnClickListener(this);

        requestTop();
    }

    private void requestTop() {
        OkGo.post(Urls.getStudentDetail)//
                .tag(this)//
                .params("userCode", usercode)//文件名
                .params("code",  (String) SharedPFUtils.getParam(this,"usercode",""))//缩略图 省略>?
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("=============", "Insfo"+s);
                        StudentDetailBean.DataBean data = new Gson().fromJson(s, StudentDetailBean.class).getData();
                        Glide.with(UserDetailActivity.this).load(Urls.IMAGEURL+data.getUser_img()).into(iv_photo);
                        Glide.with(UserDetailActivity.this).load(Urls.IMAGEURL+data.getUser_img()).into(iv_bg);
                        tv_lable.setText(data.getLabel());
                        tv_id.setText("SID:"+data.getUser_code());
                        switch (data.getSex()){
                            case "男":
                                iv_sex.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.sex_man));
                                break;
                            case "女":
                                iv_sex.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.sex_woman));
                                break;
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_care_userdetail:
                if (rb.isChecked()){
                    rb.setChecked(false);
                    tvcare.setText("已关注");
                    tvcare.setTextColor(Color.parseColor("#999999"));
                    requestCare();
                }else{
                    tvcare.setText("关注");
                    tvcare.setTextColor(Color.parseColor("#F56250"));
                    rb.setChecked(true);
                    requestNoCare();
                }
                break;
            case R.id.ll_hot_userdetail:
                topraise();
                break;
        }
    }

    private void topraise() {
        OkGo.post(Urls.addStudentRecommendFree)//
                .tag(this)//
                .params("userCode",  (String) SharedPFUtils.getParam(this,"usercode",""))
                .params("concernCode", usercode)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("=============", "topraise"+s);
                        CommenBean bean = new Gson().fromJson(s, CommenBean.class);
                        switch (bean.getMessage()){
                            case "请求成功":
                                tvhot.setText("");
                                break;
                            case "免费次数不足，即将消耗星币":
//弹窗，提示信息
                                break;
                            case "星币不足，请充值":
//跳转充值页面
                                break;
                        }
                    }
                });
    }

    private void requestNoCare() {
        OkGo.post(Urls.delfocusOn)//
                .tag(this)//
                .params("userCode",  (String) SharedPFUtils.getParam(this,"usercode",""))
                .params("concernCode", usercode)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("=============", "requestNoCare"+s);
                        Toast.makeText(UserDetailActivity.this, "已经取消关注", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void requestCare() {
        OkGo.post(Urls.addfocusOn)//
                .tag(this)//
                .params("userCode",  (String) SharedPFUtils.getParam(this,"usercode",""))
                .params("concernCode", usercode)
                .params("type", 1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("=============", "requestCare"+s);
                        Toast.makeText(UserDetailActivity.this, "关注成功", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public int changeAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }
}

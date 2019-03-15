package com.chuanqing.youngstar._home;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.UserHandle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chuanqing.youngstar.MainActivity;
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
    private String usercode,hot;
    private TextView tvhot;
    private int flag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetail);

        Intent intent = getIntent();
        usercode = intent.getStringExtra("usercode");
        hot = intent.getStringExtra("hot");
        flag = intent.getIntExtra("flag",-1);

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
        tvhot.setText(hot);
        if (flag==0){
            tvcare.setText("关注");
            tvcare.setTextColor(Color.parseColor("#F56250"));
            rb.setChecked(true);
        }else{
            rb.setChecked(false);
            tvcare.setText("已关注");
            tvcare.setTextColor(Color.parseColor("#999999"));
        }
        findViewById(R.id.iv_share_userdetail).setOnClickListener(this);
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
                        if (!s.contains("sex")){
                            return;
                        }
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
            case R.id.iv_share_userdetail:
                showpopup();
                break;
        }
    }

    private void showpopup() {
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_toshare, null);

        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(false);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);


        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
//        //底部弹出
        window.showAtLocation(UserDetailActivity.this.findViewById(R.id.ll_root_userdetail), Gravity.BOTTOM,0,0);

        ImageView iv_qq = view.findViewById(R.id.iv_qq_dialogtoshare);
        ImageView iv_wx = view.findViewById(R.id.iv_wb_dialogtoshare);
        ImageView iv_wb = view.findViewById(R.id.iv_wx_dialogtoshare);
        ImageView iv_wechat = view.findViewById(R.id.iv_wechat_dialogtoshare);

        //取消事件
        iv_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window.isShowing()){
                    window.dismiss();
                }
                Toast.makeText(UserDetailActivity.this, "iv_qq", Toast.LENGTH_SHORT).show();
            }
        });
        //取消事件
        iv_wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window.isShowing()){
                    window.dismiss();
                }
                Toast.makeText(UserDetailActivity.this, "iv_wx", Toast.LENGTH_SHORT).show();

            }
        });
        //取消事件
        iv_wb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window.isShowing()){
                    window.dismiss();
                }
                Toast.makeText(UserDetailActivity.this, "iv_wb", Toast.LENGTH_SHORT).show();

            }
        });
        //取消事件
        iv_wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window.isShowing()){
                    window.dismiss();
                }
                Toast.makeText(UserDetailActivity.this, "iv_wechat", Toast.LENGTH_SHORT).show();

            }
        });
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

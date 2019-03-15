package com.chuanqing.youngstar;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chuanqing.youngstar._active.StarActivityFragment;
import com.chuanqing.youngstar._add.PublishActivity;
import com.chuanqing.youngstar._add.TapeActivity;
import com.chuanqing.youngstar._add.company.RecuitActivity;
import com.chuanqing.youngstar._add.student.WorksActivity;
import com.chuanqing.youngstar._home.HomeFragment;
import com.chuanqing.youngstar._mine.MineFragment;
import com.chuanqing.youngstar._square.SquareFragment;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.login.choose.ChooseActivity;
import com.chuanqing.youngstar.login.login.LoginActivity;
import com.chuanqing.youngstar.mybean.IdentityBean;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.chuanqing.youngstar.tools.ToastUtils;
import com.chuanqing.youngstar.widget.CustomClickListener;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    HomeFragment homeFragment;
    SquareFragment squareFragment;
    StarActivityFragment starActivityFragment;
    MineFragment mineFragment;

    // 定义每个选项中的相关控件
    LinearLayout main_first;
    ImageView main_first_img;
    TextView main_first_tv;
    LinearLayout main_second;
    ImageView main_second_img;
    TextView main_second_tv;
    LinearLayout main_third;
    ImageView main_third_img;
    TextView main_third_tv;
    LinearLayout main_four;
    ImageView main_four_img;
    TextView main_four_tv;
    ImageView img_center;
    // 定义几个颜色
    private int whirt = 0xFFFFFFFF;  //背景色颜色
    private int gray = 0xFFE6E7E7;  //未选中颜色
    private int dark = 0xFFF56250;  //选中后的颜色
    // 定义FragmentManager对象管理器
    private FragmentManager fragmentManager;
    private static boolean mBackKeyPressed = false;//记录是否有首次按键
    public static int identity;
    public static String usercodes="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        initView(); // 初始化界面控件
        setChioceItem(0); // 初始化页面加载时显示第中间的选项卡
    }

    /**
     * 初始化页面
     */
    private void initView() {
        // 初始化底部导航栏的控件
        main_first = (LinearLayout) findViewById(R.id.main_first);
        main_first_img = (ImageView) findViewById(R.id.main_first_image);
        main_first_tv = (TextView) findViewById(R.id.main_first_text);
        main_second = (LinearLayout) findViewById(R.id.main_second);
        main_second_img = (ImageView) findViewById(R.id.main_second_image);
        main_second_tv = (TextView) findViewById(R.id.main_second_text);
        main_third = (LinearLayout) findViewById(R.id.main_third);
        main_third_img = (ImageView) findViewById(R.id.main_third_image);
        main_third_tv = (TextView) findViewById(R.id.main_third_text);
        main_four = (LinearLayout) findViewById(R.id.main_four);
        main_four_img = (ImageView) findViewById(R.id.main_four_image);
        main_four_tv = (TextView) findViewById(R.id.main_four_text);
        //中间切换身份用的图
        img_center = (ImageView) findViewById(R.id.main_img_center);
        //点击事件写入
        main_first.setOnClickListener(this);
        main_second.setOnClickListener(this);
        main_third.setOnClickListener(this);
        main_four.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                setChioceItem(3);
            }

            @Override
            protected void onFastClick() {

            }
        });
        img_center.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                requestIdentity();
            }

            @Override
            protected void onFastClick() {

            }
        });
//        RadioGroup rg = findViewById(R.id.rg_main);
//        rg.setOnCheckedChangeListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_first:
                setChioceItem(0);
                break;
            case R.id.main_second:
                setChioceItem(1);
                break;
            case R.id.main_third:
                setChioceItem(2);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        switch (checkedId){
//            case R.id.rb_home_main:
//                setChioceItem(0);
//                break;
//            case R.id.rb_square_main:
//                setChioceItem(1);
//                break;
//            case R.id.rb_medium_main:
//                requestIdentity();
//                break;
//            case R.id.rb_act_main:
//                setChioceItem(2);
//                break;
//            case R.id.rb_mine_main:
//                setChioceItem(3);
//                break;
//        }
    }
    private void requestIdentity() {
        OkGo.post(Urls.getUserType)//
                .tag(this)//
                .params("userCode",(String)SharedPFUtils.getParam(this,"usercode",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        成功则提示 考虑自动登录
                        Log.e("===============", "VeriCode: "+s);
                        IdentityBean bean = new Gson().fromJson(s, IdentityBean.class);
                        int status = bean.getData().getStatus();
                        int type = bean.getData().getType();
                        SharedPFUtils.setParam(MainActivity.this,"identity", type);
                        SharedPFUtils.setParam(MainActivity.this,"status", status);
                        if (status==1){
                            startActivity(new Intent(MainActivity.this,AuditingActivity.class));
                            return;
                        }
                        if (status==3||status==4){
                            startActivity(new Intent(MainActivity.this,AuditFailedActivity.class));
                            return;
                        }
                        switch (type){
                            case 1:
                                showPopwindow();  //展示中间按钮点击事件
                                break;
                            case 2:
                                showPopwindowgongsi();
                                break;
                            case 3:
                                showPopwindowtouziren();
                                break;
                            case 4:
                                showPopwindowfensi();
                                break;
                        }

                    }
                });
    }
    private void requestIdenMine() {
        OkGo.post(Urls.getUserType)//
                .tag(this)//
                .params("userCode",(String)SharedPFUtils.getParam(this,"usercode",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        成功则提示 考虑自动登录
                        Log.e("===============", "积分哈开始缴费单: "+SharedPFUtils.getParam(MainActivity.this,"usercode",""));
                        Log.e("===============", "积分哈开始缴费单: "+s);
                        IdentityBean bean = new Gson().fromJson(s, IdentityBean.class);
                        if (bean.getData()==null){
                            SharedPFUtils.clearData();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            return;
                        }
                        int type = bean.getData().getType();
                        int status = bean.getData().getStatus();

                        SharedPFUtils.setParam(MainActivity.this,"identity", type);
                        SharedPFUtils.setParam(MainActivity.this,"status", status);
                        switch (status){
                            case 1:
                                startActivity(new Intent(MainActivity.this,AuditingActivity.class));
                                return;
                            case 3:
                            case 4:
                                Intent intent = new Intent(MainActivity.this, AuditFailedActivity.class);
                                intent.putExtra("info",bean.getData().getReason());
                                startActivity(intent);
                                return;
                        }
                        if (mineFragment!=null){
                            fragmentManager.beginTransaction().remove(mineFragment).commit();
                        }
                        mineFragment = new MineFragment();
                        fragmentManager.beginTransaction().add(R.id.content,mineFragment).commit();
                    }
                });
    }
    /**
     * 设置点击选项卡的事件处理
     *
     * @param index 选项卡的标号：0, 1, 2, 3, 4
     */
    private void setChioceItem(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        clearChioce(); // 清空, 重置选项, 隐藏所有Fragment
        hideFragments(fragmentTransaction);
        switch (index) {
            case 0:
                main_first_tv.setTextColor(dark);
                main_first_img.setImageDrawable(MainActivity.this.getResources().getDrawable(R.mipmap.home1));
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.content, homeFragment);
                } else {
                    fragmentTransaction.show(homeFragment);
                }
                break;
            case 1:
                main_second_tv.setTextColor(dark);
                main_second_img.setImageDrawable(MainActivity.this.getResources().getDrawable(R.mipmap.square1));
                if (squareFragment == null) {
                    squareFragment = new SquareFragment();
                    fragmentTransaction.add(R.id.content, squareFragment);
                } else {
                    fragmentTransaction.show(squareFragment);
                }
                break;
            case 2:
                main_third_tv.setTextColor(dark);
                main_third_img.setImageDrawable(MainActivity.this.getResources().getDrawable(R.mipmap.staractivity1));
                if (starActivityFragment == null) {
                    starActivityFragment = new StarActivityFragment();
                    fragmentTransaction.add(R.id.content, starActivityFragment);
                } else {
                    fragmentTransaction.show(starActivityFragment);
                }
                break;
            case 3:
                main_four_tv.setTextColor(dark);
                main_four_img.setImageDrawable(MainActivity.this.getResources().getDrawable(R.mipmap.mine1));
//                int status = (int)SharedPFUtils.getParam(this, "status", -1);
//                if (status==1){
                    requestIdenMine();
//                }else{
//                    if (mineFragment!=null){
//                        fragmentManager.beginTransaction().remove(mineFragment).commit();
//                    }
//                    mineFragment = new MineFragment();
//                    fragmentManager.beginTransaction().add(R.id.content,mineFragment).commit();
//                }
                break;
        }
        fragmentTransaction.commit(); // 提交
    }
    /**
     * 当选中其中一个选项卡时，其他选项卡重置为默认
     */
    private void clearChioce() {
        main_first_tv.setTextColor(gray);
        main_first.setBackgroundColor(whirt);
        main_first_img.setImageDrawable(MainActivity.this.getResources().getDrawable(R.mipmap.home2));
        main_second_tv.setTextColor(gray);
        main_second.setBackgroundColor(whirt);
        main_second_img.setImageDrawable(MainActivity.this.getResources().getDrawable(R.mipmap.square2));
        main_third_tv.setTextColor(gray);
        main_third.setBackgroundColor(whirt);
        main_third_img.setImageDrawable(MainActivity.this.getResources().getDrawable(R.mipmap.staractivity2));
        main_four_tv.setTextColor(gray);
        main_four.setBackgroundColor(whirt);
        main_four_img.setImageDrawable(MainActivity.this.getResources().getDrawable(R.mipmap.mine2));
    }
    /**
     * 隐藏Fragment
     *
     * @param fragmentTransaction
     */
    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (squareFragment != null) {
            fragmentTransaction.hide(squareFragment);
        }
        if (starActivityFragment != null) {
            fragmentTransaction.hide(starActivityFragment);
        }
        if (mineFragment != null) {
            fragmentTransaction.hide(mineFragment);
        }
    }

    /**
     * 返回键触发的方法
     * */
    @Override
    public void onBackPressed() {
        if (!mBackKeyPressed) {
            ToastUtils.shortToast("再按一次退出程序");
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {

                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);//延时两秒，如果超出则擦错第一次按键记录
        } else
        {
            this.finish();
            System.exit(0);
        }
    }

    //声明一个静态常量，用作退出BaseActivity的Tag
    public static final String EXIST = "exist";
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {//判断其他Activity启动本Activity时传递来的intent是否为空
            //获取intent中对应Tag的布尔值
            boolean isExist = intent.getBooleanExtra(EXIST, false);
            //如果为真则退出本Activity
            if (isExist) {
//                this.finish();
            }
        }
    }

    /**
     * 显示学生popupWindow
     */
    private void showPopwindow() {
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.add_student, null);

        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()

        PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);

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
        window.showAtLocation(MainActivity.this.findViewById(R.id.main_img_center),Gravity.BOTTOM,0,0);

        LinearLayout layout_video = view.findViewById(R.id.add_video);
        LinearLayout layout_zuopinji = view.findViewById(R.id.add_zuopinji);
        LinearLayout layout_luyin = view.findViewById(R.id.add_luyin);
        ImageView img_no = view.findViewById(R.id.add_no);
        //取消事件
        img_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window.isShowing()){
                    window.dismiss();
                }
            }
        });
        //视频、图片
        layout_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window.isShowing()){
                    window.dismiss();
                    Intent intent = new Intent(MainActivity.this, PublishActivity   .class);
                    startActivity(intent);
                }
            }
        });
        //作品集
        layout_zuopinji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window.isShowing()){
                    window.dismiss();
                    Intent intent = new Intent(MainActivity.this, WorksActivity.class);
                    startActivity(intent);
                }
            }
        });
        //录音
        layout_luyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window.isShowing()){
                    window.dismiss();
                    Intent intent = new Intent(MainActivity.this, TapeActivity.class);
                    startActivity(intent);
                }
            }
        });

        // popWindow消失监听方法
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                System.out.println("popWindow消失");
            }
        });

    }

    /**
     * 显示粉丝popupWindow
     */
    private void showPopwindowfensi() {
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.add_fans, null);

        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()

        PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);

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
        window.showAtLocation(MainActivity.this.findViewById(R.id.main_img_center),Gravity.BOTTOM,0,0);

        LinearLayout layout_video = view.findViewById(R.id.add_video);
        LinearLayout layout_luyin = view.findViewById(R.id.add_luyin);
        ImageView img_no = view.findViewById(R.id.add_no);
        //取消事件
        img_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window.isShowing()){
                    window.dismiss();
                }
            }
        });
        //切换身份
        layout_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window.isShowing()){
                    window.dismiss();
                    Intent intent = new Intent(MainActivity.this, ChooseActivity.class);
                    startActivity(intent);
                }
            }
        });

        //客服
        layout_luyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window.isShowing()){
                    showdialog();
                    window.dismiss();
                }
            }
        });

        // popWindow消失监听方法
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                System.out.println("popWindow消失");
            }
        });

    }

    private void showdialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_service,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();

        TextView no = view.findViewById(R.id.tv_cancel_dialogservice);
        TextView ok = view.findViewById(R.id.tv_ok_dialogservice);

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" +Urls.SERVICE);
                intent.setData(data);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    /**
     * 显示公司popupWindow
     */
    private void showPopwindowgongsi() {
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.add_company, null);

        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()

        PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);

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
        window.showAtLocation(MainActivity.this.findViewById(R.id.main_img_center),Gravity.BOTTOM,0,0);

        LinearLayout layout_video = view.findViewById(R.id.add_video);
        LinearLayout layout_zuopinji = view.findViewById(R.id.add_zuopinji);
        LinearLayout layout_luyin = view.findViewById(R.id.add_luyin);
        ImageView img_no = view.findViewById(R.id.add_no);
        //取消事件
        img_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window.isShowing()){
                    window.dismiss();
                }
            }
        });
        //公告
        layout_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window.isShowing()){
                    window.dismiss();
                    Intent intent = new Intent(MainActivity.this, PublishActivity   .class);
                    startActivity(intent);
                }
            }
        });
        //发布招聘
        layout_zuopinji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window.isShowing()){
                    window.dismiss();
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse("tel:" +Urls.SERVICE);
                    intent.setData(data);
                    startActivity(intent);
                }
            }
        });
        //客服
        layout_luyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window.isShowing()){
                    Intent intent = new Intent(MainActivity.this, RecuitActivity.class);
                    startActivity(intent);
                    window.dismiss();
                }
            }
        });

        // popWindow消失监听方法
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                System.out.println("popWindow消失");
            }
        });

    }

    /**
     * 显示投资人popupWindow
     */
    private void showPopwindowtouziren() {
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.add_invest, null);

        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()

        PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);

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
        window.showAtLocation(MainActivity.this.findViewById(R.id.main_img_center),Gravity.BOTTOM,0,0);

        LinearLayout layout_luyin = view.findViewById(R.id.add_luyin);
        ImageView img_no = view.findViewById(R.id.add_no);
        //取消事件
        img_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window.isShowing()){
                    window.dismiss();
                }
            }
        });


        //客服
        layout_luyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window.isShowing()){
                    window.dismiss();
                }
            }
        });

        // popWindow消失监听方法
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                System.out.println("popWindow消失");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        identity = (int)SharedPFUtils.getParam(MainActivity.this,"identity",4);
        usercodes = (String) SharedPFUtils.getParam(MainActivity.this,"usercode","");
    }

}

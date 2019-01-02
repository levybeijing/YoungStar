package com.chuanqing.youngstar;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chuanqing.youngstar._active.StarActivityFragment;
import com.chuanqing.youngstar._home.HomeFragment;
import com.chuanqing.youngstar._mine.MineFragment;
import com.chuanqing.youngstar._square.SquareFragment;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.tools.ToastUtils;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity implements View.OnClickListener {
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
        main_four.setOnClickListener(this);

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
            case R.id.main_four:
                setChioceItem(3);
                break;
            case R.id.main_img_center:
                //中间按钮切换身份使用
            default:
                break;
        }
    }

    /**
     * 设置点击选项卡的事件处理
     *
     * @param index 选项卡的标号：0, 1, 2, 3,4
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
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    fragmentTransaction.add(R.id.content, mineFragment);
                } else {
                    fragmentTransaction.show(mineFragment);
                }
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
}

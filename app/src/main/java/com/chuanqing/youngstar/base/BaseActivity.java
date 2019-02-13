package com.chuanqing.youngstar.base;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.chuanqing.youngstar.tools.ScreenUtils;


public class BaseActivity extends FragmentActivity {
    /** 是否禁止旋转屏幕 **/
    private boolean isAllowScreenRoate = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isAllowScreenRoate) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        //取消标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        final int sdk = Build.VERSION.SDK_INT;
        Window window = this.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        if(Build.VERSION.SDK_INT >= 21) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(Color.TRANSPARENT);
        }


//        ScreenUtils.adaptScreen(this, 392,true);//在绘图之前执行该方法  392是设计图的dp值 可根据实际
        ScreenUtils.adaptScreen(this, 375,true);//在绘图之前执行该方法  392是设计图的dp值 可根据实际

    }
    @Override
    protected void onDestroy() {
        ScreenUtils.cancelAdaptScreen(this);
        super.onDestroy();
    }
}

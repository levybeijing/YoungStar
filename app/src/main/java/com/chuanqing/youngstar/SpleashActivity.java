package com.chuanqing.youngstar;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.login._student.ImageAuthenActivity;
import com.chuanqing.youngstar.login.choose.ChooseActivity;
import com.chuanqing.youngstar.login.login.LoginActivity;
import com.chuanqing.youngstar.tools.SharedPFUtils;

public class SpleashActivity extends BaseActivity {
    //延迟3秒
    private static final long ANIMATION_TIME = 1000;

    public SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spleash);

//        动态权限
        String[] permession =new String[]{
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        PermissionsUtils.getInstance().chekPermissions(this, permession, new PermissionsUtils.IPermissionsResult() {
            @Override
            public void passPermissons() {
                goHome();
            }

            @Override
            public void forbitPermissons() {

            }
        });
    }
    private void goHome() {
        //图片渐变模糊度始终
        AlphaAnimation gradient = new AlphaAnimation(1.0f, 0.8f);
        //渐变时间
        gradient.setDuration(ANIMATION_TIME);

        gradient.setFillAfter(true);
        //展示图片渐变动画
        this.findViewById(R.id.bg_spleash).startAnimation(gradient);

        gradient.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                boolean islogin = (boolean) SharedPFUtils.getParam(SpleashActivity.this, "islogin", false);
                int identity = (int) SharedPFUtils.getParam(SpleashActivity.this, "identity", -1);
                if (islogin){
                    if (identity>0){
                        startActivity(new Intent(SpleashActivity.this,MainActivity.class));
                    }else{
                        startActivity(new Intent(SpleashActivity.this,ChooseActivity.class));
                    }
                }else{
                    startActivity(new Intent(SpleashActivity.this,LoginActivity.class));
                }

//                startActivity(new Intent(SpleashActivity.this, ImageAuthenActivity.class));

                finish();
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsUtils.getInstance().onRequestPermissionsResult(this,requestCode,permissions,grantResults);
    }
}

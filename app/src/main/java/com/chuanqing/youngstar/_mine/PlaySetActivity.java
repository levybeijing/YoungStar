package com.chuanqing.youngstar._mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

public class PlaySetActivity extends BaseActivity implements View.OnClickListener {

    private ImageView all;
    private ImageView wifi;
    private ImageView close;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playset);

        initView();
    }

    private void initView() {
        all = findViewById(R.id.iv_all_play);
        wifi = findViewById(R.id.iv_wifi_play);
        close = findViewById(R.id.iv_close_play);

        findViewById(R.id.ll_all_play).setOnClickListener(this);
        findViewById(R.id.ll_wifi_play).setOnClickListener(this);
        findViewById(R.id.ll_close_play).setOnClickListener(this);

        findViewById(R.id.iv_back_play).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_all_play:
                SharedPFUtils.setParam(this,"videoplay",2);
                all.setVisibility(View.VISIBLE);
                wifi.setVisibility(View.INVISIBLE);
                close.setVisibility(View.INVISIBLE);
                setvideo(1);
                break;
            case R.id.ll_wifi_play:
                SharedPFUtils.setParam(this,"videoplay",1);
                wifi.setVisibility(View.VISIBLE);
                all.setVisibility(View.INVISIBLE);
                close.setVisibility(View.INVISIBLE);
                setvideo(2);
                break;
            case R.id.ll_close_play:
                SharedPFUtils.setParam(this,"videoplay",0);
                close.setVisibility(View.VISIBLE);
                wifi.setVisibility(View.INVISIBLE);
                all.setVisibility(View.INVISIBLE);
                setvideo(3);
                break;
            case R.id.iv_back_play:
                finish();
                break;
        }
    }
    private void setvideo(int a){
        OkGo.post(Urls.updateUserVideoSwitch)//
                .tag(this)//
                .params("userCode", (String) SharedPFUtils.getParam(this,"usercode",""))//文件名
                .params("musicSwitch", a)//墙的ID
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("=============", "updateUserVideoSwitch"+s);
                    }
                });
    }
}

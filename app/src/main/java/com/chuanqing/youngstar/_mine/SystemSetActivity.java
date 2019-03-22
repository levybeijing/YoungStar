package com.chuanqing.youngstar._mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

public class SystemSetActivity extends BaseActivity implements View.OnClickListener {

    private Switch audio;
    private Switch shake;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systemset);

        initView();
    }

    private void initView() {
        findViewById(R.id.ll_autoplay_system).setOnClickListener(this);

        findViewById(R.id.iv_back_system).setOnClickListener(this);


        audio = findViewById(R.id.sw_audio_system);
        shake = findViewById(R.id.sw_shake_system);
        audio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPFUtils.setParam(SystemSetActivity.this,"voice",isChecked);
                int a=isChecked?1:0;
                setvoice(a);
                SharedPFUtils.setParam(SystemSetActivity.this,"voice",isChecked);
            }
        });
        shake.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPFUtils.setParam(SystemSetActivity.this,"shake",isChecked);
                int b=isChecked?1:0;
                setshake(b);
                SharedPFUtils.setParam(SystemSetActivity.this,"shake",isChecked);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_autoplay_system:
                startActivity(new Intent(this,PlaySetActivity.class));
                break;
            case R.id.iv_back_system:
                finish();
                break;
        }
    }

    private void setvoice(int a){
        OkGo.post(Urls.updateUserMusicSwitch)//
                .tag(this)//
                .params("userCode", (String) SharedPFUtils.getParam(this,"usercode",""))//文件名
                .params("musicSwitch", a)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("=============", "updateUserMusicSwitch"+s);
                    }
                });
    }
    private void setshake(int a){
        OkGo.post(Urls.updateUserShockSwitch)//
                .tag(this)//
                .params("userCode", (String) SharedPFUtils.getParam(this,"usercode",""))//文件名
                .params("shockSwitch", a)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("=============", "updateUserShockSwitch"+s);
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        shake.setChecked((Boolean) SharedPFUtils.getParam(this,"shake",false));
        audio.setChecked((Boolean) SharedPFUtils.getParam(this,"voice",false));
    }
}

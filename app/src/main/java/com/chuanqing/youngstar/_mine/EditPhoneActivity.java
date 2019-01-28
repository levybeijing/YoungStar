package com.chuanqing.youngstar._mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.mybean.FragCareCBean;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class EditPhoneActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_phone;
    private EditText et_code;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changephone);

        initView();

    }

    private void initView() {

        et_phone = findViewById(R.id.et_phone_changephone);
        et_code = findViewById(R.id.et_code_changephone);

        findViewById(R.id.tv_getcode_changephone).setOnClickListener(this);
        TextView tochange = findViewById(R.id.tv_tochange_changephone);
        tochange.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_getcode_changephone:
                String phone = et_phone.getText().toString().trim();
                OkGo.post(Urls.getSms)//
                        .tag(this)//
                        .params("mobile", phone)//墙的ID
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
//                                Log.e("=============", "getSms"+s);
                            }
                        });
                break;
            case R.id.tv_tochange_changephone:
                String pho = et_phone.getText().toString().trim();
                String code = et_code.getText().toString().trim();

                OkGo.post(Urls.checkSms)//
                    .tag(this)//
                    .params("mobile", pho)//墙的ID
                    .params("smsCode", code)//墙的ID
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            Log.e("=============", "getSms"+s);
                            change(pho);
                        }
                    });
                break;
        }
    }
    private void change(String phone){
        OkGo.post(Urls.updateUserMobile)//
                .tag(this)//
                .params("userCode", (String) SharedPFUtils.getParam(this,"usercode",""))//文件名
                .params("mobile", phone)//墙的ID
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("=============", "updateUserMobile"+s);
                    }
                });
    }
}

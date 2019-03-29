package com.chuanqing.youngstar.login.forget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.myadapter.AdapterFragCareSRV;
import com.chuanqing.youngstar.mybean.MatchBean;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class ForgetPWDActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_phone;
    private EditText et_code;
    private EditText et_pwd;
    private EditText et_again;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpwd);

        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back_forget).setOnClickListener(this);
        findViewById(R.id.tv_ok_forget).setOnClickListener(this);
        findViewById(R.id.tv_getcode_forget).setOnClickListener(this);

        et_phone = findViewById(R.id.et_phone_forget);
        et_code = findViewById(R.id.et_code_forget);
        et_pwd = findViewById(R.id.et_pwd_forget);
        et_again = findViewById(R.id.et_pwdagain_forget);
    }
    private void getcode() {
        String s = et_phone.getText().toString().trim();
        OkGo.post(Urls.getSms)//
                .tag(this)//
                .params("mobile", s)//文件名
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("=============", "getSms"+s);
                    }
                });
    }
    private void checkcode() {
        String s = et_phone.getText().toString().trim();
        String code = et_code.getText().toString().trim();
        OkGo.post(Urls.checkSms)//
                .tag(this)//
                .params("mobile", s)//文件名
                .params("smsCode", code)//文件名
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("=============", "checkSms"+s);
//                      验证验证码！！
                        changepwd();
                    }
                });
    }
    private void changepwd() {
        String s = et_phone.getText().toString().trim();
        String pwd = et_pwd.getText().toString().trim();
        String again = et_again.getText().toString().trim();
        if (!pwd.equals(again)){
            Toast.makeText(this, "前后输入不一致，重新输入", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.post(Urls.forgetUserPassword)//
                .tag(this)//
                .params("mobile", s)//文件名
                .params("password", pwd)//文件名
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("=============", "forgetUserPassword"+s);
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_forget:
                finish();
                break;
            case R.id.tv_getcode_forget:
                getcode();
                break;
            case R.id.tv_ok_forget:
                checkcode();
                break;
        }
    }
}

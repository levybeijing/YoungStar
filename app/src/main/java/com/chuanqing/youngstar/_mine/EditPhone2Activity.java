package com.chuanqing.youngstar._mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.login.bean.CommenBean;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.chuanqing.youngstar.tools.StringUtil;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

public class EditPhone2Activity extends BaseActivity implements View.OnClickListener {

    private EditText et_code;
    private EditText et_phone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changephone2);

        initView();
    }

    private void initView() {
//
        et_phone = findViewById(R.id.et_phone_changephone2);
        et_code = findViewById(R.id.et_code_changephone2);
//
        TextView tv_ok = findViewById(R.id.tv_getcode_changephone2);
        tv_ok.setOnClickListener(this);
        et_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()==6){
                    tv_ok.setClickable(true);
                    tv_ok.setBackgroundColor(Color.parseColor("#F5575F"));
                }else{
                    tv_ok.setClickable(false);
                    tv_ok.setBackgroundColor(Color.parseColor("#CCCCCC"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//
        TextView tochange = findViewById(R.id.tv_tochange_changephone2);
        tochange.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_getcode_changephone2:
                String phone = et_phone.getText().toString().trim();
                if (!StringUtil.isPhoneNumber(phone)){
                    Toast.makeText(this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
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
            case R.id.tv_tochange_changephone2:
                String pho = et_phone.getText().toString().trim();
                String code = et_code.getText().toString().trim();
                if (!StringUtil.isPhoneNumber(pho)){
                    Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                OkGo.post(Urls.checkSms)//
                    .tag(this)//
                    .params("mobile", pho)//墙的ID
                    .params("smsCode", code)//墙的ID
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            Log.e("=============", "getSms"+s);
                            CommenBean bean = new Gson().fromJson(s, CommenBean.class);
                            if (bean.getMessage().equals("请求成功")){
                                change(pho);
                            }else{
                                Toast.makeText(EditPhone2Activity.this, ""+bean.getMessage(), Toast.LENGTH_SHORT).show();
                            }
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
                        CommenBean bean = new Gson().fromJson(s, CommenBean.class);
                        if (bean.getMessage().equals("请求成功")){
                            Toast.makeText(EditPhone2Activity.this, "修改成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(EditPhone2Activity.this, ""+bean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

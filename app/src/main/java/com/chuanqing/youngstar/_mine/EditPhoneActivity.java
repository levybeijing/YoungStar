package com.chuanqing.youngstar._mine;

import android.content.Intent;
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
import com.chuanqing.youngstar.mybean.FragCareCBean;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class EditPhoneActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_code;
    private TextView tv_phone;
    private String phone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changephone);

        phone = (String) SharedPFUtils.getParam(this, "phone", "");
        initView();
    }

    private void initView() {

        tv_phone = findViewById(R.id.tv_phone_changephone);
        et_code = findViewById(R.id.et_code_changephone);
        tv_phone.setText(phone);

        findViewById(R.id.tv_getcode_changephone).setOnClickListener(this);
        TextView tochange = findViewById(R.id.tv_tochange_changephone);
        tochange.setOnClickListener(this);
        et_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()==6){
                    tochange.setClickable(true);
                    tochange.setBackgroundColor(Color.parseColor("#F5575F"));
                    tochange.setTextColor(Color.parseColor("#FFFFFF"));
                }else{
                    tochange.setClickable(false);
                    tochange.setBackgroundColor(Color.parseColor("#CCCCCC"));
                    tochange.setTextColor(Color.parseColor("#000000"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_getcode_changephone:
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
                String code = et_code.getText().toString().trim();
                OkGo.post(Urls.checkSms)//
                    .tag(this)//
                    .params("mobile", phone)//墙的ID
                    .params("smsCode", code)//墙的ID
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            Log.e("=============", "getSms"+s);
                            CommenBean bean = new Gson().fromJson(s, CommenBean.class);
                            if (bean.getMessage().equals("请求成功")){
                                startActivity(new Intent(EditPhoneActivity.this,EditPhone2Activity.class));
                                finish();
                            }else{
                                Toast.makeText(EditPhoneActivity.this, ""+bean.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                break;
        }
    }
}

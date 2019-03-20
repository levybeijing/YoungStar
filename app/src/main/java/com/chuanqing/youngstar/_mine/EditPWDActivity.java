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
import com.chuanqing.youngstar.login.login.LoginActivity;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

public class EditPWDActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_code;
    private TextView tv_phone;
    private String phone;
    private TextView tv_ok;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editpwd);
        phone = (String) SharedPFUtils.getParam(this, "phone", "");
        initView();
    }

    private void initView() {
        findViewById(R.id.tv_getcode_editpwd).setOnClickListener(this);
        tv_ok = findViewById(R.id.tv_tochange_editpwd);
        tv_ok.setOnClickListener(this);

        et_code = findViewById(R.id.et_code_editpwd);
        tv_phone = findViewById(R.id.tv_current_editpwd);
        tv_phone.setText(phone);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_getcode_editpwd:
//                String phone = (String) SharedPFUtils.getParam(EditPWDActivity.this,"phone","");
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
            case R.id.tv_tochange_editpwd:
                String trim = et_code.getText().toString().trim();
                OkGo.post(Urls.checkSms)//
                        .tag(this)//
                        .params("mobile", phone)//墙的ID
                        .params("smsCode", trim)//墙的ID
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                Log.e("=============", "getSms"+s);
                                startActivity(new Intent(EditPWDActivity.this,EditPWD2Activity.class));
                                finish();
                            }
                        });
                break;

        }
    }
}

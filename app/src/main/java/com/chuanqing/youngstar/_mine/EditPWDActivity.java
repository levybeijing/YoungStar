package com.chuanqing.youngstar._mine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
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
        String sub1 = null,sub2=null;
        if (phone!=null&&phone.length()==13){
            sub1 = phone.substring(0,2);
            sub2 = phone.substring(7,10);
        }
        tv_phone.setText(sub1+"****"+sub2);

        et_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()==6){
                    tv_ok.setClickable(true);
                    tv_ok.setBackgroundColor(Color.parseColor("#F5575F"));
                    tv_ok.setTextColor(Color.parseColor("#FFFFFF"));
                }else{
                    tv_ok.setClickable(false);
                    tv_ok.setBackgroundColor(Color.parseColor("#CCCCCC"));
                    tv_ok.setTextColor(Color.parseColor("#000000"));
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
                if (StringUtil.isCode(trim)){
                    vericode(trim);
                }else{
                    Toast.makeText(this, "验证码格式不正确", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    private void vericode(String trim) {
        OkGo.post(Urls.checkSms)//
                .tag(this)//
                .params("mobile", phone)
                .params("smsCode", trim)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                                Log.e("=============", "getSms"+s);
                        CommenBean commenBean = new Gson().fromJson(s, CommenBean.class);
                        if (commenBean.getMessage().equals("请求成功")){
                            startActivity(new Intent(EditPWDActivity.this, EditPWD2Activity.class));
                            finish();
                        }else{
                            Toast.makeText(EditPWDActivity.this, ""+commenBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}

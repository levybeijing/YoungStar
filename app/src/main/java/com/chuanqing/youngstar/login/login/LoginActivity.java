package com.chuanqing.youngstar.login.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.chuanqing.youngstar.MainActivity;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.login.bean.LoginBean;
import com.chuanqing.youngstar.login.bean.RegisterBean;
import com.chuanqing.youngstar.login.bean.VeriCodeBean;
import com.chuanqing.youngstar.login.choose.ChooseActivity;
import com.chuanqing.youngstar.login.forget.ForgetPWDActivity;
import com.chuanqing.youngstar.login.privacy.PrivacyProtectActivity;
import com.chuanqing.youngstar.login.protocol.UserProtocolActivity;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.chuanqing.youngstar.tools.StringUtil;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;

import okhttp3.Call;
import okhttp3.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private EditText phone;
    private EditText pwd;
    private EditText pwdagain;
    private EditText code;

    private EditText et_phone;
    private EditText et_pwd;
    private TextView getcode;

    private TimeCount time;
    private RadioButton login;
    private RadioButton register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        time = new TimeCount(60000, 1000);

        initView();

    }

    private void initView() {

        login = findViewById(R.id.rb_login);
        register = findViewById(R.id.rb_register);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
//  登录
        et_phone = findViewById(R.id.et_phone_login);
        et_pwd = findViewById(R.id.et_pwd_login);

        LinearLayout forget = findViewById(R.id.ll_forget_login);
        ImageView tologin = findViewById(R.id.iv_tologin_login);

        forget.setOnClickListener(this);
        tologin.setOnClickListener(this);

        ImageView qq = findViewById(R.id.qq_login);
        ImageView wx = findViewById(R.id.wx_login);
        ImageView wb = findViewById(R.id.wb_login);

        qq.setOnClickListener(this);
        wx.setOnClickListener(this);
        wb.setOnClickListener(this);
//        注册
        phone = findViewById(R.id.et_phone_register);
        pwd = findViewById(R.id.et_pwd_register);
        pwdagain = findViewById(R.id.et_pwdagain_register);
        code = findViewById(R.id.et_code_register);

        ImageView toregister = findViewById(R.id.iv_toregister_register);

        getcode = findViewById(R.id.tv_getcode_register);
        TextView protocol = findViewById(R.id.tv_protocol_register);
        TextView privacy = findViewById(R.id.tv_privacy_register);

        toregister.setOnClickListener(this);
        getcode.setOnClickListener(this);
        protocol.setOnClickListener(this);
        privacy.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rb_login:
                findViewById(R.id.loginlayout).setVisibility(View.VISIBLE);
                findViewById(R.id.regilayout).setVisibility(View.INVISIBLE);
                break;
            case R.id.rb_register:
                findViewById(R.id.loginlayout).setVisibility(View.INVISIBLE);
                findViewById(R.id.regilayout).setVisibility(View.VISIBLE);
                break;
            case R.id.ll_forget_login:
                startActivity(new Intent(LoginActivity.this,ForgetPWDActivity.class));
                break;
            case R.id.iv_tologin_login:
                String trim5 = et_phone.getText().toString().trim();
                String trim6 = et_pwd.getText().toString().trim();
                if (!StringUtil.isPhoneNumber(trim5)){
                    Toast.makeText(this, "手机号不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (trim6==null||trim6.length()==0){
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                tologin(trim5,trim6);
                break;
            case R.id.qq_login:

                break;
            case R.id.wx_login:

                break;
            case R.id.wb_login:

                break;
//                注册
            case R.id.iv_toregister_register:
                String trim1 = pwd.getText().toString().trim();
                String trim2 = pwdagain.getText().toString().trim();
                String trim3 = phone.getText().toString().trim();
                String trim4 = code.getText().toString().trim();
                if (!StringUtil.isPhoneNumber(trim3)){
                    Toast.makeText(this, "手机号不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (trim1==null||trim2==null||trim1.length()==0||trim2.length()==0){
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!trim1.equals(trim2)){
                    Toast.makeText(this, "前后密码不相同,请重新输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                VeriCode(trim3,trim4,trim1);
                break;
            case R.id.tv_getcode_register:
                String trim = phone.getText().toString().trim();
                if (!StringUtil.isPhoneNumber(trim)){
                    Toast.makeText(this, "手机号不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                obtioncode(trim);
                time.start();
                break;
            case R.id.tv_protocol_register:
                startActivity(new Intent(LoginActivity.this,UserProtocolActivity.class));
                break;
            case R.id.tv_privacy_register:
                startActivity(new Intent(LoginActivity.this,PrivacyProtectActivity.class));
                break;

        }
    }
//登录

    private void tologin(final String phone, String pwd) {
        OkGo.post(Urls.logen)//
                .tag(this)//
                .params("mobile",phone)
                .params("password", pwd)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        成功则提示 考虑自动登录
                        Log.e("===============", "tologin: "+s);
                        LoginBean bean = new Gson().fromJson(s, LoginBean.class);
                        if ("请求成功".equals(bean.getMessage())){
//                          设置全局请求头
                            SharedPFUtils.setParam(LoginActivity.this,"token",bean.getData().getToken());
                            HttpHeaders headers = new HttpHeaders();
                            headers.put("token",bean.getData().getToken());
                            OkGo.getInstance().addCommonHeaders(headers);
//                          保存信息
                            SharedPFUtils.setParam(LoginActivity.this,"usercode",bean.getData().getUser_code());
                            SharedPFUtils.setParam(LoginActivity.this,"password",bean.getData().getPassword());
                            SharedPFUtils.setParam(LoginActivity.this,"islogin",true);
//
                            int identity = (int) SharedPFUtils.getParam(LoginActivity.this, "identity", -1);
                            if (identity>0){
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            }else{
                                startActivity(new Intent(LoginActivity.this,ChooseActivity.class));
                            }
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // 注册
    private void VeriCode(final String phone, String code, final String pwd) {
        OkGo.post(Urls.checkSms)//
                .tag(this)//
                .params("mobile",phone)
                .params("smsCode", code)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        成功则提示 考虑自动登录
                        Log.e("===============", "VeriCode: "+s);
                        VeriCodeBean bean = new Gson().fromJson(s, VeriCodeBean.class);
                        if ("请求成功".equals(bean.getMessage())){
                            register(phone,pwd);
                        }
                    }
                });
    }

    private void obtioncode(String phone) {
        OkGo.post(Urls.getSms)//
                .tag(this)//
                .params("mobile", phone)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("===============", "obtioncode: "+s);
                    }
                });
    }

    private void register(final String phone, String code) {
        OkGo.post(Urls.addUser)//
                .tag(this)//
                .params("mobile",phone)
                .params("password", code)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        成功则提示 考虑自动登录
                        Log.e("===============", "register: "+s);
                        RegisterBean registerBean = new Gson().fromJson(s, RegisterBean.class);
                        if ("请求成功".equals(registerBean.getMessage())){
                            Toast.makeText(LoginActivity.this, registerBean.getMessage(), Toast.LENGTH_SHORT).show();
                            findViewById(R.id.loginlayout).setVisibility(View.VISIBLE);
                            findViewById(R.id.regilayout).setVisibility(View.INVISIBLE);
                            et_phone.setText(phone);
                        }

                    }
                });
    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            getcode.setClickable(false);
            getcode.setText(millisUntilFinished / 1000 +"秒后重新发送");
        }

        @Override
        public void onFinish() {
            getcode.setText("重新获取验证码");
            getcode.setClickable(true);
        }
    }
}

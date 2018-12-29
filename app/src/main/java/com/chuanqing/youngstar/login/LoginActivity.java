package com.chuanqing.youngstar.login;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class LoginActivity extends BaseActivity implements LoginContract.IView{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText et_phone = findViewById(R.id.et_phone_login);
        EditText et_pwd = findViewById(R.id.et_pwd_login);

        LinearLayout forget = findViewById(R.id.ll_forget_login);
        ImageView tologin = findViewById(R.id.iv_tologin_login);

        ImageView qq = findViewById(R.id.qq_login);
        ImageView wx = findViewById(R.id.wx_login);
        ImageView wb = findViewById(R.id.wb_login);
    }

    @Override
    public void showRegisterRb() {

    }

    @Override
    public void showLoginRb() {

    }


}

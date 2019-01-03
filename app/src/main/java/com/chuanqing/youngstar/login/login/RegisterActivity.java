package com.chuanqing.youngstar.login.login;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class RegisterActivity  extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

    }

    private void initView() {

        RadioButton login = findViewById(R.id.rb_login_);
        RadioButton register = findViewById(R.id.rb_register_);

        EditText phone = findViewById(R.id.et_phone_register);
        EditText pwd = findViewById(R.id.et_pwd_register);
        EditText pwdagain = findViewById(R.id.et_pwdagain_register);
        EditText code = findViewById(R.id.et_code_register);

        ImageView toregister = findViewById(R.id.iv_toregister_register);

        TextView getcode = findViewById(R.id.tv_getcode_register);
        TextView protocol = findViewById(R.id.tv_protocol_register);
        TextView privacy = findViewById(R.id.tv_privacy_register);
    }
}

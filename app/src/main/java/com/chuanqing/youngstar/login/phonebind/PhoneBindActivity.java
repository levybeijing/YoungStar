package com.chuanqing.youngstar.login.phonebind;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class PhoneBindActivity extends BaseActivity {

    private EditText et_phone;
    private EditText et_code;
    private TextView tv_getcode;
    private CheckBox checked;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebind);

        initView();
    }

    private void initView() {

        et_phone = findViewById(R.id.et_phone_bind);
        et_code = findViewById(R.id.et_code_bind);

        tv_getcode = findViewById(R.id.tv_getcode_bind);
        checked = findViewById(R.id.checked_bind);
        TextView tv_protocol = findViewById(R.id.tv_protocol_bind);
        TextView tv_privacy = findViewById(R.id.tv_privacy_bind);

        TextView tv_login = findViewById(R.id.tv_tologin_bind);
    }
}

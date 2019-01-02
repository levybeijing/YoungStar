package com.chuanqing.youngstar.login.phonebind;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class PhoneBindActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebind);

        initView();
    }

    private void initView() {

        EditText phone = findViewById(R.id.et_phone_bind);
        EditText code = findViewById(R.id.et_code_bind);

        TextView getcode = findViewById(R.id.tv_getcode_bind);
        CheckBox checked = findViewById(R.id.checked_bind);
        TextView protocol = findViewById(R.id.tv_protocol_bind);
        TextView privacy = findViewById(R.id.tv_privacy_bind);

        TextView login = findViewById(R.id.tv_tologin_bind);
    }
}

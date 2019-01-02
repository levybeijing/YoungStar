package com.chuanqing.youngstar.login.authentication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class AuthenActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authen);

        initView();
    }

    private void initView() {
//
        ImageView logo = findViewById(R.id.iv_logo_authen);
//
        View man = findViewById(R.id.iv_man_authen);
        View woman = findViewById(R.id.iv_woman_authen);
//
        EditText name = findViewById(R.id.et_name_authen);
        EditText phone = findViewById(R.id.et_phone_authen);
        EditText school = findViewById(R.id.et_school_authen);
        EditText major = findViewById(R.id.et_major_authen);
        EditText snumber = findViewById(R.id.et_snumber_authen);
        EditText inumber = findViewById(R.id.et_inumber_authen);
        EditText email = findViewById(R.id.et_email_authen);
//
        TextView next = findViewById(R.id.iv_next_authen);
    }
}

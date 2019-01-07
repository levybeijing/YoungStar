package com.chuanqing.youngstar.login._invest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class InvestAuthenActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investauthen);

        initView();
    }

    private void initView() {


        EditText name = findViewById(R.id.et_username_investauthen1);
        EditText phone = findViewById(R.id.et_phone_investauthen1);
        EditText email = findViewById(R.id.et_email_investauthen1);
        EditText intro = findViewById(R.id.et_intro_investauthen1);

        TextView next = findViewById(R.id.tv_next_investauthen1);
    }
}

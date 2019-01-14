package com.chuanqing.youngstar._mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class EditPhoneActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changephone);

        initView();

    }

    private void initView() {

        EditText phone = findViewById(R.id.et_phone_changephone);
        EditText code = findViewById(R.id.et_code_changephone);

        findViewById(R.id.tv_getcode_changephone).setOnClickListener(this);
        TextView tochange = findViewById(R.id.tv_tochange_changephone);
        tochange.setOnClickListener(this);

//        tochange.setBackgroundResource(R.drawable.bg2_tv_changephone);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_getcode_changephone:

                break;
            case R.id.tv_tochange_changephone:

                break;

        }
    }
}

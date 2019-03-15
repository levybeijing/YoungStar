package com.chuanqing.youngstar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chuanqing.youngstar.base.BaseActivity;

public class AuditingActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auditing);

        initView();
    }

    private void initView() {
//        findViewById(R.id.iv_back_auditing).setOnClickListener(this);
        findViewById(R.id.tv_back_auditing).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.iv_back_auditing:
            case R.id.tv_back_auditing:
                finish();
                break;
        }
    }
}

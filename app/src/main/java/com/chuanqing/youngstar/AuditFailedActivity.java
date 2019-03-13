package com.chuanqing.youngstar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.login.choose.ChooseActivity;

public class AuditFailedActivity extends BaseActivity implements View.OnClickListener {

    private String info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auditfailed);
        info = getIntent().getStringExtra("info");

        initView();
    }

    private void initView() {
        TextView tv = findViewById(R.id.tv2_auditfailed);
        tv.setText(info);
        findViewById(R.id.iv_back_auditfailed).setOnClickListener(this);
        findViewById(R.id.tv_again_auditfailed).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_auditfailed:
                finish();
                break;
            case R.id.tv_again_auditfailed:
                startActivity(new Intent(this, ChooseActivity.class));
                finish();
                break;
        }
    }
}

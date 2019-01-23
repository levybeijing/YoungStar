package com.chuanqing.youngstar.login._company;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class ChoseActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companychoose);

        findViewById(R.id.iv_back_comchose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initView();
    }

    private void initView() {
        findViewById(R.id.iv_new_comchose).setOnClickListener(this);
        findViewById(R.id.iv_bind_comchose).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_new_comchose:
                startActivity(new Intent(ChoseActivity.this,Authen1Activity.class));
                break;
            case R.id.iv_bind_comchose:
                startActivity(new Intent(ChoseActivity.this,ComBindActivity.class));
                break;
        }
    }
}

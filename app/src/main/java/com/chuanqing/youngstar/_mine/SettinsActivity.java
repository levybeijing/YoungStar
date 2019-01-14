package com.chuanqing.youngstar._mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class SettinsActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initView();
    }

    private void initView() {
        findViewById(R.id.ll_system_settings).setOnClickListener(this);
        findViewById(R.id.ll_picket_settings).setOnClickListener(this);
        findViewById(R.id.ll_clear_settings).setOnClickListener(this);
        findViewById(R.id.ll_aboutours_settings).setOnClickListener(this);
        findViewById(R.id.ll_publish_settings).setOnClickListener(this);
        findViewById(R.id.ll_help_settings).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_system_settings:

                break;
            case R.id.ll_picket_settings:

                break;
            case R.id.ll_clear_settings:

                break;
            case R.id.ll_aboutours_settings:

                break;
            case R.id.ll_publish_settings:

                break;
            case R.id.ll_help_settings:

                break;
        }
    }
}

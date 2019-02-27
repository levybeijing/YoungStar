package com.chuanqing.youngstar._mine;

import android.content.Intent;
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

        findViewById(R.id.iv_back_settings).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_system_settings:
                startActivity(new Intent(this,SystemSetActivity.class));
                break;
            case R.id.ll_picket_settings:
//                ???
                break;
            case R.id.ll_clear_settings:
//image video 文件夹递归删除

                break;
            case R.id.ll_aboutours_settings:
                startActivity(new Intent(this,CopyRightActivity.class));
                break;
            case R.id.ll_publish_settings:
                startActivity(new Intent(this,ReliefActivity.class));
                break;
            case R.id.ll_help_settings:
                startActivity(new Intent(this,HelpActivity.class));
                break;
            case R.id.iv_back_settings:
                finish();
                break;
        }
    }
}

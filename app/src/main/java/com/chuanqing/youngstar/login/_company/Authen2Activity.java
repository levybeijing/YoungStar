package com.chuanqing.youngstar.login._company;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class Authen2Activity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comauthen2);

        initView();
    }

    private void initView() {
        findViewById(R.id.rl1_comauthen2).setOnClickListener(this);
        findViewById(R.id.rl2_comauthen2).setOnClickListener(this);
        findViewById(R.id.rl3_comauthen2).setOnClickListener(this);
        findViewById(R.id.rl4_comauthen2).setOnClickListener(this);
        findViewById(R.id.tv_ok_comauthen2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl1_comauthen2:

                break;
            case R.id.rl2_comauthen2:

                break;
            case R.id.rl3_comauthen2:

                break;
            case R.id.rl4_comauthen2:

                break;
            case R.id.tv_ok_comauthen2:

                break;

        }
    }
}

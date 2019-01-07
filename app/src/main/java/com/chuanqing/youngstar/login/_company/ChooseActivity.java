package com.chuanqing.youngstar.login._company;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class ChooseActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companychoose);

        initView();
    }

    private void initView() {
        findViewById(R.id.iv_new_company).setOnClickListener(this);
        findViewById(R.id.iv_bind_company).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_new_company:

                break;
            case R.id.iv_bind_company:

                break;
        }
    }
}

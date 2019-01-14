package com.chuanqing.youngstar._mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class EditUserDataActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdata);

        initView();
    }

    private void initView() {
        findViewById(R.id.tv_exit_editdata).setOnClickListener(this);
        findViewById(R.id.ll_set_editdata).setOnClickListener(this);
        findViewById(R.id.ll_changepwd_editdata).setOnClickListener(this);
        findViewById(R.id.ll_changeveri_editdata).setOnClickListener(this);
        findViewById(R.id.ll_phone_editdata).setOnClickListener(this);
        findViewById(R.id.ll_email_editdata).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_exit_editdata:

                break;
            case R.id.ll_set_editdata:

                break;
            case R.id.ll_changepwd_editdata:

                break;
            case R.id.ll_changeveri_editdata:

                break;
            case R.id.ll_phone_editdata:

                break;
            case R.id.ll_email_editdata:

                break;

        }
    }
}

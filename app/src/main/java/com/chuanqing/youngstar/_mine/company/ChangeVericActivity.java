package com.chuanqing.youngstar._mine.company;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;


public class ChangeVericActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_company;
    private EditText et_phone;
    private EditText et_intro;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeveric);

        initView();
    }

    private void initView() {
        findViewById(R.id.iv1_changeveric).setOnClickListener(this);
        findViewById(R.id.iv2_changeveric).setOnClickListener(this);
        findViewById(R.id.iv3_changeveric).setOnClickListener(this);
        findViewById(R.id.iv4_changeveric).setOnClickListener(this);
        findViewById(R.id.tv_ok_changeveric).setOnClickListener(this);


        et_name = findViewById(R.id.et_name_changeveric);
        et_company = findViewById(R.id.et_company_changeveric);
        et_phone = findViewById(R.id.et_phone_changeveric);
        et_intro = findViewById(R.id.et_intro_changeveric);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv1_changeveric:

                break;
            case R.id.iv2_changeveric:

                break;
            case R.id.iv3_changeveric:

                break;
            case R.id.iv4_changeveric:

                break;
            case R.id.tv_ok_changeveric:

                break;

        }
    }
}

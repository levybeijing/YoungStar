package com.chuanqing.youngstar.login.identitychoose;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class ChooseActivity  extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        initView();
    }

    private void initView() {
//        ImageView student = findViewById(R.id.iv_student_choose);
//        ImageView invest = findViewById(R.id.iv_invest_choose);
//        ImageView company = findViewById(R.id.iv_company_choose);
//        ImageView visitor = findViewById(R.id.iv_visitor_choose);
        findViewById(R.id.iv_visitor_choose).setOnClickListener(this);
        findViewById(R.id.iv_invest_choose).setOnClickListener(this);
        findViewById(R.id.iv_company_choose).setOnClickListener(this);
        findViewById(R.id.iv_visitor_choose).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_student_choose:

                break;
            case R.id.iv_invest_choose:

                break;
            case R.id.iv_company_choose:

                break;
            case R.id.iv_visitor_choose:

                break;
        }
    }
}

package com.chuanqing.youngstar.login.choose;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chuanqing.youngstar.MainActivity;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.login._company.ChoseActivity;
import com.chuanqing.youngstar.login._invest.InvestAuthenActivity;
import com.chuanqing.youngstar.login._student.AuthenActivity;
import com.chuanqing.youngstar.login.login.LoginActivity;
import com.chuanqing.youngstar.tools.SharedPFUtils;

public class ChooseActivity  extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        initView();
    }

    private void initView() {
        findViewById(R.id.iv_student_choose).setOnClickListener(this);
        findViewById(R.id.iv_company_choose).setOnClickListener(this);
        findViewById(R.id.iv_invest_choose).setOnClickListener(this);
        findViewById(R.id.iv_visitor_choose).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_student_choose:
                startActivity(new Intent(ChooseActivity.this,AuthenActivity.class));
                break;
            case R.id.iv_invest_choose:
                startActivity(new Intent(ChooseActivity.this,InvestAuthenActivity.class));
                break;
            case R.id.iv_company_choose:
                startActivity(new Intent(ChooseActivity.this,ChoseActivity.class));
                break;
            case R.id.iv_visitor_choose:
                startActivity(new Intent(ChooseActivity.this,MainActivity.class));
                SharedPFUtils.setParam(ChooseActivity.this,"identity",4);
                finish();
                break;
        }
    }
}

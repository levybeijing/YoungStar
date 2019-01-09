package com.chuanqing.youngstar.login._invest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class InvestAuthen2Activity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investauthen2);

        initView();
    }

    private void initView() {
        findViewById(R.id.tv_ok_investauthen2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                next
            }
        });

        findViewById(R.id.rl1_investauthen2).setOnClickListener(this);
        findViewById(R.id.rl2_investauthen2).setOnClickListener(this);
        findViewById(R.id.rl3_investauthen2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_ok_investauthen2:

                break;
            case R.id.rl1_investauthen2:

                break;
            case R.id.rl2_investauthen2:

                break;
            case R.id.rl3_investauthen2:

                break;

        }
    }
}

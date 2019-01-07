package com.chuanqing.youngstar.login._invest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class InvestAuthen2Activity extends BaseActivity {

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

        RelativeLayout img1 = findViewById(R.id.rl1_investauthen2);
        RelativeLayout img2 = findViewById(R.id.rl2_investauthen2);
        RelativeLayout img3= findViewById(R.id.rl3_investauthen2);
    }
}

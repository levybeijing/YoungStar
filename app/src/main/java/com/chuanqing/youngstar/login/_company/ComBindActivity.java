package com.chuanqing.youngstar.login._company;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class ComBindActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combind);

        initView();
    }

    private void initView() {
        findViewById(R.id.tv_ok_combind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        EditText name = findViewById(R.id.et_name_combind);
        EditText phone = findViewById(R.id.et_phone_combind);
        EditText id = findViewById(R.id.et_comId_combind);
    }

}

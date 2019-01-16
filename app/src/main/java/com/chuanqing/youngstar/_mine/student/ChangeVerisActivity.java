package com.chuanqing.youngstar._mine.student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class ChangeVerisActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_phone;
    private EditText et_school;
    private EditText et_major;
    private EditText et_snum;
    private EditText et_inum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeveris);

        initView();
    }

    private void initView() {
        findViewById(R.id.iv1_changeveris).setOnClickListener(this);
        findViewById(R.id.iv2_changeveris).setOnClickListener(this);
        findViewById(R.id.iv3_changeveris).setOnClickListener(this);
        findViewById(R.id.tv_ok_changeveris).setOnClickListener(this);

        et_name = findViewById(R.id.et_name_changeveris);
        et_phone = findViewById(R.id.et_phone_changeveris);
        et_school = findViewById(R.id.et_school_changeveris);
        et_major = findViewById(R.id.et_major_changeveris);
        et_snum = findViewById(R.id.et_snum_changeveris);
        et_inum = findViewById(R.id.et_inum_changeveris);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv1_changeveris:

                break;
            case R.id.iv2_changeveris:

                break;
            case R.id.iv3_changeveris:

                break;
            case R.id.tv_ok_changeveris:

                break;

        }
    }
}

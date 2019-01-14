package com.chuanqing.youngstar._mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class EditPWDActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editpwd);
        initView();
    }

    private void initView() {
        findViewById(R.id.tv_getcode_editpwd).setOnClickListener(this);
        findViewById(R.id.tv_tochange_editpwd).setOnClickListener(this);

        EditText et_code = findViewById(R.id.et_code_editpwd);

        TextView tv_phone = findViewById(R.id.tv_current_editpwd);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_getcode_editpwd:

                break;
            case R.id.tv_tochange_editpwd:

                break;

        }
    }
}

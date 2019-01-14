package com.chuanqing.youngstar._mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class EditPWD2Activity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editpwd2);

        initView();
    }

    private void initView() {
        findViewById(R.id.tv_ok_editpwd2).setOnClickListener(this);

        EditText et_old = findViewById(R.id.et_oldpwd_editpwd2);
        EditText et_new = findViewById(R.id.et_newpwd_editpwd2);
        EditText et_again = findViewById(R.id.et_pwdagain_editpwd2);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_ok_editpwd2:

                break;

        }
    }
}

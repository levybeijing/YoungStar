package com.chuanqing.youngstar._mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class EditEmailActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editemail);

        initView();
    }

    private void initView() {
        findViewById(R.id.tv_ok_editemail).setOnClickListener(this);

        TextView tv_old = findViewById(R.id.tv_oldemail_editemail);
        EditText et_new = findViewById(R.id.et_new_editemail);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_ok_editemail:

                break;
        }
    }
}

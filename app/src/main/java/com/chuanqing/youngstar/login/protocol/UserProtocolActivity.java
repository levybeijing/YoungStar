package com.chuanqing.youngstar.login.protocol;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class UserProtocolActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protect);

        TextView tv_protocol = findViewById(R.id.tv_protocol);
    }
}
